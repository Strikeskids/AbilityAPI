package sk.graphics;

import java.awt.Graphics;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.powerbot.core.event.listeners.PaintListener;

public class PaintHandler implements PaintListener, Paintable {

	private final List<Paintable> painters = Collections.synchronizedList(new LinkedList<Paintable>());

	@Override
	public final void paint(Graphics g1) {
		synchronized (painters) {
			for (final Paintable p : painters) {
				p.paint(g1);
			}
		}
	}

	@Override
	public final void onRepaint(Graphics g1) {
		paint(g1);
	}

	public void add(Paintable p) {
		if (p == null) {
			return;
		}
		painters.add(p);
	}

	public boolean remove(Paintable p) {
		return painters.remove(p);
	}

	public void clear() {
		painters.clear();
	}

}
