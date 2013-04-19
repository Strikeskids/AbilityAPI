package sk.action;

import sk.action.magic.Rune;
import sk.action.magic.Spell;
import sk.item.AvailableGroup;
import sk.item.ItemCache;

public class BarSpell extends BarDraggable {

	public BarSpell(Spell d, int slot) {
		super(d, slot);
	}

	@Override
	public Spell getDraggable() {
		return (Spell) super.getDraggable();
	}

	@Override
	public boolean canUse() {
		return getDraggable().getSpellbook().isOpen() && super.canUse();
	}

	private int[] counts;

	@Override
	public boolean start() {
		synchronized (this) {
			Rune[] runes = getDraggable().getRunes();
			counts = new int[runes.length];
			AvailableGroup g = ItemCache.getItems();
			for (int i = 0; i < runes.length; i++) {
				counts[i] = g.getCount(runes[i].getType().getItemId());
				if (counts[i] < runes[i].getAmount())
					return false;
			}
			return true;
		}
	}

	@Override
	public boolean isDone() {
		Rune[] runes = getDraggable().getRunes();
		AvailableGroup g = ItemCache.getItems();
		for (int i = 0; i < runes.length; i++) {
			if (counts[i] > g.getCount(runes[i].getType().getItemId()))
				return true;
		}
		return false;
	}

}
