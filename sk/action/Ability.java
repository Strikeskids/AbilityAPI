package sk.action;

import java.util.HashMap;

import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.general.Completion;

public interface Ability {
	public abstract boolean show();
	public abstract boolean isVisible();
	public abstract boolean available();
	public abstract WidgetChild getChild();
	public abstract Completion getChange();
	public abstract int getAbilityId();

	public static HashMap<Integer, Ability> ALL_ABILITIES = new HashMap<Integer, Ability>(2000);
	
}
