package sk.action.book;

/**
 * An enum of the type of ability (basic, threshold, ultimate)
 * 
 * @author Strikeskids
 * 
 */
public enum AbilityType {
	BASIC(0), THRESHOLD(500), ULTIMATE(1000);

	private final int adrenaline;

	private AbilityType(int a) {
		this.adrenaline = a;
	}

	/**
	 * Gets the adrenaline requirement for this type of ability
	 * 
	 * @return the adrenaline requirement as an <tt>int</tt> percentage (out of 1000)
	 */
	public int getAdrenalineRequirement() {
		return adrenaline;
	}
}
