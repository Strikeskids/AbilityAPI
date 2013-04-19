package sk.general;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.HashSet;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Entity;
import org.powerbot.game.api.wrappers.Locatable;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.interactive.Player;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;
import org.powerbot.game.bot.Context;

import sk.action.ActionBar;
import sk.map.CollisionFlags;
import sk.map.FastPath;
import sk.map.MapUtil;
import sk.map.OffsetPoint;

public class EntityUtil {

	private static final Rectangle screen = new Rectangle(4, 54, 512, 334);

	public static boolean isOnScreen(final Entity e) {
		if (e instanceof WidgetChild) {
			return ((WidgetChild) e).visible();
		} else {
			Rectangle loc = screen;
			if (Widgets.get(548).validate()) {
				final Widget w2 = ActionBar.BarWidgets.get();
				if (w2 != null && w2.validate()) {
					final Rectangle rect = Widgets.get(548, 5).getBoundingRectangle();
					final WidgetChild wc1 = w2.getChild(2);
					final WidgetChild wc2 = w2.getChild(4);
					if (wc1.visible()) {
						rect.height -= wc1.getBoundingRectangle().height;
					} else if (wc2.visible()) {
						rect.height -= wc2.getBoundingRectangle().height;
					}
					loc = rect;
				}
			}
			return loc.contains(e.getCentralPoint()) && e.isOnScreen();
		}
	}

	public synchronized static boolean turnTo(final Entity e) {
		if (isOnScreen(e)) {
			return true;
		}
		if (e instanceof Locatable) {
			final Locatable l = (Locatable) e;
			int ang = angleTo(l), prev = ang;
			if (Math.abs(ang) <= 5) {
				return false;
			}
			final char key = (char) (ang < 0 ? KeyEvent.VK_RIGHT : KeyEvent.VK_LEFT);
			final Timer time = new Timer(500);
			Keyboard.pressKey(key, 0, 0);
			while ((((ang = angleTo(l)) & 0x80000000) == (prev & 0x80000000)) && Math.abs(ang) > 5
					&& time.isRunning() && Game.getClientState() == Game.INDEX_MAP_LOADED) {
				if (isOnScreen(e)) {
					break;
				}
				if (ang != prev) {
					time.reset();
					prev = ang;
				}
				Task.sleep(10);
			}
			Keyboard.releaseKey(key, 0, 0);
			return isOnScreen(e);
		}
		return false;
	}

	private static int angleTo(final Locatable l) {
		return Camera.getAngleTo(Camera.getMobileAngle(l) % 360);
	}

	public static boolean bringOnScreen(final boolean move, final Entity e) {
		final Tile nearLoc = e instanceof Locatable ? getReachable((Locatable) e) : null;
		if (e instanceof Locatable && nearLoc == null) {
			return false;
		}
		if (turnTo(e)) {
			return true;
		} else if (e instanceof Locatable && move) {
			final Locatable l = (Locatable) e;
			final FastPath fast = new FastPath(l.getLocation());
			if (new TimedCondition(10000) {
				@Override
				public boolean isDone() {
					fast.traverse();
					return turnTo(e) && !fast.validate();
				}
			}.waitStop(500) && turnTo(e)) {
				return true;
			}
		}
		return false;
	}

	public static boolean interact(final boolean move, final Entity e, final String... actions) {
		if (bringOnScreen(move, e)) {
			final Trigger offScreen = new Trigger();
			boolean nic = false;
			if (e instanceof WidgetChild)
				nic = true;
			final Trigger walked = new Trigger();
			Context.cancelMouse();
			return Mouse.apply(e, new Filter<Point>() {
				Settable<String> name = new Settable<String>(null);

				@Override
				public boolean accept(final Point p) {
					if (!isOnScreen(e)) {
						offScreen.trigger();
						return true;
					}
					return selectMenu(e, name, walked, actions);
				}
			})
					&& !offScreen.isDone()
					&& (nic || walked.isDone() || Game.getCrossHairType() == GeneralConstants.INTERACTION_CROSSHAIR);
		}
		return false;
	}

	public static boolean interact(final Entity e, final String... actions) {
		return interact(false, e, actions);
	}

	public static boolean interact(final Entity e) {
		return interact(false, e);
	}

