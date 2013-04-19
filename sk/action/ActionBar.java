package sk.action;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.action.ability.Ability;
import sk.action.ability.AttackAbility;
import sk.action.ability.BarAbility;
import sk.action.ability.ConstitutionAbility;
import sk.action.ability.DefenseAbility;
import sk.action.ability.MagicAbility;
import sk.action.ability.RangedAbility;
import sk.action.ability.StrengthAbility;
import sk.action.draggable.Emote;
import sk.action.magic.AllSpell;
import sk.action.magic.AncientSpell;
import sk.action.magic.FreeSpell;
import sk.action.magic.LunarSpell;
import sk.action.magic.Spell;
import sk.action.magic.StandardSpell;
import sk.general.EntityUtil;
import sk.general.TimedCondition;
import sk.general.WidgetUtil;
import sk.tab.MainTabs;

public class ActionBar {

	/**
	 * The total number of slots on the Action Bar
	 */
	public static final int NUM_SLOTS = 12;
	private static final int ADRENALINE_SETTING = 679;
	public static final int AVAILABLE_TEXT_COLOR = 0xFFFFFF;

	// Keyboard
	public static final int FOCUSED_TEXT_COLOR = 0x0000FF;

	// Expanding, currentBar, and locking
	private static final int BAR_LOCKED = 682, BAR_LOCKED_MASK = 0x10;
	private static final int CURRENT_BAR_SETTING = 682, CURRENT_BAR_MASK = 0x7, CURRENT_BAR_SHIFT = 5;

	public static final int MAX_BAR = 5;

	/**
	 * A slot order comparator. Sorts {@link BarNode}s by their order in the Action Bar
	 */
	public static final Comparator<BarNode> SLOT_ORDER = new Comparator<BarNode>() {
		@Override
		public int compare(BarNode o1, BarNode o2) {
			return (o1 == null ? (o2 == null ? 0 : 1) : (o2 == null ? -1 : o1.getSlot() - o2.getSlot()));
		}
	};

	/**
	 * Gets the current adrenaline of the player
	 * 
	 * @return The adrenaline of the player (0-1000)
	 */
	public static int getAdrenaline() {
		return Settings.get(ADRENALINE_SETTING);
	}

	/**
	 * A reverse slot order comparator. Sorts {@link BarNode}s by the opposite of their order in the Action
	 * Bar
	 */
	public static final Comparator<BarNode> REVERSE_SLOT_ORDER = new Comparator<BarNode>() {
		@Override
		public int compare(BarNode o1, BarNode o2) {
			return -SLOT_ORDER.compare(o1, o2);
		}
	};

	private static List<BarNode> listNodes(Filter<BarNode> filter, Comparator<BarNode> sorter) {
		List<BarNode> nodes = new ArrayList<BarNode>(NUM_SLOTS);
		for (int i = 0; i < NUM_SLOTS; i++) {
			BarNode cur = getNode(i);
			if (cur != null && (filter == null || filter.accept(cur)))
				nodes.add(cur);
		}
		if (nodes.size() > 0 && sorter != null && sorter != SLOT_ORDER)
			Collections.sort(nodes, sorter);
		return nodes;
	}

	/**
	 * Gets the first {@link BarNode} that meets the {@link Filter} when sorted by the {@link Comparator}
	 * 
	 * @param filter
	 *            Which BarNodes to keep
	 * @param sorter
	 *            the order to sort the {@link BarNode}s
	 * @return The first {@link BarNode} found or <tt>null</tt> if none matched the {@link Filter}
	 * @see getNode(Filter)
	 * @see getNode(Draggable...)
	 * @see getNode(int)
	 */
	public static BarNode getNode(Filter<BarNode> filter, Comparator<BarNode> sorter) {
		for (BarNode b : listNodes(filter, sorter))
			return b;
		return null;
	}

	/**
	 * Gets the first {@link BarNode} the meets the {@link Filter}
	 * 
	 * @param accept
	 *            Which {@link BarNode}s to keep
	 * @return The first {@link BarNode} found or <tt>null</tt> if none match the {@link Filter}
	 * @see getNode(Filter, Comparator)
	 * @see getNode(Draggable...)
	 * @see getNode(int)
	 */
	public static BarNode getNode(Filter<BarNode> accept) {
		return getNode(accept, SLOT_ORDER);
	}

