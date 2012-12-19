package ext.test;

import java.util.HashMap;

import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.ActionBar;
import sk.general.TimedCondition;
import sk.tab.InnerAbilityTabs;

@Manifest(authors = { "Strikeskids" }, name = "Magic Finder")
public class MagicFinder extends ActiveScript {

	private int i = 0;

	@Override
	public int loop() {
		Widget w = Widgets.get(BOOK_WIDGET);
		if (!w.validate()) {
			System.out.println("Bad widget");
			return 1000;
		}
		InnerAbilityTabs tab = InnerAbilityTabs.getCurrent();
		if (tab == null) {
			System.out.println("Bad tab");
			return 1000;
		}
		WidgetChild list = w.getChild(CLICK_LIST);
		if (list == null || !list.validate()) {
			System.out.println("Bad list");
			return 1000;
		}
		WidgetChild cur = list.getChild(i);
		if (cur == null || !cur.validate())
			return -1;
		final int texture = cur.getTextureId();
		if (!cur.visible()) {
			i++;
			return 0;
		}
		if (!showChild(w.getChild(14), cur, w.getChild(SCROLL_BAR)) || !cur.hover() || new TimedCondition(1000) {

			@Override
			public boolean isDone() {
				// TODO Auto-generated method stub
				return false;
			}
		}.waitStop() || !cur.getBoundingRectangle().contains(Mouse.getLocation())) {
			System.out.println("Bad hover");
			return 100;
		}
		Widget info = Widgets.get(INFO_WIDGET);
		if (!info.validate())
			return 100;
		String name = info.getChild(NAME_CHILD).getText().split("<br>")[0].replaceAll("<[^>]*>", "")
				.replaceAll(" ", "_").replaceAll("-", "").toUpperCase();
		int level = 0;
		StringBuilder runeText = new StringBuilder();
		for (int req = 0; req < 5; req++) {
			WidgetChild rc = info.getChild(REQ_CHILD[req]);
			WidgetChild txt = info.getChild(REQ_CHILD[req] + 1);
			if (rc.getTextureId() == LEVEL_REQ_TEXTURE) {
				level = Integer.parseInt(txt.getText().replaceAll("<[^>]*>", ""));
				continue;
			}
			if (!rc.visible())
				break;
			try {
				int num = Integer.parseInt(txt.getText().replaceAll("<[^>]*>", ""));
				runeText.append(", ");
				runeText.append("new Rune(RuneType.");
				runeText.append(runeNames.get(rc.getTextureId()));
				runeText.append(", ");
				runeText.append(num);
				runeText.append(")");
			} catch (Exception ex) {
			}
		}
		final WidgetChild itemChild = ActionBar.getItemChild(1);
		while (itemChild.getTextureId() != texture) {
			ActionBar.dragWidgetToSlot(cur, 1);
			new TimedCondition(3000) {

				@Override
				public boolean isDone() {
					return itemChild.getTextureId() == texture && !Mouse.isPressed();
				}
			}.waitStop();
		}
		Task.sleep(2000);
		int abil = ActionBar.getAbilityId(1);
		System.out.print(name + "(Spellbook.STANDARD, InnerAbilityTabs." + tab.name() + ", " + i + ", " + texture);
		System.out.println(", " + abil + ", " + level + runeText.toString() + "),");
		i++;
		return 1000;
	}

	private boolean showChild(WidgetChild list, WidgetChild sub, WidgetChild bar) {
		if (list == null || bar == null || !list.validate() || !bar.validate()) {
			System.out.println("Bad super");
			return false;
		}
		if (sub == null || !sub.visible()) {
			System.out.println("Bad sub");
			return false;
		}
		if (list.getBoundingRectangle().contains(sub.getBoundingRectangle()))
			return true;
		return Widgets.scroll(sub, bar) && list.getBoundingRectangle().contains(sub.getBoundingRectangle());
	}

	private static int BOOK_WIDGET = 275, CLICK_LIST = 16, SCROLL_BAR = 20;

	private static int INFO_WIDGET = 638, NAME_CHILD = 1, REQ_CHILD[] = { 44, 49, 53, 57, 61 };

	private static int LEVEL_REQ_TEXTURE = 202;

	//	private static final int[] INDEXES = { 43, 46, 51, 63, 69, 71, 36, 60, 22, 38 };

	private static final HashMap<Integer, String> runeNames = new HashMap<Integer, String>();

	static {
		runeNames.put(14909, "AIR");
		runeNames.put(14912, "WATER");
		runeNames.put(14910, "EARTH");
		runeNames.put(14911, "FIRE");
		runeNames.put(14913, "MIND");
		runeNames.put(14914, "BODY");
		runeNames.put(14919, "CHAOS");
		runeNames.put(14918, "COSMIC");
		runeNames.put(14922, "LAW");
		runeNames.put(14920, "ARMADYL");
		runeNames.put(14921, "NATURE");
		runeNames.put(14916, "DEATH");
		runeNames.put(14923, "SOUL");
	}

}
