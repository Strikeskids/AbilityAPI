package sk.map.ultimate;

import org.powerbot.game.api.wrappers.Tile;

public class StaticPathPart implements PathPart {

	private final Tile tile;

	public StaticPathPart(Tile me) {
		this.tile = me;
	}

	@Override
	public boolean validate() {
		return false;
	}

	@Override
	public void traverse() {

	}

	@Override
	public boolean ready() {
		return false;
	}

	@Override
	public Tile getDestination() {
		return tile;
	}

	@Override
	public String toString() {
		return "Static tile: " + tile;
	}

}
