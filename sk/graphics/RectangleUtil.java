package sk.graphics;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class RectangleUtil {
	public static Rectangle build(final Point s, final Dimension size, final Anchor a) {
		final Point val = (Point) s.clone();
		if (a == Anchor.BOTTOM || a == Anchor.CENTER || a == Anchor.TOP) {
			val.x -= size.width / 2;
		}
		if (a == Anchor.TOP_RIGHT || a == Anchor.RIGHT || a == Anchor.BOTTOM_RIGHT) {
			val.x -= size.width;
		}
		if (a == Anchor.CENTER || a == Anchor.RIGHT || a == Anchor.LEFT) {
			val.y -= size.height / 2;
		}
		if (a == Anchor.BOTTOM || a == Anchor.BOTTOM_LEFT || a == Anchor.BOTTOM_RIGHT) {
			val.y -= size.height;
		}
		return new Rectangle(val, size);
	}
}
