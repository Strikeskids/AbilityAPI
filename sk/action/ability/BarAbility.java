package sk.action.ability;

import sk.action.BarDraggable;

public class BarAbility extends BarDraggable {

	public BarAbility(Ability d, int slot) {
		super(d, slot);
	}

	@Override
	public Ability getDraggable() {
		return (Ability) super.getDraggable();
	}

	@Override
	public boolean use() {
		if (!super.use())
			return false;
		return waitForCombo();
	}

	@Override
	public boolean spam() {
		if (!super.spam())
			return false;
		return waitForCombo();
	}

	public boolean waitForCombo() {
		Ability a = getDraggable();
		if (a.getComboLength() <= 0)
			return true;
		try {
			Thread.sleep(a.getComboLength() + 500);
		} catch (InterruptedException ex) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isDone() {
		return false;
	}

	@Override
	public String toString() {
		return "Ability: " + getDraggable();
	}

}
