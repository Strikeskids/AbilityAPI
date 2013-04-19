package sk.map.ultimate.teleport;

import java.util.EnumSet;

import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.Item;

import sk.Script;
import sk.general.EntityUtil;
import sk.general.TimedCondition;
import sk.item.DefinedItem;
import sk.item.ItemInformation;
import sk.item.MultiItemInformation;
import sk.item.RequiredGroup;
import sk.requirement.Requirement;
import sk.widget.Chatbox;

public class ItemTeleport implements Teleport {

	private Location info;

	public ItemTeleport(Location loc) {
		this.info = loc;
	}

	@Override
	public Tile getLocation() {
		return info.getLocation();
	}

	@Override
	public RequiredGroup getItems() {
		return new RequiredGroup(new DefinedItem(1, info.getItem()));
	}

	@Override
	public boolean use() {
		if (TeleportUtil.atEnd(this))
			return true;
		Item it = info.getItem().findItem(false, false);
		if (it == null)
			it = info.getItem().findItem(false, true);
		return it != null
				&& EntityUtil.interact(it.getWidgetChild(), info.getTeleportActions())
				&& new TimedCondition(3000) {

					@Override
					public boolean isDone() {
						return info.getChatboxChoice() != null && Chatbox.isOpen()
								&& Chatbox.hasOption(info.getChatboxChoice()) || Script.isPlayerAnimated(true);
					}

				}.waitStop()
				&& (Chatbox.isOpen() && info.getChatboxChoice() != null ? Chatbox.chooseOption(info
						.getChatboxChoice()) && new TimedCondition(2000) {
					@Override
					public boolean isDone() {
						return Script.isPlayerAnimated(true) && !Chatbox.isOpen();
					}
				}.waitStop() : true) && new TimedCondition(10000) {
					@Override
					public boolean isDone() {
						return !Script.holdExecution() && !Script.isPlayerAnimated();
					}
				}.waitStop() && TeleportUtil.atEnd(this);
	}

	@Override
	public Requirement getRequirements() {
		return Requirement.DONE;
	}

	@Override
	public boolean canUse() {
		return true;
	}

	@Override
	public int getWeight() {
		return (info.isConsumable() ? 30 : 5);
	}

	@Override
	public String toString() {
		return "ItemTeleport: " + info;
	}

	@Override
	public boolean isFreeToPlay() {
		return free.contains(info);
	}

	private static final ItemInformation AMULET_OF_GLORY = new MultiItemInformation(new int[] { 1712, 1710, 1708,
			1706 }, new int[] { 1713, 1711, 1709, 1707 }, new int[] { 4, 3, 2, 1 });
	private static final ItemInformation RING_OF_WEALTH = new MultiItemInformation(new int[] { 20659, 20657,
			20655, 20653 }, new int[] { 20660, 20658, 20656, 20654 }, new int[] { 4, 3, 2, 1 });
	private static final ItemInformation RING_OF_DUELLING = new MultiItemInformation(new int[] { 2552, 2554, 2556,
			2558, 2560, 2562, 2564, 2566 }, new int[] { 2553, 2555, 2557, 2559, 2561, 2563, 2565, 2567 },
			new int[] { 8, 7, 6, 5, 4, 3, 2, 1 });
	private static final ItemInformation GAMES_NECKLACE = new MultiItemInformation(new int[] { 3853, 3855, 3857,
			3859, 3861, 3863, 3865, 3867 }, new int[] { 3854, 3856, 3858, 3860, 3862, 3864, 3866, 3868 },
			new int[] { 8, 7, 6, 5, 4, 3, 2, 1 });
	private static final ItemInformation COMBAT_BRACELET = new MultiItemInformation(new int[] { 1118, 1120, 1122,
			1124 }, new int[] { 1119, 1121, 1123, 1125 }, new int[] { 4, 3, 2, 1 });
	private static final ItemInformation SKILLS_NECKLACE = new MultiItemInformation(new int[] { 11105, 11107,
			11109, 11111 }, new int[] { 11106, 11108, 11110, 11112 }, new int[] { 4, 3, 2, 1 });
	private static final ItemInformation RING_OF_SLAYING = new MultiItemInformation(new int[] { 13281, 13282,
			13283, 13284, 13285, 13286, 13287, 13288 }, new int[] { 8, 7, 6, 5, 4, 3, 2, 1 });
	@SuppressWarnings("unused")
	private static final ItemInformation TOKKUL_ZO = new ItemInformation(23643);

