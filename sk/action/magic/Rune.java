package sk.action.magic;

/**
 * Corresponds to a rune requirement that has an amount and a {@link RuneType}
 *
 * @author Strikeskids
 */
public class Rune {
	private final RuneType type;
	private final int amount;

	/**
	 * Constructor that allows the amount and the {@link RuneType} to be specified
	 *
	 * @param t    The {@linkplain RuneType} of this rune
	 * @param amnt The number required of this rune
	 */
	public Rune(final RuneType t, final int amnt) {
		this.type = t;
		this.amount = amnt;
	}

	/**
	 * Constructor that has the default (<tt>1</tt>) amount and allows the {@link RuneType} to be specified
	 *
	 * @param t The {@linkplain RuneType} of this rune
	 */
	public Rune(final RuneType t) {
		this(t, 1);
	}

	/**
	 * Gets the amount required for this rune
	 *
	 * @return the amount required for this rune
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Gets the {@link RuneType} of this rune
	 *
	 * @return the type of this rune
	 */
	public RuneType getType() {
		return type;
	}
}
