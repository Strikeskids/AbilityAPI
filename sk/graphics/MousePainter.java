package sk.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import org.powerbot.game.api.methods.input.Mouse;

import sk.general.Getter;
import sk.general.STimer;

public class MousePainter implements Paintable {

	public MousePainter() {
		this(300);
	}

	public MousePainter(int delay) {
		this(Color.blue, Color.red, delay);
	}

	public MousePainter(Color trail, Color center, int del) {
		this(trail, center, del, new Getter<Point>() {
			@Override
			public Point get() {
				return Mouse.getLocation();
			}
		}, new Getter<Long>() {
			@Override
			public Long get() {
				return Mouse.getPressTime();
			}
		});
	}

	public MousePainter(Color trail, Color center, int del, Getter<Point> p, Getter<Long> press) {
		this.trailDelay = del;
		this.trailColor = trail;
		this.centerColor = center;
		this.loc = p;
		this.press = press;
	}

	private final Color trailColor, centerColor;

	private final int trailDelay;

	private final MousePoint[] trail = new MousePoint[50];
	private int start = 0;
	private int end = 0;
	private Getter<Point> loc;
	private Getter<Long> press;
	private int angle = 0;
	private long lastIter = -1;

	@Override
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		MousePoint point = new MousePoint(loc.get());
		MousePoint m = null;
		if (end == 0 || (m = trail[end - 1]) == null || start == end || m.expired() || !m.equals(point)) {
			end += 1;
			end %= trail.length;
			trail[end] = point;
			if (start == end) {
				start++;
			}
		}
		for (start %= trail.length; start != end; start++, start %= trail.length) {
			MousePoint p = trail[start];
			if (p != null && !p.expired())
				break;
		}
		start %= trail.length;
		end %= trail.length;
		int[] xpnts = new int[trail.length], ypnts = new int[trail.length];
		int num = 0;
		for (int i = start; i != end; i++, i %= trail.length, num++) {
			Point p = trail[i];
			xpnts[num] = p.x;
			ypnts[num] = p.y;
		}
		g.setColor(trailColor);
		g.drawPolyline(xpnts, ypnts, num);

		if (lastIter != -1)
			angle += System.currentTimeMillis() - lastIter;
		angle %= ROT;
		lastIter = System.currentTimeMillis();
		g.setColor(centerColor);
		int rad = RADIUS;
		Long ptime = press.get();
		if (ptime == null)
			ptime = 0L;
		long time = System.currentTimeMillis() - ptime;
		if (time < trailDelay) {
			rad = (int) (rad * time / trailDelay);
			g.setColor(trailColor);
		}
		g.drawArc(point.x - rad, point.y - rad, rad * 2, rad * 2, -angle * 360 / ROT, 90);
		g.drawArc(point.x - rad, point.y - rad, rad * 2, rad * 2, -angle * 360 / ROT + 180, 90);
	}

	private static final int RADIUS = 6;
	private static final int ROT = 1440;

	class MousePoint extends Point {

		private static final long serialVersionUID = -4009141819723166400L;
		private STimer start;

		public MousePoint(Point p) {
			super(p);
			this.start = new STimer(trailDelay);
		}

		public boolean expired() {
			return !start.isRunning();
		}

		public long getStartTime() {
			return start.getStartTime();
		}
	}

}