	public static enum Location {
		EDGEVILLE(new Tile(3087, 3497, 0), AMULET_OF_GLORY, true, "Edgeville", "Rub", "Edgeville"),
		KARAMJA(new Tile(2916, 3160, 0), AMULET_OF_GLORY, true, "Karamja", "Rub", "Karamja"),
		DRAYNOR_VILLAGE(new Tile(3103, 3248, 0), AMULET_OF_GLORY, true, "Draynor", "Rub", "Draynor"),
		AL_KHARID(new Tile(3292, 3160, 0), AMULET_OF_GLORY, true, "Al Kharid", "Rub", "Kharid"),

		MOBILISING_ARMIES(
				new Tile(2414, 2844, 0), RING_OF_DUELLING, true, "Mobilising Armies", "Rub", "Mobilising Armies"),
		DUEL_ARENA(new Tile(3314, 3234, 0), RING_OF_DUELLING, true, "Duel Arena", "Rub", "Duel Arena"),
		CASTLEWARS(new Tile(2444, 3088, 0), RING_OF_DUELLING, true, "Castlewars", "Rub", "Castlewars"),

		GRAND_EXCHANGE(new Tile(3164, 3461, 0), RING_OF_WEALTH, true, "Grand", "Rub", "Grand"),

		BARBARIAN_OUTPOST(new Tile(2520, 3569, 0), GAMES_NECKLACE, true, "Barbarian", "Rub", "Barbarian"),

		WARRIORS_GUILD(new Tile(2879, 3540, 0), COMBAT_BRACELET, true, "Warrior", "Rub", "Warrior"),
		CHAMPIONS_GUILD(new Tile(3192, 3365, 0), COMBAT_BRACELET, true, "Champion", "Rub", "Champion"),
		MONASTERY(new Tile(3053, 3493, 0), COMBAT_BRACELET, true, "Monastery", "Rub", "Monastery"),
		RANGING(new Tile(2657, 3440, 0), COMBAT_BRACELET, true, "Ranging", "Rub", "Ranging"),

		FISHING_GUILD(new Tile(2614, 3382, 0), SKILLS_NECKLACE, true, "Fishing", "Rub", "Fishing"),
		CRAFTING_GUILD(new Tile(2935, 3293, 0), SKILLS_NECKLACE, true, "Crafting", "Rub", "Crafting"),
		COOKING(new Tile(3144, 3441, 0), SKILLS_NECKLACE, true, "Cooking", "Rub", "Cooking"),

		SUMONAS_HOUSE(new Tile(3359, 2993, 0), RING_OF_SLAYING, true, "Sumona", "Rub", "Sumona"),
		SLAYER_TOWER(new Tile(3429, 3531, 0), RING_OF_SLAYING, true, "tower", "Rub", "tower"),
		SLAYER_DUNGEON(new Tile(2791, 3613, 0), RING_OF_SLAYING, true, "dungeon", "Rub", "dungeon"),

		CABBAGE_PORT(
				new Tile(3055, 3289, 0), new MultiItemInformation(new int[] { 13562, 19760 }), false, null,
				"Cabbage"),

		RING_OF_KINSHIP(new Tile(3450, 3719, 0), new ItemInformation(false, 15707), false, null, "Teleport"),

		JUJU_SPIRITBAG(new Tile(2953, 2929, 0), new ItemInformation(19967, 19968), true, null, "Teleport"),
		WITCHDOCTOR_MASK(
				new Tile(2953, 2929, 0), new MultiItemInformation(new int[] { 20046, 20050 }), false, null,
				"Teleport"),
		// TODO check

