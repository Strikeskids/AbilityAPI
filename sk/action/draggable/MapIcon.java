package sk.action.draggable;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.Draggable;
import sk.general.WidgetUtil;

/**
 * Icons near the map in the top left part of the screen.
 * 
 * @author Strikeskids
 * 
 */
public enum MapIcon implements Draggable {
	HEALTH(748, 7, 8, 28), PRAYER(749, 2, 6, 21399), RUN(750, 2, 6, 24), SUMMONING(747, 18, 23, 29);

	private final int widget, dragChild, textChild, ability;

	private MapIcon(int w, int d, int t, int a) {
		this.widget = w;
		this.dragChild = d;
		this.textChild = t;
		this.ability = a;
	}

	/**
	 * Gets the current value of this MapIcon
	 * 
	 * @return the value as an integer of this MapIcon
	 */
	public int getValue() {
		try {
			return Integer.parseInt(Widgets.get(widget, textChild).getText());
		} catch (Exception ex) {
			return -1;
		}
	}

	@Override
	public int getId() {
		return ability;
	}

	@Override
	public boolean show() {
		return isVisible();
	}

	@Override
	public boolean isVisible() {
		return WidgetUtil.visible(getChild());
	}

	@Override
	public WidgetChild getChild() {
		return Widgets.get(widget, dragChild);
	}

}
