package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.wrappers.common.locations.SceneLocation;
import com.acuity.api.rs.wrappers.common.locations.StrictLocation;
import com.acuity.api.rs.wrappers.common.locations.WorldLocation;
import com.acuity.api.rs.wrappers.common.locations.screen.ScreenLocation;
import com.acuity.api.rs.wrappers.common.locations.screen.ScreenLocation3D;
import com.acuity.api.rs.wrappers.peers.engine.Client;
import com.acuity.api.rs.wrappers.peers.rendering.bounding_boxes.AxisAlignedBoundingBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class Projection {

    private static final Logger logger = LoggerFactory.getLogger(Projection.class);
    public static final Rectangle GAMESCREEN = new Rectangle(4, 4, 512, 334);

    public static final int TILE_PIXEL_SIZE = 128;
    public static final int[] SINE = new int[2048];
    public static final int[] COSINE = new int[2048];

    public static final int[][] SIDES = {
            {0, 1, 2, 3},
            {4, 5, 6, 7},

            {0, 4, 5, 1},
            {2, 6, 7, 3},

            {1, 5, 6, 2},
            {3, 7, 4, 0}
    };

    public static final int[][] TRIANGLES = {
            {0, 1, 3},
            {2, 3, 1},
    };


    static {
        for (int i = 0; i < SINE.length; i++) {
            SINE[i] = (int) (65536.0D * Math.sin((double) i * 0.0030679615D));
            COSINE[i] = (int) (65536.0D * Math.cos((double) i * 0.0030679615D));
        }
    }

    public static Optional<ScreenLocation> worldToScreen(WorldLocation worldLocation) {
        return sceneToScreen(worldLocation.toCurrentSceneLocation());
    }

    public static Optional<ScreenLocation> sceneToScreen(SceneLocation sceneLocation) {
        return strictToScreen(sceneLocation.getStrictLocation());
    }

    public static Optional<ScreenLocation> strictToScreen(StrictLocation strictLocation) {
        return strictToScreen(strictLocation.getX(), strictLocation.getY(), strictLocation.getPlane());
    }

    public static Optional<ScreenLocation> strictToScreen(int strictX, int strictY, int height) {
        if (strictX >= TILE_PIXEL_SIZE && strictX <= 13056 && strictY >= TILE_PIXEL_SIZE && strictY <= 13056) {
            int alt = Camera.getPitch();
            if (alt < 0) {
                return Optional.empty();
            }
            int yaw = Camera.getYaw();
            if (yaw < 0) {
                return Optional.empty();
            }
            int elevation = Scene.getGroundHeight(strictX, strictY).orElse(0) - height;
            strictX -= Camera.getX();
            strictY -= Camera.getY();
            elevation -= Camera.getZ();
            int altSin = SINE[alt];
            int altCos = COSINE[alt];
            int yawSin = SINE[yaw];
            int yawCos = COSINE[yaw];
            int angle = strictY * yawSin + strictX * yawCos >> 16;
            strictY = strictY * yawCos - strictX * yawSin >> 16;
            strictX = angle;
            angle = elevation * altCos - strictY * altSin >> 16;
            strictY = elevation * altSin + strictY * altCos >> 16;
            if (strictY == 0) {
                return Optional.empty();
            }
            return Optional.of(new ScreenLocation(256 + (strictX << 9) / strictY, (angle << 9) / strictY + 167));
        }
        return Optional.empty();
    }

    public static Optional<ScreenLocation> sceneToMiniMap(int sceneX, int sceneY) {
        return sceneToMiniMap(sceneX, sceneY, null);
    }

    public static Optional<ScreenLocation> sceneToMiniMap(int sceneX, int sceneY, Integer distanceFilter) {
        int angle = MiniMap.getScale() + MiniMap.getRotation() & 0x7FF;

        SceneLocation sceneLocation = LocalPlayer.getSceneLocation().orElseThrow(() -> new NullPointerException("LocalPlayer.getSceneLocation() failed to return a location."));
        sceneX = sceneX / 32 - sceneLocation.getSceneX() / 32;
        sceneY = sceneY / 32 - sceneLocation.getSceneY() / 32;

        int dist = sceneX * sceneX + sceneY * sceneY;
        if (distanceFilter == null || dist < distanceFilter) {
            int sin = SINE[angle];
            int cos = COSINE[angle];

            sin = sin * 256 / (MiniMap.getOffset() + 256);
            cos = cos * 256 / (MiniMap.getOffset() + 256);

            int xx = sceneY * sin + cos * sceneX >> 16;
            int yy = sin * sceneX - sceneY * cos >> 16;

            Client client = AcuityInstance.getClient();
            int miniMapX = client.getViewportWidth() - (!client.isResized() ? 208 : 167);

            sceneX = (miniMapX + 167 / 2) + xx;
            sceneY = (167 / 2 - 1) + yy;
            return Optional.of(new ScreenLocation(sceneX, sceneY));
        }

        return Optional.empty();
    }

    /**
     * @author Dogerina
     */
    public static int[][][] boundingBoxToScreen(AxisAlignedBoundingBox boundingBox){
        ScreenLocation3D[] vertices = boundingBox.getVertices();

        int[][][] model = new int[SIDES.length * TRIANGLES.length][3][3];
        for (int[] side : SIDES) {
            for (int face = 0; face < TRIANGLES.length; face++) {
                int[] triangle = TRIANGLES[face];
                model[face][0] = vertices[side[triangle[0]]].toArray();
                model[face][1] = vertices[side[triangle[1]]].toArray();
                model[face][2] = vertices[side[triangle[2]]].toArray();
            }
        }

        return model;
    }
}
