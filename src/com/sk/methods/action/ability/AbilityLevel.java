package com.sk.methods.action.ability;

public enum AbilityLevel {
	NONE(-1), BASIC(0), THRESHOLD(500), ULTIMATE(1000);
	private final int adrenaline;

	private AbilityLevel(int a) {
		this.adrenaline = a;
	}

	public int getAdrenaline() {
		return adrenaline;
	}
}
