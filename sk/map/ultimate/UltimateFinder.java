package sk.map.ultimate;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.Tile;

import sk.Universal;
import sk.item.AvailableGroup;
import sk.item.ItemCache;
import sk.map.FastPath;
import sk.map.MapData;
import sk.map.MapDataFlags;
import sk.map.MapUtil;
import sk.map.Node;
import sk.map.OffsetPoint;
import sk.map.ultimate.teleport.Teleport;
import sk.map.ultimate.teleport.TeleportUtil;

public class UltimateFinder {

	public static int TELEPORT_WEIGHT = 30;
	private static final int MAX_DIST = 200;
	private static final Tile BASE_TILE = new Tile(0, 0, 0);

	public static PathList findPath(Tile start, Tile end, AvailableGroup items) {
		if (start == null)
			start = Players.getLocal().getLocation();
		if (start == null || end == null)
			return null;
		if (items == null)
			items = new AvailableGroup();
		final MapDataFlags obs = new MapDataFlags();
		if (!obs.walkable(start) || !obs.walkable(end)) {
			return null;
		}
		final OffsetPoint goal = new OffsetPoint(end);
		final Collection<Teleport> teles = getAvailableTeleports(items);
		final Queue<PathList> paths = new PriorityQueue<PathList>();
		paths.add(new PathList(new StaticPathPart(start), end));
		for (Teleport t : teles) {
			paths.add(new PathList(new TeleportPathPart(t), end));
		}
		boolean found = false;
		while (!paths.isEmpty()) {
			final PathList curPath = paths.poll();
			int dte = MapUtil.dist(curPath.getStop(), end);
			if (dte <= 5) {
				return curPath;
			}
			if (dte >= MAX_DIST) {
				if (found)
					continue;
				else
					break;
			}
			PathPart first = curPath.get(0);
			if (first instanceof TeleportPathPart && ((TeleportPathPart) first).getTeleport() instanceof Network) {
				final Network net = (Network) ((TeleportPathPart) first).getTeleport();
				Network[] dest = net.getDestinations();
				LinkedList<PathPart> parts = new LinkedList<>();
				parts.add(new StaticPathPart(start));
				for (Teleport t : teles) {
					if (!net.isSameNetwork(t))
						parts.add(new TeleportPathPart(t));
				}
				Queue<PathList> firsts = new PriorityQueue<>();
				for (PathPart part : parts) {
					int minDist = -1;
					Tile closest = null;
					for (Network n : dest) {
						int dist = MapUtil.dist(part.getDestination(), n.getLocation());
						if (closest == null || minDist > dist) {
							minDist = dist;
							closest = n.getLocation();
						}
					}
					if (closest != null && minDist < MAX_DIST) {
						firsts.add(new PathList(part, closest));
					}
				}
				while (!firsts.isEmpty()) {
					PathList list = firsts.poll();
					Node n = MapUtil.findPath(obs, new OffsetPoint(list.getStop()),
							new OffsetPoint(list.getDestination()), MAX_DIST);
					if (n != null) {
						curPath.add(0, createTilePart(BASE_TILE, n));
						curPath.add(0, list.get(0));
						paths.add(curPath);
						break;
					}
				}
			} else {
				Node n = MapUtil.findPath(obs, new OffsetPoint(curPath.getStop()), goal, 200);
				if (n != null) {
					curPath.add(createTilePart(BASE_TILE, n));
					paths.add(curPath);
					found = true;
				}
			}
		}
		return null;
	}

	// public static List<PathPart> findPath(Tile start, Tile end, AvailableGroup items) {
	// if (start == null) {
	// start = Players.getLocal().getLocation();
	// }
	// if (start == null || end == null)
	// return null;
	// if (items == null)
	// items = new AvailableGroup();
	// final Obstacles obs = new MapDataFlags();
	// final OffsetPoint goal = new OffsetPoint(end);
	// final Node fromMe = MapUtil.findPath(obs, new OffsetPoint(start), goal, 200);
	// final ArrayList<PathPart> ret = new ArrayList<PathPart>();
	// for (final Teleport t : getAvailableTeleports(end, items)) {
	// int curDist = MapUtil.dist(t.getLocation(), end);
	// if (fromMe != null && curDist + TELEPORT_WEIGHT >= fromMe.getG()) {
	// break;
	// }
	// Node fromTele = MapUtil.findPath(obs, new OffsetPoint(t.getLocation()), goal, 200);
	// if (fromTele != null) {
	// ret.add(new TeleportPathPart(t));
	// ret.add(createTilePart(new Tile(0, 0, 0), fromTele));
	// break;
	// }
	// }
	// if (ret.size() > 0)
	// return ret;
	// if (fromMe == null) {
	// return null;
	// } else {
	// ret.add(createTilePart(new Tile(0, 0, 0), fromMe));
	// return ret;
	// }
	// }

	private static PathPart createTilePart(Tile base, Node n) {
		LinkedList<Tile> tiles = new LinkedList<Tile>();
		for (int i = 0; n != null; n = n.getPrev(), i++) {
			if (i % 10 == 0) {
				tiles.addFirst(base.derive(n.getX(), n.getY()));
			}
		}
		return new TilePathPart(tiles.getFirst(), new FastPath(MapData.get().getObjectIds(),
				tiles.toArray(new Tile[tiles.size()])));
	}

	public List<PathPart> findPath(Tile start, Tile end) {
		return findPath(start, end, ItemCache.getItems());
	}

	public static Collection<Teleport> getAvailableTeleports(Tile loc) {
		return getAvailableTeleports(loc, ItemCache.getItems());
	}

	public static Collection<Teleport> getAvailableTeleports(final Tile loc, AvailableGroup items) {
		TeleportUtil.load();
		final Collection<Teleport> teleports = (loc != null ? new TreeSet<Teleport>(new Comparator<Teleport>() {

			@Override
			public int compare(Teleport t1, Teleport t2) {
				int dif = MapUtil.dist(loc, t1.getLocation()) - MapUtil.dist(loc, t2.getLocation());
				if (dif != 0)
					return dif;
				return t1.hashCode() - t2.hashCode();
			}

		}) : new LinkedList<Teleport>());
		for (Teleport t : TeleportUtil.getTeleports()) {
			if (t != null && (Universal.testing || t.getRequirements().meets()) && items.contains(t.getItems())) {
				teleports.add(t);
			}
		}
		for (Collection<Network> col : TeleportUtil.getNetworks()) {
			for (Network t : col) {
				if (t != null && (Universal.testing || t.getRequirements().meets())
						&& items.contains(t.getItems())) {
					teleports.add(t);
				}
			}
		}
		return teleports;
	}

	private static Collection<Teleport> getAvailableTeleports(AvailableGroup items) {
		return getAvailableTeleports(null, items);
	}

}
