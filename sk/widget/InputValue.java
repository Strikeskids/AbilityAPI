package sk.widget;

import java.awt.event.KeyEvent;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.general.TimedCondition;

public class InputValue {

	private static final int WIDGET = 752, TITLE = 4, INPUT = 5;

	public static boolean isOpen() {
		final WidgetChild wc = Widgets.get(WIDGET, INPUT);
		return wc != null && wc.visible();
	}

	public static String getTitle() {
		final WidgetChild title = Widgets.get(WIDGET, TITLE);
		if (title == null || !title.visible())
			return "";
		final String val = title.getText();
		return val == null ? "" : val;
	}

	public static String getValue() {
		final WidgetChild input = Widgets.get(WIDGET, INPUT);
		if (input == null || !input.visible())
			return "";
		final String val = input.getText();
		return val == null ? "" : val;
	}

	public static boolean sendInput(String inp) {
		return sendInput(true, inp);
	}

	public static boolean sendInput(final boolean waitForOpen, final String inp) {
		return sendInput(waitForOpen, inp, 0);
	}

	private static boolean sendInput(final boolean waitForOpen, final String inp, int depth) {
		if (inp == null || depth > 3)
			return false;
		if (!new TimedCondition(waitForOpen ? 3000 : 0) {
			@Override
			public boolean isDone() {
				return isOpen();
			}
		}.waitStop())
			return false;
		final String cur = getValue();
		if (!cur.equals(inp)) {
			TimedCondition wait = new TimedCondition(3000) {
				@Override
				public boolean isDone() {
					return inp.startsWith(getValue());
				}
			};
			while (wait.isRunning()) {
				Keyboard.sendKey(KeyEvent.CHAR_UNDEFINED, 100, KeyEvent.VK_BACK_SPACE);
				Task.sleep(50, 100);
			}
			if (wait.isDone()) {
				Keyboard.sendText(inp.substring(getValue().length()), false);
				return sendInput(false, inp, depth + 1);
			} else {
				return false;
			}
		} else {
			Keyboard.sendText("", true);
			return new TimedCondition(3000) {

				@Override
				public boolean isDone() {
					return !isOpen();
				}
			}.waitStop();
		}
	}
}
