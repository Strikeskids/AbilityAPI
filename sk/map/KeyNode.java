package sk.map;

public class KeyNode extends Node {

	private final int g;
	private final int f;

	public KeyNode(final int x, final int y, final OffsetPoint dest) {
		this(null, x, y, dest);
	}

	public KeyNode(final Node p, final int x, final int y, final OffsetPoint dest) {
		super(p, x, y);
		if (p != null) {
			this.g = getPrev().getG() + MapUtil.dist(this, getPrev());
		} else {
			this.g = 0;
		}
		this.f = MapUtil.dist(this, dest) + this.g;
	}

	public KeyNode(final Node n, final OffsetPoint dest) {
		this(n.getPrev(), n.getX(), n.getY(), dest);
	}

	@Override
	public int getG() {
		return g;
	}

	public int getF() {
		return f;
	}

}
