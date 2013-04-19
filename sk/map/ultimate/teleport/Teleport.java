package sk.map.ultimate.teleport;

import org.powerbot.game.api.wrappers.Tile;

import sk.item.RequiredGroup;
import sk.requirement.Requirement;

public interface Teleport {

	public abstract Tile getLocation();

	public abstract RequiredGroup getItems();

	public abstract boolean use();

	public abstract Requirement getRequirements();

	public abstract boolean canUse();

	public abstract int getWeight();

	public abstract boolean isFreeToPlay();
}
