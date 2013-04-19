package sk.map.ultimate.teleport;

import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;

import sk.general.EntityUtil;
import sk.item.RequiredGroup;
import sk.map.ultimate.Network;
import sk.requirement.Requirement;

public class Shipping implements Network {

	private final Tile loc;
	private final int npc;
	private final Route route;
	private Shipping[] dests;

	public Shipping(Route r, int ind) {
		this.loc = r.getLocs()[ind];
		this.npc = r.getNpcs()[ind];
		this.route = r;
	}

	@SuppressWarnings("unused")
	private void setDests(Shipping[] d) {
		this.dests = d;
	}

	@Override
	public Tile getLocation() {
		return loc;
	}

	@Override
	public RequiredGroup getItems() {
		return new RequiredGroup();
	}

	@Override
	public boolean use() {
		NPC n = getNpc();
		if (n != null)
			return false;
		return EntityUtil.interact(true, n, "Pay-fare", "Talk");// TODO finish
	}

	@Override
	public Requirement getRequirements() {
		return Requirement.DONE;
	}

	@Override
	public boolean canUse() {
		return getNpc() != null;
	}

	public NPC getNpc() {
		return NPCs.getNearest(npc);
	}

	@Override
	public int getWeight() {
		return 20;
	}

	@Override
	public Network[] getDestinations() {
		return dests;
	}

	@Override
	public boolean isSameNetwork(Teleport t) {
		return t != null && t instanceof Shipping && ((Shipping) t).route == this.route;
	}

	public static enum Route {
		;

		public int[] getNpcs() {
			return null;
		}

		public Tile[] getLocs() {
			return null;
		}

	}

	public static void load() {
		// TODO implement
	}

	@SuppressWarnings("unused")
	private static final int SHIPPING_WIDGET = -1; // TODO get widget id

	@Override
	public boolean isFreeToPlay() {
		// TODO Auto-generated method stub
		return false;
	}

}
