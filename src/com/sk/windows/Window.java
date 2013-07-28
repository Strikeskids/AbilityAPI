package com.sk.windows;

import com.sk.SkMethodContext;

public interface Window {
	public static final Window NIL = new Window() {
		@Override
		public boolean open(SkMethodContext ctx) {
			return false;
		}
		@Override
		public boolean isOpen(SkMethodContext ctx) {
			return false;
		}
	};
	public boolean open(final SkMethodContext ctx);
	public boolean isOpen(final SkMethodContext ctx);
}
