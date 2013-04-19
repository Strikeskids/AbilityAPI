package sk.action.ability;

import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.BookUtil;
import sk.tab.InnerAbilityTabs;
import sk.tab.Tab;

public enum RangedAbility implements Ability {
	PIERCING_SHOT(AbilityType.BASIC, 1, 1, 14243, 21, 5),

	SNAP_SHOT(AbilityType.THRESHOLD, 2, 7, 14249, 117, 20),

	DEADSHOT(AbilityType.ULTIMATE, 3, 12, 14254, 197, 30),

	SNIPE(AbilityType.BASIC, 7, 4, 14246, 69, 10, 3000),

	BINDING_SHOT(AbilityType.BASIC, 15, 2, 14244, 37, 15),

	FRAGMENTATION_SHOT(AbilityType.BASIC, 25, 5, 14247, 85, 30),

	ESCAPE(AbilityType.BASIC, 34, 3, 14245, 53, 20),

	RAPID_FIRE(AbilityType.THRESHOLD, 37, 8, 14250, 133, 20, 6000),

	RICOCHET(AbilityType.BASIC, 45, 6, 14248, 101, 10),

	BOMBARDMENT(AbilityType.THRESHOLD, 55, 9, 14251, 149, 30),

	INCENDIARY_SHOT(AbilityType.ULTIMATE, 66, 10, 14252, 165, 60),

	UNLOAD(AbilityType.ULTIMATE, 81, 11, 14253, 181, 60, 6000),
	
	DAZING_SHOT(AbilityType.BASIC, 8, 15, 9316, 245, 5),
	
	NEEDLE_STRIKE(AbilityType.BASIC, 12, 16, 9315, 261, 5),
	;

	@Override
	public Tab getTab() {
		return InnerAbilityTabs.RANGE_TAB;
	}

	@Override
	public int getSkill() {
		return Skills.RANGE;
	}
	
	@Override
	public AbilityStyle getStyle() {
		return AbilityStyle.RANGED;
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

	private RangedAbility(AbilityType type, int level, int childId, int childTexture, int abilityId, int cooldown) {
		this.type = type;
		this.level = level;
		this.childId = childId;
		this.childTexture = childTexture;
		this.abilityId = abilityId;
		this.cooldown = cooldown;
		this.comboLength = 0;
	}

	private RangedAbility(AbilityType type, int level, int childId, int childTexture, int abilityId, int cooldown,
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
