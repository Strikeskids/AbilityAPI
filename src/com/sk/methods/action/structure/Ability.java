package com.sk.methods.action.structure;

import org.powerbot.script.wrappers.Component;

import com.sk.SkMethodContext;
import com.sk.methods.action.ability.AbilityLevel;
import com.sk.methods.action.ability.AbilityStyle;
import com.sk.windows.Window;

public interface Ability extends BookIcon {
	public AbilityStyle getStyle();

	public AbilityLevel getAbilityLevel();

	public int getCooldown();

	public int getChanneled();

	public static Ability NIL = new Ability() {
		@Override
		public AbilityStyle getStyle() {
			return AbilityStyle.NONE;
		}

		@Override
		public AbilityLevel getAbilityLevel() {
			return AbilityLevel.NONE;
		}

		@Override
		public int getCooldown() {
			return 0;
		}

		@Override
		public int getChanneled() {
			return 0;
		}

		@Override
		public boolean isValid() {
			return false;
		}

		@Override
		public int getId() {
			return -1;
		}

		@Override
		public Component getComponent(SkMethodContext ctx) {
			return ctx.widgets.get(0, 0);
		}

		@Override
		public int getChildIndex() {
			return -1;
		}

		@Override
		public int getChildTexture() {
			return -1;
		}

		@Override
		public Window getWindow() {
			return Window.NIL;
		}

		@Override
		public int getSkillLevel() {
			return 0;
		}

		@Override
		public int getSkill() {
			return -1;
		}

		@Override
		public Component getCooldownComponent(SkMethodContext ctx) {
			return getComponent(ctx);
		}

		@Override
		public int getWidget() {
			return 0;
		}
	};
}
