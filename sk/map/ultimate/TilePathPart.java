package sk.map.ultimate;

import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.Path;

import sk.map.FastPath;
import sk.map.MapUtil;

public class TilePathPart implements PathPart {

	private Path path;
	private Tile start;
	private int weight;

	public TilePathPart(Tile start, Path p) {
		this.path = p;
		this.start = start;
		if (p instanceof FastPath) {
			Tile prev = start;
			Tile[] dests = ((FastPath) p).getDestinations();
			for (int i = 0; i < dests.length; i++) {
				weight += MapUtil.dist(prev, dests[i]);
				prev = dests[i];
			}
		} else {
			weight = MapUtil.dist(start, p.getEnd());
		}
	}

	@Override
	public boolean validate() {
		return path.init();
	}

	@Override
	public boolean ready() {
		return path.validate();
	}

	@Override
	public void traverse() {
		path.traverse();
	}

	public int getSize() {
		return weight;
	}

	@Override
	public Tile getDestination() {
		return path.getEnd();
	}

	@Override
	public String toString() {
		return "TilePathPart: " + start + " -> " + getDestination();
	}
}
