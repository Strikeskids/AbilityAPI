package sk.map;

import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.Tile.Flag;

public class CollisionFlags extends Obstacles {

	protected final Tile base;
	protected final Tile offset;
	protected final int[][] flags;

	public CollisionFlags() {
		this(Game.getMapBase());
	}

	public CollisionFlags(final Tile base) {
		this(base, Walking.getCollisionOffset(base.getPlane()), Walking.getCollisionFlags(base.getPlane()));
	}

	public CollisionFlags(final Tile base, final Tile offset, final int[][] flags) {
		this.base = base;
		this.offset = offset;
		this.flags = flags;
	}

	public boolean walkable(final Tile t) {
		return walkable(toPoint(t));
	}

	public boolean blocked(final Tile p, final Tile t) {
		return blocked(new Node(new Node(toPoint(p)), toPoint(t)));
	}

	public Tile getOffset() {
		return offset;
	}

	public int[][] getFlags() {
		return flags;
	}

	public Tile getBase() {
		return base;
	}

	public int getFlag(final OffsetPoint n) {
		final int x = n.getX() - offset.getX();
		final int y = n.getY() - offset.getY();
		if (x >= 0 && y >= 0 && x < flags.length && y < flags[x].length) {
			return flags[x][y];
		} else {
			return -1;
		}
	}

	public boolean checkPoint(final OffsetPoint n) {
		return checkPoint(n, 0);
	}

	public boolean checkPoint(final OffsetPoint n, int extra) {
		int pad = PADDING + extra;
		final int x = n.getX() - offset.getX();
		final int y = n.getY() - offset.getY();
		return x >= pad && y >= pad && x < flags.length - pad && y < flags[x].length - pad;
	}

	@Override
	public boolean blocked(final Node n) {
		if (n == null || !walkable(n)) {
			return true;
		}
		if (n.getPrev() == null) {
			return false;
		}
		if (!walkable(n.getPrev())) {
			return true;
		}
		final Node p = n.getPrev();
		return checkBlocked(n, p) || checkBlocked(p, n);
	}

	private boolean checkBlocked(final OffsetPoint n, final OffsetPoint b) {
		final int x = n.getX() - offset.getX();
		final int y = n.getY() - offset.getY();
		final int dx = n.getX() - b.getX();
		final int dy = n.getY() - b.getY();

		if (dx == 0) {
			if (dy == 0) {
				return false;
			}
			// Y direction only
			return (flags[x][y] & Y_WALL[(dy + 1) / 2]) != 0;
		} else if (dy == 0)
		// X direction only
		{
			return (flags[x][y] & X_WALL[(dx + 1) / 2]) != 0;
		} else {
			// Both directions
			final int diagNum = (dy + 1) | ((dx + 1) / 2);
			if ((flags[x][y] & DIAG_WALL[diagNum]) != 0) {
				return true;
			}
			if ((!walkable(n.derive(-dx, 0)) || (flags[x - dx][y] & DIAG_X[diagNum]) != 0)
					&& (!walkable(n.derive(0, -dy)) || (flags[x][y - dy] & DIAG_Y[diagNum]) != 0)) {
				return true;
			}
		}
		return false;
	}

	private static final int[] X_WALL = { Flag.WALL_EAST, Flag.WALL_WEST }, Y_WALL = { Flag.WALL_NORTH,
			Flag.WALL_SOUTH }, DIAG_WALL = { Flag.WALL_NORTHEAST, Flag.WALL_NORTHWEST, Flag.WALL_SOUTHEAST,
			Flag.WALL_SOUTHWEST }, DIAG_X = { Flag.WALL_WEST | Flag.WALL_NORTH, Flag.WALL_EAST | Flag.WALL_NORTH,
			Flag.WALL_WEST | Flag.WALL_SOUTH, Flag.WALL_EAST | Flag.WALL_SOUTH }, DIAG_Y = {
			Flag.WALL_EAST | Flag.WALL_SOUTH, Flag.WALL_WEST | Flag.WALL_SOUTH, Flag.WALL_EAST | Flag.WALL_NORTH,
			Flag.WALL_WEST | Flag.WALL_NORTH };

	@Override
	public boolean walkable(final OffsetPoint n) {
		if (n == null) {
			return false;
		}
		final int x = n.getX() - offset.getX();
		final int y = n.getY() - offset.getY();
		return x >= PADDING && y >= PADDING && x < flags.length - PADDING && y < flags[x].length - PADDING
				&& (flags[x][y] & BLOCKED) == 0;
	}

	public OffsetPoint pullUntilWalkable(OffsetPoint n, final OffsetPoint me) {
		if (n == null || me == null) {
			return null;
		}
		int x = n.getX() - offset.getX();
		int y = n.getY() - offset.getY();
		while (x < PADDING) {
			x++;
		}
		while (x >= flags.length - PADDING) {
			x--;
		}
		while (y < PADDING) {
			y++;
		}
		while (y >= flags[x].length - PADDING) {
			y--;
		}
		n = new OffsetPoint(x + offset.getX(), y + offset.getY());
		while (!walkable(n) && !n.equals(me)) {
			int dx = 0;
			if (n.getX() > me.getX()) {
				dx = -1;
			} else if (n.getX() < me.getX()) {
				dx = 1;
			}
			int dy = 0;
			if (n.getY() > me.getY()) {
				dy = -1;
			} else if (n.getY() < me.getY()) {
				dy = 1;
			}
			n.shift(dx, dy);
		}
		return n;
	}

	public OffsetPoint toPoint(final Tile t) {
		if (t == null || base == null) {
			return null;
		}
		return new OffsetPoint(t.getX() - base.getX(), t.getY() - base.getY());
	}

	public Tile toTile(final OffsetPoint o) {
		if (o == null || base == null) {
			return null;
		}
		return new Tile(base.getX() + o.getX(), base.getY() + o.getY(), base.getPlane());
	}

	private static final int PADDING = 5, BLOCKED = 0x260000;

}
