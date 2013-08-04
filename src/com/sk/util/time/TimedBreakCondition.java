package com.sk.util.time;

import org.powerbot.script.util.Timer;

public abstract class TimedBreakCondition extends TimedCondition {

	public TimedBreakCondition() {
	}

	public abstract boolean shouldBreak();

	public boolean waitFor(long maxTime) {
		for (Timer end = new Timer(maxTime); end.isRunning();) {
			if (this.shouldBreak())
				return false;
			if (this.check())
				return true;
			Delay.sleep(30, 50);
		}
		return false;
	}

	public boolean waitFor(long maxTime, long delay) {
		for (Timer end = new Timer(maxTime); end.isRunning();) {
			if (this.shouldBreak())
				return false;
			if (this.check())
				return true;
			Delay.sleep(delay);
		}
		return false;
	}

}
