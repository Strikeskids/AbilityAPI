package com.sk.methods.action.ability;

import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.Component;

import com.sk.SkMethodContext;
import com.sk.methods.action.structure.Ability;
import com.sk.windows.InnerAbilityTab;
import com.sk.windows.Window;

public enum StrengthAbility implements Ability {
	KICK(AbilityLevel.BASIC, 2, 14256, 3, 34, 15, 0),
PUNISH(AbilityLevel.BASIC, 3, 14257, 8, 50, 5, 0),
DISMEMBER(AbilityLevel.BASIC, 1, 14255, 14, 18, 30, 0),
FURY(AbilityLevel.BASIC, 4, 14258, 24, 66, 20, 6000),
DESTROY(AbilityLevel.THRESHOLD, 9, 14263, 37, 146, 20, 6000),
QUAKE(AbilityLevel.THRESHOLD, 8, 14262, 37, 130, 20, 0),
BERSERK(AbilityLevel.ULTIMATE, 10, 14264, 42, 162, 60, 0),
CLEAVE(AbilityLevel.BASIC, 6, 14260, 48, 98, 10, 0),
ASSAULT(AbilityLevel.THRESHOLD, 7, 14261, 55, 114, 30, 6000),
DECIMATE(AbilityLevel.BASIC, 5, 14259, 67, 82, 10, 0),
PULVERISE(AbilityLevel.ULTIMATE, 12, 14266, 81, 194, 60, 0),
FRENZY(AbilityLevel.ULTIMATE, 11, 14265, 86, 178, 60, 10000),
;

	private StrengthAbility(AbilityLevel level, int a, int b, int c, int id, int cd, int ch) {
		this.abilityLevel = level;
		this.childIndex = a;
		this.childTexture = b;
		this.skillLevel = c;
		this.id = id;
		this.cooldown = cd;
		this.channeled = ch;
		this.skill = Skills.STRENGTH;
		this.window = InnerAbilityTab.STRENGTH_ABILITY;
		this.style = AbilityStyle.STRENGTH;

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

