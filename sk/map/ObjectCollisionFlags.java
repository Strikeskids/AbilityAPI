package sk.map;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class ObjectCollisionFlags extends CollisionFlags {

	private final Collection<Integer> objIds;
	private final Set<OffsetPoint> objectTiles = new HashSet<OffsetPoint>();

	public ObjectCollisionFlags(final Collection<Integer> objIds) {
		super();
		this.objIds = objIds;
		loadObjects();
	}

	public ObjectCollisionFlags(final Tile base, final Collection<Integer> objIds) {
		super(base);
		this.objIds = objIds;
		loadObjects();
	}

	public ObjectCollisionFlags(final Tile base, final Tile offset, final int[][] flags,
			final Collection<Integer> objIds) {
		super(base, offset, flags);
		this.objIds = objIds;
		loadObjects();
	}

	public void loadObjects() {
		objectTiles.clear();
		for (final SceneObject o : SceneEntities.getLoaded(new Filter<SceneObject>() {

			@Override
			public boolean accept(SceneObject o) {
				return o != null && objIds.contains(o.getId());
			}

		})) {
			if (o == null || o.getLocation() == null) {
				continue;
			}
			objectTiles.add(toPoint(o.getLocation()));
			final Area objectArea = o.getArea();
			if (objectArea == null) {
				continue;
			}
			for (final Tile t : objectArea.getTileArray()) {
				if (t == null) {
					continue;
				}
				objectTiles.add(toPoint(t));
			}
		}
	}

	public boolean isObject(final Tile t) {
		return isObject(toPoint(t));
	}

	public boolean isObject(final OffsetPoint o) {
		return objectTiles.contains(o);
	}

	@Override
	public boolean blocked(final Node n) {
		if (n == null) {
			return true;
		} else if (n.getPrev() == null || objectTiles.contains(n) || objectTiles.contains(n.getPrev())) {
			return false;
		} else {
			return super.blocked(n);
		}
	}

	@Override
	public boolean walkable(final OffsetPoint n) {
		return n != null && objectTiles.contains(n) || super.walkable(n);
	}

	public Set<OffsetPoint> getObjectTiles() {
		return objectTiles;
	}
}
