package sk.general;

public class Trigger implements Completion {

	private boolean value;

	public Trigger() {
		this.value = false;
	}

	public void trigger() {
		value = true;
	}

	@Override
	public boolean isDone() {
		return value;
	}

	public void reset() {
		value = false;
	}
}
