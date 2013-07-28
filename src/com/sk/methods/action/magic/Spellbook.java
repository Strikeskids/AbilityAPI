package com.sk.methods.action.magic;

import org.powerbot.script.methods.MethodContext;

public enum Spellbook {
	STANDARD(0), ANCIENT(1), LUNAR(2), NONE(-1), ALL(-1) {
		@Override
		public boolean isOpen(MethodContext ctx) {
			return true;
		}
	};

	private int id;

	private Spellbook(int id) {
		this.id = id;
	}

	public boolean isOpen(MethodContext ctx) {
		return (ctx.settings.get(SPELLBOOK_SETTING) & MASK) == id;
	}

	private static final int SPELLBOOK_SETTING = 4, MASK = 0x3;

}
