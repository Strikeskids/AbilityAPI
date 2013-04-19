package sk.item;

import java.util.Collection;
import java.util.HashMap;

public class ItemGroup {

	private int size;
	private final HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();

	public ItemGroup() {

	}

	public ItemGroup(final DefinedItem... i) {
		add(i);
	}

	public ItemGroup(final ItemGroup... i) {
		add(i);
	}

	public static ItemGroup createFromCharacter() {
		// TODO create from character
		return null;
	}

	public int getSize() {
		return size;
	}

	// Modification methods

	private void addTo(final int id, final int val) {
		Integer pre = counts.get(id);
		if (pre == null) {
			pre = 0;
		}
		counts.put(id, pre + val);
	}

	private int removeFrom(final int id, final int val) {
		Integer pre = counts.get(id);
		if (pre == null) {
			return val;
		}
		pre -= val;
		final int ret = -pre;
		if (pre <= 0) {
			counts.remove(id);
		} else {
			counts.put(id, pre);
		}
		return Math.min(0, ret);
	}

	public void add(final DefinedItem i) {
		if (i != null) {
			addTo(i.getItemId() | (i.shouldNote() ? NOTE_MASK : 0), i.getAmount());
			size += i.getSize();
		}
	}

	public void add(final DefinedItem... in) {
		if (in == null) {
			return;
		}
		for (final DefinedItem i : in) {
			add(i);
		}
	}

	public void add(final Collection<DefinedItem> in) {
		if (in == null) {
			return;
		}
		for (final DefinedItem i : in) {
			add(i);
		}
	}

	public void add(final ItemGroup... groups) {
		if (groups == null) {
			return;
		}
		for (final ItemGroup i : groups) {
			if (i != null) {
				for (final int id : i.counts.keySet()) {
					addTo(id, i.counts.get(id));
				}
			}
		}
	}

	public void remove(final DefinedItem i) {
		if (i != null) {
			int amnt = i.getAmount();
			if (i.shouldNote()) {
				amnt = removeFrom(i.getItemId() | NOTE_MASK, amnt);
				if (counts.get(i.getItemId()) == null) {
					size--;
				}
			}
			size -= amnt - removeFrom(i.getItemId(), amnt);
		}
	}

	public void remove(final DefinedItem... in) {
		if (in == null) {
			return;
		}
		for (final DefinedItem i : in) {
			remove(i);
		}
	}

	public void remove(final Collection<DefinedItem> in) {
		if (in == null) {
			return;
		}
		for (final DefinedItem i : in) {
			remove(i);
		}
	}

	public void remove(final ItemGroup... groups) {
		if (groups == null) {
			return;
		}
		for (final ItemGroup i : groups) {
			if (i != null) {
				for (final int id : i.counts.keySet()) {
					int amnt = i.counts.get(id);
					if ((id & NOTE_MASK) == NOTE_MASK) {
						amnt = removeFrom(id, amnt);
						if (counts.get(id) == null) {
							size--;
						}
					}
					size -= amnt - removeFrom(id & ~NOTE_MASK, amnt);
				}
			}
		}
	}

	public HashMap<Integer, Integer> getCounts() {
		return counts;
	}

	public boolean contains(final DefinedItem i) {
		final HashMap<Integer, Integer> counts = getCounts();
		Integer sum = counts.get(i.getItemId());
		if (sum == null) {
			sum = 0;
		}
		if (i.shouldNote()) {
			final Integer noted = counts.get(i.getItemId() | NOTE_MASK);
			if (noted != null) {
				sum += noted;
			}
		}
		return sum >= i.getAmount();
	}

	public boolean inside(final ItemGroup g) {
		final HashMap<Integer, Integer> in = getCounts(), out = g.getCounts();
		return contains(out, in);
	}

	protected boolean contains(final HashMap<Integer, Integer> out, final HashMap<Integer, Integer> in) {
		for (final int i : in.keySet()) {
			Integer val1 = 0;
			int sum = in.get(i);
			if ((i & NOTE_MASK) == NOTE_MASK) {
				final int ci = i & ~NOTE_MASK;
				final Integer part2 = in.get(ci);
				if (part2 != null) {
					sum += part2;
				}
				val1 = out.get(ci);
				if (val1 == null) {
					val1 = 0;
				}
			}
			final Integer val = out.get(i);
			if (val == null || val + val1 < sum) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemGroup clone() {
		final ItemGroup ret = new ItemGroup();
		// ret.items.addAll(this.items);
		for (final int id : this.counts.keySet()) {
			ret.counts.put(id, this.counts.get(id));
		}
		return ret;
	}

	protected static final int NOTE_MASK = 0x1000000;

}
