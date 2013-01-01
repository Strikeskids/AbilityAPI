package sk.action.book.magic;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.Ability;
import sk.general.Completion;
import sk.tab.InnerAbilityTabs;
import sk.tab.Tab;

/**
 * An enum with all the spells from all the spellbooks.
 * 
 * Note: The {@link Spell#LUNAR_TROLLHEIM_TELEPORT} and {@link Spell#STANDARD_TROLLHEIM_TELEPORT} refer to the
 * corresponding trollheim teleports for those respective spellbooks
 * 
 * @author Strikeskids
 * 
 */
public enum Spell implements Ability {
	AIR_STRIKE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 14, 14348, 230, 0, new Rune(RuneType.AIR, 1)),
	CONFUSE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 15, 14389, 246, 3, new Rune(RuneType.MIND, 1)),
	WATER_STRIKE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 16, 14349, 262, 5, new Rune(RuneType.AIR,
			1), new Rune(RuneType.WATER, 1)),
	EARTH_STRIKE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 18, 14350, 294, 9, new Rune(RuneType.AIR,
			1), new Rune(RuneType.EARTH, 1)),
	WEAKEN(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 20, 14390, 326, 11, new Rune(RuneType.BODY, 1)),
	FIRE_STRIKE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 21, 14351, 342, 13, new Rune(RuneType.AIR,
			1), new Rune(RuneType.FIRE, 1)),
	AIR_BOLT(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 23, 14352, 374, 17, new Rune(RuneType.AIR, 2)),
	CURSE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 24, 14391, 390, 19, new Rune(RuneType.CHAOS, 1)),
	BIND(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 25, 14392, 406, 20, new Rune(RuneType.NATURE, 1)),
	WATER_BOLT(
			Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 27, 14353, 438, 23, new Rune(RuneType.AIR, 2),
			new Rune(RuneType.WATER, 2)),
	EARTH_BOLT(
			Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 30, 14354, 486, 29, new Rune(RuneType.AIR, 2),
			new Rune(RuneType.EARTH, 2)),
	FIRE_BOLT(
			Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 33, 14355, 534, 35, new Rune(RuneType.AIR, 2),
			new Rune(RuneType.FIRE, 2)),
	AIR_BLAST(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 37, 14356, 598, 41, new Rune(RuneType.AIR, 3)),
	WATER_BLAST(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 40, 14357, 646, 47, new Rune(RuneType.AIR,
			3), new Rune(RuneType.WATER, 3)),
	SNARE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 43, 14393, 694, 50, new Rune(RuneType.NATURE, 2)),
	SLAYER_DART(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 44, 14388, 710, 50, new Rune(RuneType.AIR,
			3), new Rune(RuneType.DEATH, 1)),
	EARTH_BLAST(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 46, 14358, 742, 53, new Rune(RuneType.AIR,
			3), new Rune(RuneType.EARTH, 3)),
	FIRE_BLAST(
			Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 51, 14359, 822, 59, new Rune(RuneType.AIR, 3),
			new Rune(RuneType.FIRE, 3)),
	DIVINE_STORM(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 54, 14369, 870, 60, new Rune(RuneType.AIR,
			5)),
	AIR_WAVE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 58, 14360, 934, 62, new Rune(RuneType.AIR, 4)),
	WATER_WAVE(
			Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 61, 14361, 982, 65, new Rune(RuneType.AIR, 4),
			new Rune(RuneType.WATER, 4)),
	VULNERABILITY(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 63, 14395, 1014, 66, new Rune(
			RuneType.CHAOS, 1), new Rune(RuneType.SOUL, 1)),
	EARTH_WAVE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 65, 14362, 1046, 70, new Rune(RuneType.AIR,
			4), new Rune(RuneType.EARTH, 4)),
	ENFEEBLE(
			Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 66, 14371, 1062, 73,
			new Rune(RuneType.BODY, 1), new Rune(RuneType.SOUL, 1)),
	FIRE_WAVE(
			Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 68, 14363, 1094, 75, new Rune(RuneType.AIR, 4),
			new Rune(RuneType.FIRE, 4)),
	STORM_OF_ARMADYL(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 69, 14369, 1110, 77, new Rune(
			RuneType.ARMADYL, 1)),
	ENTANGLE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 70, 14394, 1126, 79, new Rune(RuneType.NATURE,
			3)),
	STAGGER(
			Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 71, 14377, 1142, 80,
			new Rune(RuneType.MIND, 1), new Rune(RuneType.SOUL, 1)),
	AIR_SURGE(
			Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 73, 14364, 1174, 81, new Rune(RuneType.AIR, 5)),
	TELEPORT_BLOCK(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 75, 14344, 1206, 85, new Rune(
			RuneType.CHAOS, 1), new Rune(RuneType.LAW, 1), new Rune(RuneType.DEATH, 1)),
	EARTH_SURGE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 76, 14366, 1222, 90, new Rune(RuneType.AIR,
			5), new Rune(RuneType.EARTH, 5)),
	WATER_SURGE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 78, 14365, 1254, 85, new Rune(RuneType.AIR,
			5), new Rune(RuneType.WATER, 5)),
	FIRE_SURGE(Spellbook.STANDARD, InnerAbilityTabs.COMBAT_SPELL_TAB, 80, 14367, 1286, 95, new Rune(RuneType.AIR,
			5), new Rune(RuneType.FIRE, 5)),
	POLYPORE_STRIKE(Spellbook.NONE, InnerAbilityTabs.COMBAT_SPELL_TAB, 162, 14396, 2598, 80),

	MOBILISING_ARMIES_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 19, 14335, 310, 10, new Rune(
			RuneType.AIR, 1), new Rune(RuneType.WATER, 1), new Rune(RuneType.LAW, 1)),
	VARROCK_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 28, 14336, 454, 25, new Rune(RuneType.AIR,
			3), new Rune(RuneType.FIRE, 1), new Rune(RuneType.LAW, 1)),
	LUMBRIDGE_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 31, 14334, 502, 31, new Rune(
			RuneType.AIR, 3), new Rune(RuneType.EARTH, 1), new Rune(RuneType.LAW, 1)),
	FALADOR_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 34, 14337, 550, 37, new Rune(RuneType.AIR,
			3), new Rune(RuneType.WATER, 1), new Rune(RuneType.LAW, 1)),
	HOUSE_TELEPORT(
			Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 36, 14338, 582, 40, new Rune(RuneType.AIR, 1),
			new Rune(RuneType.EARTH, 1), new Rune(RuneType.LAW, 1)),
	CAMELOT_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 39, 14339, 630, 45, new Rune(RuneType.AIR,
			5), new Rune(RuneType.LAW, 1)),
	ARDOUGNE_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 45, 14340, 726, 51, new Rune(
			RuneType.WATER, 2), new Rune(RuneType.LAW, 2)),
	WATCHTOWER_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 50, 14341, 806, 58, new Rune(
			RuneType.EARTH, 2), new Rune(RuneType.LAW, 2)),
	STANDARD_TROLLHEIM_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 57, 14342, 918, 61, new Rune(
			RuneType.FIRE, 2), new Rune(RuneType.LAW, 2)),
	APE_ATOLL_TELEPORT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 60, 14343, 966, 64, new Rune(
			RuneType.WATER, 2), new Rune(RuneType.FIRE, 2), new Rune(RuneType.LAW, 2)),
	TELEOTHER_LUMBRIDGE(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 67, 14345, 1078, 74, new Rune(
			RuneType.EARTH, 1), new Rune(RuneType.LAW, 1), new Rune(RuneType.SOUL, 1)),
	TELEOTHER_FALADOR(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 74, 14346, 1190, 82, new Rune(
			RuneType.WATER, 1), new Rune(RuneType.LAW, 1), new Rune(RuneType.SOUL, 1)),
	TELEOTHER_CAMELOT(Spellbook.STANDARD, InnerAbilityTabs.TELEPORT_TAB, 79, 14347, 1270, 90, new Rune(
			RuneType.LAW, 1), new Rune(RuneType.SOUL, 2)),
	HOME_TELEPORT(Spellbook.NONE, InnerAbilityTabs.TELEPORT_TAB, 155, 14333, 2491, 0),

	LVL1_ENCHANT(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 17, 14382, 278, 7, new Rune(RuneType.WATER,
			1), new Rune(RuneType.COSMIC, 1)),
	BONES_TO_BANANAS(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 22, 14380, 358, 15, new Rune(
			RuneType.WATER, 2), new Rune(RuneType.EARTH, 2), new Rune(RuneType.NATURE, 1)),
	LOW_LEVEL_ALCHEMY(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 26, 14378, 422, 21, new Rune(
			RuneType.FIRE, 3), new Rune(RuneType.NATURE, 1)),
	LVL2_ENCHANT(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 29, 14385, 470, 27, new Rune(RuneType.AIR,
			3), new Rune(RuneType.COSMIC, 1)),
	TELEKINETIC_GRAB(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 32, 14332, 518, 33, new Rune(
			RuneType.AIR, 1), new Rune(RuneType.LAW, 1)),
	SUPERHEAT_ITEM(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 38, 14372, 614, 43, new Rune(
			RuneType.FIRE, 4), new Rune(RuneType.NATURE, 1)),
	LVL3_ENCHANT(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 41, 14384, 662, 49, new Rune(RuneType.FIRE,
			5), new Rune(RuneType.COSMIC, 1)),
	HIGH_LEVEL_ALCHEMY(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 47, 14379, 758, 55, new Rune(
			RuneType.FIRE, 5), new Rune(RuneType.NATURE, 1)),
	CHARGE_WATER_ORB(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 48, 14374, 774, 56, new Rune(
			RuneType.WATER, 30), new Rune(RuneType.COSMIC, 3)),
	LVL4_ENCHANT(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 49, 14383, 790, 57, new Rune(
			RuneType.EARTH, 10), new Rune(RuneType.COSMIC, 1)),
	CHARGE_EARTH_ORB(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 52, 14375, 838, 60, new Rune(
			RuneType.EARTH, 30), new Rune(RuneType.COSMIC, 3)),
	BONES_TO_PEACHES(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 53, 14381, 854, 60, new Rune(
			RuneType.WATER, 4), new Rune(RuneType.EARTH, 4), new Rune(RuneType.NATURE, 2)),
	CHARGE_FIRE_ORB(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 59, 14376, 950, 63, new Rune(
			RuneType.FIRE, 30), new Rune(RuneType.COSMIC, 3)),
	CHARGE_AIR_ORB(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 62, 14373, 998, 66, new Rune(
			RuneType.AIR, 30), new Rune(RuneType.COSMIC, 3)),
	LVL5_ENCHANT(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 64, 14386, 1030, 68, new Rune(
			RuneType.WATER, 15), new Rune(RuneType.EARTH, 15), new Rune(RuneType.COSMIC, 1)),
	LVL6_ENCHANT(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 77, 14387, 1238, 87, new Rune(
			RuneType.EARTH, 20), new Rune(RuneType.FIRE, 20), new Rune(RuneType.COSMIC, 1)),
	ENCHANT_CROSSBOW_BOLT(Spellbook.STANDARD, InnerAbilityTabs.OTHER_SPELL_TAB, 156, 14370, 2502, 4),
	PADDEWWA_TELEPORT(Spellbook.ANCIENT, InnerAbilityTabs.TELEPORT_TAB, 83, 14477, 1334, 54, new Rune(
			RuneType.AIR, 1), new Rune(RuneType.FIRE, 1), new Rune(RuneType.LAW, 2)),

	SENNTISTEN_TELEPORT(Spellbook.ANCIENT, InnerAbilityTabs.TELEPORT_TAB, 86, 14478, 1382, 60, new Rune(
			RuneType.LAW, 2), new Rune(RuneType.SOUL, 1)),
	KHARYRLL_TELEPORT(Spellbook.ANCIENT, InnerAbilityTabs.TELEPORT_TAB, 89, 14479, 1430, 66, new Rune(
			RuneType.LAW, 2), new Rune(RuneType.BLOOD, 1)),
	LASSAR_TELEPORT(Spellbook.ANCIENT, InnerAbilityTabs.TELEPORT_TAB, 92, 14480, 1478, 72, new Rune(
			RuneType.WATER, 4), new Rune(RuneType.LAW, 2)),
	DAREEYAK_TELEPORT(Spellbook.ANCIENT, InnerAbilityTabs.TELEPORT_TAB, 95, 14481, 1526, 78, new Rune(
			RuneType.AIR, 2), new Rune(RuneType.FIRE, 3), new Rune(RuneType.LAW, 2)),
	CARRALLANGER_TELEPORT(Spellbook.ANCIENT, InnerAbilityTabs.TELEPORT_TAB, 98, 14482, 1574, 84, new Rune(
			RuneType.LAW, 2), new Rune(RuneType.SOUL, 2)),
	ANNAKARL_TELEPORT(Spellbook.ANCIENT, InnerAbilityTabs.TELEPORT_TAB, 101, 14483, 1622, 90, new Rune(
			RuneType.LAW, 2), new Rune(RuneType.BLOOD, 2)),
	GHORROCK_TELEPORT(Spellbook.ANCIENT, InnerAbilityTabs.TELEPORT_TAB, 104, 14484, 1670, 96, new Rune(
			RuneType.WATER, 8), new Rune(RuneType.LAW, 2)),
	GALE_RUSH(
			Spellbook.ANCIENT, InnerAbilityTabs.COMBAT_SPELL_TAB, 81, 14485, 1302, 50, new Rune(RuneType.AIR, 3),
			new Rune(RuneType.DEATH, 1)),
	ROCK_RUSH(Spellbook.ANCIENT, InnerAbilityTabs.COMBAT_SPELL_TAB, 82, 14489, 1318, 52, new Rune(RuneType.EARTH,
			3), new Rune(RuneType.DEATH, 1)),
	BLOODFIRE_RUSH(Spellbook.ANCIENT, InnerAbilityTabs.COMBAT_SPELL_TAB, 84, 14497, 1350, 56, new Rune(
			RuneType.FIRE, 3), new Rune(RuneType.DEATH, 1)),
	ICE_RUSH(
			Spellbook.ANCIENT, InnerAbilityTabs.COMBAT_SPELL_TAB, 85, 14493, 1366, 58,
			new Rune(RuneType.WATER, 3), new Rune(RuneType.DEATH, 1)),
	GALE_BURST(
			Spellbook.ANCIENT, InnerAbilityTabs.COMBAT_SPELL_TAB, 87, 14486, 1398, 62, new Rune(RuneType.AIR, 4),
			new Rune(RuneType.DEATH, 2)),
	ROCK_BURST(Spellbook.ANCIENT, InnerAbilityTabs.COMBAT_SPELL_TAB, 88, 14490, 1414, 64, new Rune(RuneType.EARTH,
			4), new Rune(RuneType.DEATH, 2)),
	BLOODFIRE_BURST(Spellbook.ANCIENT, InnerAbilityTabs.COMBAT_SPELL_TAB, 90, 14498, 1446, 68, new Rune(
			RuneType.FIRE, 4), new Rune(RuneType.DEATH, 2)),
	ICE_BURST(Spellbook.ANCIENT, InnerAbilityTabs.COMBAT_SPELL_TAB, 91, 14494, 1462, 70, new Rune(RuneType.WATER,
			4), new Rune(RuneType.DEATH, 2)),
	GALE_BLITZ(
			Spellbook.ANCIENT, InnerAbilityTabs.COMBAT_SPELL_TAB, 93, 14487, 1494, 74, new Rune(RuneType.AIR, 5),
			new Rune(RuneType.BLOOD, 1)),
	ROCK_BLITZ(Spellbook.ANCIENT, InnerAbilityTabs.COMBAT_SPELL_TAB, 94, 14491, 1510, 76, new Rune(RuneType.EARTH,
			5), new Rune(RuneType.BLOOD, 1)),
	BLOODFIRE_BLITZ(Spellbook.ANCIENT, InnerAbilityTabs.COMBAT_SPELL_TAB, 96, 14499, 1542, 80, new Rune(
			RuneType.FIRE, 5), new Rune(RuneType.BLOOD, 1)),
	ICE_BLITZ(Spellbook.ANCIENT, InnerAbilityTabs.COMBAT_SPELL_TAB, 97, 14495, 1558, 82, new Rune(RuneType.WATER,
			5), new Rune(RuneType.BLOOD, 1)),
	GALE_BARRAGE(Spellbook.ANCIENT, InnerAbilityTabs.COMBAT_SPELL_TAB, 99, 14488, 1590, 86, new Rune(RuneType.AIR,
			5), new Rune(RuneType.BLOOD, 2)),
	ROCK_BARRAGE(Spellbook.ANCIENT, InnerAbilityTabs.COMBAT_SPELL_TAB, 100, 14492, 1606, 88, new Rune(
			RuneType.EARTH, 5), new Rune(RuneType.BLOOD, 2)),
	BLOODFIRE_BARRAGE(Spellbook.ANCIENT, InnerAbilityTabs.COMBAT_SPELL_TAB, 102, 14500, 1638, 92, new Rune(
			RuneType.FIRE, 5), new Rune(RuneType.BLOOD, 2)),
	ICE_BARRAGE(Spellbook.ANCIENT, InnerAbilityTabs.COMBAT_SPELL_TAB, 103, 14496, 1654, 94, new Rune(
			RuneType.WATER, 5), new Rune(RuneType.BLOOD, 2)),

	CURE_ME(
			Spellbook.LUNAR, InnerAbilityTabs.COMBAT_SPELL_TAB, 114, 14421, 1830, 71,
			new Rune(RuneType.COSMIC, 2), new Rune(RuneType.ASTRAL, 2)),
	CURE_GROUP(Spellbook.LUNAR, InnerAbilityTabs.COMBAT_SPELL_TAB, 119, 14424, 1910, 74, new Rune(RuneType.COSMIC,
			2), new Rune(RuneType.ASTRAL, 2)),
	STAT_SPY(
			Spellbook.LUNAR, InnerAbilityTabs.COMBAT_SPELL_TAB, 122, 14435, 1958, 75, new Rune(RuneType.BODY, 5),
			new Rune(RuneType.COSMIC, 2), new Rune(RuneType.ASTRAL, 2)),
	DREAM(
			Spellbook.LUNAR, InnerAbilityTabs.COMBAT_SPELL_TAB, 129, 14439, 2070, 79, new Rune(RuneType.BODY, 5),
			new Rune(RuneType.COSMIC, 1), new Rune(RuneType.ASTRAL, 2)),
	SPIRITUALISE_FOOD(Spellbook.LUNAR, InnerAbilityTabs.COMBAT_SPELL_TAB, 130, 14449, 2086, 80, new Rune(
			RuneType.BODY, 5), new Rune(RuneType.COSMIC, 3), new Rune(RuneType.ASTRAL, 2)),
	STAT_RESTORE_POT_SHARE(Spellbook.LUNAR, InnerAbilityTabs.COMBAT_SPELL_TAB, 132, 14413, 2118, 81, new Rune(
			RuneType.WATER, 10), new Rune(RuneType.EARTH, 10), new Rune(RuneType.ASTRAL, 2)),
	BOOST_POTION_SHARE(Spellbook.LUNAR, InnerAbilityTabs.COMBAT_SPELL_TAB, 136, 14410, 2182, 84, new Rune(
			RuneType.WATER, 10), new Rune(RuneType.EARTH, 12), new Rune(RuneType.ASTRAL, 3)),
	DISRUPTION_SHIELD(Spellbook.LUNAR, InnerAbilityTabs.COMBAT_SPELL_TAB, 145, 14450, 2326, 90, new Rune(
			RuneType.BODY, 10), new Rune(RuneType.BLOOD, 3), new Rune(RuneType.ASTRAL, 3)),
	HEAL_OTHER(Spellbook.LUNAR, InnerAbilityTabs.COMBAT_SPELL_TAB, 147, 14419, 2358, 92, new Rune(RuneType.ASTRAL,
			3), new Rune(RuneType.LAW, 3), new Rune(RuneType.BLOOD, 1)),
	VENGEANCE_OTHER(Spellbook.LUNAR, InnerAbilityTabs.COMBAT_SPELL_TAB, 150, 14420, 2406, 93, new Rune(
			RuneType.EARTH, 10), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.DEATH, 3)),
	VENGEANCE(Spellbook.LUNAR, InnerAbilityTabs.COMBAT_SPELL_TAB, 151, 14423, 2422, 94, new Rune(RuneType.EARTH,
			10), new Rune(RuneType.ASTRAL, 4), new Rune(RuneType.DEATH, 2)),
	VENGEANCE_GROUP(Spellbook.LUNAR, InnerAbilityTabs.COMBAT_SPELL_TAB, 152, 14451, 2438, 95, new Rune(
			RuneType.EARTH, 11), new Rune(RuneType.ASTRAL, 4), new Rune(RuneType.DEATH, 3)),
	HEAL_GROUP(Spellbook.LUNAR, InnerAbilityTabs.COMBAT_SPELL_TAB, 153, 14425, 2454, 95, new Rune(RuneType.ASTRAL,
			4), new Rune(RuneType.LAW, 3), new Rune(RuneType.BLOOD, 2)),
	MOONCLAN_TELEPORT(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 111, 14403, 1782, 69, new Rune(
			RuneType.EARTH, 2), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),
	TELEGROUP_MOONCLAN(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 112, 14428, 1798, 70, new Rune(
			RuneType.EARTH, 4), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),
	OURANIA_TELEPORT(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 113, 14442, 1814, 71, new Rune(
			RuneType.EARTH, 6), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),
	SOUTH_FALADOR_TELEPORT(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 116, 14444, 1862, 72, new Rune(
			RuneType.AIR, 2), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),
	WATERBIRTH_TELEPORT(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 117, 14404, 1878, 72, new Rune(
			RuneType.WATER, 1), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),
	TELEGROUP_WATERBIRTH(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 118, 14429, 1894, 73, new Rune(
			RuneType.WATER, 5), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),
	BARBARIAN_TELEPORT(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 121, 14406, 1942, 75, new Rune(
			RuneType.FIRE, 3), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 2)),
	NORTH_ARDOUGNE_TELEPORT(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 123, 14446, 1974, 76, new Rune(
			RuneType.WATER, 5), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),
	TELEGROUP_BARBARIAN(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 124, 14430, 1990, 76, new Rune(
			RuneType.FIRE, 6), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 2)),
	KHAZARD_TELEPORT(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 127, 14408, 2038, 78, new Rune(
			RuneType.WATER, 4), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 2)),
	TELEGROUP_KHAZARD(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 128, 14431, 2054, 79, new Rune(
			RuneType.WATER, 8), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 2)),
	FISHING_GUILD_TELEPORT(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 137, 14414, 2198, 85, new Rune(
			RuneType.WATER, 8), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),
	TELEGROUP_FISHING_GUILD(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 138, 14432, 2214, 86, new Rune(
			RuneType.WATER, 10), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),
	CATHERBY_TELEPORT(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 140, 14415, 2246, 87, new Rune(
			RuneType.WATER, 10), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),
	TELEGROUP_CATHERBY(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 142, 14433, 2278, 88, new Rune(
			RuneType.WATER, 12), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),
	ICE_PLATEAU_TELEPORT(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 143, 14416, 2294, 89, new Rune(
			RuneType.WATER, 8), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),
	TELEGROUP_ICE_PLATEAU(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 144, 14434, 2310, 90, new Rune(
			RuneType.WATER, 16), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),
	LUNAR_TROLLHEIM_TELEPORT(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 148, 14453, 2374, 92, new Rune(
			RuneType.WATER, 10), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),
	TELEGROUP_TROLLHEIM(Spellbook.LUNAR, InnerAbilityTabs.TELEPORT_TAB, 149, 14454, 2390, 92, new Rune(
			RuneType.WATER, 20), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 3)),
	BAKE_PIE(
			Spellbook.LUNAR, InnerAbilityTabs.OTHER_SPELL_TAB, 105, 14402, 1686, 65, new Rune(RuneType.WATER, 4),
			new Rune(RuneType.FIRE, 5), new Rune(RuneType.ASTRAL, 1)),
	CURE_PLANT(
			Spellbook.LUNAR, InnerAbilityTabs.OTHER_SPELL_TAB, 106, 14426, 1702, 66, new Rune(RuneType.EARTH, 8),
			new Rune(RuneType.ASTRAL, 1)),
	NPC_CONTACT(
			Spellbook.LUNAR, InnerAbilityTabs.OTHER_SPELL_TAB, 108, 14427, 1734, 67, new Rune(RuneType.AIR, 2),
			new Rune(RuneType.COSMIC, 1), new Rune(RuneType.ASTRAL, 1)),
	HUMIDIFY(
			Spellbook.LUNAR, InnerAbilityTabs.OTHER_SPELL_TAB, 110, 14437, 1766, 68, new Rune(RuneType.WATER, 3),
			new Rune(RuneType.FIRE, 1), new Rune(RuneType.ASTRAL, 1)),
	HUNTER_KIT(
			Spellbook.LUNAR, InnerAbilityTabs.OTHER_SPELL_TAB, 115, 14438, 1846, 71, new Rune(RuneType.EARTH, 2),
			new Rune(RuneType.ASTRAL, 2)),
	REPAIR_RUNE_POUCH(Spellbook.LUNAR, InnerAbilityTabs.OTHER_SPELL_TAB, 120, 14445, 1926, 75, new Rune(
			RuneType.COSMIC, 1), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.LAW, 1)),
	SUPERGLASS_MAKE(Spellbook.LUNAR, InnerAbilityTabs.OTHER_SPELL_TAB, 125, 14407, 2006, 77, new Rune(
			RuneType.AIR, 10), new Rune(RuneType.FIRE, 6), new Rune(RuneType.ASTRAL, 2)),
	REMOTE_FARM(Spellbook.LUNAR, InnerAbilityTabs.OTHER_SPELL_TAB, 126, 14447, 2022, 78, new Rune(RuneType.EARTH,
			2), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.NATURE, 3)),
	STRING_JEWELLERY(Spellbook.LUNAR, InnerAbilityTabs.OTHER_SPELL_TAB, 131, 14409, 2102, 80, new Rune(
			RuneType.WATER, 5), new Rune(RuneType.EARTH, 10), new Rune(RuneType.ASTRAL, 2)),
	MAGIC_IMBUE(Spellbook.LUNAR, InnerAbilityTabs.OTHER_SPELL_TAB, 133, 14411, 2134, 82, new Rune(RuneType.WATER,
			7), new Rune(RuneType.FIRE, 7), new Rune(RuneType.ASTRAL, 2)),
	MAKE_LEATHER(Spellbook.LUNAR, InnerAbilityTabs.OTHER_SPELL_TAB, 134, 14448, 2150, 83, new Rune(RuneType.FIRE,
			2), new Rune(RuneType.BODY, 2), new Rune(RuneType.ASTRAL, 2)),
	FERTILE_SOIL(Spellbook.LUNAR, InnerAbilityTabs.OTHER_SPELL_TAB, 135, 14412, 2166, 83, new Rune(RuneType.EARTH,
			15), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.NATURE, 2)),
	PLANK_MAKE(Spellbook.LUNAR, InnerAbilityTabs.OTHER_SPELL_TAB, 139, 14440, 2230, 86, new Rune(RuneType.EARTH,
			15), new Rune(RuneType.ASTRAL, 2), new Rune(RuneType.NATURE, 1)),
	TUNE_BANE_ORE(Spellbook.LUNAR, InnerAbilityTabs.OTHER_SPELL_TAB, 141, 14452, 2262, 87, new Rune(
			RuneType.EARTH, 4), new Rune(RuneType.ASTRAL, 2)),
	SPELLBOOK_SWAP(Spellbook.LUNAR, InnerAbilityTabs.OTHER_SPELL_TAB, 154, 14441, 2470, 96, new Rune(
			RuneType.COSMIC, 2), new Rune(RuneType.ASTRAL, 3), new Rune(RuneType.LAW, 1)), ;

	private final int level;
	private final Rune[] runes;
	private final int abilityId;
	private final int bookTexture;
	private final int bookChild;
	private final Tab tab;
	private final Spellbook spellbook;

	private Spell(Spellbook book, Tab t, int child, int text, int abil, int level, Rune... runes) {
		this.runes = runes;
		this.spellbook = book;
		this.tab = t;
		this.bookChild = child;
		this.bookTexture = text;
		this.abilityId = abil;
		this.level = level;

		Ability.ALL_ABILITIES.put(this.abilityId, this);
	}

	@Override
	public boolean show() {
		if (!tab.open())
			return false;
		WidgetChild window = Widgets.get(BOOK_WIDGET, CLICK_WINDOW);
		WidgetChild wc = getChild();
		WidgetChild scrollbar = Widgets.get(BOOK_WIDGET, SCROLLBAR);
		if (wc == null || window == null || scrollbar == null || !wc.visible() || !window.visible())
			return false;
		if (window.getBoundingRectangle().contains(wc.getBoundingRectangle()))
			return isVisible();
		if (!Widgets.scroll(wc, scrollbar))
			return false;
		return isVisible();
	}

	@Override
	public boolean isVisible() {
		WidgetChild wc = getChild();
		WidgetChild window = Widgets.get(BOOK_WIDGET, CLICK_WINDOW);
		return tab.isOpen() && wc != null && wc.visible() && window.visible()
				&& window.getBoundingRectangle().contains(wc.getBoundingRectangle());
	}

	@Override
	public boolean available() {
		WidgetChild wc = getChild();
		int curLevel = Skills.getLevel(Skills.MAGIC);
		return getSpellbook().isOpen() && (curLevel >= level || curLevel == 0)
				&& (wc == null || wc.getTextColor() == 0xFFFFFF);
	}

	public Tab getTab() {
		return tab;
	}

	@Override
	public WidgetChild getChild() {
		WidgetChild main = Widgets.get(BOOK_WIDGET, MAIN_CHILD);
		if (main == null)
			return null;
		WidgetChild ret = main.getChild(bookChild);
		if (ret == null || ret.getTextureId() != bookTexture)
			return null;
		return ret;
	}

	@Override
	public Completion getChange() {
		// TODO improve
		return null;
	}

	@Override
	public int getAbilityId() {
		return abilityId;
	}

	/**
	 * Gets the required {@link Rune}s for this spell to be cast
	 * 
	 * @return an array of Runes
	 */
	public Rune[] getRunes() {
		return runes;
	}

	/**
	 * Gets the {@link Spellbook} that this spell corresponds to
	 * 
	 * @return the Spellbook that this spell corresponds to
	 */
	public Spellbook getSpellbook() {
		return spellbook;
	}

	private static final int BOOK_WIDGET = 275, MAIN_CHILD = 16, SCROLLBAR = 20, CLICK_WINDOW = 14;

}
