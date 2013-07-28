package com.sk.windows;

import org.powerbot.script.methods.Hud;

import com.sk.SkMethodContext;

public enum MainWindow implements Window {
	ALL_CHAT(Hud.Window.ALL_CHAT),
	BACKPACK(Hud.Window.BACKPACK),
	CLAN(Hud.Window.CLAN),
	CLAN_CHAT(Hud.Window.CLAN_CHAT),
	DEFENCE_ABILITIES(Hud.Window.DEFENCE_ABILITIES),
	EMOTES(Hud.Window.EMOTES),
	FAMILIAR(Hud.Window.FAMILIAR),
	FRIENDS(Hud.Window.FRIENDS),
	FRIENDS_CHAT(Hud.Window.FRIENDS_CHAT),
	FRIENDS_CHAT_INFO(Hud.Window.FRIENDS_CHAT_INFO),
	GUEST_CLAN_CHAT(Hud.Window.GUEST_CLAN_CHAT),
	MAGIC_ABILITIES(Hud.Window.MAGIC_ABILITIES),
	MELEE_ABILITIES(Hud.Window.MELEE_ABILITIES),
	MINIMAP(Hud.Window.MINIMAP),
	MUSIC_PLAYER(Hud.Window.MUSIC_PLAYER),
	NOTES(Hud.Window.NOTES),
	PRAYER_ABILITIES(Hud.Window.PRAYER_ABILITIES),
	PRIVATE_CHAT(Hud.Window.PRIVATE_CHAT),
	RANGED_ABILITIES(Hud.Window.RANGED_ABILITIES),
	SKILLS(Hud.Window.SKILLS),
	WORN_EQUIPMENT(Hud.Window.WORN_EQUIPMENT);

	private final Hud.Window wind;

	private MainWindow(Hud.Window w) {
		this.wind = w;
	}

	public boolean close(SkMethodContext ctx) {
		return ctx.hud.close(wind);
	}

	@Override
	public boolean open(SkMethodContext ctx) {
		return ctx.hud.view(wind);
	}

	@Override
	public boolean isOpen(SkMethodContext ctx) {
		return ctx.hud.isVisible(wind);
	}

	public Hud.Window getSource() {
		return wind;
	}

}
