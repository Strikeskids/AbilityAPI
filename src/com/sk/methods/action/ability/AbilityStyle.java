package com.sk.methods.action.ability;

import org.powerbot.script.methods.Skills;

import com.sk.windows.InnerAbilityTab;
import com.sk.windows.Window;

/**
 * An enum of styles of ability. Corresponds to the skill requirement to use the ability
 * 
 * @author Strikeskids
 */
public enum AbilityStyle {
	ATTACK(InnerAbilityTab.ATTACK_ABILITY, Skills.ATTACK),
	STRENGTH(InnerAbilityTab.STRENGTH_ABILITY, Skills.STRENGTH),
	RANGED(InnerAbilityTab.RANGED_ABILITY, Skills.RANGE),
	MAGIC(InnerAbilityTab.MAGIC_ABILITY, Skills.MAGIC),
	DEFENCE(InnerAbilityTab.DEFENCE_ABILITY, Skills.DEFENSE),
	CONSTITUTION(InnerAbilityTab.CONSTITUTION_ABILITY, Skills.CONSTITUTION),
	NONE(null, -1);

	private final InnerAbilityTab tab;
	private final int skillId;
	private final int widgetId;

	private AbilityStyle(final InnerAbilityTab t, final int sid) {
		this.tab = t;
		this.skillId = sid;
		if (t == null)
			this.widgetId = 0;
		else
			this.widgetId = t.getSuperWindow().getSource().getWidget();
	}

	/**
	 * Gets {@link InnerAbilityTab} of abilities that is used for this style
	 * 
	 * @return the tab of abilities for this style
	 */
	public Window getTab() {
		return tab;
	}

	/**
	 * Gets the skill id for the skill for this style of ability
	 * 
	 * @return the skill id
	 */
	public int getSkillId() {
		return skillId;
	}

	public int getWidgetId() {
		return widgetId;
	}
}
