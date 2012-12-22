package sk.general;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.util.Timer;

/**
 * A timer that will stop instantly if the {@link Completion} is done
 * 
 * @author Strikeskids
 * 
 */
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

	/**
	 * Waits for the timer to run out or the {@link Completion} to be met with a default sleep (50 ms)
	 * 
	 * @return the value of the Completion when the timer stops
	 * @see TimedCondition#waitStop(int)
	 */
	public boolean waitStop() {
		return waitStop(50);
	}

	/**
	 * Waits for the timer to run out or the {@link Completion} to be met with a specified sleep between
	 * checks
	 * 
	 * @param intersleep
	 *            The time (in millis) to wait between checks
	 * @return the value of the Completion when the timer stops
	 * @see TimedCondition#waitStop()
	 */
	public boolean waitStop(int intersleep) {
		while (isRunning()) {
			Task.sleep(intersleep);
		}
		return isDone();
	}
}
