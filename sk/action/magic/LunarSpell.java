package sk.action.magic;

import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.BookUtil;
import sk.item.DefinedItem;
import sk.item.RequiredGroup;
import sk.tab.InnerAbilityTabs;
import sk.tab.Tab;

public enum LunarSpell implements Spell {
	CURE_ME(InnerAbilityTabs.COMBAT_SPELL_TAB, 114, 14421, 1830, 71, new Rune(RuneType.COSMIC, 2), new Rune(
			RuneType.ASTRAL, 2)),

	CURE_GROUP(InnerAbilityTabs.COMBAT_SPELL_TAB, 119, 14424, 1910, 74, new Rune(RuneType.COSMIC, 2), new Rune(
			RuneType.ASTRAL, 2)),

	STAT_SPY(InnerAbilityTabs.COMBAT_SPELL_TAB, 122, 14435, 1958, 75, new Rune(RuneType.BODY, 5), new Rune(
			RuneType.COSMIC, 2), new Rune(RuneType.ASTRAL, 2)),

	DREAM(InnerAbilityTabs.COMBAT_SPELL_TAB, 129, 14439, 2070, 79, new Rune(RuneType.BODY, 5), new Rune(
			RuneType.COSMIC, 1), new Rune(RuneType.ASTRAL, 2)),

	SPIRITUALISE_FOOD(
			InnerAbilityTabs.COMBAT_SPELL_TAB, 130, 14449, 2086, 80, new Rune(RuneType.BODY, 5), new Rune(
					RuneType.COSMIC, 3), new Rune(RuneType.ASTRAL, 2)),

	STAT_RESTORE_POT_SHARE(
			InnerAbilityTabs.COMBAT_SPELL_TAB, 132, 14413, 2118, 81, new Rune(RuneType.WATER, 10), new Rune(
					RuneType.EARTH, 10), new Rune(RuneType.ASTRAL, 2)),

	BOOST_POTION_SHARE(
			InnerAbilityTabs.COMBAT_SPELL_TAB, 136, 14410, 2182, 84, new Rune(RuneType.WATER, 10), new Rune(
					RuneType.EARTH, 12), new Rune(RuneType.ASTRAL, 3)),

	DISRUPTION_SHIELD(
			InnerAbilityTabs.COMBAT_SPELL_TAB, 145, 14450, 2326, 90, new Rune(RuneType.BODY, 10), new Rune(
					RuneType.BLOOD, 3), new Rune(RuneType.ASTRAL, 3)),

	HEAL_OTHER(InnerAbilityTabs.COMBAT_SPELL_TAB, 147, 14419, 2358, 92, new Rune(RuneType.ASTRAL, 3), new Rune(
			RuneType.LAW, 3), new Rune(RuneType.BLOOD, 1)),

	VENGEANCE_OTHER(
			InnerAbilityTabs.COMBAT_SPELL_TAB, 150, 14420, 2406, 93, new Rune(RuneType.EARTH, 10), new Rune(
					RuneType.ASTRAL, 3), new Rune(RuneType.DEATH, 3)),

	VENGEANCE(InnerAbilityTabs.COMBAT_SPELL_TAB, 151, 14423, 2422, 94, new Rune(RuneType.EARTH, 10), new Rune(
			RuneType.ASTRAL, 4), new Rune(RuneType.DEATH, 2)),

	VENGEANCE_GROUP(
			InnerAbilityTabs.COMBAT_SPELL_TAB, 152, 14451, 2438, 95, new Rune(RuneType.EARTH, 11), new Rune(
					RuneType.ASTRAL, 4), new Rune(RuneType.DEATH, 3)),

	HEAL_GROUP(InnerAbilityTabs.COMBAT_SPELL_TAB, 153, 14425, 2454, 95, new Rune(RuneType.ASTRAL, 4), new Rune(
			RuneType.LAW, 3), new Rune(RuneType.BLOOD, 2)),

