package sk.action.ability;

import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.BookUtil;
import sk.tab.InnerAbilityTabs;
import sk.tab.Tab;

public enum AttackAbility implements Ability {
	SLICE(AbilityType.BASIC, 1, 1, 14207, 17, 5),

	SLAUGHTER(AbilityType.THRESHOLD, 1, 7, 14213, 113, 30),

	OVERPOWER(AbilityType.ULTIMATE, 3, 10, 14216, 161, 30),

	HAVOC(AbilityType.BASIC, 7, 4, 14210, 65, 10),

	BACKHAND(AbilityType.BASIC, 15, 6, 14212, 97, 15),

	SMASH(AbilityType.BASIC, 25, 5, 14211, 81, 10),

	BARGE(AbilityType.BASIC, 34, 2, 14208, 33, 20),

	FLURRY(AbilityType.THRESHOLD, 37, 8, 14214, 129, 20, 6000),

	SEVER(AbilityType.BASIC, 45, 3, 14209, 49, 30),

	HURRICANE(AbilityType.THRESHOLD, 55, 9, 14215, 145, 20),

	MASSACRE(AbilityType.ULTIMATE, 66, 11, 14217, 177, 60),

	METEOR_STRIKE(AbilityType.ULTIMATE, 81, 12, 14218, 193, 60);

	@Override
	public Tab getTab() {
		return InnerAbilityTabs.ATTACK_TAB;
	}

	@Override
	public int getSkill() {
		return Skills.ATTACK;
	}
	
	@Override
	public AbilityStyle getStyle() {
		return AbilityStyle.ATTACK;
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

	private AttackAbility(AbilityType type, int level, int childId, int childTexture, int abilityId, int cooldown) {
		this.type = type;
		this.level = level;
		this.childId = childId;
		this.childTexture = childTexture;
		this.abilityId = abilityId;
		this.cooldown = cooldown;
		this.comboLength = 0;
	}

	private AttackAbility(AbilityType type, int level, int childId, int childTexture, int abilityId, int cooldown,
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
