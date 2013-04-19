package sk.map;

import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedList;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.Verifiable;
import org.powerbot.game.api.wrappers.interactive.Player;
import org.powerbot.game.api.wrappers.map.Path;
import org.powerbot.game.api.wrappers.node.SceneObject;

import sk.Script;
import sk.general.EntityUtil;
import sk.general.TimedCondition;
import sk.widget.Chatbox;
import sk.widget.Chatbox.Option;

public class FastPath extends Path {

	private final Tile[] destinations;
	private final Tile end;
	private Collection<Integer> objIds;

	private Tile base;
	private Node path;
	private Tile[] tileCache;
	private CollisionFlags obs;

	public FastPath(final Tile... end) {
		this.destinations = end;
		this.end = destinations[destinations.length - 1];
	}

	public FastPath(final Collection<Integer> ids, final Tile... end) {
		this(end);
		this.objIds = ids;
	}

	@Override
	public boolean traverse(final EnumSet<TraversalOption> options) {
		final Player me = Players.getLocal();
		if (me == null) {
			return false;
		}
		final Tile loc = me.getLocation();
		final boolean moving = me.isMoving();
		if (loc == null || MapUtil.dist(loc, getEnd()) <= 1) {
			return false;
		}
		final Node nextNode;
		if (!init() || (nextNode = getNextNode()) == null) {
			return false;
		}
		Tile next = obs.toTile(nextNode);
		final Tile dest = Walking.getDestination();
		Chatbox.haveConversation(new Option("What would you like to do?", "Leave the starting"));
		if (objIds != null && ((ObjectCollisionFlags) obs).isObject(nextNode)) {
			SceneObject tmp = null;
			for (final SceneObject o : SceneEntities.getLocalAt(nextNode.getX(), nextNode.getY(), -1)) {
				if (objIds.contains(o.getId())) {
					tmp = o;
					break;
				}
			}
			if (tmp != null) {
				final SceneObject obj = tmp;
				if (EntityUtil.isOnScreen(obj)) {
					final Verifiable waitObj = new Verifiable() {
						@Override
						public boolean validate() {
							final Tile dest = Walking.getDestination();
							return (me.isMoving() && dest != null && MapUtil.dist(dest, obj.getLocation()) < 3)
									|| (MapUtil.dist(obj.getLocation(), me.getLocation()) < 3
											&& Script.isPlayerAnimated() && !MapUtil.isResting());
						}
					};
					if (waitObj.validate()) {
						new TimedCondition(2000) {

							@Override
							public boolean isDone() {
								return !waitObj.validate();
							}

						}.waitStop();
						return false;
					}
					return EntityUtil.interact(true, obj, ACTIONS) && new TimedCondition(2000) {
						@Override
						public boolean isDone() {
							return waitObj.validate() || !obj.validate();
						}
					}.waitStop();
				} else {
					next = obs.toTile(nextNode.getPrev());
				}
			} else {
				((ObjectCollisionFlags) obs).loadObjects();
				return true;
			}
		}

		if (moving && dest != null
				&& (next.equals(getEnd()) && MapUtil.dist(dest, next) < 3 || MapUtil.dist(dest, next) < 5)) {
			return true;
		}
		if (options != null && options.contains(TraversalOption.HANDLE_RUN) && !Walking.isRunEnabled()
				&& Walking.getEnergy() > 50) {
			Walking.setRun(true);
			Task.sleep(300);
		}
		if (MapUtil.dist(loc, next) < 10 && !EntityUtil.interact(next, "Walk")) {
			return false;
		} else if (!Walking.walk(next)) {
			return false;
		}
		Camera.turnTo(next);
		final Tile n = next;
		return new TimedCondition(2000) {

			@Override
			public boolean isDone() {
				return me.isMoving() && MapUtil.dist(Walking.getDestination(), n) < 3;
			}
		}.waitStop();
	}

	public Tile[] getTiles() {
		if (init() && tileCache == null) {
			final LinkedList<Tile> tiles = new LinkedList<>();
			for (Node tmp = path; tmp != null; tmp = tmp.getPrev()) {
				tiles.addFirst(obs.toTile(tmp));
			}
			tileCache = tiles.toArray(new Tile[tiles.size()]);
		}
		return tileCache;
	}

	public Tile[] getDestinations() {
		return destinations;
	}

