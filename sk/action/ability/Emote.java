package sk.action.ability;

import java.util.Arrays;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.interactive.Player;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.Ability;
import sk.general.Completion;
import sk.tab.MainTabs;
import sk.tab.Tab;

/**
 * The emotes in the emote tab stored as abilities.
 * 
 * @author Strikeskids
 * 
 */
public enum Emote implements Ability {
	YES(0, 9, 855), NO(1, 25, 856), BOW(2, 41, 858), ANGRY(3, 57, 859), THINK(4, 73, 857),

	WAVE(5, 89, 863), SHRUG(6, 105, 2113), CHEER(7, 121, 862), BECKON(8, 137, 864),

	LAUGH(9, 153, 861), JUMP_FOR_JOY(10, 169, 2109), YAWN(11, 185, 2111), DANCE(12, 201, 866),

	JIG(13, 217, 2106), TWIRL(14, 233, 2107), HEADBANG(15, 249, 2108), CRY(16, 265, 860),

	BLOW_KISS(17, 281, 1374), PANIC(18, 297, 2105), RASPBERRY(19, 313, 2110), CLAP(20, 329, 865),

	SALUTE(21, 345, 2112);

	private final int[] animation;
	private final int abilId;
	private final int childId;

	private Emote(int child, int abil, int... anim) {
		this.animation = anim;
		Arrays.sort(this.animation);
		this.abilId = abil;
		this.childId = child;
		Ability.ALL_ABILITIES.put(abilId, this);
	}

	@Override
	public boolean show() {
		if (!getTab().open())
			return false;
		final WidgetChild list = Widgets.get(EMOTE_WIDGET, EMOTE_LIST);
		if (list == null || !list.visible())
			return false;
		final WidgetChild emote = list.getChild(childId);
		return Widgets.scroll(emote, Widgets.get(EMOTE_WIDGET, EMOTE_SCROLLBAR)) && emote.visible()
				&& list.getBoundingRectangle().contains(emote.getBoundingRectangle());
	}

	@Override
	public boolean isVisible() {
		if (!getTab().isOpen())
			return false;
		WidgetChild list = Widgets.get(EMOTE_WIDGET, EMOTE_LIST);
		if (list == null || !list.validate())
			return false;
		WidgetChild emote = list.getChild(childId);
		return emote.visible() && list.getBoundingRectangle().contains(emote.getBoundingRectangle());
	}

	@Override
	public boolean available() {
		return Players.getLocal().getAnimation() == -1;
	}

	public Tab getTab() {
		return MainTabs.EMOTES;
	}

	@Override
	public WidgetChild getChild() {
		WidgetChild list = Widgets.get(EMOTE_WIDGET, EMOTE_LIST);
		if (list != null)
			return list.getChild(childId);
		return null;
	}

	@Override
	public Completion getChange() {
		final Player me = Players.getLocal();
		return new Completion() {
			@Override
			public boolean isDone() {
				return me != null && Arrays.binarySearch(animation, me.getAnimation()) >= 0;
			}
		};
	}

	@Override
	public int getAbilityId() {
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
