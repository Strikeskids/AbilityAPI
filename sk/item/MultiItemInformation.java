package sk.item;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.node.Item;

public class MultiItemInformation extends ItemInformation {

	private Map<Integer, ItemInformation> items;
	private Map<Integer, Integer> weights;
	private int[] sortedIds;

	protected MultiItemInformation(boolean stackable, int[] ids, int[] notes, int[] weights) {
		super(stackable, ids[0], notes[0]);
		int len = Math.min(ids.length, Math.min(notes.length, weights.length));
		if (len == 0)
			throw new IllegalArgumentException("Must have some items");
		this.items = new HashMap<>(len * 9 / 6);
		this.weights = new HashMap<>(len * 9 / 6);
		Integer[] tmp = new Integer[len];
		for (int i = 0; i < len; i++) {
			ItemInformation cur = new ItemInformation(stackable, ids[i], notes[i]);
			this.items.put(ids[i], cur);
			tmp[i] = ids[i];
			if (weights[i] <= 0)
				weights[i] = 1;
			this.weights.put(ids[i], weights[i]);
			ItemInformation.putInfo(ids[i], this);
			if (this.canNote())
				ItemInformation.putInfo(notes[i], this);
		}
		Arrays.sort(tmp, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return MultiItemInformation.this.weights.get(o1) - MultiItemInformation.this.weights.get(o2);
			}
		});
		sortedIds = new int[len];
		for (int i = 0; i < len; i++) {
			sortedIds[i] = tmp[i];
		}
	}

	public MultiItemInformation(int[] ids) {
		this(ids, createArr(ids.length, 1));
	}

	public MultiItemInformation(int[] ids, int[] weights) {
		this(false, ids, weights);
	}

	public MultiItemInformation(int[] ids, int[] notes, int[] weights) {
		this(false, ids, notes, weights);
	}

	public MultiItemInformation(boolean stackable, int[] ids, int[] weights) {
		this(stackable, ids, createArr(ids.length, -1), weights);
	}

	private static int[] createArr(int len, int val) {
		int[] ret = new int[len];
		Arrays.fill(ret, val);
		return ret;
	}

	public Collection<ItemInformation> getItems() {
		return this.items.values();
	}

	@Override
	public Item findItem(boolean noted, boolean equip) {
		for (ItemInformation it : items.values()) {
			Item ret = it.findItem(noted, equip);
			if (ret != null)
				return ret;
		}
		return null;
	}

	@Override
	public Item findItem(int stack) {
		for (final Entry<Integer, ItemInformation> info : items.entrySet()) {
			Item ret = info.getValue().findItem((int) Math.ceil(stack * 1d / weights.get(info.getKey())));
			if (ret != null)
				return ret;
		}
		return null;
	}

	private int[] getCounts(boolean noted, Item... items) {
		int[] ret = new int[sortedIds.length];
		for (int i = 0; i < sortedIds.length; i++) {
			ret[i] = this.items.get(sortedIds[i]).getCount(noted, items);
		}
		return ret;
	}

	private int sum(int[] counts) {
		int ret = 0;
		for (int i = 0; i < counts.length; i++) {
			ret += counts[i] * this.weights.get(sortedIds[i]);
		}
		return ret;
	}

	@Override
	public boolean withdraw(boolean n, int num) {
		final boolean noted = n && canNote();
		final int startCount = getCount(noted);
		int dif = num - startCount;
		if (dif <= 0)
			return true;
		if (!Bank.isOpen())
			return false;

		int[] counts = getCounts(noted, Bank.getItems());
		int bcount = sum(counts);
		if (bcount < dif)
			return false;

		int ccount = 0;
		int[] toWithdraw = new int[counts.length];
		int[] weights = new int[counts.length];
		for (int i = 0; i < sortedIds.length; i++) {
			weights[i] = this.weights.get(sortedIds[i]);
		}
		outer: while (ccount < dif) {
			for (int i = 0; i < counts.length; i++) {
				if (counts[i] <= 0)
					continue;
				if (ccount + weights[i] >= dif) {
					toWithdraw[i]++;
					counts[i]--;
					ccount += weights[i];
					break outer;
				}
			}
			for (int i = counts.length - 1; i >= 0; i--) {
				if (counts[i] <= 0)
					continue;
				ccount += weights[i];
				counts[i]--;
				toWithdraw[i]++;
			}
		}
		for (int i = 0; i < toWithdraw.length; i++) {
			if (toWithdraw[i] <= 0)
				continue;
			this.items.get(sortedIds[i]).withdraw(noted,
					toWithdraw[i] + this.items.get(sortedIds[i]).getCount(noted));
		}
		return getCount(noted) >= num;
	}

	@Override
	public int getCount(boolean noted, Item... items) {
		int ret = 0;
		for (final Entry<Integer, ItemInformation> info : this.items.entrySet()) {
			ret += info.getValue().getCount(noted, items) * this.weights.get(info.getKey());
		}
		return ret;
	}

	@Override
	public boolean take(int amnt, final boolean note, final Map<Integer, Integer> items,
			final Map<Integer, Integer> used) {
		if (note && canNote()) {
			for (int id : sortedIds) {
				ItemInformation info = this.items.get(id);
				int amntToUse = Math.min(amnt, get(items, info.getNotedId()) - get(used, info.getNotedId()));
				amnt -= amntToUse;
				update(used, info.getNotedId(), amntToUse);
				if (amnt <= 0)
					break;
			}
		}
		for (int id : sortedIds) {
			ItemInformation info = this.items.get(id);
			int dif = get(items, info.getItemId()) - get(used, info.getItemId());
			int amntToUse = Math.min(amnt, dif);
			amnt -= amntToUse;
			update(used, info.getItemId(), amntToUse);
			if (amnt <= 0)
				break;
		}
		return amnt <= 0;
	}

	public int weightForId(int id) {
		Integer ret = weights.get(id);
		if (ret == null)
			return 1;
		return ret;
	}

	public ItemInformation getItem(int id) {
		return items.get(id);
	}

}
