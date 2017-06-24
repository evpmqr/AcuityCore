package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.movement.SceneLocation;
import com.acuity.api.rs.wrappers.engine.Client;
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
    public static final int[] SINE = new int[2048];
    public static final int[] COSINE = new int[2048];

    static {
        for (int i = 0; i < SINE.length; i++) {
            SINE[i] = (int) (65536.0D * Math.sin((double) i * 0.0030679615D));
            COSINE[i] = (int) (65536.0D * Math.cos((double) i * 0.0030679615D));
        }
    }

    public static Optional<Point> sceneToScreen(int sceneX, int sceneY, int plane) {
        return worldToScreen(sceneX * 128, sceneY * 128, plane);
    }

    public static Optional<Point> worldToScreen(int strictX, int strictY, int height) {
        if (strictX >= 128 && strictX <= 13056 && strictY >= 128 && strictY <= 13056) {
            int alt = Camera.getPitch();
            if (alt < 0) {
                return Optional.empty();
            }
            int yaw = Camera.getYaw();
            if (yaw < 0) {
                return Optional.empty();
            }
            int elevation = getGroundHeight(strictX, strictY) - height;
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
            return Optional.of(new Point(256 + (strictX << 9) / strictY, (angle << 9) / strictY + 167));
        }
        return Optional.empty();
    }

    public static Optional<Point> sceneToMiniMap(int sceneX, int sceneY) {
        return sceneToMiniMap(sceneX, sceneY, null);
    }

    public static Optional<Point> sceneToMiniMap(int sceneX, int sceneY, Integer distanceFilter) {
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
            return Optional.of(new Point(sceneX, sceneY));
        }

        return Optional.empty();
    }

    public static int getGroundHeight(int x, int y) {
        int x1 = x >> 7;
        int y1 = y >> 7;
        if (x1 < 0 || x1 > 103 || y1 < 0 || y1 > 103) {
            return 0;
        }
        byte[][][] rules = Scene.getRenderRules();
        if (rules == null) {
            return 0;
        }
        int[][][] heights = Scene.getTileHeights();
        if (heights == null) {
            return 0;
        }
        int plane = Scene.getPlane();
        if (plane < 3 && (rules[1][x1][y1] & 0x2) == 2) {
            plane++;
        }
        int x2 = x & 0x7F;
        int y2 = y & 0x7F;
        int h1 = heights[plane][x1][y1] * (128 - x2) + heights[plane][x1 + 1][y1] * x2 >> 7;
        int h2 = heights[plane][x1][y1 + 1] * (128 - x2) + heights[plane][x1 + 1][y1 + 1] * x2 >> 7;
        return h1 * (128 - y2) + h2 * y2 >> 7;
    }
}
