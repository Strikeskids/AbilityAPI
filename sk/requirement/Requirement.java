package sk.requirement;

public interface Requirement {
	public static final Requirement DONE = new Requirement() {
		
		@Override
		public boolean meets() {
			return true;
		}
	};

	public abstract boolean meets();
}
