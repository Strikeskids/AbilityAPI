package sk.map.ultimate;

import java.util.EnumSet;
import java.util.Iterator;

import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.Path;

import sk.Script;
import sk.item.ItemCache;
import sk.map.MapUtil;

public class UltimatePath extends Path {

	private final Tile dest;
	private PathList path;
	private Iterator<PathPart> parts;
	private PathPart cur;

	public UltimatePath(Tile dest) {
		this.dest = dest;
	}

	@Override
	public Tile getEnd() {
		return dest;
	}

	@Override
	public Tile getNext() {
		return null;
	}

	@Override
	public Tile getStart() {
		return null;
	}

	@Override
	public boolean traverse(EnumSet<TraversalOption> opt) {
		if (!init())
			return false;
		if (cur != null && cur.ready() && cur.validate()) {
			cur.traverse();
			return true;
		}
		return false;
	}

	public int getCost() {
		if (init())
			return path.getCost();
		return -1;
	}

	@Override
	public boolean init() {
		if (Script.holdExecution() || getEnd() == null)
			return false;
		if (MapUtil.dist(Players.getLocal().getLocation(), getEnd()) <= 1)
			return true;
		if (parts == null || cur == null) {
			path = UltimateFinder.findPath(Players.getLocal().getLocation(), getEnd(), ItemCache.getItems());
			if (path == null)
				return false;
			parts = path.listIterator();
			cur = parts.next();
		}
		if (cur.ready() && cur.validate())
			return true;
		while (!cur.ready() || !cur.validate()) {
			if (!parts.hasNext()) {
				parts = null;
				return false;
			}
			cur = parts.next();
		}
		return path != null;
	}

	@Override
	public boolean validate() {
		return MapUtil.dist(Players.getLocal().getLocation(), getEnd()) > 1 && init();
	}

	@Override
	public String toString() {
		if (path == null)
			return "Path: " + dest;
		else
			return "Path: " + path;
	}

}
