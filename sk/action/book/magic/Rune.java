package sk.action.book.magic;

public class Rune {
	private final RuneType type;
	private final int amount;

	public Rune(RuneType t, int amnt) {
		this.type = t;
		this.amount = amnt;
	}

	public Rune(RuneType t) {
		this(t, 1);
	}

	public int getAmount() {
		return amount;
	}

	public RuneType getType() {
		return type;
	}
}
