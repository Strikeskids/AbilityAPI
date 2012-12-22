package sk.tab;

import java.util.Arrays;

import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.general.TimedCondition;

/**
 * An enum specifying the outer tabs in the ability book
 * 
 * @author Strikeskids
 * 
 */
public enum AbilityTabs implements Tab {
	MELEE_TAB(25, 1, 2), RANGE_TAB(11, 3), MAGIC_TAB(33, 4), OTHER_TAB(51, 5, 6);

	private final int component, setVals[];

	private AbilityTabs(int c, int... setVals) {
		this.component = c;
		this.setVals = setVals;
		Arrays.sort(this.setVals);
	}

	@Override
	public boolean isOpen() {
		return MainTabs.ABILITY_BOOK.isOpen()
				&& Arrays.binarySearch(setVals, Settings.get(SETTING_ID, 0, SETTING_MASK)) >= 0;
	}

	@Override
	public boolean open() {
		WidgetChild wc;
		return MainTabs.ABILITY_BOOK.open()
				&& (this.isOpen() || (wc = Widgets.get(BOOK_WIDGET, component)) != null && wc.visible()
						&& wc.click(true) && new TimedCondition(2000) {
							@Override
							public boolean isDone() {
								return AbilityTabs.this.isOpen();
							}
						}.waitStop());
	}

	public static AbilityTabs getCurrent() {
		for (AbilityTabs t : values()) {
			if (t.isOpen())
				return t;
		}
		return null;
	}

	private static final int SETTING_ID = 682, SETTING_MASK = 0xF;
	private static final int BOOK_WIDGET = 275;

}
