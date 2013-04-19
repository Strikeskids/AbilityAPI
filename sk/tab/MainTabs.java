package sk.tab;

import org.powerbot.game.api.methods.Tabs;

public enum MainTabs implements Tab {
	ATTACK(Tabs.ATTACK),
	CLAN_CHAT(Tabs.CLAN_CHAT),
	COMBAT_ACADEMY(Tabs.COMBAT_ACADEMY),
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

	MainTabs(final Tabs t) {
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

	@Override
	public boolean open() {
		return open(USE_FAST);
	}

	public boolean open(final boolean fast) {
		return tab.open(fast);
	}

	@Override
	public boolean isOpen() {
		return tab.isOpen();
	}

	public static MainTabs getCurrent() {
		final Tabs cur = Tabs.getCurrent();
		for (final MainTabs t : values()) {
			if (t.tab == cur)
				return t;
		}
		return NONE;
	}
		
	public static boolean USE_FAST = false;

}
