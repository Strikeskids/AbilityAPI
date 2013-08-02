package com.sk.methods.action;

import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.Widget;

import com.sk.SkMethodContext;
import com.sk.methods.action.structure.Ability;
import com.sk.methods.action.structure.BarIcon;
import com.sk.methods.action.structure.Spell;

public class Action extends org.powerbot.script.wrappers.Action {

	protected SkMethodContext ctx;
	private BarIcon icon;
	private final int slot;

	private static final int WIDGET_ID = 1430;
	private static final int COOLDOWN_CHILD = 97;
	private static final int AVAILABILITY_CHILD = 96;
	private static final int AVAILABLE_TEXT_COLOR = 0xFFFFFF;

	public Action(SkMethodContext ctx, int slot, Type type, int id, BarIcon ico) {
		super(ctx, slot, type, id);
		this.ctx = ctx;
		this.icon = ico;
		this.slot = slot;
	}

	public int getSlot() {
		return slot;
	}

	public Ability getAbility() {
		return icon instanceof Ability ? (Ability) icon : Ability.NIL;
	}

	public Spell getSpell() {
		return icon instanceof Spell ? (Spell) icon : Spell.NIL;
	}

	public BarIcon getBarIcon() {
		return icon;
	}

	@Override
	public boolean select() {
		return isValid() && ctx.keyboard.key(getBind(), 0);
	}

	@Override
	public boolean isReady() {
		if (!isValid())
			return false;
		return getAvailabilityComponent().getTextColor() == AVAILABLE_TEXT_COLOR
				&& !getCooldownComponent().isVisible();
	}

	public Component getAvailabilityComponent() {
		if (0 <= slot && slot < 12)
			return getWidget().getComponent(AVAILABILITY_CHILD + 4 * slot);
		return ctx.widgets.get(0, -1);
	}

	public Component getCooldownComponent() {
		if (0 <= slot && slot < 12)
			return getWidget().getComponent(COOLDOWN_CHILD + 4 * slot);
		return ctx.widgets.get(0, -1);
	}

	private Widget getWidget() {
		return ctx.widgets.get(WIDGET_ID);
	}
}