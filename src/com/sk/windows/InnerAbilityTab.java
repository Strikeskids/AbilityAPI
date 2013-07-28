package com.sk.windows;

import com.sk.SkMethodContext;

public enum InnerAbilityTab implements Window {
	ATTACK_ABILITY(MainWindow.MELEE_ABILITIES, 0, "Attack", 4),

	STRENGTH_ABILITY(MainWindow.MELEE_ABILITIES, 1, "Strength", 4),

	RANGED_ABILITY(MainWindow.RANGED_ABILITIES, 0, "Ranged", 0),

	MAGIC_ABILITY(MainWindow.MAGIC_ABILITIES, 0, "Abilities", 20),

	COMBAT_SPELL(MainWindow.MAGIC_ABILITIES, 1, "Combat", 20),

	TELEPORT_SPELL(MainWindow.MAGIC_ABILITIES, 1, "Teleport", 20),

	SKILLING_SPELL(MainWindow.MAGIC_ABILITIES, 1, "Skilling", 20),

	DEFENCE_ABILITY(MainWindow.DEFENCE_ABILITIES, 0, "Defence", 28),

	CONSTITUTION_ABILITY(MainWindow.DEFENCE_ABILITIES, 1, "Constitution", 28);

	private final String openAction;
	private final MainWindow superWindow;
	private final int component;
	private final int shift;

	private InnerAbilityTab(final MainWindow t, final int c, final String s, final int sval) {
		this.superWindow = t;
		this.component = c;
		this.openAction = s;
		this.shift = sval;
	}

	public MainWindow getSuperWindow() {
		return superWindow;
	}

	@Override
	public boolean open(final SkMethodContext ctx) {
		if (isOpen(ctx))
			return true;
		return superWindow.open(ctx)
				&& ctx.widgets.get(superWindow.getSource().getWidget(), 6 + component).interact(openAction);
	}

	@Override
	public boolean isOpen(SkMethodContext ctx) {
		return superWindow.isOpen(ctx) && (ctx.settings.get(TAB_SETTING) >> shift & 0xf) == component;
	}

	private static final int TAB_SETTING = 3705;
}
