package sk.map;

public abstract class Obstacles {
	public abstract boolean blocked(Node n);

	public abstract boolean walkable(OffsetPoint n);

	public boolean blocked(final OffsetPoint s, final OffsetPoint e) {
		return blocked(new Node(new Node(s), e));
	}
}
