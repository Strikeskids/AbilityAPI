package sk.general;

import org.powerbot.game.api.util.Timer;

public class STimer extends Timer {

	private final long start;

	// private long end;

	public STimer(long l) {
		super(l);
		start = System.currentTimeMillis();
		// end = start + l;
	}

	//
	// public boolean isRunning() {
	// return end > System.currentTimeMillis();
	// }
	//
	// public long getRemaining() {
	// return Math.max(0, end - System.currentTimeMillis());
	// }

	public long getStartTime() {
		return start;
	}

}