	MOONCLAN_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 111, 14403, 1782, 69, new Rune(RuneType.EARTH, 2), new Rune(
			RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),

	TELEGROUP_MOONCLAN(InnerAbilityTabs.TELEPORT_TAB, 112, 14428, 1798, 70, new Rune(RuneType.EARTH, 4), new Rune(
			RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),

	OURANIA_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 113, 14442, 1814, 71, new Rune(RuneType.EARTH, 6), new Rune(
			RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),

	SOUTH_FALADOR_TELEPORT(
			InnerAbilityTabs.TELEPORT_TAB, 116, 14444, 1862, 72, new Rune(RuneType.AIR, 2), new Rune(
					RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),

	WATERBIRTH_TELEPORT(
			InnerAbilityTabs.TELEPORT_TAB, 117, 14404, 1878, 72, new Rune(RuneType.WATER, 1), new Rune(
					RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),

	TELEGROUP_WATERBIRTH(
			InnerAbilityTabs.TELEPORT_TAB, 118, 14429, 1894, 73, new Rune(RuneType.WATER, 5), new Rune(
					RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),

	BARBARIAN_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 121, 14406, 1942, 75, new Rune(RuneType.FIRE, 3), new Rune(
			RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 2)),

	NORTH_ARDOUGNE_TELEPORT(
			InnerAbilityTabs.TELEPORT_TAB, 123, 14446, 1974, 76, new Rune(RuneType.WATER, 5), new Rune(
					RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),

	TELEGROUP_BARBARIAN(InnerAbilityTabs.TELEPORT_TAB, 124, 14430, 1990, 76, new Rune(RuneType.FIRE, 6), new Rune(
			RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 2)),

	KHAZARD_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 127, 14408, 2038, 78, new Rune(RuneType.WATER, 4), new Rune(
			RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 2)),

	TELEGROUP_KHAZARD(InnerAbilityTabs.TELEPORT_TAB, 128, 14431, 2054, 79, new Rune(RuneType.WATER, 8), new Rune(
			RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 2)),

	FISHING_GUILD_TELEPORT(
			InnerAbilityTabs.TELEPORT_TAB, 137, 14414, 2198, 85, new Rune(RuneType.WATER, 8), new Rune(
					RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),

	TELEGROUP_FISHING_GUILD(
			InnerAbilityTabs.TELEPORT_TAB, 138, 14432, 2214, 86, new Rune(RuneType.WATER, 10), new Rune(
					RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),

	CATHERBY_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 140, 14415, 2246, 87, new Rune(RuneType.WATER, 10), new Rune(
			RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),

	TELEGROUP_CATHERBY(
			InnerAbilityTabs.TELEPORT_TAB, 142, 14433, 2278, 88, new Rune(RuneType.WATER, 12), new Rune(
					RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),

	ICE_PLATEAU_TELEPORT(
			InnerAbilityTabs.TELEPORT_TAB, 143, 14416, 2294, 89, new Rune(RuneType.WATER, 8), new Rune(
					RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),

	TELEGROUP_ICE_PLATEAU(
			InnerAbilityTabs.TELEPORT_TAB, 144, 14434, 2310, 90, new Rune(RuneType.WATER, 16), new Rune(
					RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),

	TROLLHEIM_TELEPORT(
			InnerAbilityTabs.TELEPORT_TAB, 148, 14453, 2374, 92, new Rune(RuneType.WATER, 10), new Rune(
					RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),

	TELEGROUP_TROLLHEIM(
			InnerAbilityTabs.TELEPORT_TAB, 149, 14454, 2390, 92, new Rune(RuneType.WATER, 20), new Rune(
					RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),

	BAKE_PIE(InnerAbilityTabs.OTHER_SPELL_TAB, 105, 14402, 1686, 65, new Rune(RuneType.WATER, 4), new Rune(
			RuneType.FIRE, 5), new Rune(RuneType.ASTRAL, 1)),

	CURE_PLANT(InnerAbilityTabs.OTHER_SPELL_TAB, 106, 14426, 1702, 66, new Rune(RuneType.EARTH, 8), new Rune(
			RuneType.ASTRAL, 1)),

	NPC_CONTACT(InnerAbilityTabs.OTHER_SPELL_TAB, 108, 14427, 1734, 67, new Rune(RuneType.AIR, 2), new Rune(
			RuneType.COSMIC, 1), new Rune(RuneType.ASTRAL, 1)),

	HUMIDIFY(InnerAbilityTabs.OTHER_SPELL_TAB, 110, 14437, 1766, 68, new Rune(RuneType.WATER, 3), new Rune(
			RuneType.FIRE, 1), new Rune(RuneType.ASTRAL, 1)),

	HUNTER_KIT(InnerAbilityTabs.OTHER_SPELL_TAB, 115, 14438, 1846, 71, new Rune(RuneType.EARTH, 2), new Rune(
			RuneType.ASTRAL, 2)),

	REPAIR_RUNE_POUCH(
			InnerAbilityTabs.OTHER_SPELL_TAB, 120, 14445, 1926, 75, new Rune(RuneType.COSMIC, 1), new Rune(
					RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),

	SUPERGLASS_MAKE(InnerAbilityTabs.OTHER_SPELL_TAB, 125, 14407, 2006, 77, new Rune(RuneType.AIR, 10), new Rune(
			RuneType.FIRE, 6), new Rune(RuneType.ASTRAL, 2)),

	REMOTE_FARM(InnerAbilityTabs.OTHER_SPELL_TAB, 126, 14447, 2022, 78, new Rune(RuneType.EARTH, 2), new Rune(
			RuneType.ASTRAL, 2), new Rune(RuneType.NATURE, 3)),

	STRING_JEWELLERY(
			InnerAbilityTabs.OTHER_SPELL_TAB, 131, 14409, 2102, 80, new Rune(RuneType.WATER, 5), new Rune(
					RuneType.EARTH, 10), new Rune(RuneType.ASTRAL, 2)),

	MAGIC_IMBUE(InnerAbilityTabs.OTHER_SPELL_TAB, 133, 14411, 2134, 82, new Rune(RuneType.WATER, 7), new Rune(
			RuneType.FIRE, 7), new Rune(RuneType.ASTRAL, 2)),

	MAKE_LEATHER(InnerAbilityTabs.OTHER_SPELL_TAB, 134, 14448, 2150, 83, new Rune(RuneType.FIRE, 2), new Rune(
			RuneType.BODY, 2), new Rune(RuneType.ASTRAL, 2)),

	FERTILE_SOIL(InnerAbilityTabs.OTHER_SPELL_TAB, 135, 14412, 2166, 83, new Rune(RuneType.EARTH, 15), new Rune(
			RuneType.ASTRAL, 3), new Rune(RuneType.NATURE, 2)),

	PLANK_MAKE(InnerAbilityTabs.OTHER_SPELL_TAB, 139, 14440, 2230, 86, new Rune(RuneType.EARTH, 15), new Rune(
			RuneType.ASTRAL, 2), new Rune(RuneType.NATURE, 1)),

	TUNE_BANE_ORE(InnerAbilityTabs.OTHER_SPELL_TAB, 141, 14452, 2262, 87, new Rune(RuneType.EARTH, 4), new Rune(
			RuneType.ASTRAL, 2)),

	SPELLBOOK_SWAP(InnerAbilityTabs.OTHER_SPELL_TAB, 154, 14441, 2470, 96, new Rune(RuneType.COSMIC, 2), new Rune(
			RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 1));

	@Override
	public Spellbook getSpellbook() {
		return Spellbook.LUNAR;
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

	private LunarSpell(Tab tab, int childId, int childTexture, int abilityId, int level, Rune... runes) {
		this.tab = tab;
		this.childId = childId;
		this.childTexture = childTexture;
		this.abilityId = abilityId;
		this.level = level;
		this.runes = runes;
	}

}
