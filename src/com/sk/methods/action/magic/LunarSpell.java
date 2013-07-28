package com.sk.methods.action.magic;

import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.Component;

import com.sk.SkMethodContext;
import com.sk.methods.action.ability.AbilityStyle;
import com.sk.methods.action.structure.Spell;
import com.sk.windows.InnerAbilityTab;
import com.sk.windows.Window;

public enum LunarSpell implements Spell {
	CURE_ME(InnerAbilityTab.COMBAT_SPELL, 1830, 71, 114, 14421, new Rune(RuneType.COSMIC, 2), new Rune(RuneType.ASTRAL, 2)),
CURE_GROUP(InnerAbilityTab.COMBAT_SPELL, 1910, 74, 119, 14424, new Rune(RuneType.COSMIC, 2), new Rune(RuneType.ASTRAL, 2)),
STAT_SPY(InnerAbilityTab.COMBAT_SPELL, 1958, 75, 122, 14435, new Rune(RuneType.BODY, 5), new Rune(RuneType.COSMIC, 2), new Rune(RuneType.ASTRAL, 2)),
DREAM(InnerAbilityTab.COMBAT_SPELL, 2070, 79, 129, 14439, new Rune(RuneType.BODY, 5), new Rune(RuneType.COSMIC, 1), new Rune(RuneType.ASTRAL, 2)),
SPIRITUALISE_FOOD(InnerAbilityTab.COMBAT_SPELL, 2086, 80, 130, 14449, new Rune(RuneType.BODY, 5), new Rune(RuneType.COSMIC, 3), new Rune(RuneType.ASTRAL, 2)),
STAT_RESTORE_POT_SHARE(InnerAbilityTab.COMBAT_SPELL, 2118, 81, 132, 14413, new Rune(RuneType.WATER, 10), new Rune(RuneType.EARTH, 10), new Rune(RuneType.ASTRAL, 2)),
BOOST_POTION_SHARE(InnerAbilityTab.COMBAT_SPELL, 2182, 84, 136, 14410, new Rune(RuneType.WATER, 10), new Rune(RuneType.EARTH, 12), new Rune(RuneType.ASTRAL, 3)),
DISRUPTION_SHIELD(InnerAbilityTab.COMBAT_SPELL, 2326, 90, 145, 14450, new Rune(RuneType.BODY, 10), new Rune(RuneType.BLOOD, 3), new Rune(RuneType.ASTRAL, 3)),
HEAL_SKILLING(InnerAbilityTab.COMBAT_SPELL, 2358, 92, 147, 14419, new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3), new Rune(RuneType.BLOOD, 1)),
VENGEANCE_SKILLING(InnerAbilityTab.COMBAT_SPELL, 2406, 93, 150, 14420, new Rune(RuneType.EARTH, 10), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.DEATH, 3)),
VENGEANCE(InnerAbilityTab.COMBAT_SPELL, 2422, 94, 151, 14423, new Rune(RuneType.EARTH, 10), new Rune(RuneType.ASTRAL, 4), new Rune(RuneType.DEATH, 2)),
VENGEANCE_GROUP(InnerAbilityTab.COMBAT_SPELL, 2438, 95, 152, 14451, new Rune(RuneType.EARTH, 11), new Rune(RuneType.ASTRAL, 4), new Rune(RuneType.DEATH, 3)),
HEAL_GROUP(InnerAbilityTab.COMBAT_SPELL, 2454, 95, 153, 14425, new Rune(RuneType.ASTRAL, 4), new Rune(RuneType.LAW, 3), new Rune(RuneType.BLOOD, 2)),
MOONCLAN_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 1782, 69, 111, 14403, new Rune(RuneType.EARTH, 2), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),
TELEGROUP_MOONCLAN(InnerAbilityTab.TELEPORT_SPELL, 1798, 70, 112, 14428, new Rune(RuneType.EARTH, 4), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),
OURANIA_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 1814, 71, 113, 14442, new Rune(RuneType.EARTH, 6), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),
SOUTH_FALADOR_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 1862, 72, 116, 14444, new Rune(RuneType.AIR, 2), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),
WATERBIRTH_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 1878, 72, 117, 14404, new Rune(RuneType.WATER, 1), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),
TELEGROUP_WATERBIRTH(InnerAbilityTab.TELEPORT_SPELL, 1894, 73, 118, 14429, new Rune(RuneType.WATER, 5), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),
BARBARIAN_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 1942, 75, 121, 14406, new Rune(RuneType.FIRE, 3), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 2)),
NORTH_ARDOUGNE_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 1974, 76, 123, 14446, new Rune(RuneType.WATER, 5), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),
TELEGROUP_BARBARIAN(InnerAbilityTab.TELEPORT_SPELL, 1990, 76, 124, 14430, new Rune(RuneType.FIRE, 6), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 2)),
KHAZARD_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 2038, 78, 127, 14408, new Rune(RuneType.WATER, 4), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 2)),
TELEGROUP_KHAZARD(InnerAbilityTab.TELEPORT_SPELL, 2054, 79, 128, 14431, new Rune(RuneType.WATER, 8), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 2)),
FISHING_GUILD_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 2198, 85, 137, 14414, new Rune(RuneType.WATER, 8), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),
TELEGROUP_FISHING_GUILD(InnerAbilityTab.TELEPORT_SPELL, 2214, 86, 138, 14432, new Rune(RuneType.WATER, 10), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),
CATHERBY_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 2246, 87, 140, 14415, new Rune(RuneType.WATER, 10), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),
TELEGROUP_CATHERBY(InnerAbilityTab.TELEPORT_SPELL, 2278, 88, 142, 14433, new Rune(RuneType.WATER, 12), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),
ICE_PLATEAU_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 2294, 89, 143, 14416, new Rune(RuneType.WATER, 8), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),
TELEGROUP_ICE_PLATEAU(InnerAbilityTab.TELEPORT_SPELL, 2310, 90, 144, 14434, new Rune(RuneType.WATER, 16), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),
TROLLHEIM_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 2374, 92, 148, 14453, new Rune(RuneType.WATER, 10), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),
TELEGROUP_TROLLHEIM(InnerAbilityTab.TELEPORT_SPELL, 2390, 92, 149, 14454, new Rune(RuneType.WATER, 20), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),
BAKE_PIE(InnerAbilityTab.SKILLING_SPELL, 1686, 65, 105, 14402, new Rune(RuneType.WATER, 4), new Rune(RuneType.FIRE, 5), new Rune(RuneType.ASTRAL, 1)),
CURE_PLANT(InnerAbilityTab.SKILLING_SPELL, 1702, 66, 106, 14426, new Rune(RuneType.EARTH, 8), new Rune(RuneType.ASTRAL, 1)),
NPC_CONTACT(InnerAbilityTab.SKILLING_SPELL, 1734, 67, 108, 14427, new Rune(RuneType.AIR, 2), new Rune(RuneType.COSMIC, 1), new Rune(RuneType.ASTRAL, 1)),
HUMIDIFY(InnerAbilityTab.SKILLING_SPELL, 1766, 68, 110, 14437, new Rune(RuneType.WATER, 3), new Rune(RuneType.FIRE, 1), new Rune(RuneType.ASTRAL, 1)),
HUNTER_KIT(InnerAbilityTab.SKILLING_SPELL, 1846, 71, 115, 14438, new Rune(RuneType.EARTH, 2), new Rune(RuneType.ASTRAL, 2)),
REPAIR_RUNE_POUCH(InnerAbilityTab.SKILLING_SPELL, 1926, 75, 120, 14445, new Rune(RuneType.COSMIC, 1), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),
SUPERGLASS_MAKE(InnerAbilityTab.SKILLING_SPELL, 2006, 77, 125, 14407, new Rune(RuneType.AIR, 10), new Rune(RuneType.FIRE, 6), new Rune(RuneType.ASTRAL, 2)),
REMOTE_FARM(InnerAbilityTab.SKILLING_SPELL, 2022, 78, 126, 14447, new Rune(RuneType.EARTH, 2), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.NATURE, 3)),
STRING_JEWELLERY(InnerAbilityTab.SKILLING_SPELL, 2102, 80, 131, 14409, new Rune(RuneType.WATER, 5), new Rune(RuneType.EARTH, 10), new Rune(RuneType.ASTRAL, 2)),
MAGIC_IMBUE(InnerAbilityTab.SKILLING_SPELL, 2134, 82, 133, 14411, new Rune(RuneType.WATER, 7), new Rune(RuneType.FIRE, 7), new Rune(RuneType.ASTRAL, 2)),
MAKE_LEATHER(InnerAbilityTab.SKILLING_SPELL, 2150, 83, 134, 14448, new Rune(RuneType.FIRE, 2), new Rune(RuneType.BODY, 2), new Rune(RuneType.ASTRAL, 2)),
FERTILE_SOIL(InnerAbilityTab.SKILLING_SPELL, 2166, 83, 135, 14412, new Rune(RuneType.EARTH, 15), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.NATURE, 2)),
PLANK_MAKE(InnerAbilityTab.SKILLING_SPELL, 2230, 86, 139, 14440, new Rune(RuneType.EARTH, 15), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.NATURE, 1)),
TUNE_BANE_ORE(InnerAbilityTab.SKILLING_SPELL, 2262, 87, 141, 14452, new Rune(RuneType.EARTH, 4), new Rune(RuneType.ASTRAL, 2)),
SPELLBOOK_SWAP(InnerAbilityTab.SKILLING_SPELL, 2470, 96, 154, 14441, new Rune(RuneType.COSMIC, 2), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 1)),
;

	private LunarSpell(Window t, int id, int l, int ci, int ct, Rune... r) {
		this.window = t;
		this.id = id;
		this.level = l;
		this.childIndex = ci;
		this.childTexture = ct;
		this.runes = r;
	}

	private final Rune[] runes;
	private final int childIndex, childTexture, level, id;
	private final Window window;

	@Override
	public int getChildIndex() {
		return childIndex;
	}

	@Override
	public int getChildTexture() {
		return childTexture;
	}

	@Override
	public Component getCooldownComponent(SkMethodContext ctx) {
		return ctx.widgets.get(getWidget(), COOLDOWN_COMPONENT).getChild(getChildIndex());
	}

	@Override
	public Window getWindow() {
		return window;
	}

	@Override
	public int getSkillLevel() {
		return level;
	}

	@Override
	public int getSkill() {
		return Skills.MAGIC;
	}

	@Override
	public Component getComponent(SkMethodContext ctx) {
		return ctx.widgets.get(getWidget(), MAIN_COMPONENT).getChild(getChildIndex());
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public Spellbook getSpellbook() {
		return Spellbook.ALL;
	}

	@Override
	public Rune[] getRunes() {
		return runes;
	}

	@Override
	public int getWidget() {
		return AbilityStyle.MAGIC.getWidgetId();
	}
}

