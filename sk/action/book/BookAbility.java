package sk.action.book;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.Ability;
import sk.action.ActionBar;
import sk.general.Completion;
import sk.tab.Tab;

/**
 * An enum of all abilities found in the ability book that are not spells
 * 
 * @author Strikeskids
 * 
 */
public enum BookAbility implements Ability {

	// style type level child booktexture bartexture cooldown
	SLICE(AbilityStyle.ATTACK, AbilityType.BASIC, 1, 1, 14207, 17, 5),

	SLAUGHTER(AbilityStyle.ATTACK, AbilityType.THRESHOLD, 1, 7, 14213, 113, 30),

	OVERPOWER(AbilityStyle.ATTACK, AbilityType.ULTIMATE, 3, 10, 14216, 161, 30),

	HAVOC(AbilityStyle.ATTACK, AbilityType.BASIC, 7, 4, 14210, 65, 10),

	BACKHAND(AbilityStyle.ATTACK, AbilityType.BASIC, 15, 6, 14212, 97, 15),

	SMASH(AbilityStyle.ATTACK, AbilityType.BASIC, 25, 5, 14211, 81, 10),

	BARGE(AbilityStyle.ATTACK, AbilityType.BASIC, 34, 2, 14208, 33, 20),

	FLURRY(AbilityStyle.ATTACK, AbilityType.THRESHOLD, 37, 8, 14214, 129, 20),

	SEVER(AbilityStyle.ATTACK, AbilityType.BASIC, 45, 3, 14209, 49, 30),

	HURRICANE(AbilityStyle.ATTACK, AbilityType.THRESHOLD, 55, 9, 14215, 145, 20),

	MASSACRE(AbilityStyle.ATTACK, AbilityType.ULTIMATE, 66, 11, 14217, 177, 60),

	METEOR_STRIKE(AbilityStyle.ATTACK, AbilityType.ULTIMATE, 81, 12, 14218, 193, 60),

	KICK(AbilityStyle.STRENGTH, AbilityType.BASIC, 3, 2, 14256, 34, 15),

	PUNISH(AbilityStyle.STRENGTH, AbilityType.BASIC, 8, 3, 14257, 50, 5),

	DISMEMBER(AbilityStyle.STRENGTH, AbilityType.BASIC, 14, 1, 14255, 18, 30),

	FURY(AbilityStyle.STRENGTH, AbilityType.BASIC, 24, 4, 14258, 66, 20),

	DESTROY(AbilityStyle.STRENGTH, AbilityType.THRESHOLD, 37, 9, 14263, 146, 20),

	QUAKE(AbilityStyle.STRENGTH, AbilityType.THRESHOLD, 37, 8, 14262, 130, 20),

	BERSERK(AbilityStyle.STRENGTH, AbilityType.ULTIMATE, 42, 10, 14264, 162, 60),

	CLEAVE(AbilityStyle.STRENGTH, AbilityType.BASIC, 48, 6, 14260, 98, 10),

	ASSAULT(AbilityStyle.STRENGTH, AbilityType.THRESHOLD, 55, 7, 14261, 114, 30),

	DECIMATE(AbilityStyle.STRENGTH, AbilityType.BASIC, 67, 5, 14259, 82, 10),

	PULVERISE(AbilityStyle.STRENGTH, AbilityType.ULTIMATE, 81, 12, 14266, 194, 60),

	FRENZY(AbilityStyle.STRENGTH, AbilityType.ULTIMATE, 86, 11, 14265, 178, 60),

	ANTICIPATION(AbilityStyle.DEFENCE, AbilityType.BASIC, 3, 1, 14219, 19, 25),

	BASH(AbilityStyle.DEFENCE, AbilityType.BASIC, 8, 6, 14224, 99, 15),

	REVENGE(AbilityStyle.DEFENCE, AbilityType.THRESHOLD, 15, 9, 14227, 147, 20),

	PROVOKE(AbilityStyle.DEFENCE, AbilityType.BASIC, 24, 3, 14221, 51, 10),