		ARDOUGNE_MONASTERY(new Tile(2607, 3216, 0), new MultiItemInformation(new int[] { 15345, 15347, 15349,
				19748 }), false, "monastery", "monastery", "Teleport"),
		ARDOUGNE_FARM(new Tile(2675, 3374, 0), new ItemInformation(19748), false, "farm", "farm", "Teleport"),
		ECTOPHIAL(new Tile(3660, 3524, 0), new ItemInformation(4251), false, null, "Empty"),

		TELEPORT_CRYSTAL(new Tile(2332, 3171, 0), new MultiItemInformation(new int[] { 6099, 6100, 6101, 6102 },
				new int[] { 4, 3, 2, 1 }), true, null, "Activate"),

		ENCHANTED_LYRE(new Tile(2666, 3636, 0), new MultiItemInformation(new int[] { 3691, 6125, 6126, 6127,
				14590, 14591 }, new int[] { 1, 2, 3, 4, 5, 6 }), true, null, "Play"),

		GOBLIN_VILLAGE_SPHERE(new Tile(2957, 3513, 0), new ItemInformation(true, 11060), true, null, "Break"),

		KARAMJAN_RUM(
				new Tile(3812, 3025, 0), new MultiItemInformation(new int[] { 8940, 8941 }), true, null, "Drink"),

		VARROCK_TAB(new Tile(3213, 3422, 0), new ItemInformation(true, 8007), true, null, "Break"),
		LUMBRIDGE_TAB(new Tile(3227, 3217, 0), new ItemInformation(true, 8008), true, null, "Break"),
		FALADOR_TAB(new Tile(2966, 3383, 0), new ItemInformation(true, 8009), true, null, "Break"),
		CAMELOT_TAB(new Tile(2758, 3476, 0), new ItemInformation(true, 8010), true, null, "Break"),
		ARDOUGNE_TAB(new Tile(2663, 3303, 0), new ItemInformation(true, 8011), true, null, "Break"),

		RIMMINGTON_TAB(new Tile(2956, 3224, 0), new ItemInformation(true, 18809), true, null, "Break"),
		TAVERLEY_TAB(new Tile(2886, 3450, 0), new ItemInformation(true, 18810), true, null, "Break"),
		POLLNIVNEACH_TAB(new Tile(3340, 3005, 0), new ItemInformation(true, 18811), true, null, "Break"),
		RELLEKKA_TAB(new Tile(2676, 3629, 0), new ItemInformation(true, 18812), true, null, "Break"),
		BRIMHAVEN_TAB(new Tile(2761, 3180, 0), new ItemInformation(true, 18813), true, null, "Break"),
		YANILLE_TAB(new Tile(2545, 3091, 0), new ItemInformation(true, 18814), true, null, "Break"),
		TROLLHEIM_TAB(new Tile(2890, 3669, 0), new ItemInformation(true, 20175), true, null, "Break"),

		NULL(null, null, true, null);

		private final Tile loc;
		private final ItemInformation item;
		private final String[] teleportAction;
		private final String chatbox;
		private boolean consumable;

		private Location(Tile loc, ItemInformation item, boolean cons, String menu, String... actions) {
			this.teleportAction = actions;
			this.chatbox = menu;
			this.item = item;
			this.loc = loc;
			this.consumable = cons;
		}

		public boolean isConsumable() {
			return consumable;
		}

		public Tile getLocation() {
			return loc;
		}

		public ItemInformation getItem() {
			return item;
		}

		public String[] getTeleportActions() {
			return teleportAction;
		}

		public String getChatboxChoice() {
			return chatbox;
		}
	}

	private static final EnumSet<Location> free = EnumSet.of(Location.CABBAGE_PORT, Location.RING_OF_KINSHIP);

	public static void load() {
		for (Location loc : Location.values()) {
			if (loc == Location.NULL)
				continue;
			TeleportUtil.add(new ItemTeleport(loc));
		}
	}

}
