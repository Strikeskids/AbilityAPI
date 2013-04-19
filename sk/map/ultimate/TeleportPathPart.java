package sk.map.ultimate;

import org.powerbot.game.api.wrappers.Tile;

import sk.item.ItemCache;
import sk.map.ultimate.teleport.Teleport;
import sk.map.ultimate.teleport.TeleportUtil;

public class TeleportPathPart implements PathPart {

	private Teleport tele;

	public TeleportPathPart(Teleport t) {
		this.tele = t;
	}

	@Override
	public boolean validate() {
		return tele.getRequirements().meets() && ItemCache.getItems().contains(tele.getItems());
	}

	@Override
	public void traverse() {
		tele.use();
	}

	@Override
	public boolean ready() {
		return tele.canUse() && !TeleportUtil.atEnd(tele);
	}

	public Teleport getTeleport() {
		return tele;
	}

	@Override
	public Tile getDestination() {
		return tele.getLocation();
	}

	@Override
	public String toString() {
		return "TeleportPathPart: " + tele;
	}

}
