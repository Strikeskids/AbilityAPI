package com.sk.methods.action.ability;

import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.Component;

import com.sk.SkMethodContext;
import com.sk.methods.action.structure.Ability;
import com.sk.windows.InnerAbilityTab;
import com.sk.windows.Window;

public enum AttackAbility implements Ability {
	SLICE(AbilityLevel.BASIC, 1, 14207, 1, 17, 5, 0),
	SLAUGHTER(AbilityLevel.THRESHOLD, 7, 14213, 1, 113, 30, 0),
	OVERPOWER(AbilityLevel.ULTIMATE, 10, 14216, 3, 161, 30, 0),
	HAVOC(AbilityLevel.BASIC, 4, 14210, 7, 65, 10, 0),
	BACKHAND(AbilityLevel.BASIC, 6, 14212, 15, 97, 15, 0),
	SMASH(AbilityLevel.BASIC, 5, 14211, 25, 81, 10, 0),
	BARGE(AbilityLevel.BASIC, 2, 14208, 34, 33, 20, 0),
	FLURRY(AbilityLevel.THRESHOLD, 8, 14214, 37, 129, 20, 6000),
	SEVER(AbilityLevel.BASIC, 3, 14209, 45, 49, 30, 0),
	HURRICANE(AbilityLevel.THRESHOLD, 9, 14215, 55, 145, 20, 0),
	MASSACRE(AbilityLevel.ULTIMATE, 11, 14217, 66, 177, 60, 0),
	METEOR_STRIKE(AbilityLevel.ULTIMATE, 12, 14218, 81, 193, 60, 0), ;

	private AttackAbility(AbilityLevel level, int a, int b, int c, int id, int cd, int ch) {
		this.abilityLevel = level;
		this.childIndex = a;
		this.childTexture = b;
		this.skillLevel = c;
		this.id = id;
		this.cooldown = cd;
		this.channeled = ch;
		this.skill = Skills.ATTACK;
		this.window = InnerAbilityTab.ATTACK_ABILITY;
		this.style = AbilityStyle.ATTACK;

		
		
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
