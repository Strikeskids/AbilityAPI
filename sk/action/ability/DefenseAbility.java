package sk.action.ability;

import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.BookUtil;
import sk.tab.InnerAbilityTabs;
import sk.tab.Tab;

public enum DefenseAbility implements Ability {
	ANTICIPATION(AbilityType.BASIC, 3, 1, 14219, 19, 25),

	BASH(AbilityType.BASIC, 8, 6, 14224, 99, 15),

	REVENGE(AbilityType.THRESHOLD, 15, 9, 14227, 147, 20),

	PROVOKE(AbilityType.BASIC, 24, 3, 14221, 51, 10),

	IMMORTALITY(AbilityType.ULTIMATE, 29, 12, 14230, 195, 120),

	FREEDOM(AbilityType.BASIC, 34, 2, 14220, 35, 30),

	REFLECT(AbilityType.THRESHOLD, 34, 7, 14225, 115, 15),

	RESONANCE(AbilityType.BASIC, 48, 4, 14222, 67, 30),

	REJUVENATE(AbilityType.ULTIMATE, 52, 11, 14229, 179, 60),

	DEBILITATE(AbilityType.THRESHOLD, 55, 8, 14226, 131, 30),

	PREPARATION(AbilityType.BASIC, 67, 5, 14223, 83, 5),

	BARRICADE(AbilityType.ULTIMATE, 81, 10, 14228, 163, 60);

	@Override
	public Tab getTab() {
		return InnerAbilityTabs.DEFENCE_TAB;
	}

	@Override
	public int getSkill() {
		return Skills.DEFENSE;
	}
	
	@Override
	public AbilityStyle getStyle() {
		return AbilityStyle.DEFENSE;
	}

	@Override
	public WidgetChild getChild() {
		return BookUtil.getChild(this);
	}

	@Override
	public WidgetChild getCooldownChild() {
		return BookUtil.getCooldownChild(this);
	}

	@Override
	public boolean show() {
		return BookUtil.show(this);
	}

	@Override
	public boolean isVisible() {
		return BookUtil.isVisible(this);
	}

	@Override
	public AbilityType getType() {
		return type;
	}

	@Override
	public int getId() {
		return abilityId;
	}

	@Override
	public int getChildId() {
		return childId;
	}

	@Override
	public int getChildTexture() {
		return childTexture;
	}

	@Override
	public int getCooldown() {
		return cooldown;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public int getComboLength() {
		return comboLength;
	}

	private final int childId, childTexture, abilityId, level, cooldown;
	private final AbilityType type;
	private final int comboLength;

	private DefenseAbility(AbilityType type, int level, int childId, int childTexture, int abilityId, int cooldown) {
		this.type = type;
		this.level = level;
		this.childId = childId;
		this.childTexture = childTexture;
		this.abilityId = abilityId;
		this.cooldown = cooldown;
		this.comboLength = 0;
	}

	private DefenseAbility(AbilityType type, int level, int childId, int childTexture, int abilityId, int cooldown,
			int combo) {
		this.type = type;
		this.level = level;
		this.childId = childId;
		this.childTexture = childTexture;
		this.abilityId = abilityId;
		this.cooldown = cooldown;
		this.comboLength = combo;
	}

}
