package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.movement.SceneLocation;
import com.acuity.api.rs.wrappers.engine.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.geom.Rectangle2D;
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

    public static Optional<Point> sceneToScreen(int x, int y, int plane) {
        return sceneToScreen(x, y, plane, 0);
    }


    public static Optional<Point> sceneToScreen(int sceneX, int sceneY, int plane, int zOffset) {
        if (sceneX >= 128 && sceneY >= 128 && sceneX <= 13056 && sceneY <= 13056) {
            int z = getSceneTileHeight(sceneX, sceneY, Scene.getPlane()) - plane;
            sceneX -= Camera.getX();
            sceneY -= Camera.getY();
            z -= Camera.getZ();
            z -= zOffset;

            int cameraPitch = Camera.getPitch();
            int cameraYaw = Camera.getYaw();

            int pitchSin = SINE[cameraPitch];
            int pitchCos = COSINE[cameraPitch];
            int yawSin = SINE[cameraYaw];
            int yawCos = COSINE[cameraYaw];

            int _angle = yawCos * sceneX + sceneY * yawSin >> 16;
            sceneY = yawCos * sceneY - yawSin * sceneX >> 16;
            sceneX = _angle;
            _angle = pitchCos * z - sceneY * pitchSin >> 16;
            sceneY = z * pitchSin + sceneY * pitchCos >> 16;

            if (sceneY >= 50) {
                Client client = AcuityInstance.getClient();
                int pointX = client.getViewportHeight() / 2 + sceneX * client.getViewportScale() / sceneY;
                int pointY = _angle * client.getViewportScale() / sceneY + client.getViewportWidth() / 2;
                return Optional.of(new Point(pointX, pointY));
            }
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

    public static int getSceneTileHeight(int x, int y, int plane) {
        int xx = x >> 7;
        int yy = y >> 7;

        if (xx < 0 || yy < 0 || xx > 103 || yy > 103) {
            throw new IllegalArgumentException("Coordinates outside loaded scene.");
        }

        byte[][][] tileSettings = Scene.getRenderRules();
        int[][][] tileHeights = Scene.getTileHeights();

        int var5 = plane;
        if (plane < 3 && (tileSettings[1][xx][yy] & 2) == 2) {
            var5 = plane + 1;
        }

        int var6 = x & 127;
        int var7 = y & 127;
        int var8 = var6 * tileHeights[var5][xx + 1][yy] + (128 - var6) * tileHeights[var5][xx][yy] >> 7;
        int var9 = tileHeights[var5][xx][yy + 1] * (128 - var6) + var6 * tileHeights[var5][xx + 1][yy + 1] >> 7;
        return (128 - var7) * var8 + var7 * var9 >> 7;
    }
}
