package sk.tab;

import org.powerbot.game.api.methods.Tabs;

public enum MainTabs implements Tab {
	ATTACK(Tabs.ATTACK),
	CLAN_CHAT(Tabs.CLAN_CHAT),
	EMOTES(Tabs.EMOTES),
	EQUIPMENT(Tabs.EQUIPMENT),
	FRIENDS(Tabs.FRIENDS),
	FRIENDS_CHAT(Tabs.FRIENDS_CHAT),
	INVENTORY(Tabs.INVENTORY),
	LOGOUT(Tabs.LOGOUT),
	ABILITY_BOOK(Tabs.ABILITY_BOOK),
	MUSIC(Tabs.MUSIC),
	NONE(Tabs.NONE),
	NOTES(Tabs.NOTES),
	OPTIONS(Tabs.OPTIONS),
	PRAYER(Tabs.PRAYER),
	NOTICEBOARD(Tabs.NOTICEBOARD),
	STATS(Tabs.STATS),
	EXTRAS(Tabs.EXTRAS);

	private final Tabs tab;

	MainTabs(Tabs t) {
		this.tab = t;
	}

	public String getDescription() {
		return tab.getDescription();
	}

	public int getFunctionKey() {
		return tab.getFunctionKey();
	}

	public boolean hasFunctionKey() {
		return tab.hasFunctionKey();
	}

	public int getIndex() {
		return tab.getIndex();
	}

	public boolean open() {
		return open(USE_FAST);
	}

	public boolean open(final boolean fast) {
		return tab.open();
	}

	public boolean isOpen() {
		return tab.isOpen();
	}

	public static MainTabs getCurrent() {
		Tabs cur = Tabs.getCurrent();
		for (MainTabs t : values()) {
			if (t.tab == cur)
				return t;
		}
		return NONE;
	}

	// private final Tabs thisTab;
	//
	// private MainTabs(Tabs t) {
	// this.thisTab = t;
	// }
	//
	// @Override
	// public boolean isOpen() {
	// return thisTab.isOpen();
	// }
	//
	// @Override
	// public boolean open() {
	// return thisTab.open(USE_FAST);
	// }
	//
	public static boolean USE_FAST = false;

}
