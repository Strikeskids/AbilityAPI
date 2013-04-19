package sk.map;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.Player;

public class MapUtil {
	public static int dist(final Tile a, final Tile b) {
		if (a == null || b == null) {
			return 0;
		}
		return dist(a.getX(), a.getY(), b.getX(), b.getY());
	}

	public static int dist(final int x1, final int y1, final int x2, final int y2) {
		return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
	}

	public static int dist(final OffsetPoint a, final OffsetPoint b) {
		if (a == null || b == null) {
			return 0;
		}
		return dist(a.getX(), a.getY(), b.getX(), b.getY());
	}

	public static Node findPath(final Obstacles obs, final OffsetPoint start, final OffsetPoint goal) {
		return findPath(obs, start, goal, null);
	}

	public static Node findPath(final Obstacles obs, final OffsetPoint start, final OffsetPoint goal, final int maxDist) {
		return findPath(obs, start, goal, maxDist, null);
	}

	public static Node findPath(final Obstacles obs, final OffsetPoint start, final OffsetPoint goal,
			final Collection<OffsetPoint> looked) {
		return findPath(obs, start, goal, -1, looked);
	}

	public static Node findPath(final Obstacles obs, final OffsetPoint start, final OffsetPoint goal,
			final int maxDist, final Collection<OffsetPoint> looked) {
		final Queue<KeyNode> open = new PriorityQueue<>(100, new Comparator<KeyNode>() {
			@Override
			public int compare(final KeyNode o1, final KeyNode o2) {
				return o1.getF() - o2.getF();
			}
		});
		if (!obs.walkable(start) || !obs.walkable(goal))
			return null;
		final HashSet<OffsetPoint> closed = new HashSet<>();

		open.add(new KeyNode(start.getX(), start.getY(), goal));

		int iter = 0;
		while (!open.isEmpty()) {
			// System.out.println("Iter");
			if (iter++ > 100_000) {
				return null;
			}
			final KeyNode cur = open.poll();
			final OffsetPoint curOff = new OffsetPoint(cur);
			if (!closed.add(curOff)) {
				continue;
			}
			if (looked != null) {
				looked.add(curOff);
			}
			if (goal.equals(cur)) {
				return cur;
			}
			if (cur.getPrev() == null) {
				for (int dx = -1; dx <= 1; dx++) {
					for (int dy = -1; dy <= 1; dy++) {
						if ((dx | dy) == 0) {
							continue;
						}
						final Node n = cur.derive(dx, dy);
						if (!obs.blocked(n)) {
							open.add(new KeyNode(n, goal));
						}
					}
				}
				continue;
			}
			final int dx = cur.getDx();
			final int dy = cur.getDy();
			final LinkedList<Node> tmp = new LinkedList<>();
			for (Node n : getNext(obs, cur, dx, dy)) {
				tmp.add(jump(obs, cur, n.getDx(), n.getDy(), goal));
			}
			for (final Node k : tmp) {
				if (k != null && !closed.contains(k)) {
					if (maxDist <= 0 || k.getG() < maxDist) {
						open.add(new KeyNode(k, goal));
					}
				}
			}
		}
		return null;
	}

	public static Node jump(final Obstacles obs, final Node prev, final int dx, final int dy, final OffsetPoint goal) {
		final Node cur = prev.derive(dx, dy);
		if (obs.blocked(cur)) {
			return null;
		}
		if (goal.equals(cur)) {
			return cur;
		}
		if ((dx | dy) == 0)
			return null;
		List<Node> next = getNext(obs, cur, dx, dy);
		if (dx != 0 && dy != 0 ? (next.size() > 3 || jump(obs, cur, dx, 0, goal) != null || jump(obs, cur, 0, dy, goal) != null)
				: next.size() > 1)
			return cur;
		// if (dy == 0) {
		// if (dx == 0) {
		// return null;
		// }
		// for (int i = -1; i <= 1; i += 2) {
		// final Node block = cur.derive(0, i);
		// final Node force = cur.derive(dx, i);
		// if ((obs.blocked(new Node(block, force)) || obs.blocked(new
		// Node(cur.getPrev(), block)))
		// && !obs.blocked(force)) {
		// return cur;
		// }
		// }
		// } else if (dx == 0) {
		// for (int i = -1; i <= 1; i += 2) {
		// final Node block = cur.derive(i, 0);
		// final Node force = cur.derive(i, dy);
		// if ((obs.blocked(new Node(block, force)) || obs.blocked(new
		// Node(cur.getPrev(), block)))
		// && !obs.blocked(force)) {
		// return cur;
		// }
		// }
		// } else {
		// for (int i = 0; i <= 1; i++) {
		// final int mdx = ((i + 1) % 2 * 2 - 1) * dx;
		// final int mdy = (i * 2 - 1) * dy;
		// final Node block = cur.derive(-i * dx, -(i + 1) % 2 * dy);
		// final Node force = cur.derive(mdx, mdy);
		// if ((obs.blocked(new Node(block, force)) || obs.blocked(new
		// Node(cur.getPrev(), block)))
		// && !obs.blocked(force)) {
		// return cur;
		// }
		// }
		// if ((jump(obs, cur, dx, 0, goal) != null || jump(obs, cur, 0, dy,
		// goal) != null)) {
		// return cur;
		// }
		// }
		return jump(obs, cur, dx, dy, goal);
	}

