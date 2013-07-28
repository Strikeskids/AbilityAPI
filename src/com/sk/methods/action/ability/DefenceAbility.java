package com.sk.methods.action.ability;

import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.Component;

import com.sk.SkMethodContext;
import com.sk.methods.action.structure.Ability;
import com.sk.windows.InnerAbilityTab;
import com.sk.windows.Window;

public enum DefenceAbility implements Ability {
	ANTICIPATION(AbilityLevel.BASIC, 1, 14219, 3, 19, 25, 0),
BASH(AbilityLevel.BASIC, 6, 14224, 8, 99, 15, 0),
REVENGE(AbilityLevel.THRESHOLD, 9, 14227, 15, 147, 20, 0),
PROVOKE(AbilityLevel.BASIC, 3, 14221, 24, 51, 10, 0),
IMMORTALITY(AbilityLevel.ULTIMATE, 12, 14230, 29, 195, 120, 0),
FREEDOM(AbilityLevel.BASIC, 2, 14220, 34, 35, 30, 0),
REFLECT(AbilityLevel.THRESHOLD, 7, 14225, 34, 115, 15, 0),
RESONANCE(AbilityLevel.BASIC, 4, 14222, 48, 67, 30, 0),
REJUVENATE(AbilityLevel.ULTIMATE, 11, 14229, 52, 179, 60, 0),
DEBILITATE(AbilityLevel.THRESHOLD, 8, 14226, 55, 131, 30, 0),
PREPARATION(AbilityLevel.BASIC, 5, 14223, 67, 83, 5, 0),
BARRICADE(AbilityLevel.ULTIMATE, 10, 14228, 81, 163, 60, 0),
;

	private DefenceAbility(AbilityLevel level, int a, int b, int c, int id, int cd, int ch) {
		this.abilityLevel = level;
		this.childIndex = a;
		this.childTexture = b;
		this.skillLevel = c;
		this.id = id;
		this.cooldown = cd;
		this.channeled = ch;
		this.skill = Skills.DEFENSE;
		this.window = InnerAbilityTab.DEFENCE_ABILITY;
		this.style = AbilityStyle.DEFENCE;

	}

	private final int childIndex, childTexture, skillLevel, skill, id, cooldown, channeled;
	private final Window window;
	private final AbilityStyle style;
	private final AbilityLevel abilityLevel;

	@Override
	public int getChildIndex() {
		return childIndex;
	}

	@Override
	public int getChildTexture() {
		return childTexture;
	}

	@Override
	public Window getWindow() {
		return window;
	}

	@Override
	public int getSkillLevel() {
		return skillLevel;
	}

	@Override
	public int getSkill() {
		return skill;
	}

	@Override
	public Component getComponent(SkMethodContext ctx) {
		return ctx.widgets.get(getWidget(), MAIN_COMPONENT).getChild(childIndex);
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
	public AbilityStyle getStyle() {
		return style;
	}

	@Override
	public AbilityLevel getAbilityLevel() {
		return abilityLevel;
	}

	@Override
	public int getCooldown() {
		return cooldown;
	}

	@Override
	public int getChanneled() {
		return channeled;
	}

	@Override
	public Component getCooldownComponent(SkMethodContext ctx) {
		return ctx.widgets.get(getWidget(), COOLDOWN_COMPONENT).getChild(childIndex);
	}

	@Override
	public int getWidget() {
		return getStyle().getWidgetId();
	}
}

