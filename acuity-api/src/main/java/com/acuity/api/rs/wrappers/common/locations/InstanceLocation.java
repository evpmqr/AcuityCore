package com.acuity.api.rs.wrappers.common.locations;

import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.utils.LocalPlayer;

/**
 * Created by Zachary Herridge on 7/27/2017.
 */
public class InstanceLocation implements Locatable {

    private int instanceX, instanceY, plane;

    public InstanceLocation(int instanceX, int instanceY, int plane) {
        this.instanceX = instanceX;
        this.instanceY = instanceY;
        this.plane = plane;
    }

    public int getInstanceX() {
        return instanceX;
    }

    public int getInstanceY() {
        return instanceY;
    }

    @Override
    public int getPlane() {
        return plane;
    }

    @Override
    public WorldLocation getWorldLocation() {
        WorldLocation worldLocation = LocalPlayer.getWorldLocation().orElseThrow(() -> new RuntimeException("Failed to get local player position while converting InstanceLocation to WorldLocation."));
        int worldX = worldLocation.getWorldX() - (worldLocation.getWorldX() % 192) + instanceX;
        int worldY = worldLocation.getWorldY() - (worldLocation.getWorldY() % 192) + instanceY;
        return new WorldLocation(worldX, worldY, plane);
    }
}
