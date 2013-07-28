package com.sk.methods.action.magic;

import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.Component;

import com.sk.SkMethodContext;
import com.sk.methods.action.ability.AbilityStyle;
import com.sk.methods.action.structure.Spell;
import com.sk.windows.InnerAbilityTab;
import com.sk.windows.Window;

public enum FreeSpell implements Spell {
	AIR_STRIKE(InnerAbilityTab.COMBAT_SPELL, 230, 0, 14, 14348, new Rune(RuneType.AIR, 1)),
CONFUSE(InnerAbilityTab.COMBAT_SPELL, 246, 3, 15, 14389, new Rune(RuneType.MIND, 1)),
WATER_STRIKE(InnerAbilityTab.COMBAT_SPELL, 262, 5, 16, 14349, new Rune(RuneType.AIR, 1), new Rune(RuneType.WATER, 1)),
EARTH_STRIKE(InnerAbilityTab.COMBAT_SPELL, 294, 9, 18, 14350, new Rune(RuneType.AIR, 1), new Rune(RuneType.EARTH, 1)),
WEAKEN(InnerAbilityTab.COMBAT_SPELL, 326, 11, 20, 14390, new Rune(RuneType.BODY, 1)),
FIRE_STRIKE(InnerAbilityTab.COMBAT_SPELL, 342, 13, 21, 14351, new Rune(RuneType.AIR, 1), new Rune(RuneType.FIRE, 1)),
AIR_BOLT(InnerAbilityTab.COMBAT_SPELL, 374, 17, 23, 14352, new Rune(RuneType.AIR, 2)),
CURSE(InnerAbilityTab.COMBAT_SPELL, 390, 19, 24, 14391, new Rune(RuneType.CHAOS, 1)),
BIND(InnerAbilityTab.COMBAT_SPELL, 406, 20, 25, 14392, new Rune(RuneType.NATURE, 1)),
WATER_BOLT(InnerAbilityTab.COMBAT_SPELL, 438, 23, 27, 14353, new Rune(RuneType.AIR, 2), new Rune(RuneType.WATER, 2)),
EARTH_BOLT(InnerAbilityTab.COMBAT_SPELL, 486, 29, 30, 14354, new Rune(RuneType.AIR, 2), new Rune(RuneType.EARTH, 2)),
FIRE_BOLT(InnerAbilityTab.COMBAT_SPELL, 534, 35, 33, 14355, new Rune(RuneType.AIR, 2), new Rune(RuneType.FIRE, 2)),
AIR_BLAST(InnerAbilityTab.COMBAT_SPELL, 598, 41, 37, 14356, new Rune(RuneType.AIR, 3)),
WATER_BLAST(InnerAbilityTab.COMBAT_SPELL, 646, 47, 40, 14357, new Rune(RuneType.AIR, 3), new Rune(RuneType.WATER, 3)),
EARTH_BLAST(InnerAbilityTab.COMBAT_SPELL, 742, 53, 46, 14358, new Rune(RuneType.AIR, 3), new Rune(RuneType.EARTH, 3)),
FIRE_BLAST(InnerAbilityTab.COMBAT_SPELL, 822, 59, 51, 14359, new Rune(RuneType.AIR, 3), new Rune(RuneType.FIRE, 3)),
VARROCK_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 454, 25, 28, 14336, new Rune(RuneType.AIR, 3), new Rune(RuneType.FIRE, 1), new Rune(RuneType.LAW, 1)),
LUMBRIDGE_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 502, 31, 31, 14334, new Rune(RuneType.AIR, 3), new Rune(RuneType.EARTH, 1), new Rune(RuneType.LAW, 1)),
FALADOR_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 550, 37, 34, 14337, new Rune(RuneType.AIR, 3), new Rune(RuneType.WATER, 1), new Rune(RuneType.LAW, 1)),
LVL1_ENCHANT(InnerAbilityTab.SKILLING_SPELL, 278, 7, 17, 14382, new Rune(RuneType.WATER, 1), new Rune(RuneType.COSMIC, 1)),
BONES_TO_BANANAS(InnerAbilityTab.SKILLING_SPELL, 358, 15, 22, 14380, new Rune(RuneType.WATER, 2), new Rune(RuneType.EARTH, 2), new Rune(RuneType.NATURE, 1)),
LOW_LEVEL_ALCHEMY(InnerAbilityTab.SKILLING_SPELL, 422, 21, 26, 14378, new Rune(RuneType.FIRE, 3), new Rune(RuneType.NATURE, 1)),
LVL2_ENCHANT(InnerAbilityTab.SKILLING_SPELL, 470, 27, 29, 14385, new Rune(RuneType.AIR, 3), new Rune(RuneType.COSMIC, 1)),
TELEKINETIC_GRAB(InnerAbilityTab.SKILLING_SPELL, 518, 33, 32, 14332, new Rune(RuneType.AIR, 1), new Rune(RuneType.LAW, 1)),
SUPERHEAT_ITEM(InnerAbilityTab.SKILLING_SPELL, 614, 43, 38, 14372, new Rune(RuneType.FIRE, 4), new Rune(RuneType.NATURE, 1)),
LVL3_ENCHANT(InnerAbilityTab.SKILLING_SPELL, 662, 49, 41, 14384, new Rune(RuneType.FIRE, 5), new Rune(RuneType.COSMIC, 1)),
HIGH_LEVEL_ALCHEMY(InnerAbilityTab.SKILLING_SPELL, 758, 55, 47, 14379, new Rune(RuneType.FIRE, 5), new Rune(RuneType.NATURE, 1)),
LVL4_ENCHANT(InnerAbilityTab.SKILLING_SPELL, 790, 57, 49, 14383, new Rune(RuneType.EARTH, 10), new Rune(RuneType.COSMIC, 1)),
;

	private FreeSpell(Window t, int id, int l, int ci, int ct, Rune... r) {
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

