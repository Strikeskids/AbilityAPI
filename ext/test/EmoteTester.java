package ext.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.ActionBar;
import sk.action.ability.Emote;
import sk.graphics.Anchor;
import sk.graphics.TextUtil;

@Manifest(authors = { "Strikeskids" }, name = "EmoteTester")
public class EmoteTester extends ActiveScript implements PaintListener {
	StringBuilder builder = new StringBuilder(100);

	@Override
	public void onRepaint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setFont(new Font("Arial", 0, 9));
		for (Emote e : Emote.values()) {
			builder.setLength(0);
			WidgetChild main = e.getChild();
			if (!main.validate()) {
				// System.out.println("Not validated");
				continue;
			}
			Point loc = new Point(main.getAbsoluteX() + main.getWidth() / 2, main.getAbsoluteY()
					+ main.getHeight() / 2);
			builder.append(e.available());
			builder.append("\n");
			builder.append(e.isVisible());
			builder.append("\n");
			builder.append(e.name());
			Rectangle rect = TextUtil.getTextRect(g, loc, builder.toString(), Anchor.CENTER);
			g.setColor(Color.WHITE);
			g.fill(rect);
			g.setColor(Color.BLACK);
			TextUtil.drawTextAtPoint(g, rect.getLocation(), builder.toString());
		}
	}

	@Override
	public int loop() {
		ActionBar.useAbility(Emote.BLOW_KISS);
		return 1000;
	}
}
