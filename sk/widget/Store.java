package sk.widget;

import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.general.ArrayUtil;
import sk.general.EntityUtil;
import sk.general.TimedCondition;

public class Store {

	private static final int WIDGET = 1265;

	private static final int ITEM_SETTING = 300, ITEM_INDEX_SETTING = 301, STORE_TAB_SETTING = 303,
			STORE_TAB_MASK = 0x1, STORE_MODE_SETTING = 307, STORE_MODE_MASK = 0x4000;

	private static final int BUY_TAB = 28, SELL_TAB = 29, WIDE_MODE = 49, THIN_MODE = 50;

	private static final int BOX_LIST = 20, PRICE_LIST = 23, ITEM_LIST = 26, ALLOWED_LIST = 27, BLOCKED_TEXTURE = 2180;

	private static final int CONFIRM_BUTTON = 205, CONFIRM_TEXT = 206;

	private static final int MONEY_LEFT = 209, PRICE_BOX = 207, AMOUNT_BOX = 67;

	private static final int PLUS_1 = 68, PLUS_5 = 69, MAX = 72;

	private static final int MINUS_1 = 73, MINUS_5 = 74, MIN = 75;

	public static boolean buyItem(int amnt, int... ids) {
		return openTab(StoreTab.BUY) && selectItem(findItemIndex(ids)) && setAmount(amnt) && confirm();
	}

	public static boolean sellItem(int amnt, int... ids) {
		return openTab(StoreTab.SELL) && selectItem(findItemIndex(ids)) && setAmount(amnt) && confirm();
	}

	public static boolean confirm() {
		WidgetChild button = getChild(CONFIRM_BUTTON);
		final Item it = getItem(getCurrentIndex());
		final int count, amount;
		return it != null && canConfirm() && visible(button) && (count = it.getStackSize()) != 0
				&& (amount = getAmount()) != 0 && button.click(true) && new TimedCondition(2000) {
					@Override
					public boolean isDone() {
						return it.getStackSize() == count - amount;
					}
				}.waitStop();
	}

	public static boolean setAmount(int amnt) {
		Item it = getItem(getCurrentIndex());
		if (it == null || amnt < 0)
			return false;
		if (amnt == 0)
			amnt = it.getStackSize();
		else if (amnt > it.getStackSize())
			return false;
		if (amnt == getAmount())
			return true;
		if (amnt == 1) {
			WidgetChild wc = getChild(MIN);
			if (!visible(wc) || !wc.click(true))
				return false;
		} else if (amnt == it.getStackSize()) {
			amnt = it.getStackSize();
			WidgetChild wc = getChild(MAX);
			if (!visible(wc) || !wc.click(true))
				return false;
		} else {
			WidgetChild by1, by5;
			if (getAmount() - amnt < 0) {
				by1 = getChild(PLUS_1);
				by5 = getChild(PLUS_5);
			} else {
				by1 = getChild(MINUS_1);
				by5 = getChild(MINUS_5);
			}
			if (!visible(by1, by5))
				return false;
			int dif = Math.abs(getAmount() - amnt);
			int num5 = dif / 5;
			int num1 = dif - num5 * 5;
			for (int i = 0; i < num5; i++) {
				if (!EntityUtil.clickChild(by5))
					return false;
			}
			for (int i = 0; i < num1; i++) {
				if (!EntityUtil.clickChild(by1))
					return false;
			}
		}

		final int endAmnt = amnt;
		return new TimedCondition(2000) {

			@Override
			public boolean isDone() {
				return getAmount() == endAmnt;
			}
		}.waitStop();
	}

	public static boolean canConfirm() {
		WidgetChild button = getChild(CONFIRM_BUTTON), text = getChild(CONFIRM_TEXT);
		int cash = getCurrentCash(), cost = getTotalPrice();
		return visible(button, text) && ArrayUtil.containsSubstring(new String[] { "Buy", "Sell" }, text.getText())
				&& (getTab() == StoreTab.BUY ? cash > cost : Integer.MAX_VALUE - cash > cost);
	}

	public static Item getItem(int index) {
		WidgetChild wc;
		if (hasItem(index) && visible(wc = getList(ITEM_LIST)[index]))
			return new Item(wc);
		return null;
	}

	public static int getPrice(int index) {
		WidgetChild wc;
		if (hasItem(index) && visible(wc = getList(PRICE_LIST)[index]))
			return wc.getChildId();
		return 0;
	}