	IMMORTALITY(AbilityStyle.DEFENCE, AbilityType.ULTIMATE, 29, 12, 14230, 195, 120),

	FREEDOM(AbilityStyle.DEFENCE, AbilityType.BASIC, 34, 2, 14220, 35, 30),

	REFLECT(AbilityStyle.DEFENCE, AbilityType.THRESHOLD, 34, 7, 14225, 115, 15),

	RESONANCE(AbilityStyle.DEFENCE, AbilityType.BASIC, 48, 4, 14222, 67, 30),

	REJUVENATE(AbilityStyle.DEFENCE, AbilityType.ULTIMATE, 52, 11, 14229, 179, 60),

	DEBILITATE(AbilityStyle.DEFENCE, AbilityType.THRESHOLD, 55, 8, 14226, 131, 30),

	PREPARATION(AbilityStyle.DEFENCE, AbilityType.BASIC, 67, 5, 14223, 83, 5),

	BARRICADE(AbilityStyle.DEFENCE, AbilityType.ULTIMATE, 81, 10, 14228, 163, 60),

	MOMENTUM(AbilityStyle.CONSTITUTION, AbilityType.ULTIMATE, 10, 7, 14674, 116, 0),

	REGENERATE(AbilityStyle.CONSTITUTION, AbilityType.BASIC, 10, 1, 14267, 20, 0),

	INCITE(AbilityStyle.CONSTITUTION, AbilityType.BASIC, 24, 2, 14268, 36, 0),

	SINGLE_WAY_WILDERNESS(AbilityStyle.CONSTITUTION, AbilityType.BASIC, 25, 8, 14269, 132, 10),

	PIERCING_SHOT(AbilityStyle.RANGED, AbilityType.BASIC, 1, 1, 14243, 21, 5),

	SNAP_SHOT(AbilityStyle.RANGED, AbilityType.THRESHOLD, 1, 7, 14249, 117, 20),

	DEADSHOT(AbilityStyle.RANGED, AbilityType.ULTIMATE, 2, 12, 14254, 197, 30),

	SNIPE(AbilityStyle.RANGED, AbilityType.BASIC, 7, 4, 14246, 69, 10),

	BINDING_SHOT(AbilityStyle.RANGED, AbilityType.BASIC, 15, 2, 14244, 37, 15),

	FRAGMENTATION_SHOT(AbilityStyle.RANGED, AbilityType.BASIC, 25, 5, 14247, 85, 30),

	ESCAPE(AbilityStyle.RANGED, AbilityType.BASIC, 34, 3, 14245, 53, 20),

	RAPID_FIRE(AbilityStyle.RANGED, AbilityType.THRESHOLD, 37, 8, 14250, 133, 20),

	RICOCHET(AbilityStyle.RANGED, AbilityType.BASIC, 45, 6, 14248, 101, 10),

	BOMBARDMENT(AbilityStyle.RANGED, AbilityType.THRESHOLD, 55, 9, 14251, 149, 30),

	INCENDIARY_SHOT(AbilityStyle.RANGED, AbilityType.ULTIMATE, 66, 10, 14252, 165, 60),

	UNLOAD(AbilityStyle.RANGED, AbilityType.ULTIMATE, 81, 11, 14253, 181, 60),

	ASPHYXIATE(AbilityStyle.MAGIC, AbilityType.THRESHOLD, 1, 7, 14237, 118, 20),

	WRACK(AbilityStyle.MAGIC, AbilityType.BASIC, 1, 1, 14231, 22, 5),

	OMNIPOWER(AbilityStyle.MAGIC, AbilityType.ULTIMATE, 2, 12, 14242, 198, 30),

	DRAGON_BREATH(AbilityStyle.MAGIC, AbilityType.BASIC, 7, 6, 14236, 102, 10),

	IMPACT(AbilityStyle.MAGIC, AbilityType.BASIC, 15, 3, 14234, 54, 15),

