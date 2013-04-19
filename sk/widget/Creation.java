package sk.widget;

import java.awt.Point;
import java.util.Arrays;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.general.ArrayUtil;
import sk.general.TimeAfterCondition;
import sk.general.TimedCondition;

public class Creation {
	private static final int SELECT_WIDGET = 1371, ITEM_LIST = 44, ITEM_LIST_SCROLLBAR = 47, CATEGORY = 51,
			CATEGORY_DROP_DOWN = 62, CATEGORY_DROP_SCROLLBAR = 61;

	private static final int ITEM_LIST_MULT = 4, MEMBERS_BOX = 3, ITEM_BOX = 2, SELECTED_BOX = 1, AVAILABLE_BOX = 0;

	private static final int ADD_AMOUNT = 35, REMOVE_AMOUNT = 34, AMOUNT_THUMB = 148, AMOUNT_POS = 145,
			AMOUNT_MAX = 144, NUM_TICKS = 143;

	@SuppressWarnings("unused")
	private static final int INFO_WIDGET = 1370, REQUIREMENT_BOX = 57, MATERIAL_BOX = 59, INFO_SCROLLBAR = 61,
			STORE_VALUE = 66, GE_VALUE = 68, HIGH_ALCH_VALUE = 70, XP_GAIN = 52;

	private static final int CURRENT_ITEM = 55, START_BUTTON = 37, AMOUNT_BOX = 73, ENTER_AMOUNT_BOX = 71,
			SELECTED_AVAILABLE = 39;
	
	private static final int CLOSE_BUTTON = 30;

	public static boolean isOpen() {
		return Widgets.get(SELECT_WIDGET).validate();
	}

	private static WidgetChild[] getItemList() {
		WidgetChild list = Widgets.get(SELECT_WIDGET, ITEM_LIST);
		if (list == null || !list.validate())
			return new WidgetChild[0];
		WidgetChild[] children = list.getChildren();
		if (children == null)
			return new WidgetChild[0];
		else
			return children;
	}

	public static int findItemPosition(final int... ids) {
		return findItemPosition(new Filter<Item>() {
			@Override
			public boolean accept(Item t) {
				return t != null && ArrayUtil.contains(ids, t.getId());
			}
		});
	}
	
	public static int findItemPosition(boolean allCategories, final int... ids) {
		if (!allCategories) return findItemPosition(ids);
		for (int i=0;i<1000;i++) {
			if (!selectCategory(i)) return -1;
			int pos = findItemPosition(ids);
			if (pos != -1) return pos;
		}
		return -1;
	}

	public static int findItemPosition(final Filter<Item> filter) {
		WidgetChild[] children = getItemList();
		for (int i = ITEM_BOX; i < children.length; i += ITEM_LIST_MULT) {
			if (filter.accept(new Item(children[i])))
				return i / ITEM_LIST_MULT;
		}
		return -1;
	}

	public static Item getItem(int pos) {
		WidgetChild[] children = getItemList();
		int loc = pos * ITEM_LIST_MULT + ITEM_BOX;
		if (loc < 0 || loc >= children.length)
			return null;
		return new Item(children[loc]);
	}

	public static boolean isItemAvailable(int pos) {
		WidgetChild[] children = getItemList();
		int loc = pos * ITEM_LIST_MULT + AVAILABLE_BOX;
		if (loc < 0 || loc >= children.length)
			return false;
		return children[loc].getTextureId() != -1;
	}

	public static boolean isItemSelected(int pos) {
		WidgetChild[] children = getItemList();
		int loc = pos * ITEM_LIST_MULT + SELECTED_BOX;
		if (loc < 0 || loc >= children.length)
			return false;
		return children[loc].getTextureId() != -1;
	}

	public static boolean isItemMembers(int pos) {
		WidgetChild[] children = getItemList();
		int loc = pos * ITEM_LIST_MULT + MEMBERS_BOX;
		if (loc < 0 || loc >= children.length)
			return false;
		return children[loc].getTextureId() != -1;
	}

	public static int getCategoryIndex(String... category) {
		final WidgetChild catlist = Widgets.get(SELECT_WIDGET, CATEGORY_DROP_DOWN);
		if (catlist == null)
			return -1;
		WidgetChild[] children = catlist.getChildren();
		String[] strs = new String[children.length];
		int loc = 0;
		for (WidgetChild sub : children) {
			String text = sub.getText();
			if (text != null && text.length() > 0) {
				strs[loc++] = text.toLowerCase();
			} else {
				loc++;
			}
		}
		System.out.println(Arrays.toString(strs));
		for (String cat : category) {
			cat = cat.toLowerCase();
			for (int i = 0; i < loc; i++) {
				if (strs[i] != null && (strs[i].contains(cat) || cat.contains(strs[i])))
					return i;
			}
		}
		return -1;
	}

