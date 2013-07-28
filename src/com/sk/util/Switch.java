package com.sk.util;

public final class Switch implements Condition {

	private boolean value;

	public Switch() {
		this(false);
	}

	public Switch(boolean init) {
		this.value = init;
	}

	public void set(boolean v) {
		this.value = v;
	}

	public void off() {
		set(false);
	}

	public void on() {
		set(true);
	}

	public void flip() {
		set(!value);
	}

	@Override
	public boolean check() {
		return value;
	}
}
