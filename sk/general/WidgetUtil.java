package sk.general;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class WidgetUtil {

	public static boolean visible(WidgetChild... wc) {
		if (wc == null || wc.length == 0)
			return false;
		for (WidgetChild c : wc) {
			if (c == null || !c.visible())
				return false;
		}
		return true;
	}
	
	public static boolean validate(WidgetChild... wc) {
		if (wc == null || wc.length == 0)
			return false;
		for (WidgetChild c : wc) {
			if (c == null || !c.validate())
				return false;
		}
		return true;
	}

	public static boolean scroll(WidgetChild wc, WidgetChild scrollbar) {
		return Widgets.scroll(wc, scrollbar);
	}

}
