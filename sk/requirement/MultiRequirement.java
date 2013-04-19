package sk.requirement;

public class MultiRequirement implements Requirement {

	private final boolean all;
	private final Requirement[] sub;

	public MultiRequirement(final Requirement... sub) {
		this(true, sub);
	}

	public MultiRequirement(final boolean all, final Requirement... sub) {
		this.all = all;
		this.sub = sub;
	}

	public boolean isAll() {
		return all;
	}

	public Requirement[] getSubRequirements() {
		return sub;
	}

	@Override
	public boolean meets() {
		for (final Requirement r : sub) {
			if (r.meets() != all) {
				return !all;
			}
		}
		return all;
	}
}
