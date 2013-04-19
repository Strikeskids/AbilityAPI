package sk.item;

public class DefinedItem {
	private final boolean useNote;
	private final int amount;
	private final int item;

	public DefinedItem(final int amnt, final ItemInformation i) {
		this(false, amnt, i);
	}

	public DefinedItem(final boolean note, final int amnt, final ItemInformation i) {
		if (i == null) {
			throw new NullPointerException("ItemInformation is null");
		}
		if (!i.canNote() && note) {
			throw new IllegalArgumentException("Cannot note a item that is not notable");
		}
		if (amnt <= 0) {
			throw new IllegalArgumentException("Need positive amount");
		}
		this.item = i.getItemId();
		this.amount = amnt;
		this.useNote = note;
	}

	public ItemInformation getItem() {
		return ItemInformation.get(item);
	}

	public int getItemId() {
		return item;
	}

	public int getAmount() {
		return amount;
	}

	public boolean shouldNote() {
		return useNote;
	}

	public int getSize() {
		if (shouldNote()) {
			return 1;
		}
		return amount;
	}

	@Override
	public int hashCode() {
		return amount | item << 16;
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof DefinedItem)) {
			return false;
		}
		final DefinedItem i = (DefinedItem) o;
		return i.item == this.item && i.getAmount() == this.getAmount() && i.shouldNote() == this.shouldNote();
	}

	@Override
	public String toString() {
		return getItem() + " " + amount;
	}
}
