package com.sk.methods.action;

import static org.powerbot.script.methods.CombatBar.SETTING_ABILITY;
import static org.powerbot.script.methods.CombatBar.SETTING_ITEM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Action.Type;
import org.powerbot.script.wrappers.Component;

import com.sk.SkMethodContext;
import com.sk.methods.action.ability.AttackAbility;
import com.sk.methods.action.ability.ConstitutionAbility;
import com.sk.methods.action.ability.DefenceAbility;
import com.sk.methods.action.ability.MagicAbility;
import com.sk.methods.action.ability.RangedAbility;
import com.sk.methods.action.ability.StrengthAbility;
import com.sk.methods.action.structure.BarIcon;
import com.sk.util.time.TimedCondition;

public class ActionBar extends ActionQuery<Action> {

	public SkMethodContext ctx;
	public MethodContext octx;
	public Logger log = Logger.getLogger(getClass().getSimpleName());

	public ActionBar(SkMethodContext ctx) {
		super(ctx);
		this.ctx = ctx;
		this.octx = ctx;

		addIcons(AttackAbility.class);
		addIcons(ConstitutionAbility.class);
		addIcons(DefenceAbility.class);
		addIcons(MagicAbility.class);
		addIcons(RangedAbility.class);
		addIcons(StrengthAbility.class);
	}

	private <T extends BarIcon> void addIcons(Class<T> clazz) {
		for (T t : clazz.getEnumConstants()) {
			icons.put(t.getId(), t);
		}
	}

	private Map<Integer, BarIcon> icons = new HashMap<>();

	public BarIcon getIconWithId(int id) {
		BarIcon ret = icons.get(id);
		return ret == null ? BarIcon.NIL : ret;
	}

	public int getCurrentBar() {
		return ctx.settings.get(CURRENT_BAR_SETTING) >>> CURRENT_BAR_SHIFT & CURRENT_BAR_MASK;
	}

	public boolean switchBar(final int bar) {
		if (getCurrentBar() == bar)
			return true;
		if (!isExpanded()) {
			if (ctx.debug)
				log.warning("switchBar: bar must be expanded to switch");
			return false;
		}
		int dir = (((bar - getCurrentBar()) % NUM_BARS + NUM_BARS / 2 + NUM_BARS) % NUM_BARS - NUM_BARS / 2);
		final Component button = ctx.widgets.get(BAR_WIDGET, dir < 0 ? PREV_BAR : NEXT_BAR);
		final String action = dir < 0 ? "Previous" : "Next";
		if (!button.isValid()) {
			if (ctx.debug)
				log.info("switchBar: switch button invalid");
			return false;
		}
		if (!button.isVisible()) {
			if (ctx.debug)
				log.info("switchBar: switch button not visible");
			return false;
		}
		for (int i = 0; i < Math.abs(dir); ++i) {
			button.interact(action);
			sleep(50, 100);
		}
		return new TimedCondition() {
			@Override
			public boolean check() {
				return getCurrentBar() == bar;
			}
		}.waitFor(1000);
	}

	public Action[] getActions() {
		Action[] ret = new Action[NUM_ACTIONS];
		for (int i = 0; i < NUM_ACTIONS; ++i)
			ret[i] = getActionAt(i);
		return ret;
	}

	public Action getActionAt(int slot) {
		if (slot < 0 || slot > NUM_ACTIONS)
			return getNil();
		final int itemId = ctx.settings.get(SETTING_ITEM + slot);
		final int abilityId = ctx.settings.get(SETTING_ABILITY + slot);
		if (itemId != -1)
			return new Action(ctx, slot, Type.ITEM, itemId, BarIcon.NIL);
		else if (abilityId != -1)
			return new Action(ctx, slot, Type.ABILITY, abilityId, getIconWithId(abilityId));
		else
			return getNil();
	}

	@Override
	protected List<Action> get() {
		List<Action> ret = new ArrayList<>(NUM_ACTIONS);
		for (Action a : getActions())
			if (a.isValid())
				ret.add(a);
		return ret;
	}

	@Override
	public Action getNil() {
		return new Action(ctx, -1, Type.UNKNOWN, -1, BarIcon.NIL);
	}

	public int getAdrenaline() {
		return ctx.settings.get(ADRENALINE_SETTING);
	}

	public int getMaximumAdrenaline() {
		return MAXIMUM_ADRENALINE;
	}

	public boolean isLocked() {
		return octx.combatBar.isLocked();
	}

	public boolean isExpanded() {
		return octx.combatBar.isExpanded();
	}

	public boolean setLocked(final boolean locked) {
		return octx.combatBar.setLocked(locked);
	}

	public boolean setExpanded(boolean expanded) {
		return octx.combatBar.setExpanded(expanded);
	}

	// private static final int BAR_LOCKED_SETTING = 682, BAR_LOCKED_MASK = 0x10;
	// private static final int LOCK_BUTTON = 26, TRASH_BUTTON = 27;

	private static final int CURRENT_BAR_SETTING = 682, CURRENT_BAR_MASK = 0x7, CURRENT_BAR_SHIFT = 5;
	private static final int NUM_BARS = 5;
	private static final int NUM_ACTIONS = 12;

	private static final int BAR_WIDGET = 1430;

	public static final int COMPONENT_AUTO_RETAILIATE_BUTTON = 6;
	public static final int COMPONENT_HEALTH_BUTTON = 2;
	public static final int COMPONENT_QUICK_PRAYER_BUTTON = 4;
	public static final int COMPONENT_SUMMONING_BUTTON = 5;
	public static final int COMPONENT_LOCK_BUTTON = 21;
	public static final int COMPONENT_TRASH_BUTTON = 22;

	public static final int COMPONENT_HEALTH_BAR = 82;
	public static final int COMPONENT_PRAYER_BAR = 88;
	public static final int COMPONENT_SUMMONING_BAR = 94;
	public static final int COMPONENT_ADRENALINE_BAR = 92;

	private static final int PREV_BAR = 169, NEXT_BAR = 170;

	private static final int ADRENALINE_SETTING = 679, MAXIMUM_ADRENALINE = 1000;
}
