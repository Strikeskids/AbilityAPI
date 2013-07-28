package com.sk.util.time;

public interface PreparedWaitable {
	public abstract boolean waitFor();
	
	public static final PreparedWaitable NIL = new PreparedWaitable() {
		@Override
		public boolean waitFor() {
			return true;
		}
	};
}