	@Override
	public boolean init() {
		final Player me = Players.getLocal();
		if (me == null) {
			return false;
		}
		final Tile start = me.getLocation();
		if (start == null) {
			return false;
		}
		if (base != null && base.equals(Game.getMapBase()) && path != null) {
			int minDist = Integer.MAX_VALUE;

			final Tile pathEnd = base.derive(path.getX(), path.getY());

			if (MapUtil.dist(start, pathEnd) >= 5 || pathEnd.equals(getEnd())) {
				for (Node tmp = path; tmp != null && tmp.getPrev() != null; tmp = tmp.getPrev()) {
					if (obs.blocked(tmp)) {
						resetPath();
						break;
					}
					minDist = Math.min(
							MapUtil.dist(start.getX(), start.getY(), tmp.getX() + base.getX(),
									tmp.getY() + base.getY()), minDist);
				}
			} else {
				base = null;
			}
			if (minDist > 7) {
				base = null;
			}
		} else {
			base = null;
		}
		if (base == null) {
			path = null;
			getObstacles();
			Tile end = getCurrentDestination();
			if (end != null && MapUtil.dist(end, start) < 100) {
				OffsetPoint goal = new OffsetPoint(end.getX() - base.getX(), end.getY() - base.getY());
				final OffsetPoint startNode = new OffsetPoint(start.getX() - base.getX(), start.getY()
						- base.getY());
				goal = obs.pullUntilWalkable(goal, startNode);
				path = MapUtil.findPath(obs, startNode, goal);
			}
		}
		return base != null && path != null;
	}

	public Tile getCurrentDestination() {
		final Tile start = Players.getLocal().getLocation();
		int minDist = Integer.MAX_VALUE;
		for (Tile t : destinations) {
			minDist = Math.min(minDist, MapUtil.dist(start, t));
		}
		if (minDist > 100)
			return null;
		int pangle = -1;
		for (int i = destinations.length - 1; i >= 0; i--) {
			Tile cur = destinations[i];
			int cangle = MapUtil.getOrientation(start, cur);
			if (cur.isOnMap()) {
				if (pangle == -1 || Math.abs(cangle - pangle) % 180 < 90)
					return cur;
				else
					return destinations[i + 1];
			}
			if (MapUtil.dist(cur, start) == minDist)
				return cur;
		}
		return null;
	}

	public CollisionFlags getObstacles() {
		if (base == null) {
			base = Game.getMapBase();
			obs = null;
		}
		if (obs != null) {
			if (obs instanceof ObjectCollisionFlags) {
				((ObjectCollisionFlags) obs).loadObjects();
			}
			return obs;
		}
		if (objIds != null) {
			return obs = new ObjectCollisionFlags(base, objIds);
		} else {
			return obs = new CollisionFlags(base);
		}
	}

	public CollisionFlags getNoObjectObstacles() {
		if (base == null) {
			base = Game.getMapBase();
			obs = null;
		}
		if (obs != null && !(obs instanceof CollisionFlags)) {
			return obs;
		}
		return new CollisionFlags(base);
	}

	private void resetPath() {
		base = null;
		path = null;
		tileCache = null;
		obs = null;
	}

	@Override
	public boolean validate() {
		final Player me = Players.getLocal();
		return me != null && init() && MapUtil.dist(me.getLocation(), getEnd()) > 1;
	}

	public Node getNextNode() {
		if (!init()) {
			return null;
		}

		if (objIds != null) {
			final Player me = Players.getLocal();
			if (me == null) {
				return null;
			}
			final ObjectCollisionFlags obs = (ObjectCollisionFlags) this.obs;
			final CollisionFlags flags = getNoObjectObstacles();
			final OffsetPoint loc = obs.toPoint(me.getLocation());
			Node closest = null;
			int minDist = Integer.MAX_VALUE;
			for (Node tmp = path; tmp != null; tmp = tmp.getPrev()) {
				final int curDist = MapUtil.dist(tmp, loc);
				if (curDist < minDist) {
					closest = tmp;
					minDist = curDist;
				}
			}
			Node best = null;
			Node prev = null;
			for (Node tmp = path; tmp != null; prev = tmp, tmp = tmp.getPrev()) {
				final Tile cur = base.derive(tmp.getX(), tmp.getY());
				if (prev != null && flags.blocked(prev, tmp)) {
					best = null;
				}
				if (cur.isOnMap() && best == null) {
					best = tmp;
				}
				if (tmp.equals(closest)) {
					break;
				}
			}
			return best;
		} else {
			for (Node tmp = path; tmp != null; tmp = tmp.getPrev()) {
				final Tile cur = base.derive(tmp.getX(), tmp.getY());
				if (cur.isOnMap()) {
					return tmp;
				}
			}
		}
		return null;

	}

	@Override
	public Tile getNext() {
		final Node next = getNextNode();
		if (next == null) {
			return prevNext = null;
		}
		return prevNext = obs.toTile(next);
	}

	private Tile prevNext;

	public Tile getPrevNext() {
		return prevNext;
	}

	@Override
	public Tile getStart() {
		return null;
	}

	@Override
	public Tile getEnd() {
		return end;
	}

	public static final String[] ACTIONS = { "open", "climb", "enter", "jump", "squeeze", "cross" };

	public int length() {
		if (path == null) {
			return -1;
		}
		int ret = 0;
		for (Node cur = path; cur != null; cur = cur.getPrev(), ret++) {
			;
		}
		return ret;
	}

}
