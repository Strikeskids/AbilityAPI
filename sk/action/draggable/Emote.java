package sk.action.draggable;

import java.util.Arrays;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.Draggable;
import sk.tab.MainTabs;
import sk.tab.Tab;

public enum Emote implements Draggable {
	YES(0, 9, 855),
	NO(1, 25, 856),
	BOW(2, 41, 858),
	ANGRY(3, 57, 859),
	THINK(4, 73, 857),

	WAVE(5, 89, 863),
	SHRUG(6, 105, 2113),
	CHEER(7, 121, 862),
	BECKON(8, 137, 864),

	LAUGH(9, 153, 861),
	JUMP_FOR_JOY(10, 169, 2109),
	YAWN(11, 185, 2111),
	DANCE(12, 201, 866),

	JIG(13, 217, 2106),
	TWIRL(14, 233, 2107),
	HEADBANG(15, 249, 2108),
	CRY(16, 265, 860),

	BLOW_KISS(17, 281, 1374),
	PANIC(18, 297, 2105),
	RASPBERRY(19, 313, 2110),
	CLAP(20, 329, 865),

	SALUTE(21, 345, 2112);

	private final int[] animation;
	private final int abilId;
	private final int childId;

	private Emote(final int child, final int abil, final int... anim) {
		this.animation = anim;
		Arrays.sort(this.animation);
		this.abilId = abil;
		this.childId = child;
	}

	@Override
	public boolean show() {
		if (!getTab().open()) {
			return false;
		}
		final WidgetChild list = Widgets.get(EMOTE_WIDGET, EMOTE_LIST);
		if (list == null || !list.visible()) {
			return false;
		}
		final WidgetChild emote = list.getChild(childId);
		return Widgets.scroll(emote, Widgets.get(EMOTE_WIDGET, EMOTE_SCROLLBAR)) && emote.visible() && list
				.getBoundingRectangle().contains(emote.getBoundingRectangle());
	}

	@Override
	public boolean isVisible() {
		if (!getTab().isOpen()) {
			return false;
		}
		final WidgetChild list = Widgets.get(EMOTE_WIDGET, EMOTE_LIST);
		if (list == null || !list.validate()) {
			return false;
		}
		final WidgetChild emote = list.getChild(childId);
		return emote.visible() && list.getBoundingRectangle().contains(emote.getBoundingRectangle());
	}

	public Tab getTab() {
		return MainTabs.EMOTES;
	}

	@Override
	public WidgetChild getChild() {
		final WidgetChild list = Widgets.get(EMOTE_WIDGET, EMOTE_LIST);
		if (list != null) {
			return list.getChild(childId);
		}
		return null;
	}

	@Override
	public int getId() {
		return abilId;
	}

	/**
	 * Gets the animation ids for this emote
	 *
	 * @return an array of animation ids
	 */
	public int[] getAnimations() {
		return animation;
	}

	private static final int EMOTE_WIDGET = 590, EMOTE_LIST = 8, EMOTE_SCROLLBAR = 7;

}