	/**
	 * Gets the first {@link BarNode} that is a {@link BarDraggable} of the specified type
	 * 
	 * @param find
	 *            The {@link Draggable} to find
	 * @return The first {@link BarNode} if the {@link Draggable} was found or <tt>null</tt> if the
	 *         {@link Draggable} is not on the Action Bar
	 * @see getNode(int)
	 * @see getNode(Filter, Comparator)
	 * @see getNode(Filter)
	 */
	public static BarNode getNode(final Draggable... find) {
		return getNode(new Filter<BarNode>() {
			@Override
			public boolean accept(BarNode t) {
				if (t == null || !(t instanceof BarDraggable))
					return false;
				Draggable cur = ((BarDraggable) t).getDraggable();
				for (Draggable a : find) {
					if (a != null && cur.equals(a))
						return true;
				}
				return false;
			}
		}, SLOT_ORDER);
	}

	/**
	 * Gets the {@link BarNode} at the specified slot
	 * 
	 * @param slot
	 *            The slot to get
	 * @return The {@link BarNode} if one exists at that slot and the slot number is valid or <tt>null</tt>
	 *         otherwise
	 * @see getNode(Filter)
	 * @see getNode(Filter, Comparator)
	 * @see getNode(Draggable...)
	 */
	public static BarNode getNode(int slot) {
		Draggable d = Draggable.ALL.get(SlotData.getDraggableId(slot));
		if (d != null) {
			if (d instanceof Ability) {
				return new BarAbility((Ability) d, slot);
			}
			if (d instanceof Spell) {
				return new BarSpell((Spell) d, slot);
			}
			return new BarDraggable(d, slot);
		}
		int itemId = SlotData.getItemId(slot);
		if (itemId != SlotData.DEFAULT_ITEM_SETTING)
			return new BarItem(itemId, slot);
		return null;
	}

	/**
	 * Gets all the nodes that match the specified {@link Filter} in the order specified by the
	 * {@link Comparator}
	 * 
	 * @param filter
	 *            {@link Filter} which ones are chosen
	 * @param sorter
	 *            The order to return the nodes
	 * @return An Array of {@link BarNode}s that were found
	 * @see getNode(Filter, Comparator)
	 * @see getNodes(Filter)
	 * @see getNodes()
	 */
	public static BarNode[] getNodes(Filter<BarNode> filter, Comparator<BarNode> sorter) {
		List<BarNode> nodes = listNodes(filter, sorter);
		return nodes.toArray(new BarNode[nodes.size()]);
	}

	/**
	 * Gets all the nodes that match the specified {@link Filter} in the order on the Action Bar
	 * 
	 * @param filter
	 *            {@link Filter} the {@link BarNodes}
	 * @return An Array of {@link BarNode}s that were found
	 * @see getNode(Filter)
	 * @see getNodes(Filter, Comparator)
	 * @see getNodes()
	 */
	public static BarNode[] getNodes(Filter<BarNode> filter) {
		return getNodes(filter, SLOT_ORDER);
	}

	/**
	 * Gets all the non-<tt>null</tt> {@link BarNode}s
	 * 
	 * @return An Array of all {@link BarNode}s that were not null
	 * @see getNodes(Filter)
	 * @see getNodes(Filter, Comparator)
	 */
	public static BarNode[] getNodes() {
		List<BarNode> nodes = new ArrayList<BarNode>(NUM_SLOTS);
		for (int i = 0; i < NUM_SLOTS; i++) {
			BarNode node = getNode(i);
			if (node != null)
				nodes.add(node);
		}
		return nodes.toArray(new BarNode[nodes.size()]);
	}

	/**
	 * Gets all the nodes. Has a constant fixed size
	 * 
	 * @return An Array of all the {@link BarNode}s
	 * @see NUM_SLOTS
	 * @see getNodes()
	 */
	public static BarNode[] getAllNodes() {
		BarNode[] ret = new BarNode[NUM_SLOTS];
		for (int i = 0; i < NUM_SLOTS; i++) {
			ret[i] = getNode(i);
		}
		return ret;
	}

	/**
	 * Clears the focus of the keyboard if the Action Bar is open
	 * 
	 * @return <tt>true</tt> if the Action Bar is closed and the keyboard is unfocused. <tt>false</tt>
	 *         otherwise
	 * @see isFocused()
	 */
	public static boolean clearFocus() {
		if (isFocused()) {
			final int reps = Random.nextInt(3, 5);
			for (int i = 0; i < reps && isFocused(); i++) {
				Keyboard.sendKey((char) KeyEvent.VK_UNDEFINED, KeyEvent.VK_ESCAPE, 50);
				Task.sleep(50, 100);
			}
		}
		return new TimedCondition(500) {

			@Override
			public boolean isDone() {
				return !isFocused();
			}
		}.waitStop();
	}

