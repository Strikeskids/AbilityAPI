package sk.action.ability;

import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.Ability;
import sk.general.Completion;

/**
 * Icons near the map in the top left part of the screen.
 * 
 * @author Strikeskids
 * 
 */
public enum MapIcon implements Ability {
	HEALTH(748, 7, 8, 28), PRAYER(749, 2, 6, 21399) {
		private final int PRAYER_SETTING = 1769;

		@Override
		public Completion getChange() {
			final int curSet = Settings.get(PRAYER_SETTING);
			return new Completion() {

				@Override
				public boolean isDone() {
					return Settings.get(PRAYER_SETTING) != curSet;
				}
			};
		}
	},
	RUN(750, 2, 6, 24) {
		private final int RUN_SETTING = 463;

		@Override
		public Completion getChange() {
			final int curSet = Settings.get(RUN_SETTING);
			return new Completion() {

				@Override
				public boolean isDone() {
					return Settings.get(RUN_SETTING) != curSet;
				}
			};
		}
	},
	SUMMONING(747, 18, 23, 29) {
		@Override
		public boolean available() {
			//TODO improve
			return true;
		}
	};

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
		return getValue() > 0;
	}

	@Override
	public WidgetChild getChild() {
		return Widgets.get(widget, dragChild);
	}

	@Override
	public Completion getChange() {
		return null;
	}

	@Override
	public int getAbilityId() {
		return ability;
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

}
