package sk.action.book;

import org.powerbot.game.api.methods.tab.Skills;

import sk.tab.InnerAbilityTabs;
import sk.tab.Tab;

/**
 * An enum of styles of ability. Corresponds to the skill requirement to use the ability
 * 
 * @author Strikeskids
 * 
 */
public enum AbilityStyle {
	ATTACK(InnerAbilityTabs.ATTACK_TAB, Skills.ATTACK),
	STRENGTH(InnerAbilityTabs.STRENGTH_TAB, Skills.STRENGTH),
	RANGED(InnerAbilityTabs.RANGE_TAB, Skills.RANGE),
	MAGIC(InnerAbilityTabs.MAGIC_ABILITY_TAB, Skills.MAGIC),
	DEFENCE(InnerAbilityTabs.DEFENCE_TAB, Skills.DEFENSE),
	CONSTITUTION(InnerAbilityTabs.CONSTITUTION_TAB, Skills.CONSTITUTION);

	private final InnerAbilityTabs tab;
	private final int skillId;

	private AbilityStyle(InnerAbilityTabs t, int sid) {
		this.tab = t;
		this.skillId = sid;
	}

	/**
	 * Gets {@link InnerAbilityTabs} of abilities that is used for this style
	 * 
	 * @return the tab of abilities for this style
	 */
	public Tab getTab() {
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
}
