package com.sk.util.time;

public class TimedWaitable implements PreparedWaitable {

	private final Waitable wait;
	private final long time, iterationDelay;

	public TimedWaitable(Waitable wait, long time) {
		this(wait, time, -1);
	}

	public TimedWaitable(Waitable wait, long time, long iterationDelay) {
		this.wait = wait;

		this.time = time;
		this.iterationDelay = iterationDelay;
	}

	public boolean waitFor() {
		if (iterationDelay > 0)
			return wait.waitFor(time, iterationDelay);
		else
			return wait.waitFor(time);
	}
}