	/**
	 * Checks whether or not the keyboard is focused
	 * 
	 * @return <tt>true</tt> if the Action Bar is closed and the keyboard is unfocused. <tt>false</tt>
	 *         otherwise
	 * @see clearFocus()
	 */
	public static boolean isFocused() {
		return isExpanded() && BarWidgets.getTextBar().getTextColor() == FOCUSED_TEXT_COLOR;
	}

	/**
	 * Attempts to send the key to the Action Bar
	 * 
	 * @param slot
	 *            The slot to send the key for
	 * @return <tt>true</tt> if the keyboard was ready and the slot had a key. <tt>false</tt> otherwise
	 */
	public static boolean sendKey(int slot) {
		int key = SlotData.getKey(slot);
		if (key == -1 || !makeReady())
			return false;
		Keyboard.pressKey((char) key, 0, 0);
		Task.sleep(50, 100);
		Keyboard.releaseKey((char) key, 0, 0);
		return true;
	}

	/**
	 * Attempts to make the ActionBar ready for interaction
	 * 
	 * @return <tt>true</tt> if the Action Bar is expanded and the keyboard focus was cleared. <tt>false</tt>
	 *         otherwise
	 * @see clearFocus()
	 * @see expand(boolean)
	 */
	public static boolean makeReady() {
		return expand(true) && clearFocus();
	}

	/**
	 * Attempts to expand the Action Bar to the specified expanding status
	 * 
	 * @param expanded
	 *            The expand status (open <tt>true</tt> or closed <tt>false</tt>)
	 * @return <tt>true</tt> if the Action Bar is at the specified expansion status. <tt>false</tt> otherwise
	 * @see isExpanded()
	 */
	public static boolean expand(final boolean expanded) {
		WidgetChild exp;
		return isExpanded() == expanded
				|| (WidgetUtil.visible(exp = BarWidgets.getExpand(expanded)) && EntityUtil.interact(false, exp) && new TimedCondition(
						700) {
					@Override
					public boolean isDone() {
						return isExpanded() == expanded;
					}
				}.waitStop());
	}

	/**
	 * Checks whether or not the Action Bar is expanded
	 * 
	 * @return <tt>true</tt> if the Action Bar is expanded. <tt>false</tt> otherwise
	 * @see expand(boolean)
	 */
	public static boolean isExpanded() {
		return WidgetUtil.visible(BarWidgets.getMainChild());
	}

	/**
	 * Attempts to lock the Action Bar to the specified locking status
	 * 
	 * @param locked
	 *            The lock status (locked <tt>true</tt> or unlocked <tt>false</tt>)
	 * @return <tt>true</tt> if the Action Bar is at the specified locking status. <tt>false</tt> otherwise
	 * @see isLocked()
	 */
	public static boolean lock(final boolean locked) {
		WidgetChild l;
		return isLocked() == locked
				|| (WidgetUtil.visible(l = BarWidgets.getLock()) && EntityUtil.interact(false, l) && new TimedCondition(
						700) {
					@Override
					public boolean isDone() {
						return isLocked() == locked;
					}
				}.waitStop());
	}

	/**
	 * Checks whether or not the Action Bar is locked
	 * 
	 * @return <tt>true</tt> if the Action Bar is locked. <tt>false</tt> otherwise
	 * @see lock(boolean)
	 */
	public static boolean isLocked() {
		return Settings.get(BAR_LOCKED, BAR_LOCKED_MASK) == BAR_LOCKED_MASK;
	}

	/**
	 * Gets the current open bar
	 * 
	 * @return the number of the current bar (0-4)
	 * @see setBar(int)
	 */
	public static int getBar() {
		return Settings.get(CURRENT_BAR_SETTING, CURRENT_BAR_SHIFT, CURRENT_BAR_MASK) - 1;
	}

