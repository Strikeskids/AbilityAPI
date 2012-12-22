package sk.action.ability;

import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.Ability;
import sk.general.Completion;
import sk.tab.MainTabs;
import sk.tab.Tab;

public enum Prayer implements Ability {
	;

	@Override
	public boolean show() {
		return false;
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	@Override
	public boolean available() {
		return false;
	}

	public Tab getTab() {
		return MainTabs.PRAYER;
	}

	@Override
	public WidgetChild getChild() {
		return null;
	}

	@Override
	public Completion getChange() {
		return null;
	}

	@Override
	public int getAbilityId() {
		return 0;
	}

}
