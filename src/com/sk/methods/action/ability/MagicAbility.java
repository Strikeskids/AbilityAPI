package com.sk.methods.action.ability;

import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.Component;

import com.sk.SkMethodContext;
import com.sk.methods.action.structure.Ability;
import com.sk.windows.InnerAbilityTab;
import com.sk.windows.Window;

public enum MagicAbility implements Ability {
	WRACK(AbilityLevel.BASIC, 1, 14231, 1, 22, 5, 0),
ASPHYXIATE(AbilityLevel.THRESHOLD, 7, 14237, 2, 118, 20, 6000),
OMNIPOWER(AbilityLevel.ULTIMATE, 12, 14242, 3, 198, 30, 0),
DRAGON_BREATH(AbilityLevel.BASIC, 6, 14236, 7, 102, 10, 0),
IMPACT(AbilityLevel.BASIC, 3, 14234, 15, 54, 15, 0),
COMBUST(AbilityLevel.BASIC, 5, 14235, 25, 86, 30, 0),
SURGE(AbilityLevel.BASIC, 2, 14233, 34, 38, 20, 0),
DETONATE(AbilityLevel.THRESHOLD, 8, 14238, 37, 134, 30, 0),
CHAIN(AbilityLevel.BASIC, 4, 14232, 45, 70, 10, 0),
WILD_MAGIC(AbilityLevel.THRESHOLD, 9, 14239, 55, 150, 20, 0),
METAMORPHOSIS(AbilityLevel.ULTIMATE, 10, 14241, 66, 166, 60, 0),
TSUNAMI(AbilityLevel.ULTIMATE, 11, 14240, 81, 182, 60, 0),
SONIC_WAVE(AbilityLevel.BASIC, 165, 9314, 8, 2646, 5, 0),
CONCENTRATED_BLAST(AbilityLevel.BASIC, 166, 8684, 12, 2662, 5, 0),
;

	private MagicAbility(AbilityLevel level, int a, int b, int c, int id, int cd, int ch) {
		this.abilityLevel = level;
		this.childIndex = a;
		this.childTexture = b;
		this.skillLevel = c;
		this.id = id;
		this.cooldown = cd;
		this.channeled = ch;
		this.skill = Skills.MAGIC;
		this.window = InnerAbilityTab.MAGIC_ABILITY;
		this.style = AbilityStyle.MAGIC;

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