	/**
	 * Attempts to set the current bar to the specified bar number
	 * 
	 * @param bar
	 *            the bar to open
	 * @return <tt>true</tt> if the current bar is the bar specified. <tt>false</tt> otherwise
	 * @see getBar()
	 */
	public static boolean setBar(final int bar) {
		if (bar < 0 || bar >= MAX_BAR)
			return false;
		int dif = bar - getBar();
		if (dif == 0)
			return true;
		else if (dif < 0)
			dif = (Math.abs(dif) < MAX_BAR + dif ? dif : MAX_BAR + dif);
		else
			dif = (dif < Math.abs(dif - MAX_BAR) ? dif : dif - MAX_BAR);
		WidgetChild but = (dif < 0 ? BarWidgets.getPrevBar() : BarWidgets.getNextBar());
		if (!WidgetUtil.visible(but))
			return false;
		for (int i = 0; i < Math.abs(dif); i++) {
			if (!EntityUtil.interact(false, but))
				return false;
			Task.sleep(50, 100);
		}
		return new TimedCondition(700) {
			@Override
			public boolean isDone() {
				return getBar() == bar;
			}
		}.waitStop();
	}

	/**
	 * Attempts to drag the specified {@link WidgetChild} to the specified slot
	 * 
	 * @param c
	 *            the child to drag
	 * @param slot
	 *            the slot destination
	 * @return <tt>true</tt> if the mouse successfully dragged the child. <tt>false</tt> otherwise
	 * @see dragDraggable(Draggable, int)
	 * @see dragItem(Item, int)
	 */
	public static boolean dragChild(WidgetChild c, int slot) {
		if (c == null || !c.visible() || !SlotData.validateSlot(slot))
			return false;
		WidgetChild dest = SlotData.getMainChild(slot);
		if (c.hover() && c.getBoundingRectangle().contains(Mouse.getLocation())) {
			Mouse.drag(dest.getCentralPoint(), 10, 10);
			return true;
		}
		return false;
	}

	/**
	 * Attempts to drag the specified {@link Draggable} to the specified slot
	 * 
	 * @param c
	 *            the draggable to drag
	 * @param slot
	 *            the slot destination
	 * @return <tt>true</tt> if the {@link Draggable} in the slot is the specified {@link Draggable}
	 *         <tt>false</tt> otherwise
	 * @see dragChild(WidgetChild, int)
	 * @see dragItem(Item, int)
	 */
	public static boolean dragDraggable(final Draggable d, final int slot) {
		return d != null
				&& (SlotData.getDraggableId(slot) == d.getId() || d.show() && dragChild(d.getChild(), slot)
						&& new TimedCondition(700) {

							@Override
							public boolean isDone() {
								return d.getId() == SlotData.getDraggableId(slot);
							}
						}.waitStop());
	}

	/**
	 * Attempts to drag the specified {@link Item} to the specified slot
	 * 
	 * @param c
	 *            the item to drag
	 * @param slot
	 *            the slot destination
	 * @return <tt>true</tt> if the {@link Item} in the slot is the specified {@link Item} <tt>false</tt>
	 *         otherwise
	 * @see dragChild(WidgetChild, int)
	 * @see dragDraggable(Draggable, int)
	 */
	public static boolean dragItem(final Item i, final int slot) {
		return i != null
				&& (SlotData.getItemId(slot) == i.getId() || MainTabs.INVENTORY.open()
						&& dragChild(i.getWidgetChild(), slot) && new TimedCondition(700) {

							@Override
							public boolean isDone() {
								return i.getId() == SlotData.getItemId(slot);
							}

						}.waitStop());
	}

	/**
	 * A utility class to get the ActionBar widgets
	 * 
	 * @author Strikeskids
	 * 
	 */
	public static class BarWidgets {
		private static final int BAR_WIDGET = 640;

		private static final int PREV_BAR = 24, NEXT_BAR = 23;

		private static final int MAIN_BAR_CHILD = 4;
		private static final int EXPAND_BUTTON = 3, MINIMIZE_BUTTON = 30;
		private static final int TARGET_EXPANDED = 31, TARGET_MINIMIZED = 117;

		private static final int LOCK_BUTTON = 26, TRASH_BUTTON = 27;
		private static final int CHATBOX_WIDGET = 137, TEXT_BAR = 56;

		public static Widget get() {
			return Widgets.get(BAR_WIDGET);
		}

		public static WidgetChild getMainChild() {
			return get().getChild(MAIN_BAR_CHILD);
		}

		public static WidgetChild getExpand(boolean expand) {
			return get().getChild((expand ? EXPAND_BUTTON : MINIMIZE_BUTTON));
		}

		public static WidgetChild getTarget() {
			return get().getChild((isExpanded() ? TARGET_EXPANDED : TARGET_MINIMIZED));
		}

		public static WidgetChild getLock() {
			return get().getChild(LOCK_BUTTON);
		}

