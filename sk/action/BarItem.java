package sk.action;

import sk.action.ActionBar.SlotData;

public class BarItem extends BarNode {

	private final int itemId;

	public BarItem(int itemId, int slot) {
		super(slot);
		this.itemId = itemId;
	}

	/**
	 * Gets the item id of this barItem
	 * 
	 * @return
	 */
	public int getItemId() {
		return itemId;
	}

	@Override
	public boolean isValid() {
		return SlotData.getItemId(getSlot()) == itemId && SlotData.validateSlot(getSlot());
	}

	@Override
	public boolean canUse() {
		return isValid();
	}

	@Override
	public String toString() {
		return "Item: " + getItemId();
	}

}
