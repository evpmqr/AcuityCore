package com.acuity.api.rs.utils;

import com.acuity.api.rs.query.Interfaces;
import com.acuity.api.rs.wrappers.interfaces.InterfaceComponent;

import java.awt.event.KeyEvent;
import java.util.Optional;

/**
 * Created by Eclipseop.
 * Date: 6/17/2017.
 */
public enum Tab {
	CLAN_CHAT(KeyEvent.VK_F7, 28),
	FRIENDS_LIST(KeyEvent.VK_F8, 29),
	IGNORE_LIST(KeyEvent.VK_F9, 30),
	LOGOUT(-1, 31),
	OPTIONS(KeyEvent.VK_F10, 32),
	EMOTES(KeyEvent.VK_F11, 33),
	MUSIC_PLAYER(KeyEvent.VK_F12, 34),
	COMBAT(KeyEvent.VK_F1, 45),
	STATS(KeyEvent.VK_F2, 46),
	QUEST_LIST(KeyEvent.VK_F3, 47),
	INVENTORY(KeyEvent.VK_ESCAPE, 48),
	EQUIPMENT(KeyEvent.VK_F4, 49),
	PRAYER(KeyEvent.VK_F5, 50),
	MAGIC(KeyEvent.VK_F6, 51);

	private final int index;
	private final int hotkey;

	Tab(int hotkey, int index) {
		this.hotkey = hotkey;
		this.index = index;
	}

	public Optional<InterfaceComponent> getComponent() {
		return Interfaces.getLoaded(548, index);
	}

	public boolean isOpen() {
		return getComponent().map(m -> m.getSpriteId() != -1).orElse(false);
	}

	public static Tab getOpen() {
		for (Tab tab : values()) {
			if (tab.isOpen()) {
				return tab;
			}
		}
		return null;
	}
}