	public static boolean isAllowed(int index) {
		WidgetChild wc;
		return hasItem(index) && validate(wc = getList(ALLOWED_LIST)[index]) && wc.getTextureId() != BLOCKED_TEXTURE;
	}

	public static int getAmount() {
		try {
			return Integer.parseInt(getChild(AMOUNT_BOX).getText());
		} catch (Exception ex) {
		}
		return 0;
	}

	public static int getTotalPrice() {
		try {
			return Math.max(
					getAmount() * getPrice(getCurrentIndex()),
					Integer.parseInt(getChild(PRICE_BOX).getText().replaceAll("B", "000M").replaceAll("M", "000K")
							.replaceAll("K", "000")));
		} catch (Exception ex) {
		}
		return 0;
	}

	public static int getCurrentCash() {
		try {
			return Integer.parseInt(getChild(MONEY_LEFT).getText().replaceAll("B", "000M").replaceAll("M", "000K")
					.replaceAll("K", "000"));
		} catch (Exception ex) {
		}
		return 0;
	}

	public static int findItemIndex(int... ids) {
		for (WidgetChild wc : getList(ITEM_LIST)) {
			if (ArrayUtil.contains(ids, wc.getChildId()))
				return wc.getChildIndex();
		}
		return -1;
	}

	public static boolean selectItem(final int index) {
		if (index < 0)
			return false;
		WidgetChild[] boxes = getList(BOX_LIST);
		return getCurrentIndex() == index || index < boxes.length && boxes[index].getTextureId() != -1
				&& boxes[index].click(true) && new TimedCondition(1400) {

					@Override
					public boolean isDone() {
						return getCurrentIndex() == index;
					}

				}.waitStop();
	}

	public static boolean isOpen() {
		Widget w = Widgets.get(WIDGET);
		return w != null && w.validate();
	}

	public static int getCurrentItem() {
		return Settings.get(ITEM_SETTING);
	}

	public static int getCurrentIndex() {
		return Settings.get(ITEM_INDEX_SETTING);
	}

	public static boolean hasItem(int index) {
		if (index < 0)
			return false;
		WidgetChild[] boxes = getList(BOX_LIST);
		return index < boxes.length && boxes[index].getTextureId() != -1;
	}

	private static WidgetChild getChild(int index) {
		return Widgets.get(WIDGET, index);
	}

	private static WidgetChild[] getList(int index) {
		WidgetChild outer = getChild(index);
		if (!validate(outer))
			return new WidgetChild[0];
		return outer.getChildren();
	}

	public static boolean openTab(final StoreTab t) {
		if (t == null)
			return false;
		WidgetChild wc;
		return getTab() == t || visible(wc = getChild(t == StoreTab.BUY ? BUY_TAB : SELL_TAB)) && wc.click(true)
				&& new TimedCondition(1400) {

					@Override
					public boolean isDone() {
						return getTab() == t;
					}

				}.waitStop();
	}

	public static boolean setMode(final StoreMode m) {
		if (m == null)
			return false;
		WidgetChild wc;
		return getMode() == m || visible(wc = getChild(m == StoreMode.WIDE ? WIDE_MODE : THIN_MODE)) && wc.click(true)
				&& new TimedCondition(1400) {

					@Override
					public boolean isDone() {
						return getMode() == m;
					}

				}.waitStop();
	}

	public static StoreTab getTab() {
		if (Settings.get(STORE_TAB_SETTING, STORE_TAB_MASK) == STORE_TAB_MASK)
			return StoreTab.SELL;
		else
			return StoreTab.BUY;
	}

	public static StoreMode getMode() {
		if (Settings.get(STORE_MODE_SETTING, STORE_MODE_MASK) == STORE_MODE_MASK)
			return StoreMode.WIDE;
		else
			return StoreMode.THIN;
	}

	public static enum StoreTab {
		BUY, SELL;
	}

	public static enum StoreMode {
		WIDE, THIN;
	}

	private static boolean validate(WidgetChild... children) {
		if (children == null)
			return false;
		for (WidgetChild wc : children) {
			if (wc == null || !wc.validate())
				return false;
		}
		return true;
	}

	private static boolean visible(WidgetChild... children) {
		if (children == null)
			return false;
		for (WidgetChild wc : children) {
			if (wc == null || !wc.visible())
				return false;
		}
		return true;
	}
}
