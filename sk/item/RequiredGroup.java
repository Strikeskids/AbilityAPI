package sk.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class RequiredGroup {

	private ArrayList<DefinedItem> items = new ArrayList<DefinedItem>();

	public RequiredGroup() {

	}

	public RequiredGroup(DefinedItem... items) {
		add(items);
	}

	public RequiredGroup(Collection<DefinedItem> items) {
		add(items);
	}

	public RequiredGroup(RequiredGroup... groups) {
		add(groups);
	}

	public RequiredGroup add(DefinedItem item) {
		if (item != null)
			items.add(item);
		return this;
	}

	public RequiredGroup add(DefinedItem... items) {
		if (items != null)
			for (DefinedItem d : items) {
				add(d);
			}
		return this;
	}

	public RequiredGroup add(Collection<DefinedItem> items) {
		if (items != null)
			for (DefinedItem d : items) {
				add(d);
			}
		return this;
	}

	public RequiredGroup add(RequiredGroup... groups) {
		if (groups != null)
			for (RequiredGroup group : groups) {
				if (group != null)
					items.addAll(group.getItems());
			}
		return this;
	}

	public List<DefinedItem> getItems() {
		return items;
	}

	public boolean inside(Map<Integer, Integer> items, Map<Integer, Integer> used) {
		for (final DefinedItem item : getItems()) {
			if (!item.getItem().take(item.getAmount(), item.shouldNote(), items, used)) {
				return false;
			}
		}
		return true;
	}

	public static final RequiredGroup EMPTY = new RequiredGroup() {
		@Override
		public RequiredGroup add(DefinedItem d) {
			return this;
		}
	};

	@Override
	public String toString() {
		return "Group: " + items.toString();
	}

}
