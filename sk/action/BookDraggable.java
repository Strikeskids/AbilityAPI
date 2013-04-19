package sk.action;

import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.tab.Tab;

public interface BookDraggable extends Draggable {
	public Tab getTab();

	public int getChildId();

	public int getChildTexture();

	public WidgetChild getCooldownChild();

	public int getLevel();

	public int getSkill();

	public static final int BOOK_WIDGET = 275, MAIN_CHILDREN = 16, COOLDOWN_CHILDREN = 17;
	public static final int SCROLLBAR = 20, CLICK_WINDOW = 14;
}
