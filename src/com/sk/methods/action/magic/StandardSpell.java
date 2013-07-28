package com.sk.methods.action.magic;

import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.Component;

import com.sk.SkMethodContext;
import com.sk.methods.action.ability.AbilityStyle;
import com.sk.methods.action.structure.Spell;
import com.sk.windows.InnerAbilityTab;
import com.sk.windows.Window;

public enum StandardSpell implements Spell {
	SNARE(InnerAbilityTab.COMBAT_SPELL, 694, 50, 44, 14393, new Rune(RuneType.NATURE, 2)),
SLAYER_DART(InnerAbilityTab.COMBAT_SPELL, 710, 50, 44, 14388, new Rune(RuneType.AIR, 3), new Rune(RuneType.DEATH, 1)),
DIVINE_STORM(InnerAbilityTab.COMBAT_SPELL, 870, 60, 54, 14369, new Rune(RuneType.AIR, 5)),
AIR_WAVE(InnerAbilityTab.COMBAT_SPELL, 934, 62, 58, 14360, new Rune(RuneType.AIR, 4)),
WATER_WAVE(InnerAbilityTab.COMBAT_SPELL, 982, 65, 61, 14361, new Rune(RuneType.AIR, 4), new Rune(RuneType.WATER, 4)),
VULNERABILITY(InnerAbilityTab.COMBAT_SPELL, 1014, 66, 63, 14395, new Rune(RuneType.CHAOS, 1), new Rune(RuneType.SOUL, 1)),
EARTH_WAVE(InnerAbilityTab.COMBAT_SPELL, 1046, 70, 65, 14362, new Rune(RuneType.AIR, 4), new Rune(RuneType.EARTH, 4)),
ENFEEBLE(InnerAbilityTab.COMBAT_SPELL, 1062, 73, 66, 14371, new Rune(RuneType.BODY, 1), new Rune(RuneType.SOUL, 1)),
FIRE_WAVE(InnerAbilityTab.COMBAT_SPELL, 1094, 75, 68, 14363, new Rune(RuneType.AIR, 4), new Rune(RuneType.FIRE, 4)),
STORM_OF_ARMADYL(InnerAbilityTab.COMBAT_SPELL, 1110, 77, 69, 14369, new Rune(RuneType.ARMADYL, 1)),
ENTANGLE(InnerAbilityTab.COMBAT_SPELL, 1126, 79, 70, 14394, new Rune(RuneType.NATURE, 3)),
STAGGER(InnerAbilityTab.COMBAT_SPELL, 1142, 80, 71, 14377, new Rune(RuneType.MIND, 1), new Rune(RuneType.SOUL, 1)),
AIR_SURGE(InnerAbilityTab.COMBAT_SPELL, 1174, 81, 73, 14364, new Rune(RuneType.AIR, 5)),
TELEPORT_BLOCK(InnerAbilityTab.COMBAT_SPELL, 1206, 85, 75, 14344, new Rune(RuneType.CHAOS, 1), new Rune(RuneType.LAW, 1), new Rune(RuneType.DEATH, 1)),
WATER_SURGE(InnerAbilityTab.COMBAT_SPELL, 1254, 85, 76, 14365, new Rune(RuneType.AIR, 5), new Rune(RuneType.WATER, 5)),
EARTH_SURGE(InnerAbilityTab.COMBAT_SPELL, 1222, 90, 78, 14366, new Rune(RuneType.AIR, 5), new Rune(RuneType.EARTH, 5)),
FIRE_SURGE(InnerAbilityTab.COMBAT_SPELL, 1286, 95, 80, 14367, new Rune(RuneType.AIR, 5), new Rune(RuneType.FIRE, 5)),
HOUSE_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 582, 40, 36, 14338, new Rune(RuneType.AIR, 1), new Rune(RuneType.EARTH, 1), new Rune(RuneType.LAW, 1)),
CAMELOT_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 630, 45, 39, 14339, new Rune(RuneType.AIR, 5), new Rune(RuneType.LAW, 1)),
ARDOUGNE_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 726, 51, 45, 14340, new Rune(RuneType.WATER, 2), new Rune(RuneType.LAW, 2)),
WATCHTOWER_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 806, 58, 50, 14341, new Rune(RuneType.EARTH, 2), new Rune(RuneType.LAW, 2)),
APE_ATOLL_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 966, 64, 60, 14343, new Rune(RuneType.WATER, 2), new Rune(RuneType.FIRE, 2), new Rune(RuneType.LAW, 2)),
TELESKILLING_LUMBRIDGE(InnerAbilityTab.TELEPORT_SPELL, 1078, 74, 67, 14345, new Rune(RuneType.EARTH, 1), new Rune(RuneType.LAW, 1), new Rune(RuneType.SOUL, 1)),
TELESKILLING_FALADOR(InnerAbilityTab.TELEPORT_SPELL, 1190, 82, 74, 14346, new Rune(RuneType.WATER, 1), new Rune(RuneType.LAW, 1), new Rune(RuneType.SOUL, 1)),
TELESKILLING_CAMELOT(InnerAbilityTab.TELEPORT_SPELL, 1270, 90, 79, 14347, new Rune(RuneType.LAW, 1), new Rune(RuneType.SOUL, 2)),
CHARGE_WATER_ORB(InnerAbilityTab.SKILLING_SPELL, 774, 56, 48, 14374, new Rune(RuneType.WATER, 30), new Rune(RuneType.COSMIC, 3)),
CHARGE_EARTH_ORB(InnerAbilityTab.SKILLING_SPELL, 838, 60, 52, 14375, new Rune(RuneType.EARTH, 30), new Rune(RuneType.COSMIC, 3)),
BONES_TO_PEACHES(InnerAbilityTab.SKILLING_SPELL, 854, 60, 53, 14381, new Rune(RuneType.WATER, 4), new Rune(RuneType.EARTH, 4), new Rune(RuneType.NATURE, 2)),
CHARGE_FIRE_ORB(InnerAbilityTab.SKILLING_SPELL, 950, 63, 59, 14376, new Rune(RuneType.FIRE, 30), new Rune(RuneType.COSMIC, 3)),
CHARGE_AIR_ORB(InnerAbilityTab.SKILLING_SPELL, 998, 66, 62, 14373, new Rune(RuneType.AIR, 30), new Rune(RuneType.COSMIC, 3)),
LVL5_ENCHANT(InnerAbilityTab.SKILLING_SPELL, 1030, 68, 64, 14386, new Rune(RuneType.WATER, 15), new Rune(RuneType.EARTH, 15), new Rune(RuneType.COSMIC, 1)),
LVL6_ENCHANT(InnerAbilityTab.SKILLING_SPELL, 1238, 87, 77, 14387, new Rune(RuneType.EARTH, 20), new Rune(RuneType.FIRE, 20), new Rune(RuneType.COSMIC, 1)),
ENCHANT_CROSSBOW_BOLT(InnerAbilityTab.SKILLING_SPELL, 2502, 4, 156, 14370),
;

	private StandardSpell(Window t, int id, int l, int ci, int ct, Rune... r) {
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

