package sk.map.ultimate.teleport;

import java.util.Collection;
import java.util.HashSet;

import org.powerbot.game.api.methods.interactive.Players;

import sk.general.EntityUtil;
import sk.item.ItemCache;
import sk.map.MapUtil;
import sk.map.ultimate.Network;

public class TeleportUtil {

	public static boolean atEnd(Teleport t) {
		return MapUtil.dist(Players.getLocal().getLocation(), t.getLocation()) < 20
				&& EntityUtil.canReach(t.getLocation());
	}

	public static boolean canTeleport(Teleport t) {
		return ItemCache.getItems().contains(t.getItems()) && t.getRequirements().meets()
				&& t.canUse();
	}

	public static Collection<Teleport> getTeleports() {
		return teleports;
	}

	public static Collection<Collection<Network>> getNetworks() {
		return networks;
	}

	public static void add(Teleport t) {
		teleports.add(t);
	}

	public static void add(Collection<Network> net) {
		networks.add(net);
	}

	private static HashSet<Collection<Network>> networks = new HashSet<>();
	private static HashSet<Teleport> teleports = new HashSet<>();

	public static void load() {
		// TODO finish
		if (teleports.size() == 0) {
			ItemTeleport.load();
			Shipping.load();
			LodestoneTeleport.load();
			SpellTeleport.load();
		}
	}

}