	public static List<Node> getNext(Obstacles obs, Node cur, int dx, int dy) {
		List<Node> ret = new ArrayList<Node>(8);
		if ((dx | dy) != 0) {
			Node next = cur.derive(dx, dy);
			if (!obs.blocked(next))
				ret.add(next);
			if (dy == 0 || dx == 0) {
				for (int i = -1; i <= 1; i += 2) {
					Node f = cur.derive((dy == 0 ? 0 : i), (dx == 0 ? 0 : i));
					if (!obs.blocked(f) && obs.blocked(cur.getPrev(), f))
						ret.add(f);
					Node f2 = cur.derive((dx == 0 ? i : dx), (dy == 0 ? i : dy));
					if (!obs.blocked(f2) && (obs.blocked(cur.getPrev(), f) || obs.blocked(f, f2)))
						ret.add(f2);
				}
			} else {
				next = cur.derive(0, dy);
				if (!obs.blocked(next))
					ret.add(next);
				next = cur.derive(dx, 0);
				if (!obs.blocked(next))
					ret.add(next);
				Node prev = cur.getPrev();
				Node f = cur.derive(-dx, dy);
				Node b = prev.derive(0, dy);
				if (!obs.blocked(f) && (obs.blocked(b) || obs.blocked(b, f)))
					ret.add(f);
				f = cur.derive(dx, -dy);
				b = prev.derive(dx, 0);
				if (!obs.blocked(f) && (obs.blocked(b) || obs.blocked(b, f)))
					ret.add(f);
			}
		}
		return ret;
	}

	public static Tile getDestination() {
		final Tile dest = Walking.getDestination();
		if (dest.getX() < 10 || dest.getY() < 10) {
			return null;
		}
		return dest;
	}

	public static boolean isResting() {
		// TODO fix resting
		return false;
		// return MapIcon.isResting();
	}

	public static Tile getMapCenter() {
		int x = Camera.getX(), y = Camera.getY(), pitch = Camera.getPitch(), yaw = Camera.getYaw();

		double hypo = -17134.8 + 20500 * Math.cos(0.00483874 * (pitch - 33));
		double theta = (yaw + 90) % 360 * Math.PI / 180;
		int dy = -(int) (hypo * Math.sin(theta));
		int dx = -(int) (hypo * Math.cos(theta));
		int px = x - dx, py = y - dy;
		Tile base = Game.getMapBase();
		if (base == null)
			return null;
		return new Tile(px / 512 + base.getX(), py / 512 + base.getY(), base.getPlane());
	}

	private static Point2D modTile(final Point2D t) {
		final Tile center = getMapCenter();
		Player p = Players.getLocal();
		if (p == null)
			return null;
		final Tile me = p.getLocation();
		if (me == null || center == null || t == null)
			return null;
		return new Point2D.Double(t.getX() + me.getX() - center.getX(), t.getY() + me.getY() - center.getY());
	}

	public static Point worldToMap(Tile t) {
		return worldToMap(t.getX(), t.getY());
	}

	public static Point worldToMap(double x, double y) {
		Point2D m = modTile(new Point2D.Double(x, y));
		Point p = null;
		return m != null && (p = Calculations.worldToMap(m.getX(), m.getY())) != null ? p : new Point(-1, -1);
	}

	public static int getOrientation(final Tile from, final Tile to) {
		double tan = Math.atan2(to.getY() - from.getY(), to.getX() - from.getX());
		return (int) (tan * 180 / Math.PI + (tan < 0 ? 360 : 0));
	}
}
