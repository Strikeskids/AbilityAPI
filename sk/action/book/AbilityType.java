package sk.action.book;

public enum AbilityType {
	BASIC(0), THRESHOLD(50), ULTIMATE(100);

	private final int adrenaline;

	private AbilityType(int a) {
		this.adrenaline = a;
	}

	public int getAdrenalineRequirement() {
		return adrenaline;
	}
}
