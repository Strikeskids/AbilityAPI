package sk.tab;

import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.general.TimedCondition;

/**
 * An enum specifying the inner tabs in the ability book
 * 
 * @author Strikeskids
 * 
 */
public enum InnerAbilityTabs implements Tab {
	ATTACK_TAB(AbilityTabs.MELEE_TAB, 29, 1), STRENGTH_TAB(AbilityTabs.MELEE_TAB, 31, 2),

	RANGE_TAB(AbilityTabs.RANGE_TAB, 2),

	MAGIC_ABILITY_TAB(AbilityTabs.MAGIC_TAB, 42) {
		@Override
		public boolean isOpen() {
			WidgetChild wc, wci;
			return super.isOpen() && (wc = Widgets.get(BOOK_WIDGET, ABILITY_LIST)) != null
					&& (wci = wc.getChild(1)) != null && wci.getTextureId() > -1;
		}
	},
	COMBAT_SPELL_TAB(43, 1), TELEPORT_TAB(46, 2), OTHER_SPELL_TAB(48, 3),

	DEFENCE_TAB(AbilityTabs.OTHER_TAB, 59, 5), CONSTITUTION_TAB(AbilityTabs.OTHER_TAB, 60, 6);

	private final Tab superTab;
	private final int component;

	private int mval;
	private int sval;

	private InnerAbilityTabs(Tab tab, int c) {
		this.superTab = tab;
		this.component = c;
	}

	private InnerAbilityTabs(Tab t, int c, int sval) {
		this(t, c);
		this.sval = sval;
	}

	private InnerAbilityTabs(int c, int mval) {
		this(AbilityTabs.MAGIC_TAB, c);
		this.mval = mval;
	}

	@Override
	public boolean open() {
		WidgetChild wc;
		return superTab.open()
				&& (isOpen() || (wc = Widgets.get(BOOK_WIDGET, component)) != null && wc.visible()
						&& wc.click(true) && new TimedCondition(2000) {
							@Override
							public boolean isDone() {
								return InnerAbilityTabs.this.isOpen();
							}
						}.waitStop());
	}

	@Override
	public boolean isOpen() {
		WidgetChild wc, wci;
		return superTab.isOpen()
				&& (mval == 0 || (Settings.get(BOOK_SETTING, MAGIC_SHIFT, MAGIC_MASK) == mval
						&& (wc = Widgets.get(BOOK_WIDGET, ABILITY_LIST)) != null
						&& (wci = wc.getChild(1)) != null && wci.getTextureId() == -1))
				&& (sval == 0 || (Settings.get(BOOK_SETTING, 0, STANDARD_MASK) == sval));
	}

	public static InnerAbilityTabs getCurrent() {
		for (InnerAbilityTabs t : values()) {
			if (t.isOpen())
				return t;
		}
		return null;
	}

	private static final int BOOK_WIDGET = 275;
	private static final int ABILITY_LIST = 16;

	private static final int BOOK_SETTING = 682, MAGIC_SHIFT = 18, MAGIC_MASK = 0x3, STANDARD_MASK = 0xF;

}