	public static boolean isCategoryAvailable(String... category) {
		return getCategoryIndex(category) >= 0;
	}

	public static String getCategory() {
		WidgetChild cat = Widgets.get(SELECT_WIDGET, CATEGORY);
		if (!isValid(cat))
			return "";
		WidgetChild title = cat.getChild(0);
		if (!isValid(title))
			return "";
		String text = title.getText();
		return text == null ? "" : text;
	}
	
	public static boolean selectCategory(int index) {
		final WidgetChild dropdown = Widgets.get(SELECT_WIDGET, CATEGORY_DROP_DOWN), scroll = Widgets.get(
				SELECT_WIDGET, CATEGORY_DROP_SCROLLBAR), box = Widgets.get(SELECT_WIDGET, CATEGORY);
		if (!isValid(box) || dropdown == null || scroll == null)
			return false;
		final WidgetChild toSelect = dropdown.getChild(index);
		if (toSelect == null)
			return false;
		final String category = toSelect.getText();
		if (getCategory().equals(category))
			return true;
		if (!dropdown.visible() && (!box.click(true) || !new TimedCondition(2000) {
			@Override
			public boolean isDone() {
				return dropdown.visible();
			}

		}.waitStop()))
			return false;
		final WidgetChild it1 = Widgets.get(SELECT_WIDGET, ITEM_LIST).getChild(ITEM_BOX);
		final int sid;
		if (isValid(it1))
			sid = it1.getChildId();
		else
			sid = -1;
		return Widgets.scroll(toSelect, scroll) && toSelect.click(true) && new TimedCondition(2000) {
			@Override
			public boolean isDone() {
				return getCategory().equals(category) && (sid == -1 || it1.getChildId() != sid);
			}

		}.waitStop();
	}

	public static boolean selectCategory(String... possible) {
		return selectCategory(getCategoryIndex(possible));
	}

	public static boolean selectItem(final int index) {
		WidgetChild[] list = getItemList();
		int loc = index * ITEM_LIST_MULT + SELECTED_BOX;
		final WidgetChild box;
		WidgetChild scroll = Widgets.get(SELECT_WIDGET, ITEM_LIST_SCROLLBAR);
		return list != null
				&& loc >= 0
				&& loc < list.length
				&& (box = list[loc]) != null
				&& box.visible()
				&& (box.getTextureId() != -1 || (scroll != null && scroll.visible() && Widgets.scroll(box, scroll)
						&& box.click(true) && new TimedCondition(2000) {

					@Override
					public boolean isDone() {
						return box.getTextureId() != -1;
					}

				}.waitStop()));
	}

	public static Item getCurrentItem() {
		WidgetChild cur = Widgets.get(INFO_WIDGET, CURRENT_ITEM);
		if (cur != null && cur.visible() && cur.getChildId() != -1)
			return new Item(cur);
		else
			return null;
	}

	public static int getGeValue() {
		try {
			return Integer.parseInt(Widgets.get(INFO_WIDGET, GE_VALUE).getText());
		} catch (NullPointerException ignored) {
		} catch (NumberFormatException ignored) {
		}
		return 0;
	}

	public static int getHighAlchemyValue() {
		try {
			return Integer.parseInt(Widgets.get(INFO_WIDGET, HIGH_ALCH_VALUE).getText());
		} catch (NullPointerException ignored) {
		} catch (NumberFormatException ignored) {
		}
		return 0;
	}

	public static int getStoreValue() {
		try {
			return Integer.parseInt(Widgets.get(INFO_WIDGET, STORE_VALUE).getText());
		} catch (NullPointerException ignored) {
		} catch (NumberFormatException ignored) {
		}
		return 0;
	}

	public static int getExperienceGain() {
		try {
			double val = Double.parseDouble(Widgets.get(INFO_WIDGET, XP_GAIN).getText().replaceAll("xp", ""));
			return (int) Math.round(val);
		} catch (NullPointerException ignored) {
		} catch (NumberFormatException ignored) {
		}
		return 0;
	}

	public static int getAmount() {
		try {
			return Integer.parseInt(Widgets.get(INFO_WIDGET, AMOUNT_BOX).getText());
		} catch (NullPointerException ignored) {
		} catch (NumberFormatException ignored) {
		}
		return 0;
	}

	public static boolean isAmountFull() {
		final WidgetChild maxLen = Widgets.get(SELECT_WIDGET, AMOUNT_MAX), amntPos = Widgets.get(SELECT_WIDGET,
				AMOUNT_POS);
		return isValid(maxLen, amntPos) && maxLen.getWidth() == amntPos.getWidth();
	}

