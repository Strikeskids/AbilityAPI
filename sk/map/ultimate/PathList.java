package sk.map.ultimate;

import java.util.ArrayList;
import java.util.Collection;

import org.powerbot.game.api.wrappers.Tile;

import sk.map.MapUtil;

public class PathList extends ArrayList<PathPart> implements Comparable<PathList> {

	private int cost = 0;
	private final Tile dest;

	public PathList(PathPart part, Tile d) {
		this(d);
		add(part);
	}

	public PathList(Tile d) {
		super();
		this.dest = d;
	}

	public PathList(Tile d, Collection<? extends PathPart> c) {
		super(c);
		this.dest = d;
	}

	public PathList(PathList pi) {
		this(pi.getDestination(), pi);
	}

	private static final long serialVersionUID = -5843448569093186472L;

	@Override
	public boolean add(PathPart p) {
		if (p instanceof TilePathPart) {
			cost += ((TilePathPart) p).getSize();
		} else if (p instanceof TeleportPathPart) {
			cost += ((TeleportPathPart) p).getTeleport().getWeight();
		}
		return super.add(p);
	}

	@Override
	public void add(int index, PathPart p) {
		if (p instanceof TilePathPart) {
			cost += ((TilePathPart) p).getSize();
		} else if (p instanceof TeleportPathPart) {
			cost += ((TeleportPathPart) p).getTeleport().getWeight();
		}
		super.add(index, p);
	}

	@Override
	public PathPart remove(int index) {
		return null;
	}

	@Override
	public boolean remove(Object p) {
		return false;
	}

	public int getCost() {
		Tile stop = getStop();
		if (stop == null)
			return 1_000_000;
		return cost + MapUtil.dist(dest, stop);
	}

	public Tile getStop() {
		if (size() == 0)
			return null;
		return get(size() - 1).getDestination();
	}

	public Tile getDestination() {
		return dest;
	}

	@Override
	public int compareTo(PathList o) {
		int dif = this.getCost() - o.getCost();
		if (dif == 0)
			return this.hashCode() - o.hashCode();
		else
			return dif;
	}
}
