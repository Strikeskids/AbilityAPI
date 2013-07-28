package com.sk.methods.action.magic;

import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.Component;

import com.sk.SkMethodContext;
import com.sk.methods.action.ability.AbilityStyle;
import com.sk.methods.action.structure.Spell;
import com.sk.windows.InnerAbilityTab;
import com.sk.windows.Window;

public enum AncientSpell implements Spell {
	PADDEWWA_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 1334, 54, 83, 14477, new Rune(RuneType.AIR, 1), new Rune(RuneType.FIRE, 1), new Rune(RuneType.LAW, 2)),
SENNTISTEN_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 1382, 60, 86, 14478, new Rune(RuneType.LAW, 2), new Rune(RuneType.SOUL, 1)),
KHARYRLL_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 1430, 66, 89, 14479, new Rune(RuneType.LAW, 2), new Rune(RuneType.BLOOD, 1)),
LASSAR_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 1478, 72, 92, 14480, new Rune(RuneType.WATER, 4), new Rune(RuneType.LAW, 2)),
DAREEYAK_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 1526, 78, 95, 14481, new Rune(RuneType.AIR, 2), new Rune(RuneType.FIRE, 3), new Rune(RuneType.LAW, 2)),
CARRALLANGER_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 1574, 84, 98, 14482, new Rune(RuneType.LAW, 2), new Rune(RuneType.SOUL, 2)),
ANNAKARL_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 1622, 90, 101, 14483, new Rune(RuneType.LAW, 2), new Rune(RuneType.BLOOD, 2)),
GHORROCK_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 1670, 96, 104, 14484, new Rune(RuneType.WATER, 8), new Rune(RuneType.LAW, 2)),
GALE_RUSH(InnerAbilityTab.COMBAT_SPELL, 1302, 50, 81, 14485, new Rune(RuneType.AIR, 3), new Rune(RuneType.DEATH, 1)),
ROCK_RUSH(InnerAbilityTab.COMBAT_SPELL, 1318, 52, 82, 14489, new Rune(RuneType.EARTH, 3), new Rune(RuneType.DEATH, 1)),
BLOODFIRE_RUSH(InnerAbilityTab.COMBAT_SPELL, 1350, 56, 84, 14497, new Rune(RuneType.FIRE, 3), new Rune(RuneType.DEATH, 1)),
ICE_RUSH(InnerAbilityTab.COMBAT_SPELL, 1366, 58, 85, 14493, new Rune(RuneType.WATER, 3), new Rune(RuneType.DEATH, 1)),
GALE_BURST(InnerAbilityTab.COMBAT_SPELL, 1398, 62, 87, 14486, new Rune(RuneType.AIR, 4), new Rune(RuneType.DEATH, 2)),
ROCK_BURST(InnerAbilityTab.COMBAT_SPELL, 1414, 64, 88, 14490, new Rune(RuneType.EARTH, 4), new Rune(RuneType.DEATH, 2)),
BLOODFIRE_BURST(InnerAbilityTab.COMBAT_SPELL, 1446, 68, 90, 14498, new Rune(RuneType.FIRE, 4), new Rune(RuneType.DEATH, 2)),
ICE_BURST(InnerAbilityTab.COMBAT_SPELL, 1462, 70, 91, 14494, new Rune(RuneType.WATER, 4), new Rune(RuneType.DEATH, 2)),
GALE_BLITZ(InnerAbilityTab.COMBAT_SPELL, 1494, 74, 93, 14487, new Rune(RuneType.AIR, 5), new Rune(RuneType.BLOOD, 1)),
ROCK_BLITZ(InnerAbilityTab.COMBAT_SPELL, 1510, 76, 94, 14491, new Rune(RuneType.EARTH, 5), new Rune(RuneType.BLOOD, 1)),
BLOODFIRE_BLITZ(InnerAbilityTab.COMBAT_SPELL, 1542, 80, 96, 14499, new Rune(RuneType.FIRE, 5), new Rune(RuneType.BLOOD, 1)),
ICE_BLITZ(InnerAbilityTab.COMBAT_SPELL, 1558, 82, 97, 14495, new Rune(RuneType.WATER, 5), new Rune(RuneType.BLOOD, 1)),
GALE_BARRAGE(InnerAbilityTab.COMBAT_SPELL, 1590, 86, 99, 14488, new Rune(RuneType.AIR, 5), new Rune(RuneType.BLOOD, 2)),
ROCK_BARRAGE(InnerAbilityTab.COMBAT_SPELL, 1606, 88, 100, 14492, new Rune(RuneType.EARTH, 5), new Rune(RuneType.BLOOD, 2)),
BLOODFIRE_BARRAGE(InnerAbilityTab.COMBAT_SPELL, 1638, 92, 102, 14500, new Rune(RuneType.FIRE, 5), new Rune(RuneType.BLOOD, 2)),
ICE_BARRAGE(InnerAbilityTab.COMBAT_SPELL, 1654, 94, 103, 14496, new Rune(RuneType.WATER, 5), new Rune(RuneType.BLOOD, 2)),
;

	private AncientSpell(Window t, int id, int l, int ci, int ct, Rune... r) {
		this.window = t;
		this.id = id;
		this.level = l;
		this.childIndex = ci;
		this.childTexture = ct;
		this.runes = r;
	}

	private final Rune[] runes;
	private final int childIndex, childTexture, level, id;
	private final Window window;

	@Override
	public int getChildIndex() {
		return childIndex;
	}

	@Override
	public int getChildTexture() {
		return childTexture;
	}

	@Override
	public Component getCooldownComponent(SkMethodContext ctx) {
		return ctx.widgets.get(getWidget(), COOLDOWN_COMPONENT).getChild(getChildIndex());
	}

	@Override
	public Window getWindow() {
		return window;
	}

	@Override
	public int getSkillLevel() {
		return level;
	}

	@Override
	public int getSkill() {
		return Skills.MAGIC;
	}

	@Override
	public Component getComponent(SkMethodContext ctx) {
		return ctx.widgets.get(getWidget(), MAIN_COMPONENT).getChild(getChildIndex());
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public Spellbook getSpellbook() {
		return Spellbook.ALL;
	}

	@Override
	public Rune[] getRunes() {
		return runes;
	}

	@Override
	public int getWidget() {
		return AbilityStyle.MAGIC.getWidgetId();
	}
}

