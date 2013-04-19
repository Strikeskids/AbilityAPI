package sk.action.magic;

import sk.action.BookDraggable;
import sk.item.RequiredGroup;

public interface Spell extends BookDraggable {
	/**
	 * Gets the {@link Spellbook} of this spell
	 * 
	 * @return the spellbook
	 */
	public Spellbook getSpellbook();

	/**
	 * Gets the required {@link Rune}s for this spell
	 * 
	 * @return An Array of the required runes
	 */
	public Rune[] getRunes();

	/**
	 * Gets a {@link RequiredGroup} of the runes for this spell
	 * 
	 * @return The required group
	 */
	public RequiredGroup getRuneGroup();
}
