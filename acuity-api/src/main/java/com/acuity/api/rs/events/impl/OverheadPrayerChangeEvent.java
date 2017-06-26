package com.acuity.api.rs.events.impl;

import com.acuity.api.rs.events.RSEvent;
import com.acuity.api.rs.wrappers.peers.scene.actors.impl.Player;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class OverheadPrayerChangeEvent implements RSEvent {

    private Player player;

    public OverheadPrayerChangeEvent(Player player) {
        this.player = player;
    }
}
