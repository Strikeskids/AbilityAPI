package sk.graphics;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.StringTokenizer;

public class TextUtil {

	public static Rectangle getTextRect(final Graphics g, final Point p, final String text, final Anchor anchor) {
		final FontMetrics fm = g.getFontMetrics(g.getFont());
		final String[] lines = text.split("\n");
		final Rectangle2D[] bounds = new Rectangle2D[lines.length];
		int width = 0;
		int height = 0;
		for (int i = 0; i < lines.length; i++) {
			final Rectangle2D b = fm.getStringBounds(lines[i], g);
			bounds[i] = b;
			width = Math.max(width, (int) b.getWidth());
			height += (int) b.getHeight();
		}
		final Rectangle ret = RectangleUtil.build(p, new Dimension(width, height), anchor);
		ret.x -= 2;
		ret.height += 2;
		ret.width += 4;
		return ret;
	}

	public static void drawTextAtPoint(final Graphics g, final Point p, final String text) {
		drawTextOnRect(g, new Rectangle(p, new Dimension(0, 0)), text);
	}

	public static void drawTextOnRect(final Graphics g, final Rectangle p, final String text) {
		final FontMetrics fm = g.getFontMetrics(g.getFont());
		final String[] lines = text.split("\n");

		final int x = p.x + 2;
		int y = p.y;

		for (final String line : lines) {
			y += (int) fm.getStringBounds(line, g).getHeight();
			if (y >= p.height && p.height > 0)
				break;
			g.drawString(line, x, y);
		}
	}

	public static String splitTextToWidth(final Graphics g, final int maxWidth, final String text,
			final String delimeter, final boolean cutOff) {
		if (maxWidth < 10) {
			return text;
		}
		final FontMetrics fm = g.getFontMetrics(g.getFont());
		final String[] lines = text.split(delimeter);
		final int spaceWidth = (int) fm.getStringBounds(" ", g).getWidth();

		final StringBuilder out = new StringBuilder(text.length() + 30);

		for (int i = 0; i < lines.length; i++) {
			final String cur = lines[i];
			final StringTokenizer tk = new StringTokenizer(cur);
			final StringBuilder curout = new StringBuilder(cur.length() + 30);
			int width = 0;
			while (tk.hasMoreTokens()) {
				final String token = tk.nextToken();
				final int cw = (int) fm.getStringBounds(token, g).getWidth();
				if (cw + width > maxWidth) {
					if (width != 0 && !cutOff) {
						curout.append(delimeter);
						width = 0;
					}
					int startWidth = width;
					final StringBuilder tmp = new StringBuilder(token.length() * 2);
					for (final char c : token.toCharArray()) {
						tmp.append(c);
						width = startWidth + (int) fm.getStringBounds(tmp.toString(), g).getWidth();
						if (width > maxWidth) {
							tmp.deleteCharAt(tmp.length() - 1);
							curout.append(tmp);
							curout.append(delimeter);
							tmp.setLength(0);
							if (cutOff)
								break;
							tmp.append(c);
							width = (int) fm.getStringBounds(tmp.toString(), g).getWidth();
						}
					}
					if (tmp.length() > 0) {
						curout.append(tmp);
					}
				} else {
					width += cw;
					curout.append(token);
					if (tk.hasMoreTokens()) {
						curout.append(" ");
						width += spaceWidth;
					}
				}
			}
			out.append(curout);
			if (i < lines.length - 1) {
				out.append(delimeter);
			}
		}
		return out.toString();
	}

	public static String splitTextToWidth(final Graphics g, final int maxWidth, final String text,
			final String delimeter) {
		return splitTextToWidth(g, maxWidth, text, delimeter, false);
	}

	public static String splitTextToWidth(final Graphics g, final int w, final String text) {
		return splitTextToWidth(g, w, text, "\n");
	}

	public static void drawTextFromPoint(final Graphics g, final Point p, final String text, final Anchor anchor) {
		drawTextAtPoint(g, getTextRect(g, p, text, anchor).getLocation(), text);
	}
}
