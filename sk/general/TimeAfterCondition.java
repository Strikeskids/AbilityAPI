package sk.general;

import org.powerbot.game.api.util.Timer;

public abstract class TimeAfterCondition extends Timer {

	public TimeAfterCondition(final long paramLong) {
		super(paramLong);
	}

	@Override
	public boolean isRunning() {
		if (check()) {
			reset();
		}
		return super.isRunning();
	}

	public abstract boolean check();

	public TimedCondition getWait(long time) {
		return new TimedCondition(time) {

			@Override
			public boolean isDone() {
				return !TimeAfterCondition.this.isRunning();
			}

		};
	}

}
