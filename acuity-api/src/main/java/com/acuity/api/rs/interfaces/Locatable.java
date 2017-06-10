package com.acuity.api.rs.interfaces;

import com.acuity.api.rs.movement.WorldLocation;
import com.acuity.api.rs.utils.LocalPlayer;
import com.google.common.base.Preconditions;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public interface Locatable {

    WorldLocation getWorldLocation();

    default int getPlane() {
        return getWorldLocation().getPlane();
    }

    default int distance() {
        return distance(LocalPlayer.getWorldLocation());
    }

    default boolean isOnMiniMap() {
        return getWorldLocation().isOnMiniMap();
    }

    default int distance(Locatable locatable) {
        return Math.toIntExact(Math.round(distancePrecise(locatable)));
    }

    default double distancePrecise() {
        return distancePrecise(LocalPlayer.getWorldLocation());
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
