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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean available() {
		// TODO Auto-generated method stub
		return false;
	}

	public Tab getTab() {
		return MainTabs.PRAYER;
	}

	@Override
	public WidgetChild getChild() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Completion getChange() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAbilityId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
