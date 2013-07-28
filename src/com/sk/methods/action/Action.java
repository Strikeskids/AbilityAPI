package com.sk.methods.action;

import com.sk.SkMethodContext;
import com.sk.methods.action.structure.Ability;
import com.sk.methods.action.structure.BarIcon;
import com.sk.methods.action.structure.Spell;

public class Action extends org.powerbot.script.wrappers.Action {

	protected SkMethodContext ctx;
	private BarIcon icon;
	private final int slot;

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
}
