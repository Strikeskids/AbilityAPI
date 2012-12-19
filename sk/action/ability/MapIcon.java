package sk.action.ability;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.Ability;
import sk.general.Completion;

public enum MapIcon implements Ability {
	HEALTH(748, 7, 8, 28), PRAYER(749, 2, 6, 21399), RUN(750, 2, 6, 24), SUMMONING(0, 0, 0, 0);

	private final int widget, dragChild, textChild, ability;

	private MapIcon(int w, int d, int t, int a) {
		this.widget = w;
		this.dragChild = d;
		this.textChild = t;
		this.ability = a;
		if (this.ability > 0)
			Ability.ALL_ABILITIES.put(this.ability, this);
	}

	@Override
	public boolean show() {
		return isVisible();
	}

	@Override
	public boolean isVisible() {
		WidgetChild wc = getChild();
		return wc != null && wc.visible();
	}

	@Override
	public boolean available() {
		//TODO add better thing
		return true;
	}

	@Override
	public WidgetChild getChild() {
		return Widgets.get(widget, dragChild);
	}

	@Override
	public Completion getChange() {
		//TODO add better change
		return Completion.TRUE;
	}

	@Override
	public int getAbilityId() {
		return ability;
	}

	public int getValue() {
		try {
			return Integer.parseInt(Widgets.get(widget, textChild).getText());
		} catch (Exception ex) {
			return -1;
		}
	}

}
