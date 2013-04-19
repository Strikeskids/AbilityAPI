package sk;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.powerbot.core.event.EventManager;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Container;
import org.powerbot.core.script.job.Job;
import org.powerbot.core.script.job.LoopTask;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.Player;
import org.powerbot.game.bot.Context;

import sk.general.TimeAfterCondition;
import sk.graphics.MousePainter;
import sk.graphics.PaintHandler;
import sk.graphics.Paintable;
import sk.graphics.main.LogTab;
import sk.graphics.main.MainPaint;
import sk.graphics.main.PaintTab;

public class Script {

	private static ActiveScript self;
	private static String name;
	private static Container jobContainer;
	private static PaintHandler paint;
	private static MainPaint mainPaint;
	private static MouseListener mouse;
	private static MouseMotionListener motion;
	private static List<EventListener> listeners = new CopyOnWriteArrayList<>();

	private static MouseListener paintMouse;
	private static MouseMotionListener paintMotion;

	private static String status;
	private static Timer time;

	public static void load(ActiveScript self) {
		load(self.toString(), self);
	}

	public static void load(String name, ActiveScript self) {
		Script.name = name;
		Script.self = self;
		Script.jobContainer = Script.self.getContainer();
		Script.login.setEndIn(0);
		Script.mapload.setEndIn(0);
		Script.setPaintHandler();
		time = new Timer(0);
		Script.submit(new LoopTask() {
			@Override
			public int loop() {
				Script.refreshListeners();
				return 20000;
			}
		});
	}

	public static String getName() {
		return name;
	}

	public static void setName(String n) {
		Script.name = n;
	}

	public static long getElapsed() {
		if (time == null) {
			return 0L;
		} else {
			return time.getElapsed();
		}
	}

	public static void status(String status) {
		Script.status = status;
		LogTab.addStatus(status);
	}

	public static String getStatus() {
		if (Script.status == null) {
			return "Unknown";
		} else {
			return Script.status;
		}
	}

	public static boolean submit(final Job job) {
		if (job != null && Script.jobContainer != null) {
			Script.jobContainer.submit(job);
			return true;
		} else {
			return false;
		}
	}

	public static boolean submitAndWait(final Job job) {
		if (job != null && Script.jobContainer != null) {
			Script.jobContainer.submit(job);
			job.join();
			return true;
		} else {
			return false;
		}
	}

	private static void setPaintHandler() {
		Script.paint = new PaintHandler();
		Script.paint.add(Script.mainPaint = new MainPaint());
		Script.paint.add(new MousePainter());
		Script.paintMotion = new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				getMainPaint().move(e.getPoint());
			}
		};
		Script.paintMouse = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getMainPaint().click(e.getPoint());
			}
		};
	}

	public static PaintHandler getPaintHandler() {
		return Script.paint;
	}

	public static MainPaint getMainPaint() {
		return mainPaint;
	}

	public static void addPainters(final Paintable... painters) {
		if (painters == null || getPaintHandler() == null) {
			return;
		}
		for (final Paintable p : painters) {
			if (p != null) {
				if (p instanceof PaintTab) {
					getMainPaint().add((PaintTab) p);
				} else {
					getPaintHandler().add(p);
				}
			}
		}
	}

	public static void removePainters(final Paintable... painters) {
		if (painters == null || getPaintHandler() == null) {
			return;
		}
		for (final Paintable p : painters) {
			if (p != null) {
				if (p instanceof PaintTab) {
					getMainPaint().remove((PaintTab) p);
				} else {
					getPaintHandler().remove(p);
				}
			}
		}
	}

	public static void addListeners(EventListener... es) {
		if (es == null)
			return;
		for (EventListener e : es) {
			if (e == null)
				continue;
			if (!listeners.contains(e)) {
				EventManager m = getEventManager();
				if (m != null) {
					m.addListener(e);
					listeners.add(e);
				}
			}
		}
	}

	public static void removeListeners(EventListener... es) {
		if (es == null)
			return;
		for (EventListener e : es) {
			if (e == null)
				continue;
			EventManager m = getEventManager();
			if (m == null)
				continue;
			m.removeListener(e);
			if (listeners.contains(e))
				listeners.remove(e);
		}
	}

	public static ActiveScript getScript() {
		return self;
	}

	public static MouseListener getMouseListener() {
		return mouse;
	}

	public static void setMouseListener(MouseListener mouse) {
		Script.mouse = mouse;
	}

	public static MouseMotionListener getMotionListener() {
		return motion;
	}

	public static void setMotionListener(MouseMotionListener motion) {
		Script.motion = motion;
	}

	private static TimeAfterCondition login = new TimeAfterCondition(5000) {

		@Override
		public boolean check() {
			return !Game.isLoggedIn();
		}
	}, mapload = new TimeAfterCondition(1000) {

		@Override
		public boolean check() {
			return Game.getClientState() != Game.INDEX_MAP_LOADED;
		}

	};

	public static boolean holdExecution() {
		return login.isRunning() || mapload.isRunning();
	}

	private static TimeAfterCondition move = new TimeAfterCondition(900) {
		@Override
		public boolean check() {
			Player me = Players.getLocal();
			if (me == null)
				return true;
			return me.getAnimation() != -1 || me.isMoving() || me.isInCombat();
		}
	};

	private static TimeAfterCondition anim = new TimeAfterCondition(1500) {
		@Override
		public boolean check() {
			Player me = Players.getLocal();
			if (me == null)
				return true;
			return me.getAnimation() != -1;
		}
	};

	public static boolean isPlayerAnimated(boolean now) {
		return (now ? anim.check() : anim.isRunning());
	}

	public static boolean isPlayerAnimated() {
		return isPlayerAnimated(false);
	}

	public static boolean isPlayerIdle() {
		return !move.isRunning() && !isPlayerAnimated();
	}

	@SuppressWarnings("deprecation")
	public static Context getContext() {
		return Context.get();
	}

	public static EventManager getEventManager() {
		// Context c =
		// Context.context.get(Thread.currentThread().getThreadGroup());

		Context c = getContext();
		return c.getEventManager();
	}

	public static void refreshListeners() {
		EventManager evt = getEventManager();
		if (evt == null) {
			System.out.println("Could not add listeners to event manager");
			return;
		}
		evt.addListener(self);
		if (Script.paint != null) {
			evt.addListener(Script.paint);
		}
		if (Script.motion != null) {
			evt.addListener(Script.motion);
		}
		if (Script.mouse != null) {
			evt.addListener(Script.mouse);
		}
		if (Script.paintMotion != null) {
			evt.addListener(Script.paintMotion);
		}
		if (Script.paintMouse != null) {
			evt.addListener(Script.paintMouse);
		}
		for (EventListener e : listeners) {
			evt.addListener(e);
		}
	}

	public static void deleteListeners() {
		EventManager evt = getEventManager();
		if (evt == null) {
			return;
		}
		evt.removeListener(self);
		if (Script.paint != null) {
			evt.removeListener(Script.paint);
		}
		if (Script.motion != null) {
			evt.removeListener(Script.motion);
		}
		if (Script.mouse != null) {
			evt.removeListener(Script.mouse);
		}
		if (Script.paintMotion != null) {
			evt.removeListener(Script.paintMotion);
		}
		if (Script.paintMouse != null) {
			evt.removeListener(Script.paintMouse);
		}
		Script.mouse = null;
		Script.paint = null;
		Script.motion = null;
		removeListeners(listeners.toArray(new EventListener[listeners.size()]));
	}

	public static void stop() {
		deleteListeners();
		self.stop();
	}
	


}