		public static WidgetChild getTrash() {
			return get().getChild(TRASH_BUTTON);
		}

		public static WidgetChild getPrevBar() {
			return get().getChild(PREV_BAR);
		}

		public static WidgetChild getNextBar() {
			return get().getChild(NEXT_BAR);
		}

		public static WidgetChild getTextBar() {
			return Widgets.get(CHATBOX_WIDGET, TEXT_BAR);
		}

		static {
			loadAbilities();
		}
	}

	/**
	 * A utility class that deals directly with the raw ActionBar data
	 * 
	 * @author Strikeskids
	 * 
	 */
	public static class SlotData {
		public static int getItemId(int slot) {
			return (checkIndex(slot) ? Settings.get(ITEM_SETTINGS[slot]) : DEFAULT_ITEM_SETTING);
		}

		public static int getDraggableId(int slot) {
			return (checkIndex(slot) ? Settings.get(DRAGGABLE_SETTINGS[slot]) : DEFAULT_DRAGGABLE_SETTING);
		}

		public static int getKey(int slot) {
			WidgetChild child = getKeyChild(slot);
			if (child == null)
				return -1;
			String text = child.getText();
			return (text == null || text.length() != 1 ? -1 : text.charAt(0));
		}

		public static WidgetChild getMainChild(int slot) {
			return (checkIndex(slot) ? BarWidgets.get().getChild(MAIN_CHILD[slot]) : null);
		}

		public static WidgetChild getCooldownChild(int slot) {
			return (checkIndex(slot) ? BarWidgets.get().getChild(COOLDOWN_CHILD[slot]) : null);
		}

		public static WidgetChild getKeyChild(int slot) {
			return (checkIndex(slot) ? BarWidgets.get().getChild(KEY_CHILD[slot]) : null);
		}

		public static WidgetChild getItemChild(int slot) {
			return (checkIndex(slot) ? BarWidgets.get().getChild(ITEM_CHILD[slot]) : null);
		}

		public static boolean validateSlot(int slot) {
			return WidgetUtil.visible(getMainChild(slot));
		}

		public static boolean checkTextColor(int slot) {
			WidgetChild ichild = getItemChild(slot);
			return WidgetUtil.visible(ichild) && ichild.getTextColor() == AVAILABLE_TEXT_COLOR;
		}

		public static boolean checkIndex(int slot) {
			return slot >= 0 && slot < NUM_SLOTS;
		}

		private static final int[] MAIN_CHILD = { 34, 38, 41, 44, 47, 50, 53, 56, 59, 62, 65, 68 }, KEY_CHILD = {
				70, 75, 79, 83, 87, 91, 95, 99, 103, 107, 111, 115 }, COOLDOWN_CHILD = { 36, 73, 77, 81, 85, 89,
				93, 97, 101, 105, 109, 113 }, ITEM_CHILD = { 32, 72, 76, 80, 84, 88, 92, 96, 100, 104, 108, 112 };

		private static final int[] ITEM_SETTINGS = new int[NUM_SLOTS], DRAGGABLE_SETTINGS = new int[NUM_SLOTS];

		private static final int ITEM_SETTING_START = 811, ABILITY_SETTING_START = 727;
		private static final int DEFAULT_ITEM_SETTING = -1, DEFAULT_DRAGGABLE_SETTING = -1;

		static {
			for (int i = 0; i < NUM_SLOTS; i++) {
				ITEM_SETTINGS[i] = ITEM_SETTING_START + i;
				DRAGGABLE_SETTINGS[i] = ABILITY_SETTING_START + i;
			}
			loadAbilities();
		}
	}

	private static boolean loaded = false;

	private static <T extends Enum<T> & Draggable> void load(Class<T> clazz) {
		for (T t : clazz.getEnumConstants()) {
			Draggable.ALL.put(t.getId(), t);
		}
		System.out.println("Loaded " + clazz.getName());
	}

	private static void loadAbilities() {
		synchronized (ActionBar.class) {
			if (!loaded) {
				loaded = true;
				System.out.println("Loading Abilities");
				load(AttackAbility.class);
				load(ConstitutionAbility.class);
				load(DefenseAbility.class);
				load(MagicAbility.class);
				load(RangedAbility.class);
				load(StrengthAbility.class);

				load(FreeSpell.class);
				load(StandardSpell.class);
				load(AllSpell.class);
				load(AncientSpell.class);
				load(LunarSpell.class);

				load(Emote.class);
			}
		}
	}

	static {
		loadAbilities();
	}

}
