package sk.action.book.magic;

/**
 * An enum that has the possible types of runes
 * 
 * @author Strikeskids
 * 
 */
public enum RuneType {
	AIR(1, 556, 2478, 7139), MIND(1, 558, 2479, 7140), WATER(5, 555, 2480, 7137),

	EARTH(9, 557, 2481, 7130), FIRE(14, 554, 2482, 7129), BODY(20, 559, 2483, 7131),

	COSMIC(27, 564, 2484, 7132), CHAOS(35, 562, 2485, 7134), ASTRAL(40, 9075, -1, 0),

	NATURE(44, 561, 2486, 7133), LAW(53, 563, 2487, 7135), DEATH(65, 560, 2488, 7136),

	BLOOD(77, 565, 30624, 7141), SOUL(1, -1, 0, 7138), ARMADYL(72, -1, 0, 0);

	private final int itemId, altarId, abyssPortalId, craftingLevel;

	private RuneType(int lvl, int item, int altar, int abyss) {
		this.craftingLevel = lvl;
		this.itemId = item;
		this.altarId = altar;
		this.abyssPortalId = abyss;
	}

	/**
	 * Returns whether this rune can be crafted through the abyss
	 * 
	 * @return <tt>true</tt> if this rune can be crafted through the abyss
	 */
	public boolean canCraftThroughAbyss() {
		return abyssPortalId > 0 && altarId > 0;
	}

	/**
	 * Returns whether this rune can be crafted on an altar
	 * 
	 * @return <tt>true</tt> if this rune can be crafted on an altar
	 */
	public boolean canCraftOnAltar() {
		return altarId > 0;
	}

	/**
	 * Gets the id for the altar that this rune would be crafted on
	 * 
	 * @return the id of the altar or <tt>-1</tt> if this rune cannot be crafted on an altar
	 */
	public int getAltar() {
		return altarId;
	}

	/**
	 * Gets the item id for this rune
	 * 
	 * @return the item id
	 */
	public int getItem() {
		return itemId;
	}

	/**
	 * Gets the runecrafting level required to craft this rune
	 * 
	 * @return the runecrafting level required
	 */
	public int getLevelToCraft() {
		return craftingLevel;
	}

	/**
	 * Gets the abyss portal id for
	 * 
	 * @return the abyss portal id or <tt>-1</tt> if no such portal exists
	 */
	public int getAbyssPortal() {
		return abyssPortalId;
	}

	/**
	 * Checks to see if this rune is an elemental rune
	 * 
	 * @return <tt>true</tt> if this rune is elemental
	 */
	public boolean isElemental() {
		return ordinal() <= FIRE.ordinal();
	}

	/**
	 * Checks to see if this rune required pure essence to be crafted
	 * 
	 * @return <tt>true</tt> if this rune cannot be crafted with a rune essence, only with a pure essence
	 */
	public boolean isPureEssence() {
		return ordinal() > BODY.ordinal();
	}
}
