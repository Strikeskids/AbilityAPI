package sk.action;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.ActionBar.SlotData;
import sk.general.EntityUtil;
import sk.general.TimedCondition;
import sk.general.WidgetUtil;

/**
 * Holds the information about a slot on the Action Bar
 * 
 * @author Strikeskids
 * 
 */
public abstract class BarNode {

	private final int slot;

	public BarNode(int slot) {
		this.slot = slot;
	}

	/**
	 * Gets the slot of this node
	 * 
	 * @return the slot number
	 */
	public int getSlot() {
		return slot;
	}

	/**
	 * Attempts to send the key or click the button on the Action Bar
	 * 
	 * @return <tt>true</tt> if the slot was typed or clicked. <tt>false</tt> otherwise
	 */
	public boolean send() {
		if (!isValid())
			return false;
		if (ActionBar.sendKey(getSlot()))
			return true;
		WidgetChild main = SlotData.getMainChild(getSlot());
		return WidgetUtil.visible(main) && EntityUtil.interact(false, main);
	}

	/**
	 * Attempts to use the slot once
	 * 
	 * @return <tt>true</tt> if the slot was used. <tt>false</tt>otherwise
	 * @see spam()
	 * @see send()
	 */
	public boolean use() {
		synchronized (this) {
			return canUse() && start() && send() && new TimedCondition(700) {
				@Override
				public boolean isDone() {
					return !canUse() || BarNode.this.isDone();
				}
			}.waitStop();
		}
	}

	/**
	 * Attempts to spam the slot until it is used
	 * 
	 * @return <tt>true</tt> if the slot was used. <tt>false</tt> otherwise
	 * @see use()
	 * @see send()
	 */
	public boolean spam() {
		synchronized (this) {
			if (!canUse() || !start())
				return false;
			final TimedCondition tc = new TimedCondition(1000) {
				@Override
				public boolean isDone() {
					return !canUse() || BarNode.this.isDone();
				}
			};
			do {
				send();
				Task.sleep(300, 400);
			} while (tc.isRunning());
			return tc.isDone();
		}
	}

	/**
	 * Attempts to interact with this slot
	 * 
	 * @param action
	 *            the actions to use
	 * @return <tt> true</tt> if the mouse interacted with this slot. <tt>false</tt> otherwise
	 */
	public boolean interact(String... action) {
		return canUse() && EntityUtil.interactChild(SlotData.getMainChild(slot), action);
	}

	/**
	 * Checks to see if this slot can be used
	 * 
	 * @return <tt>true</tt> if the slot can be used. <tt>false</tt> otherwise
	 */
	public abstract boolean canUse();

	/**
	 * Checks to see if this slot is still valid
	 * 
	 * @return <tt>true</tt> if the slot is still valid, i.e., the slot still has the same item.
	 *         <tt>false</tt> otherwise
	 */
	public abstract boolean isValid();

	/**
	 * Attempt to start using the slot. (No actual actions done) This method should be synchronized on the
	 * object
	 * 
	 * @return <tt>true</tt> if the slot can be started. <tt>false</tt> otherwise
	 * @see isDone()
	 */
	public boolean start() {
		return true;
	}

	/**
	 * Checks to see if this slot has been used after it has been last started
	 * 
	 * @return <tt>true</tt> if it was used. <tt>false</tt> otherwise
	 * @see start()
	 */
	public boolean isDone() {
		return true;
	}
}
