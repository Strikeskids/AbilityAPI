package sk.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MultiGroup extends RequiredGroup {

	private List<RequiredGroup> groups = new ArrayList<>();

	public MultiGroup() {
	}

	public MultiGroup(DefinedItem... items) {
		super(items);
	}

	public MultiGroup(Collection<DefinedItem> items) {
		super(items);
	}

	public MultiGroup(RequiredGroup... groups) {
		super(groups);
	}

	@Override
	public RequiredGroup add(RequiredGroup... groups) {
		Collections.addAll(this.groups, groups);
		return this;
	}

	public List<RequiredGroup> getGroups() {
		return groups;
	}

	private void copyInto(Map<Integer, Integer> source, Map<Integer, Integer> dest) {
		dest.clear();
		for (Entry<Integer, Integer> ent : source.entrySet()) {
			dest.put(ent.getKey(), ent.getValue());
		}
	}

	public boolean inside(final Map<Integer, Integer> items, final Map<Integer, Integer> used) {
		if (!super.inside(items, used))
			return false;
		Map<Integer, Integer> start = new HashMap<>(used);
		for (RequiredGroup g : groups) {
			if (g.inside(items, used)) {
				return true;
			}
			copyInto(start, used);
		}
		return false;
	}

	@Override
	public String toString() {
		return super.toString() + " " + groups.size();
	}
}
