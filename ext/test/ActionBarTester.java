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

import sk.action.Ability;
import sk.action.ActionBar;
import sk.graphics.Anchor;
import sk.graphics.TextUtil;

@Manifest(authors = { "Strikeskids" }, name = "ActionBarTester")
public class ActionBarTester extends ActiveScript implements PaintListener {
	StringBuilder builder = new StringBuilder(100);

	@Override
	public void onRepaint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setFont(new Font("Arial", 0, 7));
		for (int i = 0; i < 12; i++) {
			builder.setLength(0);
			WidgetChild main = ActionBar.getMainChild(i);
			if (!main.validate()) {
				// System.out.println("Not validated");
				continue;
			}
			Point loc = new Point(main.getAbsoluteX() + main.getWidth() / 2, main.getAbsoluteY());
			builder.append(ActionBar.getSlotType(i).name());
			builder.append("\n");
			builder.append(ActionBar.getId(i));
			builder.append("\n");
			builder.append(ActionBar.isReady(i));
			Ability a = ActionBar.getAbilityInSlot(i);
			if (a != null) {
				builder.append("\n");
				builder.append(a);
			}
			String out = TextUtil.splitTextToWidth(g, main.getWidth(), builder.toString());
			Rectangle rect = TextUtil.getTextRect(g, loc, out, Anchor.BOTTOM);
			g.setColor(Color.WHITE);
			g.fill(rect);
			g.setColor(Color.BLACK);
			g.draw(rect);
			TextUtil.drawTextAtPoint(g, rect.getLocation(), out);
		}
	}

	@Override
	public int loop() {
		// System.out.println(MainTabs.getCurrent());
		// InnerAbilityTabs[] tabs = InnerAbilityTabs.values();
		// ArrayUtil.shuffleInPlace(tabs);
		// for (InnerAbilityTabs t : tabs) {
		// System.out.println("Opening " + t + " " + t.open());
		// Task.sleep(1000);
		// // Tabs.INVENTORY.open(true);
		// }
		// InnerAbilityTabs cur = InnerAbilityTabs.getCurrent();
		// if (cur != null) {
		// int index = 0;
		// for (BookAbility a : BookAbility.values()) {
		// if (a.getTab().equals(cur)) {
		// ActionBar.dragAbilityToSlot(a, index);
		// index++;
		// }
		// }
		// }
		// Spell s = Spell.values()[loc];
		// loc++;
		// loc %= Spell.values().length;
		// if (s.getTab() == InnerAbilityTabs.TELEPORT_TAB) {
		// ActionBar.dragAbilityToSlot(s, index++);
		// index %= 12;
		// return 1000;
		// }
		// return 0;

		return 1000;
	}

	int index = 0;
	int loc = 0;

}
