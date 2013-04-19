package sk.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.powerbot.game.api.wrappers.Tile;

import sk.map.MapUtil;

public class PaintUtil {

	public static final Color TRANSPARENT = new Color(0, 0, 0, 0);

	public static void drawTile(final Graphics g, final Tile t) {
		drawTile(g, t, false);
	}

	public static void drawTile(final Graphics g, final Tile t, final boolean fill) {
		final Point map = MapUtil.worldToMap(t.getX() - 0.5, t.getY() - 0.5);
		if (map.x >= 0) {
			g.fillRect(map.x - 1, map.y - 1, 3, 3);
		}
		if (t.isOnScreen()) {
			if (fill) {
				g.fillPolygon(t.getBounds()[0]);
			} else {
				g.drawPolygon(t.getBounds()[0]);
			}
		}
	}

	public static BufferedImage getImage(String url) {
		if (url == null)
			return null;
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
