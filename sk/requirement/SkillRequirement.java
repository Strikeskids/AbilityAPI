package sk.requirement;

import org.powerbot.game.api.methods.tab.Skills;


public class SkillRequirement implements Requirement {

	private final int skill, level;

	public SkillRequirement(int skill, int level) {
		this.skill = skill;
		this.level = level;
	}

	@Override
	public boolean meets() {
		return Skills.getRealLevel(skill) >= level;
	}

}
