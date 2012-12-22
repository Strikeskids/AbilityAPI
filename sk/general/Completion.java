package sk.general;

/**
 * An interface that checks to see if something is done
 * 
 * @author Strikeskids
 * 
 */
public interface Completion {
	public static final Completion TRUE = new Completion() {
		@Override
		public boolean isDone() {
			return true;
		}
	};

	/**
	 * Checks to see if the task (or action, etc.) is done
	 * 
	 * @return <tt>true</tt> if the task is done; <tt>false</tt> otherwise
	 */
	public abstract boolean isDone();
}
