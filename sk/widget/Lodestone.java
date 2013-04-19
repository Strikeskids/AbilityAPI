package sk.widget;

import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.Player;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.Script;
import sk.action.BookUtil;
import sk.action.magic.AllSpell;
import sk.general.EntityUtil;
import sk.general.TimedCondition;
import sk.map.MapUtil;

public class Lodestone {

	private static int PREV_SETTING = 82, MASK = 0xFF, WIDGET = 1092, SETTING = 3;

	public static boolean canUse(final Location loc) {
		// return true;
		return loc != null
				&& (SETTING == 0 || (Settings.get(SETTING) & loc.getSettingVal()) == loc.getSettingVal());
	}

	public static boolean travel(final Location loc) {
		if (!canUse(loc))
			return false;
		if (atEnd(loc))
			return true;
		final Player me = Players.getLocal();
		if (me == null)
			return false;
		if (isLastLocation(loc)) {
			if (!(BookUtil.interact(AllSpell.HOME_TELEPORT, "previous") && new TimedCondition(2000) {

				@Override
				public boolean isDone() {
					return !me.isMoving() && me.getAnimation() != -1;
				}
			}.waitStop()))
				return false;
			;
		} else {
			WidgetChild onScreen;
			if (BookUtil.use(AllSpell.HOME_TELEPORT) && new TimedCondition(2000) {
				@Override
				public boolean isDone() {
					return Widgets.get(WIDGET).validate();
				}
			}.waitStop() && (onScreen = Widgets.get(WIDGET, loc.getChild())) != null && onScreen.visible()
					&& onScreen.click(true) && new TimedCondition(2000) {
						@Override
						public boolean isDone() {
							return !Widgets.get(WIDGET).validate() && !me.isMoving()
									&& Script.isPlayerAnimated(true);
						}
					}.waitStop()) {

			} else {
				return false;
			}
		}
		return new TimedCondition(20000) {
			@Override
			public boolean isDone() {
				return Script.isPlayerIdle() || atEnd(loc) && !Script.isPlayerAnimated(true);
			}
		}.waitStop() && atEnd(loc);
	}

	public static boolean unlock(final Location loc) {
		return unlock(loc, new Timer(20000));
	}

	public static boolean unlock(final Location loc, final Timer stop) {
		SceneObject obj = getObject(loc);
		return obj != null && EntityUtil.bringOnScreen(true, obj) && stop.isRunning()
				&& EntityUtil.interact(true, obj, "Activate") && new TimedCondition(10000) {

					@Override
					public boolean isDone() {
						return !stop.isRunning() || canUse(loc);
					}

				}.waitStop();
	}

	public static SceneObject getObject(final Location loc) {
		if (loc == null)
			return null;
		return SceneEntities.getNearest(loc.getObjectId());
	}

	public static boolean atEnd(Location lode) {
		return MapUtil.dist(Players.getLocal().getLocation(), lode.getEnd()) < 15
				&& EntityUtil.canReach(getObject(lode));
	}

	public static int getLastTeleportSetting() {
		return (Settings.get(PREV_SETTING)) & MASK;
	}

	public static boolean isLastLocation(Location l) {
		return l != null && l.getPrevVal() == getLastTeleportSetting();
	}

	public static Location getLastLocation() {
		final int val = getLastTeleportSetting();
		for (final Location l : Location.values()) {
			if (l.getPrevVal() == val)
				return l;
		}
		return Location.LUMBRIDGE;
	}

	public static enum Location {
		BANDIT_CAMP("Bandit Camp", 7, 0x8, 0, true, new Tile(3213, 2955, 0), 0), // TODO get bandit camp
																					// lodestone
		LUNAR_ISLE("Lunar Isle", 39, 0x10, 0, true, new Tile(2084, 3913, 0), 69828),
		AL_KHARID("Al Kharid", 40, 0x18, 0x1, false, new Tile(3296, 3185, 0), 69829),
		ARDOUGNE("Ardougne", 41, 0x20, 0x2, true, new Tile(2633, 3347, 0), 69830),
		BURTHORPE("Burthorpe", 42, 0x28, 0x4, true, new Tile(2898, 3544, 0), 69831),
		CATHERBY("Catherby", 43, 0x30, 0x8, true, new Tile(2830, 3451, 0), 69832),
		DRAYNOR_VILLAGE("Draynor", 44, 0x38, 0x10, false, new Tile(3104, 3299, 0), 69833),
		EDGEVILLE("Edgeville", 45, 0x40, 0x20, false, new Tile(3066, 3505, 0), 69834),
		FALADOR("Falador", 46, 0x48, 0x40, false, new Tile(2966, 3403, 0), 69835),
		LUMBRIDGE("Lumbridge", 47, 0x50, 0x80, false, new Tile(3232, 3221, 0), 69836),
		PORT_SARIM("Port Sarim", 48, 0x58, 0x100, false, new Tile(3010, 3216, 0), 69837),
		SEERS_VILLAGE("Seer's Village", 49, 0x60, 0x200, true, new Tile(2690, 3483, 0), 69838),
		TAVERLEY("Taverley", 50, 0x68, 0x400, true, new Tile(2877, 3442, 0), 69839),
		VARROCK("Varrock", 51, 0x70, 0x800, false, new Tile(3214, 3376, 0), 69840),
		YANILLE("Yanille", 52, 0x78, 0x1000, true, new Tile(2528, 3095, 0), 69841);

		private final int set;
		private final int prev;
		private final int child;
		private final Tile endLoc;
		private final boolean members;
		private final String name;
		private final int objectId;

		private Location(String name, int c, int p, int s, boolean mems, Tile end, int obj) {
			this.set = s;
			this.prev = p;
			this.child = c;
			this.endLoc = end;
			this.members = mems;
			this.name = name;
			this.objectId = obj;
		}

		public int getSettingVal() {
			return set;
		}

		public int getChild() {
			return child;
		}

		public Tile getEnd() {
			return endLoc;
		}

		public int getPrevVal() {
			return prev;
		}

		public boolean isMembers() {
			return members;
		}

		public int getObjectId() {
			return objectId;
		}

		public String getName() {
			return name;
		}
	}
}
