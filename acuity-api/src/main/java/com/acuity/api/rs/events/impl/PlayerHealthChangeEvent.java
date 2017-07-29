package com.acuity.api.rs.events.impl;

import com.acuity.api.rs.events.RSEvent;
import com.acuity.api.rs.wrappers.peers.scene.actors.impl.Player;
import com.acuity.rs.api.RSPlayer;

/**
 * Created by MadDev on 7/28/17.
 */
public class PlayerHealthChangeEvent implements RSEvent {

    private Player player;
    private double newHealthPercentage;

    public PlayerHealthChangeEvent(Player player, double newHealthPercentage) {
        this.player = player;
        this.newHealthPercentage = newHealthPercentage;
    }

    public Player getPlayer() {
        return player;
    }

    public double getNewHealthPercentage() {
        return newHealthPercentage;
    }

    @Override
    public String toString() {
        return "PlayerHealthChangeEvent{" +
                "player=" + player.getName() +
                ", newHealthPercentage=" + newHealthPercentage +
                '}';
    }
}
