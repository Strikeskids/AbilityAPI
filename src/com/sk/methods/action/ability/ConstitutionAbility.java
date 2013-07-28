package com.sk.methods.action.ability;

import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.Component;

import com.sk.SkMethodContext;
import com.sk.methods.action.structure.Ability;
import com.sk.windows.InnerAbilityTab;
import com.sk.windows.Window;

public enum ConstitutionAbility implements Ability {
	MOMENTUM(AbilityLevel.ULTIMATE, 7, 14674, 10, 116, 0, 0),
REGENERATE(AbilityLevel.BASIC, 1, 14267, 10, 20, 0, 0),
INCITE(AbilityLevel.BASIC, 2, 14268, 24, 36, 0, 0),
SINGLE_WAY_WILDERNESS(AbilityLevel.BASIC, 8, 14269, 25, 132, 10, 0),
;

	private ConstitutionAbility(AbilityLevel level, int a, int b, int c, int id, int cd, int ch) {
		this.abilityLevel = level;
		this.childIndex = a;
		this.childTexture = b;
		this.skillLevel = c;
		this.id = id;
		this.cooldown = cd;
		this.channeled = ch;
		this.skill = Skills.CONSTITUTION;
		this.window = InnerAbilityTab.CONSTITUTION_ABILITY;
		this.style = AbilityStyle.CONSTITUTION;

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