	public static boolean isSelectedAvailable() {
		return !isVisible(Widgets.get(INFO_WIDGET, SELECTED_AVAILABLE))
				&& isVisible(Widgets.get(INFO_WIDGET, START_BUTTON));
	}

	public static int getMaxTicks() {
		WidgetChild list = Widgets.get(SELECT_WIDGET, NUM_TICKS);
		if (list == null)
			return 0;
		return list.getChildren().length;
	}

	public static boolean setAmount(int amnt) {
		return setAmount(true, amnt);
	}

	public static boolean setAmount(final boolean useSlider, final int amnt) {
		if (!isSelectedAvailable())
			return false;
		if (amnt == getAmount() || (amnt == 0 && isAmountFull()))
			return true;
		int maxTicks = getMaxTicks();
		if (maxTicks == 0)
			maxTicks = 100;
		if (amnt < 0 || amnt > maxTicks)
			return false;
		if (useSlider) {
			final WidgetChild maxLen = Widgets.get(SELECT_WIDGET, AMOUNT_MAX), amntPos = Widgets.get(SELECT_WIDGET,
					AMOUNT_POS), thumb = Widgets.get(SELECT_WIDGET, AMOUNT_THUMB), increase = Widgets.get(
					SELECT_WIDGET, ADD_AMOUNT), decrease = Widgets.get(SELECT_WIDGET, REMOVE_AMOUNT);
			System.out.println("Slide " + maxLen.visible() + amntPos.visible() + thumb.visible() + increase.visible()
					+ decrease.visible());
			if (!isVisible(maxLen, amntPos, thumb, increase, decrease))
				return false;
			int ratio;
			if (amnt == 0)
				ratio = maxLen.getWidth();
			else
				ratio = amnt * maxLen.getWidth() / maxTicks;
			if (thumb.hover() && thumb.getBoundingRectangle().contains(Mouse.getLocation())) {
				Mouse.drag(new Point(ratio + maxLen.getAbsoluteX(), Mouse.getLocation().y), 2, 10);
				int cur;
				int clicks = 0;
				while (((cur = getAmount()) != amnt && amnt != 0) && clicks < 20) {
					WidgetChild wc = cur < amnt ? increase : decrease;
					if (wc.getBoundingRectangle().contains(Mouse.getLocation()))
						Mouse.click(true);
					else
						wc.click(true);
					Task.sleep(100);
					++clicks;
				}
				return new TimedCondition(1000) {

					private TimeAfterCondition tac = new TimeAfterCondition(700) {
						private int amnt = -1;

						@Override
						public boolean check() {
							int tmp = getAmount();
							if (tmp != amnt) {
								amnt = tmp;
								return true;
							}
							return false;
						}
					};

					@Override
					public boolean isDone() {
						return !tac.isRunning();
					}

				}.waitStop() && (getAmount() == amnt || (amnt == 0 && maxLen.getWidth() == amntPos.getWidth()));
			}
			return false;
		} else {
			WidgetChild wc = Widgets.get(INFO_WIDGET, AMOUNT_BOX);
			final WidgetChild ent = Widgets.get(INFO_WIDGET, ENTER_AMOUNT_BOX);
			if (isVisible(wc) && isValid(ent) && wc.click(true) && new TimedCondition(2000) {

				@Override
				public boolean isDone() {
					return isVisible(ent);
				}

			}.waitStop()) {
				Keyboard.sendText((amnt == 0 ? 1000 : amnt) + "", true);
				return new TimedCondition(2000) {

					@Override
					public boolean isDone() {
						return getAmount() == amnt || amnt == 0 && isAmountFull();
					}

				}.waitStop();
			}
			return false;
		}
	}

	public static boolean startProcess() {
		WidgetChild button = Widgets.get(INFO_WIDGET, START_BUTTON);
		return button != null && isSelectedAvailable() && button.visible() && button.click(true)
				&& new TimedCondition(2000) {

					@Override
					public boolean isDone() {
						return !isOpen();
					}
				}.waitStop();
	}

	private static boolean isVisible(WidgetChild... children) {
		for (WidgetChild wc : children) {
			if (wc == null || !wc.visible())
				return false;
		}
		return true;
	}

	private static boolean isValid(WidgetChild... children) {
		for (WidgetChild wc : children) {
			if (wc == null || !wc.validate())
				return false;
		}
		return true;
	}

	public static boolean close() {
		WidgetChild close = Widgets.get(INFO_WIDGET, CLOSE_BUTTON);
		return !isOpen() || isVisible(close) && close.click(true) && new TimedCondition(2000) {

			@Override
			public boolean isDone() {
				return !isOpen();
			}
			
		}.waitStop();
	}
}
