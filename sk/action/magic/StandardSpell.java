package sk.action.magic;

import java.util.Arrays;
import java.util.Comparator;

import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.BookUtil;
import sk.item.DefinedItem;
import sk.item.RequiredGroup;
import sk.tab.InnerAbilityTabs;
import sk.tab.Tab;

public enum StandardSpell implements Spell {
	SNARE(InnerAbilityTabs.COMBAT_SPELL_TAB, 43, 14393, 694, 50, new Rune(RuneType.NATURE, 2)),

	SLAYER_DART(InnerAbilityTabs.COMBAT_SPELL_TAB, 44, 14388, 710, 50, new Rune(RuneType.AIR, 3), new Rune(
			RuneType.DEATH, 1)),

	DIVINE_STORM(InnerAbilityTabs.COMBAT_SPELL_TAB, 54, 14369, 870, 60, new Rune(RuneType.AIR, 5)),

	AIR_WAVE(InnerAbilityTabs.COMBAT_SPELL_TAB, 58, 14360, 934, 62, new Rune(RuneType.AIR, 4)),

	WATER_WAVE(InnerAbilityTabs.COMBAT_SPELL_TAB, 61, 14361, 982, 65, new Rune(RuneType.AIR, 4), new Rune(
			RuneType.WATER, 4)),

	VULNERABILITY(InnerAbilityTabs.COMBAT_SPELL_TAB, 63, 14395, 1014, 66, new Rune(RuneType.CHAOS, 1), new Rune(
			RuneType.SOUL, 1)),

	EARTH_WAVE(InnerAbilityTabs.COMBAT_SPELL_TAB, 65, 14362, 1046, 70, new Rune(RuneType.AIR, 4), new Rune(
			RuneType.EARTH, 4)),

	ENFEEBLE(InnerAbilityTabs.COMBAT_SPELL_TAB, 66, 14371, 1062, 73, new Rune(RuneType.BODY, 1), new Rune(
			RuneType.SOUL, 1)),

	FIRE_WAVE(InnerAbilityTabs.COMBAT_SPELL_TAB, 68, 14363, 1094, 75, new Rune(RuneType.AIR, 4), new Rune(
			RuneType.FIRE, 4)),

	STORM_OF_ARMADYL(InnerAbilityTabs.COMBAT_SPELL_TAB, 69, 14369, 1110, 77, new Rune(RuneType.ARMADYL, 1)),

	ENTANGLE(InnerAbilityTabs.COMBAT_SPELL_TAB, 70, 14394, 1126, 79, new Rune(RuneType.NATURE, 3)),

	STAGGER(InnerAbilityTabs.COMBAT_SPELL_TAB, 71, 14377, 1142, 80, new Rune(RuneType.MIND, 1), new Rune(
			RuneType.SOUL, 1)),

	AIR_SURGE(InnerAbilityTabs.COMBAT_SPELL_TAB, 73, 14364, 1174, 81, new Rune(RuneType.AIR, 5)),

	TELEPORT_BLOCK(InnerAbilityTabs.COMBAT_SPELL_TAB, 75, 14344, 1206, 85, new Rune(RuneType.CHAOS, 1), new Rune(
			RuneType.LAW, 1), new Rune(RuneType.DEATH, 1)),

	WATER_SURGE(InnerAbilityTabs.COMBAT_SPELL_TAB, 76, 14365, 1254, 85, new Rune(RuneType.AIR, 5), new Rune(
			RuneType.WATER, 5)),

	EARTH_SURGE(InnerAbilityTabs.COMBAT_SPELL_TAB, 78, 14366, 1222, 90, new Rune(RuneType.AIR, 5), new Rune(
			RuneType.EARTH, 5)),

	FIRE_SURGE(InnerAbilityTabs.COMBAT_SPELL_TAB, 80, 14367, 1286, 95, new Rune(RuneType.AIR, 5), new Rune(
			RuneType.FIRE, 5)),

	MOBILISING_ARMIES_TELEPORT(
			InnerAbilityTabs.TELEPORT_TAB, 19, 14335, 310, 10, new Rune(RuneType.AIR, 1), new Rune(RuneType.WATER,
					1), new Rune(RuneType.LAW, 1)),

