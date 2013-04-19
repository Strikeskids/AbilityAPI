package sk.action.ability;

import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.BookUtil;
import sk.tab.InnerAbilityTabs;
import sk.tab.Tab;

public enum MagicAbility implements Ability {

	WRACK(AbilityType.BASIC, 1, 1, 14231, 22, 5),
	
	ASPHYXIATE(AbilityType.THRESHOLD, 2, 7, 14237, 118, 20, 6000),

	OMNIPOWER(AbilityType.ULTIMATE, 3, 12, 14242, 198, 30),

	DRAGON_BREATH(AbilityType.BASIC, 7, 6, 14236, 102, 10),

	IMPACT(AbilityType.BASIC, 15, 3, 14234, 54, 15),

	COMBUST(AbilityType.BASIC, 25, 5, 14235, 86, 30),

	SURGE(AbilityType.BASIC, 34, 2, 14233, 38, 20),

	DETONATE(AbilityType.THRESHOLD, 37, 8, 14238, 134, 30),

	CHAIN(AbilityType.BASIC, 45, 4, 14232, 70, 10),

	WILD_MAGIC(AbilityType.THRESHOLD, 55, 9, 14239, 150, 20),

	METAMORPHOSIS(AbilityType.ULTIMATE, 66, 10, 14241, 166, 60),

	TSUNAMI(AbilityType.ULTIMATE, 81, 11, 14240, 182, 60),
	
	SONIC_WAVE(AbilityType.BASIC, 8, 165, 9314, 2646, 5),
	
	CONCENTRATED_BLAST(AbilityType.BASIC, 12, 166, 8684, 2662, 5),
	;

	@Override
	public Tab getTab() {
		return InnerAbilityTabs.MAGIC_ABILITY_TAB;
	}

	@Override
	public int getSkill() {
		return Skills.MAGIC;
	}
	
	@Override
	public AbilityStyle getStyle() {
		return AbilityStyle.MAGIC;
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

	private MagicAbility(AbilityType type, int level, int childId, int childTexture, int abilityId, int cooldown) {
		this.type = type;
		this.level = level;
		this.childId = childId;
		this.childTexture = childTexture;
		this.abilityId = abilityId;
		this.cooldown = cooldown;
		this.comboLength = 0;
	}

	private MagicAbility(AbilityType type, int level, int childId, int childTexture, int abilityId, int cooldown,
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
