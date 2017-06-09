package com.acuity.api.rs.utils;

import com.acuity.api.RSInstance;
import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.movement.Tile;
import com.acuity.api.rs.peers.mobile.Actor;
import com.acuity.api.rs.peers.mobile.Player;
import com.acuity.api.rs.query.Players;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class LocalPlayer {

    public static Optional<Player> get() {
        if (RSInstance.getClient().getGameState() <= 20) {
            return Optional.empty();
        }
        return Players.getLocal();
    }

    public static Tile getLocation() {
        return get().map(Locatable::toLocation).orElse(null);
    }

    public static int getX() {
        return getLocation().getX();
    }

    public static int getY() {
        return getLocation().getY();
    }

    public static boolean isAnimating() {
        return get().map(Actor::isAnimating).orElse(false);
    }

    public static String getName() {
        return get().map(Player::getName).orElse(null);
    }
}
