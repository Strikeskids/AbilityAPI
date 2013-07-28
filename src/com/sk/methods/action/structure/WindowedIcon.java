package com.sk.methods.action.structure;

import com.sk.windows.Window;

public interface WindowedIcon extends BarIcon {
	public Window getWindow();

	public int getSkillLevel();

	public int getSkill();
	
	public static final WindowedIcon NIL = Ability.NIL;
}
