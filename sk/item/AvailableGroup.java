package sk.item;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.powerbot.game.api.wrappers.node.Item;

public class AvailableGroup {

	public static final AvailableGroup NOTHING = new AvailableGroup();;
	private final Map<Integer, Integer> items;

	public AvailableGroup() {
		items = new HashMap<Integer, Integer>();
	}

	public AvailableGroup(Item... items) {
		this();
		add(items);
	}

	public AvailableGroup(Collection<Item> items) {
		this();
		add(items);
	}

	public AvailableGroup(AvailableGroup... groups) {
		this();
		add(groups);
	}

	private AvailableGroup(Map<Integer, Integer> items) {
		this.items = items;
	}

	public void clear() {
		items.clear();
	}

	private void update(Map<Integer, Integer> map, int key, int change) {
		int value = get(map, key) + change;
		if (value <= 0)
			map.remove(key);
		else
			map.put(key, value);
	}

	private int get(Map<Integer, Integer> map, int key) {
		Integer ret = map.get(key);
		if (ret == null)
			return 0;
		return ret;
	}

	public void remove(Item item) {
		if (item != null) {
			update(items, item.getId(), -item.getStackSize() * ItemInformation.getWeight(item.getId()));
		}
	}

	public void add(Item item) {
		if (item != null)
			update(items, item.getId(), item.getStackSize() * ItemInformation.getWeight(item.getId()));
	}

	public void add(Item... items) {
		if (items != null)
			for (Item d : items) {
				add(d);
			}
	}

	public void add(Collection<Item> items) {
		if (items != null)
			for (Item d : items) {
				add(d);
			}
	}

	public void add(AvailableGroup group) {
		if (group == null)
			return;
		for (Entry<Integer, Integer> its : group.items.entrySet()) {
			update(items, its.getKey(), its.getValue());
		}
	}

	public void add(AvailableGroup... groups) {
		if (groups == null)
			return;
		for (AvailableGroup g : groups) {
			add(g);
		}
	}

	public AvailableGroup getDifference(AvailableGroup group) {
		if (group == null)
			group = new AvailableGroup();
		Map<Integer, Integer> ret = new HashMap<>(items);
		for (Entry<Integer, Integer> ent : group.items.entrySet()) {
			int amnt = get(ret, ent.getKey());
			if (amnt < ent.getValue())
				continue;
			update(ret, ent.getKey(), -ent.getValue());
		}
		Map<Integer, Integer> tmp = new HashMap<>(group.items);
		for (Entry<Integer, Integer> ent : this.items.entrySet()) {
			int amnt = get(tmp, ent.getKey());
			if (amnt < ent.getValue())
				continue;
			update(tmp, ent.getKey(), -ent.getValue());
		}
		for (Entry<Integer, Integer> ent : tmp.entrySet()) {
			update(ret, ent.getKey(), -ent.getValue());
		}
		return new AvailableGroup(ret);
	}

	public AvailableGroup getOutside(AvailableGroup used) {
		Map<Integer, Integer> ret = new HashMap<>(items);
		for (Entry<Integer, Integer> ent : used.items.entrySet()) {
			int amnt = get(ret, ent.getKey());
			if (amnt < ent.getValue())
				return null;
			update(ret, ent.getKey(), -ent.getValue());
		}
		return new AvailableGroup(ret);
	}

	public boolean contains(final RequiredGroup... groups) {
		if (groups == null)
			return true;
		Map<Integer, Integer> used = new HashMap<>();
		for (final RequiredGroup group : groups) {
			if (group != null && !group.inside(items, used))
				return false;
		}
		return true;
	}

	public Item[] createItems() {
		Item[] ret = new Item[items.size()];
		int loc = 0;
		for (Entry<Integer, Integer> ent : items.entrySet()) {
			int val = ent.getValue();
			int weight = ItemInformation.getWeight(ent.getKey());
			if (val % weight != 0)
				val += weight;
			ret[loc++] = new Item(ent.getKey(), val / weight);
		}
		return ret;
	}

	public int size() {
		return items.size();
	}

	public Map<Integer, Integer> getItems() {
		return items;
	}

	public int getCount(int itemId) {
		if (!items.containsKey(itemId))
			return 0;
		int val = items.get(itemId);
		int weight = ItemInformation.getWeight(itemId);
		if (val % weight != 0)
			val += weight;
		return val / weight;
	}
}
