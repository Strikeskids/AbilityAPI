package sk.general;

public interface Completion {
	public static final Completion TRUE = new Completion() {
		@Override
		public boolean isDone() {
			return true;
		}
	};

	public abstract boolean isDone();
}