	HOUSE_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 36, 14338, 582, 40, new Rune(RuneType.AIR, 1), new Rune(
			RuneType.EARTH, 1), new Rune(RuneType.LAW, 1)),

	CAMELOT_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 39, 14339, 630, 45, new Rune(RuneType.AIR, 5), new Rune(
			RuneType.LAW, 1)),

	ARDOUGNE_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 45, 14340, 726, 51, new Rune(RuneType.WATER, 2), new Rune(
			RuneType.LAW, 2)),

	WATCHTOWER_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 50, 14341, 806, 58, new Rune(RuneType.EARTH, 2), new Rune(
			RuneType.LAW, 2)),

	TROLLHEIM_TELEPORT(
			InnerAbilityTabs.TELEPORT_TAB, 57, 14342, 918, 61, new Rune(RuneType.FIRE, 2), new Rune(RuneType.LAW,
					2)),

	APE_ATOLL_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 60, 14343, 966, 64, new Rune(RuneType.WATER, 2), new Rune(
			RuneType.FIRE, 2), new Rune(RuneType.LAW, 2)),

	TELEOTHER_LUMBRIDGE(InnerAbilityTabs.TELEPORT_TAB, 67, 14345, 1078, 74, new Rune(RuneType.EARTH, 1), new Rune(
			RuneType.LAW, 1), new Rune(RuneType.SOUL, 1)),

	TELEOTHER_FALADOR(InnerAbilityTabs.TELEPORT_TAB, 74, 14346, 1190, 82, new Rune(RuneType.WATER, 1), new Rune(
			RuneType.LAW, 1), new Rune(RuneType.SOUL, 1)),

	TELEOTHER_CAMELOT(InnerAbilityTabs.TELEPORT_TAB, 79, 14347, 1270, 90, new Rune(RuneType.LAW, 1), new Rune(
			RuneType.SOUL, 2)),

	CHARGE_WATER_ORB(InnerAbilityTabs.OTHER_SPELL_TAB, 48, 14374, 774, 56, new Rune(RuneType.WATER, 30), new Rune(
			RuneType.COSMIC, 3)),

	CHARGE_EARTH_ORB(InnerAbilityTabs.OTHER_SPELL_TAB, 52, 14375, 838, 60, new Rune(RuneType.EARTH, 30), new Rune(
			RuneType.COSMIC, 3)),

	BONES_TO_PEACHES(InnerAbilityTabs.OTHER_SPELL_TAB, 53, 14381, 854, 60, new Rune(RuneType.WATER, 4), new Rune(
			RuneType.EARTH, 4), new Rune(RuneType.NATURE, 2)),

	CHARGE_FIRE_ORB(InnerAbilityTabs.OTHER_SPELL_TAB, 59, 14376, 950, 63, new Rune(RuneType.FIRE, 30), new Rune(
			RuneType.COSMIC, 3)),

	CHARGE_AIR_ORB(InnerAbilityTabs.OTHER_SPELL_TAB, 62, 14373, 998, 66, new Rune(RuneType.AIR, 30), new Rune(
			RuneType.COSMIC, 3)),

	LVL5_ENCHANT(InnerAbilityTabs.OTHER_SPELL_TAB, 64, 14386, 1030, 68, new Rune(RuneType.WATER, 15), new Rune(
			RuneType.EARTH, 15), new Rune(RuneType.COSMIC, 1)),

	LVL6_ENCHANT(InnerAbilityTabs.OTHER_SPELL_TAB, 77, 14387, 1238, 87, new Rune(RuneType.EARTH, 20), new Rune(
			RuneType.FIRE, 20), new Rune(RuneType.COSMIC, 1)),

	ENCHANT_CROSSBOW_BOLT(InnerAbilityTabs.OTHER_SPELL_TAB, 156, 14370, 2502, 4);

	@Override
	public Spellbook getSpellbook() {
		return Spellbook.STANDARD;
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
	public int getId() {
		return abilityId;
	}

	@Override
	public Tab getTab() {
		return tab;
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
	public int getLevel() {
		return level;
	}

	@Override
	public int getSkill() {
		return Skills.MAGIC;
	}

	@Override
	public Rune[] getRunes() {
		return runes;
	}

	@Override
	public RequiredGroup getRuneGroup() {
		RequiredGroup ret = new RequiredGroup();
		for (Rune r : getRunes()) {
			ret.add(new DefinedItem(r.getAmount(), r.getType().getItem()));
		}
		return ret;
	}

	private final Tab tab;
	private final int childId, childTexture, abilityId, level;
	private final Rune[] runes;

	private StandardSpell(Tab tab, int childId, int childTexture, int abilityId, int level, Rune... runes) {
		this.tab = tab;
		this.childId = childId;
		this.childTexture = childTexture;
		this.abilityId = abilityId;
		this.level = level;
		this.runes = runes;
	}

	private static Spell[] ALL;

	public static Spell[] getAll() {
		if (ALL == null) {
			ALL = new Spell[FreeSpell.values().length + StandardSpell.values().length];
			int loc = 0;
			for (Spell s : FreeSpell.values()) {
				ALL[loc++] = s;
			}
			for (Spell s : StandardSpell.values()) {
				ALL[loc++] = s;
			}
			Arrays.sort(ALL, new Comparator<Spell>() {
				@Override
				public int compare(Spell o1, Spell o2) {
					return o1.getLevel() - o2.getLevel();
				}
			});
		}
		return ALL;
	}

}
