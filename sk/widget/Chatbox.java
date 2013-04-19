package sk.widget;

import java.awt.event.KeyEvent;
import java.util.Arrays;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.general.TimedCondition;

public class Chatbox {
	private static final int WIDGET = 1188, TITLE = 20, OPTIONS[] = { 3, 24, 29, 34, 39 }, KEYS[] = {
			KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5 };

	public static boolean isOpen() {
		return Widgets.get(WIDGET).validate();
	}

	public static int findOption(String... options) {
		if (options == null)
			return -1;
		if (options.length == 0)
			return isOpen() ? 0 : -1;
		for (int i = 0; i < options.length; i++) {
			options[i] = options[i].toLowerCase();
		}
		for (int opt = 0; opt < 5; opt++) {
			String s = getOption(opt).toLowerCase();
			if (s.length() > 0) {
				for (String check : options) {
					if (check.contains(s) || s.contains(check))
						return opt;
				}
			}
		}
		return -1;
	}

	public static boolean hasOption(String... options) {
		return findOption(options) >= 0;
	}

	public static boolean chooseOption(boolean fast, String... options) {
		return chooseOption(fast, findOption(options));
	}

	public static boolean chooseOption(String... options) {
		return chooseOption(true, options);
	}

	public static boolean chooseOption(int opt) {
		return chooseOption(true, opt);
	}

	public static boolean chooseOption(boolean fast, int opt) {
		WidgetChild wc = getOptionChild(opt);
		if (wc == null || !wc.visible())
			return false;
		final String[] cache = createCache();
		if (fast) {
			Keyboard.sendKey((char) KEYS[opt]);
		} else if (!wc.click(true)) {
			return false;
		}
		return new TimedCondition(2000) {

			@Override
			public boolean isDone() {
				return !isOpen() || !Arrays.deepEquals(cache, createCache());
			}
		}.waitStop(300);
	}

	public static String getTitle() {
		WidgetChild wc = Widgets.get(WIDGET, TITLE);
		if (wc == null)
			return "";
		else {
			String text = wc.getText();
			return text == null ? "" : text;
		}
	}

	public static String getOption(int opt) {
		WidgetChild wc = getOptionChild(opt);
		if (wc == null)
			return "";
		else {
			String text = wc.getText();
			return text == null ? "" : text;
		}
	}

	public static WidgetChild getOptionChild(int opt) {
		if (opt < 0 || opt >= OPTIONS.length)
			return null;
		return Widgets.get(WIDGET, OPTIONS[opt]);
	}

	private static String[] createCache() {
		if (!isOpen())
			return new String[0];
		String[] ret = new String[6];
		ret[0] = getTitle();
		for (int i = 0; i < 5; i++) {
			ret[i + 1] = getOption(i);
		}
		return ret;
	}

	public static boolean haveConversation(Option... options) {
		final TimedCondition stop = new TimedCondition(20000) {
			@Override
			public boolean isDone() {
				return !isOpen() && !Widgets.canContinue();
			}
		};
		while (stop.isRunning()) {
			if (Widgets.canContinue()) {
				final WidgetChild cont = Widgets.getContinue();
				Keyboard.sendText(" ", false);
				new TimedCondition(1000) {
					@Override
					public boolean isDone() {
						return !cont.visible();
					}
				}.waitStop();
			} else {
				for (Option opt : options) {
					if (opt.meets() && chooseOption(opt.getText())) {
						break;
					}
				}
			}
		}
		return stop.isDone();
	}

	public static class Option {

		private final String text;
		private final String title;

		public Option(String opt) {
			this(null, opt);
		}

		public String getTitle() {
			return title;
		}

		public String getText() {
			return text;
		}

		public Option(String title, String opt) {
			this.title = (title == null ? title : title.toLowerCase());
			this.text = opt.toLowerCase();
		}

		public boolean meets() {
			final String curTitle = getTitle();
			return isOpen()
					&& (getTitle() == null || curTitle != null
							&& (curTitle.contains(getTitle()) || getTitle().contains(curTitle)))
					&& hasOption(text);
		}
	}
}
