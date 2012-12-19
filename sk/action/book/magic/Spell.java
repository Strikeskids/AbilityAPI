package sk.action.book.magic;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.Ability;
import sk.general.Completion;
import sk.tab.InnerAbilityTabs;
import sk.tab.Tab;

public enum Spell implements Ability {
	AIR_STRIKE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 14, 14348, 230, 0, new Rune(
			RuneType.AIR, 1)),
	CONFUSE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 15, 14389, 246, 3, new Rune(RuneType.MIND,
			1)),
	WATER_STRIKE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 16, 14349, 262, 5, new Rune(
			RuneType.AIR, 1), new Rune(RuneType.WATER, 1)),
	EARTH_STRIKE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 18, 14350, 294, 9, new Rune(
			RuneType.AIR, 1), new Rune(RuneType.EARTH, 1)),
	WEAKEN(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 20, 14390, 326, 11, new Rune(RuneType.BODY,
			1)),
	FIRE_STRIKE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 21, 14351, 342, 13, new Rune(
			RuneType.AIR, 1), new Rune(RuneType.FIRE, 1)),
	AIR_BOLT(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 23, 14352, 374, 17, new Rune(
			RuneType.AIR, 2)),
	CURSE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 24, 14391, 390, 19, new Rune(RuneType.CHAOS,
			1)),
	BIND(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 25, 14392, 406, 20, new Rune(RuneType.NATURE,
			1)),
	WATER_BOLT(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 27, 14353, 438, 23, new Rune(
			RuneType.AIR, 2), new Rune(RuneType.WATER, 2)),
	EARTH_BOLT(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 30, 14354, 486, 29, new Rune(
			RuneType.AIR, 2), new Rune(RuneType.EARTH, 2)),
	FIRE_BOLT(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 33, 14355, 534, 35, new Rune(
			RuneType.AIR, 2), new Rune(RuneType.FIRE, 2)),
	AIR_BLAST(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 37, 14356, 598, 41, new Rune(
			RuneType.AIR, 3)),
	WATER_BLAST(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 40, 14357, 646, 47, new Rune(
			RuneType.AIR, 3), new Rune(RuneType.WATER, 3)),
	SNARE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 43, 14393, 694, 50, new Rune(
			RuneType.NATURE, 2)),
	SLAYER_DART(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 44, 14388, 710, 50, new Rune(
			RuneType.AIR, 3), new Rune(RuneType.DEATH, 1)),
	EARTH_BLAST(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 46, 14358, 742, 53, new Rune(
			RuneType.AIR, 3), new Rune(RuneType.EARTH, 3)),
	FIRE_BLAST(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 51, 14359, 822, 59, new Rune(
			RuneType.AIR, 3), new Rune(RuneType.FIRE, 3)),
	DIVINE_STORM(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 54, 14369, 870, 60, new Rune(
			RuneType.AIR, 5)),
	AIR_WAVE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 58, 14360, 934, 62, new Rune(
			RuneType.AIR, 4)),
	WATER_WAVE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 61, 14361, 982, 65, new Rune(
			RuneType.AIR, 4), new Rune(RuneType.WATER, 4)),
	VULNERABILITY(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 63, 14395, 1014, 66, new Rune(
			RuneType.CHAOS, 1), new Rune(RuneType.SOUL, 1)),
	EARTH_WAVE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 65, 14362, 1046, 70, new Rune(
			RuneType.AIR, 4), new Rune(RuneType.EARTH, 4)),
	ENFEEBLE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 66, 14371, 1062, 73, new Rune(
			RuneType.BODY, 1), new Rune(RuneType.SOUL, 1)),
	FIRE_WAVE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 68, 14363, 1094, 75, new Rune(
			RuneType.AIR, 4), new Rune(RuneType.FIRE, 4)),
	STORM_OF_ARMADYL(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 69, 14369, 1110, 77, new Rune(
			RuneType.ARMADYL, 1)),
	ENTANGLE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 70, 14394, 1126, 79, new Rune(
			RuneType.NATURE, 3)),
	STAGGER(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 71, 14377, 1142, 80, new Rune(
			RuneType.MIND, 1), new Rune(RuneType.SOUL, 1)),
	AIR_SURGE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 73, 14364, 1174, 81, new Rune(
			RuneType.AIR, 5)),
	TELEPORT_BLOCK(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 75, 14344, 1206, 85, new Rune(
			RuneType.CHAOS, 1), new Rune(RuneType.LAW, 1), new Rune(RuneType.DEATH, 1)),
	EARTH_SURGE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 76, 14366, 1222, 90, new Rune(
			RuneType.AIR, 5), new Rune(RuneType.EARTH, 5)),
	WATER_SURGE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 78, 14365, 1254, 85, new Rune(
			RuneType.AIR, 5), new Rune(RuneType.WATER, 5)),
	FIRE_SURGE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 80, 14367, 1286, 95, new Rune(
			RuneType.AIR, 5), new Rune(RuneType.FIRE, 5)),
	POLYPORE_STRIKE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 162, 14396, 2598, 80),

	MOBILISING_ARMIES_TELEPORT(
			Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 19, 14335, 310, 10, new Rune(RuneType.AIR,
					1), new Rune(RuneType.WATER, 1), new Rune(RuneType.LAW, 1)),
	VARROCK_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 28, 14336, 454, 25, new Rune(
			RuneType.AIR, 3), new Rune(RuneType.FIRE, 1), new Rune(RuneType.LAW, 1)),
	LUMBRIDGE_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 31, 14334, 502, 31, new Rune(
			RuneType.AIR, 3), new Rune(RuneType.EARTH, 1), new Rune(RuneType.LAW, 1)),
	FALADOR_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 34, 14337, 550, 37, new Rune(
			RuneType.AIR, 3), new Rune(RuneType.WATER, 1), new Rune(RuneType.LAW, 1)),
	HOUSE_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 36, 14338, 582, 40, new Rune(
			RuneType.AIR, 1), new Rune(RuneType.EARTH, 1), new Rune(RuneType.LAW, 1)),
	CAMELOT_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 39, 14339, 630, 45, new Rune(
			RuneType.AIR, 5), new Rune(RuneType.LAW, 1)),
	ARDOUGNE_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 45, 14340, 726, 51, new Rune(
			RuneType.WATER, 2), new Rune(RuneType.LAW, 2)),
	WATCHTOWER_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 50, 14341, 806, 58, new Rune(
			RuneType.EARTH, 2), new Rune(RuneType.LAW, 2)),
	TROLLHEIM_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 57, 14342, 918, 61, new Rune(
			RuneType.FIRE, 2), new Rune(RuneType.LAW, 2)),
	APE_ATOLL_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 60, 14343, 966, 64, new Rune(
			RuneType.WATER, 2), new Rune(RuneType.FIRE, 2), new Rune(RuneType.LAW, 2)),
	TELEOTHER_LUMBRIDGE(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 67, 14345, 1078, 74, new Rune(
			RuneType.EARTH, 1), new Rune(RuneType.LAW, 1), new Rune(RuneType.SOUL, 1)),
	TELEOTHER_FALADOR(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 74, 14346, 1190, 82, new Rune(
			RuneType.WATER, 1), new Rune(RuneType.LAW, 1), new Rune(RuneType.SOUL, 1)),
	TELEOTHER_CAMELOT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 79, 14347, 1270, 90, new Rune(
			RuneType.LAW, 1), new Rune(RuneType.SOUL, 2)),
	HOME_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 155, 14333, 2491, 11),

	LVL1_ENCHANT(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 17, 14382, 278, 7, new Rune(
			RuneType.WATER, 1), new Rune(RuneType.COSMIC, 1)),
	BONES_TO_BANANAS(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 22, 14380, 358, 15, new Rune(
			RuneType.WATER, 2), new Rune(RuneType.EARTH, 2), new Rune(RuneType.NATURE, 1)),
	LOW_LEVEL_ALCHEMY(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 26, 14378, 422, 21, new Rune(
			RuneType.FIRE, 3), new Rune(RuneType.NATURE, 1)),
	LVL2_ENCHANT(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 29, 14385, 470, 27, new Rune(
			RuneType.AIR, 3), new Rune(RuneType.COSMIC, 1)),
	TELEKINETIC_GRAB(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 32, 14332, 518, 33, new Rune(
			RuneType.AIR, 1), new Rune(RuneType.LAW, 1)),
	SUPERHEAT_ITEM(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 38, 14372, 614, 43, new Rune(
			RuneType.FIRE, 4), new Rune(RuneType.NATURE, 1)),
	LVL3_ENCHANT(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 41, 14384, 662, 49, new Rune(
			RuneType.FIRE, 5), new Rune(RuneType.COSMIC, 1)),
	HIGH_LEVEL_ALCHEMY(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 47, 14379, 758, 55, new Rune(
			RuneType.FIRE, 5), new Rune(RuneType.NATURE, 1)),
	CHARGE_WATER_ORB(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 48, 14374, 774, 56, new Rune(
			RuneType.WATER, 30), new Rune(RuneType.COSMIC, 3)),
	LVL4_ENCHANT(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 49, 14383, 790, 57, new Rune(
			RuneType.EARTH, 10), new Rune(RuneType.COSMIC, 1)),
	CHARGE_EARTH_ORB(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 52, 14375, 838, 60, new Rune(
			RuneType.EARTH, 30), new Rune(RuneType.COSMIC, 3)),
	BONES_TO_PEACHES(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 53, 14381, 854, 60, new Rune(
			RuneType.WATER, 4), new Rune(RuneType.EARTH, 4), new Rune(RuneType.NATURE, 2)),
	CHARGE_FIRE_ORB(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 59, 14376, 950, 63, new Rune(
			RuneType.FIRE, 30), new Rune(RuneType.COSMIC, 3)),
	CHARGE_AIR_ORB(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 62, 14373, 998, 66, new Rune(
			RuneType.AIR, 30), new Rune(RuneType.COSMIC, 3)),
	LVL5_ENCHANT(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 64, 14386, 1030, 68, new Rune(
			RuneType.WATER, 15), new Rune(RuneType.EARTH, 15), new Rune(RuneType.COSMIC, 1)),
	LVL6_ENCHANT(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 77, 14387, 1238, 87, new Rune(
			RuneType.EARTH, 20), new Rune(RuneType.FIRE, 20), new Rune(RuneType.COSMIC, 1)),
	ENCHANT_CROSSBOW_BOLT(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 156, 14370, 2502, 4), ;

	private final int level;
	private final Rune[] runes;
	private final int abilityId;
	private final int bookTexture;
	private final int bookChild;
	private final Tab tab;
	private final Spellbook spellbook;

	private Spell(Spellbook book, Tab t, int child, int text, int abil, int level, Rune... runes) {
		this.runes = runes;
		this.spellbook = book;
		this.tab = t;
		this.bookChild = child;
		this.bookTexture = text;
		this.abilityId = abil;
		this.level = level;

		Ability.ALL_ABILITIES.put(this.abilityId, this);
	}

	@Override
	public boolean show() {
		if (!tab.open())
			return false;
		WidgetChild window = Widgets.get(BOOK_WIDGET, CLICK_WINDOW);
		WidgetChild wc = getChild();
		WidgetChild scrollbar = Widgets.get(BOOK_WIDGET, SCROLLBAR);
		if (wc == null || window == null || scrollbar == null || !wc.visible() || !window.visible())
			return false;
		if (window.getBoundingRectangle().contains(wc.getBoundingRectangle()))
			return isVisible();
		if (!Widgets.scroll(wc, scrollbar))
			return false;
		return isVisible();
	}

	@Override
	public boolean isVisible() {
		WidgetChild wc = getChild();
		WidgetChild window = Widgets.get(BOOK_WIDGET, CLICK_WINDOW);
		return tab.isOpen() && wc != null && wc.visible() && window.visible()
				&& window.getBoundingRectangle().contains(wc.getBoundingRectangle());
	}

	@Override
	public boolean available() {
		WidgetChild wc = getChild();
		return Skills.getLevel(Skills.MAGIC) >= level && (wc == null || wc.getTextColor() == 0xFFFFFF);
	}

	public Tab getTab() {
		return tab;
	}

	@Override
	public WidgetChild getChild() {
		WidgetChild main = Widgets.get(BOOK_WIDGET, MAIN_CHILD);
		if (main == null)
			return null;
		WidgetChild ret = main.getChild(bookChild);
		if (ret == null || ret.getTextureId() != bookTexture)
			return null;
		return ret;
	}

	@Override
	public Completion getChange() {
		// TODO improve
		return Completion.TRUE;
	}

	@Override
	public int getAbilityId() {
		return abilityId;
	}

	public Rune[] getRunes() {
		return runes;
	}

	public Spellbook getSpellbook() {
		return spellbook;
	}

	private static final int BOOK_WIDGET = 275, MAIN_CHILD = 16, SCROLLBAR = 20, CLICK_WINDOW = 14;

}
