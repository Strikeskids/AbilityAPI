package sk.map;

public class Node extends OffsetPoint {

	private final Node prev;

	public Node(final int x, final int y) {
		this(null, x, y);
	}

	public Node(final Node prev, final int x, final int y) {
		super(x, y);
		this.prev = prev;
	}

	public Node(final Node prev, final OffsetPoint loc) {
		this(prev, loc.getX(), loc.getY());
	}

	public Node(final OffsetPoint loc) {
		this(null, loc);
	}

	public int getG() {
		if (this.prev == null) {
			return 0;
		}
		return this.prev.getG() + MapUtil.dist(this, this.prev);
	}

	public Node getPrev() {
		return prev;
	}

	@Override
	public Node derive(final int dx, final int dy) {
		return new Node(this, getX() + dx, getY() + dy);
	}

	public Node derive() {
		if (prev == null) {
			return null;
		}
		return derive(getDx(), getDy());
	}

	public int getDx() {
		if (prev == null) {
			return 0;
		}
		return getX() - prev.getX();
	}

	public int getDy() {
		if (prev == null) {
			return 0;
		}
		return getY() - prev.getY();
	}
}
