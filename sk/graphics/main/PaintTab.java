package sk.graphics.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import sk.graphics.Paintable;
import sk.graphics.TextUtil;

public abstract class PaintTab implements Paintable {

	private final int size;
	private final BufferedImage image;

	public PaintTab() {
		this(-1);
	}

	public PaintTab(int h) {
		this(null, h);
	}

	public PaintTab(BufferedImage img) {
		this(img, -1);
	}

	public PaintTab(BufferedImage img, int h) {
		this.size = (h >= 0 ? h : DEFAULT_HEIGHT);
		this.image = img;
	}

	public int getSize() {
		return size;
	}

	public BufferedImage getImage() {
		return image;
	}

	public abstract String[] getStrings();

	@Override
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		StringBuilder b = new StringBuilder();
		for (String s : getStrings()) {
			if (s != null)
				b.append(s);
			b.append("\n");
		}
		g.setColor(MainPaint.TEXT_COLOR);
		g.setFont(MainPaint.FONT);
		TextUtil.drawTextAtPoint(g, new Point(3, 0), b.toString());
	}

	public void click(Point p) {

	}

	public static final int DEFAULT_HEIGHT = 250, MAX_LINES = 22;


}
