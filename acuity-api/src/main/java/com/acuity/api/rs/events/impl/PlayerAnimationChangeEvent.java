package com.acuity.api.rs.events.impl;

import com.acuity.api.rs.events.RSEvent;
import com.acuity.api.rs.wrappers.peers.scene.actors.impl.Player;
import com.acuity.rs.api.RSPlayer;

/**
 * Created by MadDev on 7/27/17.
 */
public class PlayerAnimationChangeEvent implements RSEvent {

    private Player player;
    private long animation;

    public PlayerAnimationChangeEvent(Player player, long animation) {
        this.player = player;
        this.animation = animation;
    }

    public Player getPlayer() {
        return player;
    }

    public long getAnimation() {
        return animation;
    }

    @Override
    public String toString() {
        return "PlayerAnimationChangeEvent{" +
                "player=" + player.getName() +
                ", animation=" + animation +
                '}';
    }
}
