package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.movement.SceneLocation;
import com.acuity.api.rs.movement.WorldLocation;
import com.acuity.api.rs.peers.mobile.Actor;
import com.acuity.api.rs.peers.mobile.Player;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class LocalPlayer {

    public static Optional<Player> get() {
        if (AcuityInstance.getClient().getGameState() <= 20) {
            return Optional.empty();
        }
        return AcuityInstance.getClient().getLocalPlayer();
    }

    public static WorldLocation getWorldLocation() {
        return get().map(Locatable::getWorldLocation).orElse(null);
    }

    public static SceneLocation getSceneLocation() {
        return get().map(Locatable::getSceneLocation).orElse(null);
    }

    public static boolean isAnimating() {
        return get().map(Actor::isAnimating).orElse(false);
    }

    public static String getName() {
        return get().map(Player::getName).orElse(null);
    }
}
