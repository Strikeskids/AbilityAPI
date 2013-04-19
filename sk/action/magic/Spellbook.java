package sk.action.magic;

import org.powerbot.game.api.methods.Settings;

/**
 * An enum of the possible magic spellbooks
 *
 * @author Strikeskids
 */
public enum Spellbook {
	STANDARD(0),
	ANCIENT(1),
	LUNAR(2),
	/**
	 * The Spellbook used for a {@link Spell} that can be cast with any spellbook
	 */
	NONE(-1) {
		@Override
		public boolean isOpen() {
			return true;
		}
	};

	private final int value;

	private Spellbook(final int set) {
		this.value = set;
	}

	/**
	 * Checks to see if this spellbook is open
	 *
	 * @return <tt>true</tt> if this spellbook is the current spellbook
	 */
	public boolean isOpen() {
		return (Settings.get(SETTING) & MASK) == value;
	}

	/**
	 * Gets the current spellbook
	 *
	 * @return The Spellbook that is currently open or {@link Spellbook#NONE} if no such spellbook exists
	 */
	public static Spellbook getCurrent() {
		for (final Spellbook s : values()) {
			if (s.isOpen()) {
				return s;
			}
		}
		return Spellbook.NONE;
	}

	private static final int SETTING = 4, MASK = 0x3;
}
