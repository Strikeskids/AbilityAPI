package sk.action.magic;

import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.BookUtil;
import sk.item.DefinedItem;
import sk.item.RequiredGroup;
import sk.tab.InnerAbilityTabs;
import sk.tab.Tab;

public enum FreeSpell implements Spell {
	AIR_STRIKE(InnerAbilityTabs.COMBAT_SPELL_TAB, 14, 14348, 230, 0, new Rune(RuneType.AIR, 1)),

	CONFUSE(InnerAbilityTabs.COMBAT_SPELL_TAB, 15, 14389, 246, 3, new Rune(RuneType.MIND, 1)),

	WATER_STRIKE(InnerAbilityTabs.COMBAT_SPELL_TAB, 16, 14349, 262, 5, new Rune(RuneType.AIR, 1), new Rune(
			RuneType.WATER, 1)),

	EARTH_STRIKE(InnerAbilityTabs.COMBAT_SPELL_TAB, 18, 14350, 294, 9, new Rune(RuneType.AIR, 1), new Rune(
			RuneType.EARTH, 1)),

	WEAKEN(InnerAbilityTabs.COMBAT_SPELL_TAB, 20, 14390, 326, 11, new Rune(RuneType.BODY, 1)),

	FIRE_STRIKE(InnerAbilityTabs.COMBAT_SPELL_TAB, 21, 14351, 342, 13, new Rune(RuneType.AIR, 1), new Rune(
			RuneType.FIRE, 1)),

	AIR_BOLT(InnerAbilityTabs.COMBAT_SPELL_TAB, 23, 14352, 374, 17, new Rune(RuneType.AIR, 2)),

	CURSE(InnerAbilityTabs.COMBAT_SPELL_TAB, 24, 14391, 390, 19, new Rune(RuneType.CHAOS, 1)),

	BIND(InnerAbilityTabs.COMBAT_SPELL_TAB, 25, 14392, 406, 20, new Rune(RuneType.NATURE, 1)),

	WATER_BOLT(InnerAbilityTabs.COMBAT_SPELL_TAB, 27, 14353, 438, 23, new Rune(RuneType.AIR, 2), new Rune(
			RuneType.WATER, 2)),

	EARTH_BOLT(InnerAbilityTabs.COMBAT_SPELL_TAB, 30, 14354, 486, 29, new Rune(RuneType.AIR, 2), new Rune(
			RuneType.EARTH, 2)),

	FIRE_BOLT(InnerAbilityTabs.COMBAT_SPELL_TAB, 33, 14355, 534, 35, new Rune(RuneType.AIR, 2), new Rune(
			RuneType.FIRE, 2)),

	AIR_BLAST(InnerAbilityTabs.COMBAT_SPELL_TAB, 37, 14356, 598, 41, new Rune(RuneType.AIR, 3)),

	WATER_BLAST(InnerAbilityTabs.COMBAT_SPELL_TAB, 40, 14357, 646, 47, new Rune(RuneType.AIR, 3), new Rune(
			RuneType.WATER, 3)),

	EARTH_BLAST(InnerAbilityTabs.COMBAT_SPELL_TAB, 46, 14358, 742, 53, new Rune(RuneType.AIR, 3), new Rune(
			RuneType.EARTH, 3)),

	FIRE_BLAST(InnerAbilityTabs.COMBAT_SPELL_TAB, 51, 14359, 822, 59, new Rune(RuneType.AIR, 3), new Rune(
			RuneType.FIRE, 3)),

	VARROCK_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 28, 14336, 454, 25, new Rune(RuneType.AIR, 3), new Rune(
			RuneType.FIRE, 1), new Rune(RuneType.LAW, 1)),

	LUMBRIDGE_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 31, 14334, 502, 31, new Rune(RuneType.AIR, 3), new Rune(
			RuneType.EARTH, 1), new Rune(RuneType.LAW, 1)),

	FALADOR_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 34, 14337, 550, 37, new Rune(RuneType.AIR, 3), new Rune(
			RuneType.WATER, 1), new Rune(RuneType.LAW, 1)),

	LVL1_ENCHANT(InnerAbilityTabs.OTHER_SPELL_TAB, 17, 14382, 278, 7, new Rune(RuneType.WATER, 1), new Rune(
			RuneType.COSMIC, 1)),

	BONES_TO_BANANAS(InnerAbilityTabs.OTHER_SPELL_TAB, 22, 14380, 358, 15, new Rune(RuneType.WATER, 2), new Rune(
			RuneType.EARTH, 2), new Rune(RuneType.NATURE, 1)),

	LOW_LEVEL_ALCHEMY(InnerAbilityTabs.OTHER_SPELL_TAB, 26, 14378, 422, 21, new Rune(RuneType.FIRE, 3), new Rune(
			RuneType.NATURE, 1)),

	LVL2_ENCHANT(InnerAbilityTabs.OTHER_SPELL_TAB, 29, 14385, 470, 27, new Rune(RuneType.AIR, 3), new Rune(
			RuneType.COSMIC, 1)),

	TELEKINETIC_GRAB(InnerAbilityTabs.OTHER_SPELL_TAB, 32, 14332, 518, 33, new Rune(RuneType.AIR, 1), new Rune(
			RuneType.LAW, 1)),

	SUPERHEAT_ITEM(InnerAbilityTabs.OTHER_SPELL_TAB, 38, 14372, 614, 43, new Rune(RuneType.FIRE, 4), new Rune(
			RuneType.NATURE, 1)),

	LVL3_ENCHANT(InnerAbilityTabs.OTHER_SPELL_TAB, 41, 14384, 662, 49, new Rune(RuneType.FIRE, 5), new Rune(
			RuneType.COSMIC, 1)),

	HIGH_LEVEL_ALCHEMY(InnerAbilityTabs.OTHER_SPELL_TAB, 47, 14379, 758, 55, new Rune(RuneType.FIRE, 5), new Rune(
			RuneType.NATURE, 1)),

	LVL4_ENCHANT(InnerAbilityTabs.OTHER_SPELL_TAB, 49, 14383, 790, 57, new Rune(RuneType.EARTH, 10), new Rune(
			RuneType.COSMIC, 1)), ;

	@Override
	public Spellbook getSpellbook() {
		return Spellbook.STANDARD;
	}

	@Override
	public WidgetChild getChild() {
		return BookUtil.getChild(this);
	}

	@Override
	public WidgetChild getCooldownChild() {
		return BookUtil.getCooldownChild(this);
	}

	@Override
	public boolean show() {
		return BookUtil.show(this);
	}

	@Override
	public boolean isVisible() {
		return BookUtil.isVisible(this);
	}

	@Override
	public int getId() {
		return abilityId;
	}

	@Override
	public Tab getTab() {
		return tab;
	}

	@Override
	public int getChildId() {
		return childId;
	}

	@Override
	public int getChildTexture() {
		return childTexture;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public int getSkill() {
		return Skills.MAGIC;
	}

	@Override
	public Rune[] getRunes() {
		return runes;
	}

	@Override
	public RequiredGroup getRuneGroup() {
		RequiredGroup ret = new RequiredGroup();
		for (Rune r : getRunes()) {
			ret.add(new DefinedItem(r.getAmount(), r.getType().getItem()));
		}
		return ret;
	}

	private final Tab tab;
	private final int childId, childTexture, abilityId, level;
	private final Rune[] runes;

	private FreeSpell(Tab tab, int childId, int childTexture, int abilityId, int level, Rune... runes) {
		this.tab = tab;
		this.childId = childId;
		this.childTexture = childTexture;
		this.abilityId = abilityId;
		this.level = level;
		this.runes = runes;
	}

}
