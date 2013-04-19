package sk.map.ultimate;

import java.util.EnumSet;

import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.Entity;
import org.powerbot.game.api.wrappers.Locatable;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.Path;

import sk.general.EntityUtil;
import sk.map.MapUtil;

public class BankPath extends Path {

	private UltimatePath curBank = null;

	@Override
	public Tile getEnd() {
		if (curBank != null)
			return curBank.getEnd();
		else
			return null;
	}

	@Override
	public Tile getNext() {
		return null;
	}

	@Override
	public Tile getStart() {
		return null;
	}

	@Override
	public boolean traverse(EnumSet<TraversalOption> options) {
		if (!init())
			return false;
		Entity near = Bank.getNearest();
		if (near != null && curBank == null)
			return EntityUtil.bringOnScreen(true, near);
		else
			return init() && curBank.traverse(options);
	}

	@Override
	public boolean init() {
		Entity near = Bank.getNearest();
		Tile reach = EntityUtil.getReachable((Locatable) near);
		int dist = Integer.MAX_VALUE;
		if (near != null && reach != null && (dist = MapUtil.dist(Players.getLocal().getLocation(), reach)) < 30) {
			curBank = null;
			return true;
		} else {
			if (curBank != null && curBank.init())
				return true;
			int minCost = dist;
			for (final BankLocations bloc : BankLocations.values()) {
				final UltimatePath cur = new UltimatePath(bloc.getLocation());
				if (cur.init() && (curBank == null || minCost > cur.getCost())) {
					curBank = cur;
					minCost = cur.getCost();
					if (minCost < 30)
						break;
				}
			}
			return near != null || (curBank != null && curBank.init());
		}
	}

	@Override
	public boolean validate() {
		Entity near = Bank.getNearest();
		return (near == null || !EntityUtil.isOnScreen(near)) && init();
	}

}