	COMBUST(AbilityStyle.MAGIC, AbilityType.BASIC, 25, 5, 14235, 86, 30),

	SURGE(AbilityStyle.MAGIC, AbilityType.BASIC, 34, 2, 14233, 38, 20),

	DETONATE(AbilityStyle.MAGIC, AbilityType.THRESHOLD, 37, 8, 14238, 134, 30),

	CHAIN(AbilityStyle.MAGIC, AbilityType.BASIC, 45, 4, 14232, 70, 10),

	WILD_MAGIC(AbilityStyle.MAGIC, AbilityType.THRESHOLD, 55, 9, 14239, 150, 20),

	METAMORPHOSIS(AbilityStyle.MAGIC, AbilityType.ULTIMATE, 66, 10, 14241, 166, 60),

	TSUNAMI(AbilityStyle.MAGIC, AbilityType.ULTIMATE, 81, 11, 14240, 182, 60);

	private final AbilityStyle style;
	private final AbilityType type;
	private final int barTexture;
	private final int bookTexture;
	private final int bookChild;
	private final int cooldown;
	private final int level;

	// ATTACK BASIC 1 0 1000 1001 3
	private BookAbility(AbilityStyle s, AbilityType t, int level, int child, int book, int bar, int cool) {
		this.style = s;
		this.type = t;
		this.level = level;
		this.bookChild = child;
		this.bookTexture = book;
		this.barTexture = bar;
		this.cooldown = cool;
		Ability.ALL_ABILITIES.put(this.barTexture, this);
	}

	/**
	 * Gets the cooldown time in seconds for this ability
	 * 
	 * @return the cooldown time
	 */
	public int getCooldown() {
		return cooldown;
	}

	/**
	 * Gets the texture of the ability in the ability book
	 * 
	 * @return the texture id of the widget
	 */
	public int getTexture() {
		return bookTexture;
	}

	/**
	 * Gets the {@link AbilityType} of this ability (basic, threshold, ultimate)
	 * 
	 * @return the type of this ability
	 */
	public AbilityType getType() {
		return type;
	}

	/**
	 * Gets the {@link AbilityStyle} of this ability
	 * 
	 * @return the style of this ability
	 */
	public AbilityStyle getStyle() {
		return style;
	}

	/**
	 * Gets the required level for this ability in the style specified by the
	 * ability style
	 * 
	 * @return the required level
	 * @see BookAbility#getStyle()
	 */
	public int getRequiredLevel() {
		return level;
	}

	/**
	 * Gets the reload child for this ability in the ability book
	 * 
	 * @return the reload child in the ability book
	 * @see BookAbility#available()
	 * @see ActionBar#getReloadChild(int)
	 */
	public WidgetChild getReloadChild() {
		if (getChild() == null)
			return null;
		WidgetChild rlist = Widgets.get(BOOK_WIDGET, RELOAD_LIST);
		return rlist == null ? null : rlist.getChild(bookChild);
	}

	@Override
	public boolean show() {
		return getTab().open() && isVisible();
	}

	@Override
	public boolean isVisible() {
		WidgetChild wc;
		return getTab().isOpen() && (wc = getChild()) != null && wc.visible();
	}

	@Override
	public boolean available() {
		int curLevel = Skills.getLevel(style.getSkillId());
		return (curLevel >= level || curLevel == 0) && ActionBar.getAdrenaline() >= type.getAdrenalineRequirement();
	}

	public Tab getTab() {
		return style.getTab();
	}

	@Override
	public WidgetChild getChild() {
		WidgetChild group = Widgets.get(BOOK_WIDGET, CLICK_LIST);
		if (group == null)
			return null;
		WidgetChild c = group.getChild(bookChild);
		if (c == null || c.getTextureId() != bookTexture)
			return null;
		return c;
	}

	@Override
	public Completion getChange() {
		return null;
	}

	@Override
	public int getAbilityId() {
		return barTexture;
	}

	private static final int BOOK_WIDGET = 275, CLICK_LIST = 16, RELOAD_LIST = 17;
}
