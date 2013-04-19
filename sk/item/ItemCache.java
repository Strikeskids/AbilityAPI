package sk.item;

import org.powerbot.core.script.job.LoopTask;
import org.powerbot.game.api.methods.tab.Equipment;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.DepositBox;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.Item;

import sk.Script;
import sk.general.PriceUtil;
import sk.tab.MainTabs;

public class ItemCache {

	private AvailableGroup icache, ecache, gained = new AvailableGroup();
	private static final int UPDATE_DELAY = 30000;
	private Timer iupdate = new Timer(UPDATE_DELAY), eupdate = new Timer(UPDATE_DELAY);
	private boolean inPause = false;
	private boolean paused = false;
	private long profit = 0;

	private ItemCache() {
	}

	public long getProfit() {
		return profit;
	}

	public AvailableGroup getThisItems() {
		if (Script.holdExecution())
			return new AvailableGroup();
		AvailableGroup ret = new AvailableGroup(icache, ecache);
		if (icache == null)
			ret.add(Inventory.getItems(true));
		if (ecache == null) {
			if (Bank.isOpen())
				ret.add(Equipment.getItems());
			else
				ret.add(Equipment.getCachedItems());
		}
		return ret;
	}

	public static AvailableGroup getItems() {
		return get().getThisItems();
	}

	public void update() {
		if (Script.holdExecution()) {
			icache = null;
			ecache = null;
			inPause = true;
			return;
		}
		AvailableGroup invent = AvailableGroup.NOTHING, equip = AvailableGroup.NOTHING;
		MainTabs start = MainTabs.getCurrent();
		if (paused || Bank.isOpen() || DepositBox.isOpen()) {
			if (!inPause) {
				inPause = true;
				if (Bank.isOpen()) {
					equip = new AvailableGroup(Equipment.getItems());
					invent = new AvailableGroup(Inventory.getItems());
				} else if (DepositBox.isOpen()) {
					equip = new AvailableGroup(Equipment.getCachedItems());
					invent = new AvailableGroup(Inventory.getItems());
				} else {
					MainTabs.EQUIPMENT.open(true);
					equip = new AvailableGroup(Equipment.getCachedItems());
					MainTabs.INVENTORY.open(true);
					invent = new AvailableGroup(Inventory.getItems(true));
				}
			} else {
				icache = null;
				ecache = null;
				return;
			}
		} else {
			inPause = false;
			if (icache == null) {
				MainTabs.INVENTORY.open(true);
				icache = new AvailableGroup(Inventory.getItems(true));
				invent = icache;
				iupdate.reset();
			} else {
				if (!iupdate.isRunning()) {
					MainTabs.INVENTORY.open(true);
					iupdate.reset();
				}
				invent = new AvailableGroup(Inventory.getItems(true));
			}
			if (ecache == null) {
				MainTabs.EQUIPMENT.open(true);
				ecache = new AvailableGroup(Equipment.getCachedItems());
				equip = ecache;
				eupdate.reset();
			} else {
				if (!eupdate.isRunning()) {
					MainTabs.EQUIPMENT.open(true);
					eupdate.reset();
				}
				equip = new AvailableGroup(Equipment.getCachedItems());
			}
		}
		start.open(true);
		gained.add(invent.getDifference(icache));
		gained.add(equip.getDifference(ecache));
		for (Item i : gained.createItems()) {
			if (i == null)
				continue;
			long price = PriceUtil.getPrice(ItemInformation.getUnnoteId(i.getId()));
			if (price >= 0) {
				gained.remove(i);
				profit += price * i.getStackSize();
			}
		}
		icache = invent;
		ecache = equip;
	}

	public void hold(boolean pause) {
		this.paused = pause;
	}

	private static ItemCache cur;
	private static LoopTask task;

	public static ItemCache get() {
		return (cur == null ? cur = new ItemCache() : cur);
	}

	public static LoopTask getTask() {
		return task != null ? task : (task = new LoopTask() {
			@Override
			public int loop() {
				try {
					ItemCache.get().update();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return UPDATE_INTERVAL;
			}
		});
	}

	private static final int UPDATE_INTERVAL = 500;
}
