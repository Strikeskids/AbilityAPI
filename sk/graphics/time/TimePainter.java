package sk.graphics.time;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import sk.Script;
import sk.graphics.PaintUtil;
import sk.graphics.Paintable;

public class TimePainter implements Paintable {

	private final Rectangle rect;
	private final Color back;
	private final Number number;

	public TimePainter() {
		this(null, Color.blue, Color.black);
	}

	public TimePainter(Color fore, Color back) {
		this(null, fore, back);
	}

	public TimePainter(Rectangle loc, Color fore, Color back) {
		this.rect = loc;
		this.back = back;
		this.number = new Number(-1, fore);
	}

	@Override
	public void paint(Graphics g1) {
		number.setNumber((int) (getElapsed() / 1000));
		number.update(2);

		BufferedImage img = number.getImage();
		Rectangle loc = new Rectangle(rect == null ? new Rectangle(0, 0, img.getWidth(), img.getHeight()) : rect);
		if (this.back == null) {
			g1.drawImage(number.getImage(), loc.x, loc.y, loc.height * img.getWidth() / img.getHeight(),
					loc.height, null);
		} else {
			g1.drawImage(number.getImage(), loc.x, loc.y, loc.height * img.getWidth() / img.getHeight(),
					loc.height, back, null);
		}
	}

	public void paint(Graphics g1, Rectangle loc) {
		number.setNumber((int) (getElapsed() / 1000));
		number.update(2);

		final Color back = this.back == null ? PaintUtil.TRANSPARENT : this.back;
		BufferedImage img = number.getImage();
		if (this.back == null) {
			g1.drawImage(number.getImage(), loc.x, loc.y, loc.height * img.getWidth() / img.getHeight(),
					loc.height, null);
		} else {
			g1.drawImage(number.getImage(), loc.x, loc.y, loc.height * img.getWidth() / img.getHeight(),
					loc.height, back, null);
		}
	}

	public long getElapsed() {
		return Script.getElapsed();
	}

}