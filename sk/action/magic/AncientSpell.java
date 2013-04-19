package sk.action.magic;

import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.BookUtil;
import sk.item.DefinedItem;
import sk.item.RequiredGroup;
import sk.tab.InnerAbilityTabs;
import sk.tab.Tab;

public enum AncientSpell implements Spell {
	PADDEWWA_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 83, 14477, 1334, 54, new Rune(RuneType.AIR, 1), new Rune(
			RuneType.FIRE, 1), new Rune(RuneType.LAW, 2)),

	SENNTISTEN_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 86, 14478, 1382, 60, new Rune(RuneType.LAW, 2), new Rune(
			RuneType.SOUL, 1)),

	KHARYRLL_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 89, 14479, 1430, 66, new Rune(RuneType.LAW, 2), new Rune(
			RuneType.BLOOD, 1)),

	LASSAR_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 92, 14480, 1478, 72, new Rune(RuneType.WATER, 4), new Rune(
			RuneType.LAW, 2)),

	DAREEYAK_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 95, 14481, 1526, 78, new Rune(RuneType.AIR, 2), new Rune(
			RuneType.FIRE, 3), new Rune(RuneType.LAW, 2)),

	CARRALLANGER_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 98, 14482, 1574, 84, new Rune(RuneType.LAW, 2), new Rune(
			RuneType.SOUL, 2)),

	ANNAKARL_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 101, 14483, 1622, 90, new Rune(RuneType.LAW, 2), new Rune(
			RuneType.BLOOD, 2)),

	GHORROCK_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 104, 14484, 1670, 96, new Rune(RuneType.WATER, 8), new Rune(
			RuneType.LAW, 2)),

	GALE_RUSH(InnerAbilityTabs.COMBAT_SPELL_TAB, 81, 14485, 1302, 50, new Rune(RuneType.AIR, 3), new Rune(
			RuneType.DEATH, 1)),

	ROCK_RUSH(InnerAbilityTabs.COMBAT_SPELL_TAB, 82, 14489, 1318, 52, new Rune(RuneType.EARTH, 3), new Rune(
			RuneType.DEATH, 1)),

	BLOODFIRE_RUSH(InnerAbilityTabs.COMBAT_SPELL_TAB, 84, 14497, 1350, 56, new Rune(RuneType.FIRE, 3), new Rune(
			RuneType.DEATH, 1)),

	ICE_RUSH(InnerAbilityTabs.COMBAT_SPELL_TAB, 85, 14493, 1366, 58, new Rune(RuneType.WATER, 3), new Rune(
			RuneType.DEATH, 1)),

	GALE_BURST(InnerAbilityTabs.COMBAT_SPELL_TAB, 87, 14486, 1398, 62, new Rune(RuneType.AIR, 4), new Rune(
			RuneType.DEATH, 2)),

	ROCK_BURST(InnerAbilityTabs.COMBAT_SPELL_TAB, 88, 14490, 1414, 64, new Rune(RuneType.EARTH, 4), new Rune(
			RuneType.DEATH, 2)),

	BLOODFIRE_BURST(InnerAbilityTabs.COMBAT_SPELL_TAB, 90, 14498, 1446, 68, new Rune(RuneType.FIRE, 4), new Rune(
			RuneType.DEATH, 2)),

	ICE_BURST(InnerAbilityTabs.COMBAT_SPELL_TAB, 91, 14494, 1462, 70, new Rune(RuneType.WATER, 4), new Rune(
			RuneType.DEATH, 2)),

	GALE_BLITZ(InnerAbilityTabs.COMBAT_SPELL_TAB, 93, 14487, 1494, 74, new Rune(RuneType.AIR, 5), new Rune(
			RuneType.BLOOD, 1)),

	ROCK_BLITZ(InnerAbilityTabs.COMBAT_SPELL_TAB, 94, 14491, 1510, 76, new Rune(RuneType.EARTH, 5), new Rune(
			RuneType.BLOOD, 1)),

	BLOODFIRE_BLITZ(InnerAbilityTabs.COMBAT_SPELL_TAB, 96, 14499, 1542, 80, new Rune(RuneType.FIRE, 5), new Rune(
			RuneType.BLOOD, 1)),

	ICE_BLITZ(InnerAbilityTabs.COMBAT_SPELL_TAB, 97, 14495, 1558, 82, new Rune(RuneType.WATER, 5), new Rune(
			RuneType.BLOOD, 1)),

	GALE_BARRAGE(InnerAbilityTabs.COMBAT_SPELL_TAB, 99, 14488, 1590, 86, new Rune(RuneType.AIR, 5), new Rune(
			RuneType.BLOOD, 2)),

	ROCK_BARRAGE(InnerAbilityTabs.COMBAT_SPELL_TAB, 100, 14492, 1606, 88, new Rune(RuneType.EARTH, 5), new Rune(
			RuneType.BLOOD, 2)),

	BLOODFIRE_BARRAGE(
			InnerAbilityTabs.COMBAT_SPELL_TAB, 102, 14500, 1638, 92, new Rune(RuneType.FIRE, 5), new Rune(
					RuneType.BLOOD, 2)),

	ICE_BARRAGE(InnerAbilityTabs.COMBAT_SPELL_TAB, 103, 14496, 1654, 94, new Rune(RuneType.WATER, 5), new Rune(
			RuneType.BLOOD, 2));

	@Override
	public Spellbook getSpellbook() {
		return Spellbook.ANCIENT;
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

	private AncientSpell(Tab tab, int childId, int childTexture, int abilityId, int level, Rune... runes) {
		this.tab = tab;
		this.childId = childId;
		this.childTexture = childTexture;
		this.abilityId = abilityId;
		this.level = level;
		this.runes = runes;
	}

}
