package com.acuity.api.rs.interfaces;

import com.acuity.api.rs.utils.LocalPlayer;
import com.acuity.api.rs.utils.Projection;
import com.acuity.api.rs.utils.Scene;
import com.acuity.api.rs.wrappers.common.locations.WorldLocation;
import com.acuity.api.rs.wrappers.common.locations.screen.ScreenLocation;
import com.google.common.base.Preconditions;

import java.util.Optional;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public interface Locatable {

    WorldLocation getWorldLocation();

    default Optional<ScreenLocation> getScreenLocation(){
        return Projection.worldToScreen(getWorldLocation());
    }

    default int getPlane() {
        return Scene.getPlane();
    }

    default int distance() {
        return distance(LocalPlayer.getWorldLocation().orElse(null));
    }

    default boolean isOnMiniMap() {
        return getWorldLocation().isOnMiniMap();
    }

    default int distance(Locatable locatable) {
        return Math.toIntExact(Math.round(distancePrecise(locatable)));
    }

    default double distancePrecise() {
        return distancePrecise(LocalPlayer.getWorldLocation().orElse(null));
    }

    default double distancePrecise(Locatable locatable) {
        Preconditions.checkNotNull(locatable);

        WorldLocation location1 = locatable.getWorldLocation();
        WorldLocation location2 = getWorldLocation();

        if (location1.getPlane() != location2.getPlane()) {
            return Integer.MAX_VALUE - 1;
        }

        return Math.hypot(location1.getWorldY() - location2.getWorldY(), location1.getWorldX() - location2.getWorldX());
    }
}
