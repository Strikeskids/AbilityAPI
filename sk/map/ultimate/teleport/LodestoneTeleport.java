package sk.map.ultimate.teleport;

import org.powerbot.game.api.wrappers.Tile;

import sk.item.RequiredGroup;
import sk.requirement.Requirement;
import sk.widget.Lodestone;
import sk.widget.Lodestone.Location;

public class LodestoneTeleport implements Teleport {

	private Location loc;

	public LodestoneTeleport(Location loc) {
		this.loc = loc;
	}

	@Override
	public Tile getLocation() {
		return loc.getEnd();
	}

	@Override
	public RequiredGroup getItems() {
		return RequiredGroup.EMPTY;
	}

	@Override
	public boolean use() {
		return Lodestone.travel(loc);
	}

	@Override
	public Requirement getRequirements() {
		return new Requirement() {
			@Override
			public boolean meets() {
				return Lodestone.canUse(loc);
			}
		};
	}

	@Override
	public boolean canUse() {
		return true;
	}

	@Override
	public int getWeight() {
		return 30;
	}

	@Override
	public String toString() {
		return "Lodestone: " + loc;
	}

	public static void load() {
		for (Location loc : Location.values()) {
			TeleportUtil.add(new LodestoneTeleport(loc));
		}
	}

	@Override
	public boolean isFreeToPlay() {
		return !loc.isMembers();
	}

}
