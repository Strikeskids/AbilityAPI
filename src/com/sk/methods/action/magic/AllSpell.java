package com.sk.methods.action.magic;

import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.Component;

import com.sk.SkMethodContext;
import com.sk.methods.action.ability.AbilityStyle;
import com.sk.methods.action.structure.Spell;
import com.sk.windows.InnerAbilityTab;
import com.sk.windows.Window;

public enum AllSpell implements Spell {
	POLYPORE_STRIKE(InnerAbilityTab.COMBAT_SPELL, 2598, 80, 162, 14396),
HOME_TELEPORT(InnerAbilityTab.TELEPORT_SPELL, 2491, 0, 155, 14333),
;

	private AllSpell(Window t, int id, int l, int ci, int ct, Rune... r) {
		this.window = t;
		this.id = id;
		this.level = l;
		this.childIndex = ci;
		this.childTexture = ct;
		this.runes = r;
	}

	private final Rune[] runes;
	private final int childIndex, childTexture, level, id;
	private final Window window;

	@Override
	public int getChildIndex() {
		return childIndex;
	}

	@Override
	public int getChildTexture() {
		return childTexture;
	}

	@Override
	public Component getCooldownComponent(SkMethodContext ctx) {
		return ctx.widgets.get(getWidget(), COOLDOWN_COMPONENT).getChild(getChildIndex());
	}

	@Override
	public Window getWindow() {
		return window;
	}

	@Override
	public int getSkillLevel() {
		return level;
	}

	@Override
	public int getSkill() {
		return Skills.MAGIC;
	}

	@Override
	public Component getComponent(SkMethodContext ctx) {
		return ctx.widgets.get(getWidget(), MAIN_COMPONENT).getChild(getChildIndex());
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public Spellbook getSpellbook() {
		return Spellbook.ALL;
	}

	@Override
	public Rune[] getRunes() {
		return runes;
	}

	@Override
	public int getWidget() {
		return AbilityStyle.MAGIC.getWidgetId();
	}
}

