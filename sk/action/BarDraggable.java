package sk.action;

import sk.action.ActionBar.SlotData;

public class BarDraggable extends BarNode {

	private final Draggable drag;

	public BarDraggable(Draggable d, int slot) {
		super(slot);
		if (d == null)
			throw new NullPointerException();
		this.drag = d;
	}

	public Draggable getDraggable() {
		return drag;
	}

	@Override
	public boolean isValid() {
		return drag.getId() == SlotData.getDraggableId(getSlot()) && SlotData.validateSlot(getSlot());
	}

	@Override
	public boolean canUse() {
		return isValid() && !SlotData.getCooldownChild(getSlot()).visible() && SlotData.checkTextColor(getSlot());
	}

	@Override
	public String toString() {
		return "Drag: " + getDraggable();
	}

}
