package com.acuity.api.rs.events;

import com.acuity.api.rs.wrappers.scene.actors.impl.Player;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class OverheadPrayerChangeEvent implements RSEvent {

    private Player player;

    public OverheadPrayerChangeEvent(Player player) {
        this.player = player;
    }
}
