package com.sk.methods.action.ability;

import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.Component;

import com.sk.SkMethodContext;
import com.sk.methods.action.structure.Ability;
import com.sk.windows.InnerAbilityTab;
import com.sk.windows.Window;

public enum RangedAbility implements Ability {
	PIERCING_SHOT(AbilityLevel.BASIC, 1, 14243, 1, 21, 5, 0),
	SNAP_SHOT(AbilityLevel.THRESHOLD, 7, 14249, 2, 117, 20, 0),
	DEADSHOT(AbilityLevel.ULTIMATE, 12, 14254, 3, 197, 30, 0),
	SNIPE(AbilityLevel.BASIC, 4, 14246, 7, 69, 10, 3000),
	BINDING_SHOT(AbilityLevel.BASIC, 2, 14244, 15, 37, 15, 0),
	FRAGMENTATION_SHOT(AbilityLevel.BASIC, 5, 14247, 25, 85, 30, 0),
	ESCAPE(AbilityLevel.BASIC, 3, 14245, 34, 53, 20, 0),
	RAPID_FIRE(AbilityLevel.THRESHOLD, 8, 14250, 37, 133, 20, 6000),
	RICOCHET(AbilityLevel.BASIC, 6, 14248, 45, 101, 10, 0),
	BOMBARDMENT(AbilityLevel.THRESHOLD, 9, 14251, 55, 149, 30, 0),
	INCENDIARY_SHOT(AbilityLevel.ULTIMATE, 10, 14252, 66, 165, 60, 0),
	UNLOAD(AbilityLevel.ULTIMATE, 11, 14253, 81, 181, 60, 6000),
	DAZING_SHOT(AbilityLevel.BASIC, 15, 9316, 8, 245, 5, 0),
	NEEDLE_STRIKE(AbilityLevel.BASIC, 16, 9315, 12, 261, 5, 0), ;

	private RangedAbility(AbilityLevel level, int a, int b, int c, int id, int cd, int ch) {
		this.abilityLevel = level;
		this.childIndex = a;
		this.childTexture = b;
		this.skillLevel = c;
		this.id = id;
		this.cooldown = cd;
		this.channeled = ch;
		this.skill = Skills.RANGE;
		this.window = InnerAbilityTab.RANGED_ABILITY;
		this.style = AbilityStyle.RANGED;

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
