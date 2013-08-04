package com.sk.util.time;

import org.powerbot.script.util.Timer;

import com.sk.util.Condition;

public abstract class TimeAfterCondition extends Timer implements Condition, Waitable {

	public TimeAfterCondition(long repeat) {
		super(repeat);
	}

	@Override
	public boolean isRunning() {
		if (check())
			reset();
		return super.isRunning();
	}

	@Override
	public boolean waitFor(long maxTime) {
		reset();
		boolean ret = false;
		for (Timer end = new Timer(maxTime); end.isRunning() && !(ret = this.isRunning()); Delay.sleep(30, 50))
			;
		return ret;
	}

	@Override
	public boolean waitFor(long maxTime, long delay) {
		reset();
		boolean ret = false;
		for (Timer end = new Timer(maxTime); end.isRunning() && !(ret = this.isRunning()); Delay.sleep(delay))
			;
		return ret;
	}

}
