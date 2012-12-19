package sk.action.book;

import org.powerbot.game.api.methods.tab.Skills;

import sk.tab.InnerAbilityTabs;
import sk.tab.Tab;

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

	public Tab getTab() {
		return tab;
	}

	public int getSkillId() {
		return skillId;
	}
}
