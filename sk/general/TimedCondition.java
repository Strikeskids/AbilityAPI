package sk.general;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.util.Timer;

public abstract class TimedCondition extends Timer implements Completion {

	public TimedCondition(long period) {
		super(period);
	}

	@Override
	public boolean isRunning() {
		if (!super.isRunning())
			return false;
		if (isDone()) {
			super.setEndIn(0);
		}
		return super.isRunning();
	}
	
	public boolean waitStop() {
		return waitStop(50);
	}

	public boolean waitStop(int intersleep) {
		while (isRunning()) {
			Task.sleep(intersleep);
		}
		return isDone();
	}
}
