package sk.action.ability;

import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.BookUtil;
import sk.tab.InnerAbilityTabs;
import sk.tab.Tab;

public enum StrengthAbility implements Ability {
	KICK(AbilityType.BASIC, 3, 2, 14256, 34, 15),

	PUNISH(AbilityType.BASIC, 8, 3, 14257, 50, 5),

	DISMEMBER(AbilityType.BASIC, 14, 1, 14255, 18, 30),

	FURY(AbilityType.BASIC, 24, 4, 14258, 66, 20, 6000),

	DESTROY(AbilityType.THRESHOLD, 37, 9, 14263, 146, 20, 6000),

	QUAKE(AbilityType.THRESHOLD, 37, 8, 14262, 130, 20),

	BERSERK(AbilityType.ULTIMATE, 42, 10, 14264, 162, 60),

	CLEAVE(AbilityType.BASIC, 48, 6, 14260, 98, 10),

	ASSAULT(AbilityType.THRESHOLD, 55, 7, 14261, 114, 30, 6000),

	DECIMATE(AbilityType.BASIC, 67, 5, 14259, 82, 10),

	PULVERISE(AbilityType.ULTIMATE, 81, 12, 14266, 194, 60),

	FRENZY(AbilityType.ULTIMATE, 86, 11, 14265, 178, 60, 10000);

	@Override
	public Tab getTab() {
		return InnerAbilityTabs.STRENGTH_TAB;
	}

	@Override
	public int getSkill() {
		return Skills.STRENGTH;
	}
	
	@Override
	public AbilityStyle getStyle() {
		return AbilityStyle.STRENGTH;
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

	private StrengthAbility(AbilityType type, int level, int childId, int childTexture, int abilityId, int cooldown) {
		this.type = type;
		this.level = level;
		this.childId = childId;
		this.childTexture = childTexture;
		this.abilityId = abilityId;
		this.cooldown = cooldown;
		this.comboLength = 0;
	}

	private StrengthAbility(AbilityType type, int level, int childId, int childTexture, int abilityId, int cooldown,
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
