package sk.action.book.magic;

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

	public boolean canCraftThroughAbyss() {
		return abyssPortalId > 0 && altarId > 0;
	}

	public boolean canCraftOnAltar() {
		return altarId > 0;
	}

	public int getAltar() {
		return altarId;
	}

	public int getItem() {
		return itemId;
	}

	public int getLevelToCraft() {
		return craftingLevel;
	}

	public int getAbyssPortal() {
		return abyssPortalId;
	}

	public boolean isElemental() {
		return ordinal() <= FIRE.ordinal();
	}

	public boolean isPureEssence() {
		return ordinal() > BODY.ordinal();
	}
}
