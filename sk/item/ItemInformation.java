package sk.item;

import java.util.HashMap;
import java.util.Map;

import org.powerbot.game.api.methods.tab.Equipment;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.node.Item;

import sk.general.TimedCondition;

public class ItemInformation {
	private int itemId;
	private int notedId;
	private boolean stackable;

	public ItemInformation(final int id) {
		this(id, -1);
	}

	public ItemInformation(final int id, final int note) {
		this(false, id, note);
	}

	public ItemInformation(final boolean stackable, final int id) {
		this(stackable, id, -1);
	}

	protected ItemInformation(final boolean stackable, final int id, final int note) {
		this.itemId = id;
		this.notedId = note;
		this.stackable = stackable;
		putInfo(this);
	}

	public boolean canNote() {
		return notedId > 0;
	}

	public boolean isStackable() {
		return stackable && !canNote();
	}

	public int getNotedId() {
		return notedId;
	}

	public int getItemId() {
		return itemId;
	}

	public final Item findItem() {
		return findItem(false);
	}

	public Item findItem(boolean noted) {
		return findItem(noted, false);
	}

	public Item findItem(boolean noted, boolean equip) {
		if (noted && canNote()) {
			return (equip ? Equipment.getItem(itemId, notedId) : Inventory.getItem(itemId, notedId));
		} else {
			return (equip ? Equipment.getItem(itemId) : Inventory.getItem(itemId));
		}
	}

	public Item findItem(final int stack) {
		return Inventory.getItem(new Filter<Item>() {
			@Override
			public boolean accept(Item i) {
				return (i.getId() == itemId || canNote() && i.getId() == notedId) && i.getStackSize() >= stack;
			}
		});
	}

	public final boolean withdraw(int num) {
		return withdraw(false, num);
	}

	public boolean withdraw(boolean n, final int num) {
		final boolean noted = n && canNote();
		int startNum = getCount(noted);
		int dif = num - startNum;
		if (dif <= 0)
			return true;
		if (!Bank.isOpen())
			return false;
		if (!Bank.setWithdrawNoted(noted))
			return false;
		int bcount = getCount(Bank.getItems());
		return bcount >= dif && Bank.withdraw((noted ? notedId : itemId), (bcount > dif ? dif : 0))
				&& Bank.setWithdrawNoted(false) && new TimedCondition(1000) {
					@Override
					public boolean isDone() {
						return getCount(noted) >= num;
					}
				}.waitStop();
	}

	public final int getCount() {
		return getCount(false);
	}

	public final int getCount(boolean noted) {
		return getCount(noted, Inventory.getItems());
	}

	public final int getCount(Item... items) {
		return getCount(false, items);
	}

	public int getCount(boolean noted, Item... items) {
		noted &= canNote();
		int ret = 0;
		for (Item i : items) {
			if (i == null)
				continue;
			if (i.getId() == itemId || noted && i.getId() == notedId)
				ret += i.getStackSize();
		}
		return ret;
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof ItemInformation)) {
			return false;
		}
		final ItemInformation i = (ItemInformation) o;
		return i.getItemId() == this.getItemId();
	}

	@Override
	public int hashCode() {
		return getItemId();
	}

	public boolean take(int amnt, final boolean note, final Map<Integer, Integer> items,
			final Map<Integer, Integer> used) {
		amnt *= getWeight(itemId);
		if (note && canNote()) {
			int noteId = getNotedId();
			int amntToUse = Math.min(amnt, get(items, noteId) - get(used, noteId));
			amnt -= amntToUse;
			update(used, noteId, amntToUse);
		}
		int itemId = getItemId();
		int amntToUse = Math.min(amnt, get(items, itemId) - get(used, itemId));
		amnt -= amntToUse;
		update(used, itemId, amntToUse);
		return amnt <= 0;
	}

	protected void update(Map<Integer, Integer> map, int key, int change) {
		int value = get(map, key) + change;
		if (value <= 0)
			map.remove(key);
		else
			map.put(key, value);
	}

	protected int get(Map<Integer, Integer> map, int key) {
		Integer ret = map.get(key);
		if (ret == null)
			return 0;
		return ret;
	}

	private static final Map<Integer, ItemInformation> info = new HashMap<Integer, ItemInformation>(100);

	public static void putInfo(int id, final ItemInformation i) {
		info.put(id, i);
	}

	public static void putInfo(final ItemInformation i) {
		final ItemInformation cur = info.get(i.getItemId());
		if (cur == null || i.canNote() && !cur.canNote()) {
			putInfo(i.getItemId(), i);
			if (i.canNote())
				putInfo(i.getNotedId(), i);
		}
	}

	public static ItemInformation get(final int id) {
		return info.get(id);
	}

	public static int getWeight(final int id) {
		ItemInformation i = get(id);
		if (i != null && i instanceof MultiItemInformation) {
			return ((MultiItemInformation) i).weightForId(id);
		}
		return 1;
	}

	public static int getUnnoteId(final int id) {
		ItemInformation i = get(id);
		if (i != null) {
			if (i instanceof MultiItemInformation) {
				i = ((MultiItemInformation) i).getItem(id);
				if (i == null)
					return id;
			}
			return i.getItemId();
		}
		return id;
	}

	@Override
	public String toString() {
		if (canNote()) {
			return "Item: " + itemId + " " + notedId;
		} else {
			return "Item: " + itemId + " " + stackable;
		}
	}
}
