package sk.action;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.general.EntityUtil;
import sk.general.WidgetUtil;

public class BookUtil {

	public static WidgetChild getChild(final BookDraggable b) {
		if (b == null)
			return null;
		WidgetChild wc = Widgets.get(BookDraggable.BOOK_WIDGET, BookDraggable.MAIN_CHILDREN);
		return (wc == null || (wc = wc.getChild(b.getChildId())) == null
				|| wc.getTextureId() != b.getChildTexture() ? null : wc);
	}

	public static WidgetChild getCooldownChild(final BookDraggable b) {
		if (b == null)
			return null;
		if (getChild(b) == null)
			return null;
		WidgetChild wc = Widgets.get(BookDraggable.BOOK_WIDGET, BookDraggable.COOLDOWN_CHILDREN);
		return (wc == null ? null : wc.getChild(b.getChildId()));
	}

	public static boolean show(final BookDraggable b) {
		if (b == null)
			return false;
		if (!b.getTab().open()) {
			return false;
		}
		final WidgetChild window = Widgets.get(BookDraggable.BOOK_WIDGET, BookDraggable.CLICK_WINDOW);
		final WidgetChild wc = b.getChild();
		final WidgetChild scrollbar = Widgets.get(BookDraggable.BOOK_WIDGET, BookDraggable.SCROLLBAR);
		if (!WidgetUtil.visible(window, wc) || !WidgetUtil.validate(scrollbar)) {
			return false;
		}
		if (window.getBoundingRectangle().contains(wc.getBoundingRectangle())) {
			return isVisible(b);
		}
		if (!Widgets.scroll(wc, scrollbar)) {
			return false;
		}
		return isVisible(b)
				|| (WidgetUtil.visible(window, wc, scrollbar) && WidgetUtil.scroll(wc, scrollbar) && isVisible(b));
	}

	public static boolean isVisible(final BookDraggable b) {
		return isVisible(b, true);
	}

	public static boolean isVisible(BookDraggable b, final boolean bounding) {
		if (b == null)
			return false;
		final WidgetChild wc, window;
		return b.getTab().isOpen()
				&& WidgetUtil.visible(wc = getChild(b),
						window = Widgets.get(BookDraggable.BOOK_WIDGET, BookDraggable.CLICK_WINDOW))
				&& (!bounding || window.getBoundingRectangle().contains(wc.getBoundingRectangle()));
	}

	public static boolean canUse(final BookDraggable b) {
		BarNode n = ActionBar.getNode(b);
		if (n != null)
			return n.canUse();
		return isVisible(b, false) && !WidgetUtil.visible(b.getCooldownChild())
				&& b.getChild().getTextColor() == ActionBar.AVAILABLE_TEXT_COLOR;
	}

	public static boolean use(final BookDraggable b) {
		if (!canUse(b))
			return false;
		BarNode n = ActionBar.getNode(b);
		if (n != null) {
			return n.use();
		}
		return show(b) && EntityUtil.interact(false, getChild(b));
	}

	public static boolean interact(final BookDraggable b, String... actions) {
		if (!canUse(b))
			return false;
		BarNode n = ActionBar.getNode(b);
		if (n != null) {
			return n.use();
		}
		return show(b) && EntityUtil.interact(false, getChild(b), actions);
	}
}
