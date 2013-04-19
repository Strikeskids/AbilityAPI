package sk.map;

import org.powerbot.game.api.wrappers.Tile;

public class OffsetPoint {
	private short x, y;

	public OffsetPoint(final int x, final int y) {
		this.x = (short) x;
		this.y = (short) y;
	}

	public OffsetPoint(final OffsetPoint o) {
		this.x = o.x;
		this.y = o.y;
	}
	
	public OffsetPoint(final Tile tile) {
		this(tile.getX(), tile.getY());
	}

	public Tile get(final Tile loc) {
		return new Tile(loc.getX() + x, loc.getY() + y, loc.getPlane());
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void shift(final int dx, final int dy) {
		x += dx;
		y += dy;
	}

	public OffsetPoint derive(final int dx, final int dy) {
		return new OffsetPoint(x + dx, y + dy);
	}

	public boolean equals(final OffsetPoint o) {
		return o.x == this.x && o.y == this.y;
	}

	@Override
	public boolean equals(final Object o) {
		if (o instanceof OffsetPoint) {
			return equals((OffsetPoint) o);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return x << 16 | y;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
