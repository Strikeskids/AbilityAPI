package sk.map;

import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.Tile.Flag;

import sk.Universal;

public class MapDataFlags extends Obstacles {

	private MapData map;

	private int plane = (Universal.testing ? 0 : -1);

	public MapDataFlags(MapData md, int p) {
		this.plane = p;
		this.map = md;
	}

	public MapDataFlags(MapData md) {
		this.map = md;
	}

	public MapDataFlags() {
		this(MapData.get());
	}

	@Override
	public boolean blocked(Node n) {
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
		final int x = n.getX();
		final int y = n.getY();
		final int dx = n.getX() - b.getX();
		final int dy = n.getY() - b.getY();

		int flag = map.get(toTile(n));

		if (dx == 0) {
			if (dy == 0) {
				return false;
			}
			int f = Y_WALL[(dy + 1) / 2];
			return (flag & f) != f;
		} else if (dy == 0) {
			int f = X_WALL[(dx + 1) / 2];
			return (flag & f) != f;
		} else {
			// Both directions
			final int diagNum = (dy + 1) | ((dx + 1) / 2);
			if ((flag & DIAG_WALL[diagNum]) == 0) {
				return true;
			}
			if ((!walkable(n.derive(-dx, 0)) || (map.get(toTile(x - dx, y)) & DIAG_X[diagNum]) == 0)
					&& (!walkable(n.derive(0, -dy)) || (map.get(toTile(x, y - dy)) & DIAG_Y[diagNum]) == 0)) {
				return true;
			}
		}
		return false;
	}

	private Tile toTile(OffsetPoint p) {
		return toTile(p.getX(), p.getY());
	}

	private Tile toTile(int x, int y) {
		return new Tile(x, y, (plane == -1 ? Game.getPlane() : plane));
	}

	private static final int[] X_WALL = { Flag.WALL_EAST, Flag.WALL_WEST }, Y_WALL = { Flag.WALL_NORTH,
			Flag.WALL_SOUTH }, DIAG_WALL = { Flag.WALL_NORTHEAST, Flag.WALL_NORTHWEST, Flag.WALL_SOUTHEAST,
			Flag.WALL_SOUTHWEST }, DIAG_X = { Flag.WALL_WEST | Flag.WALL_NORTH, Flag.WALL_EAST | Flag.WALL_NORTH,
			Flag.WALL_WEST | Flag.WALL_SOUTH, Flag.WALL_EAST | Flag.WALL_SOUTH }, DIAG_Y = {
			Flag.WALL_EAST | Flag.WALL_SOUTH, Flag.WALL_WEST | Flag.WALL_SOUTH, Flag.WALL_EAST | Flag.WALL_NORTH,
			Flag.WALL_WEST | Flag.WALL_NORTH };

	@Override
	public boolean walkable(OffsetPoint n) {
		return (map.get(toTile(n))) != 0;
	}

	public boolean walkable(Tile t) {
		return map.get(t) != 0;
	}

	public MapData getMapData() {
		return map;
	}

	public void setPlane(int plane) {
		this.plane = plane;
	}

}