	public static boolean selectMenu(String... actions) {
		return selectMenu(null, null, null, actions);
	}

	public static boolean selectMenu(Entity e, Settable<String> name, Trigger walked, String... actions) {
		if (Menu.isOpen()) {
			Menu.select("Cancel");
		}
		if (actions.length == 0)
			return Mouse.click(true);
		for (final String s : actions) {
			boolean trig = s.toLowerCase().contains("walk");
			if (name != null && (name.get() == null && e != null ? name.set(getName(e)) : name.get()) != null
					&& name.get().length() > 0 && !name.get().equalsIgnoreCase("null")) {
				// System.out.println(name);
				if (Menu.contains(s, name.get())) {
					if (trig && walked != null)
						walked.trigger();
					return Menu.select(s, name.get());
				}
			} else if (Menu.contains(s)) {
				if (trig)
					walked.trigger();
				return Menu.select(s);
			}
		}
		return false;
	}

	public static String getName(final Entity e) {
		if (e == null)
			return null;
		if (e instanceof org.powerbot.game.api.wrappers.interactive.Character) {
			return ((org.powerbot.game.api.wrappers.interactive.Character) e).getName();
		} else if (e instanceof GroundItem) {
			Item it = ((GroundItem) e).getGroundItem();
			return (it == null ? null : it.getName());
		} else if (e instanceof SceneObject) {
			try {
				String n = ((SceneObject) e).getDefinition().getName();
				return n;
			} catch (Throwable t) {
				return null;
			}
		} else {
			return null;
		}
	}

	public static Tile getReachable(final Locatable l) {
		if (l == null)
			return null;
		final Tile loc = l.getLocation();
		final CollisionFlags flags = new CollisionFlags();
		OffsetPoint base = flags.toPoint(loc);
		if (base == null || !flags.checkPoint(base)) {
			return null;
		}
		final Player me = Players.getLocal();
		if (me == null) {
			return null;
		}
		final Tile meloc = me.getLocation();
		final OffsetPoint mepoint = flags.toPoint(meloc);

		final HashSet<OffsetPoint> locations = new HashSet<OffsetPoint>();
		if (l instanceof SceneObject && ((SceneObject) l).getArea() != null) {
			base = null;
			Tile tmp = null;
			for (Tile t : ((SceneObject) l).getArea().getBoundingTiles()) {
				if (tmp == null || MapUtil.dist(meloc, t) < MapUtil.dist(meloc, tmp))
					tmp = t;
			}
			base = flags.toPoint(tmp);
		}
		if (base == null)
			return null;
		locations.add(base);
		for (int i = -1; i <= 1; i += 2) {
			locations.add(base.derive(i, 0));
			locations.add(base.derive(0, i));
		}
		for (final OffsetPoint p : locations) {
			if (!flags.walkable(p)) {
				continue;
			}
			if (MapUtil.findPath(flags, mepoint, p) != null) {
				return flags.toTile(p);
			}
		}
		return null;
	}

	public static boolean canReach(final Locatable l) {
		return getReachable(l) != null;
	}

	public static boolean interactChild(WidgetChild wc, String... actions) {
		return wc != null
				&& wc.visible()
				&& (wc.getBoundingRectangle().contains(Mouse.getLocation()) && selectMenu(actions) ? true
						: interact(wc, actions));
	}

	public static boolean clickChild(WidgetChild wc) {
		return interactChild(wc);
	}

	public static boolean isAttacking(Player me) {
		final org.powerbot.game.api.wrappers.interactive.Character interact;
		return me != null && me.validate() && (interact = me.getInteracting()) != null && interact.validate()
				&& interact.getHealthPercent() > 0;
	}

	public static boolean isAttacking() {
		return isAttacking(Players.getLocal());
	}

	public static NPC getInteractingWith(final org.powerbot.game.api.wrappers.interactive.Character c) {
		if (c == null || !c.validate())
			return null;
		return NPCs.getNearest(new Filter<NPC>() {
			@Override
			public boolean accept(NPC n) {
				return n != null && n.getInteracting().equals(c);
			}
		});
	}

	public static NPC getInteractingWith() {
		return getInteractingWith(Players.getLocal());
	}
}
