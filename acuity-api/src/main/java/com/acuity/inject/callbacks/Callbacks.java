package com.acuity.inject.callbacks;

import com.acuity.api.AcuityInstance;
import com.acuity.api.Events;
import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.events.impl.GameStateChangeEvent;
import com.acuity.api.rs.utils.Game;
import com.acuity.rs.api.RSConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

/**
 * Created by Zachary Herridge on 6/7/2017.
 */
public class Callbacks {

    private static final Logger logger = LoggerFactory.getLogger(Callbacks.class);

    @ClientInvoked
    public static void fieldUpdating(String name, int index, Object instance) {
        logger.trace("Field Updating: '{}' with index={} and instance={}", name, index, instance);
        switch (name) {

        }
    }

    @ClientInvoked
    public static void fieldUpdated(String name, int index, Object instance) {
        logger.trace("Field Updated: '{}' with index={} and instance={}", name, index, instance);
        switch (name) {
            case "gameState":
                Events.getRsEventBus().post(new GameStateChangeEvent(AcuityInstance.getClient().getGameState()));
                break;
        }
    }

    public static void processActionCallback(int var0, int var1, int var2, int var3, String var4, String var5, int var6, int var7, int var8){

    }

    @ClientInvoked
    public static void tick() {

    }

    @ClientInvoked
    public static void queueForWriteCallback(RSConnection connectionInstance, byte[] writeBuffer, int offset, int length){
        logger.trace("Queued for write buffer={} offset={} length={}", writeBuffer == null ? "null" : "[" + writeBuffer.length + "]", offset, length);
    }

    @ClientInvoked
    public static void drawCallback(Image image) {
        try {
            if (Game.getGameState() == Game.IN_GAME) {

/*                Npcs.streamLoaded().sorted(Comparator.comparingInt(Locatable::distance)).limit(20).forEach(npc -> {
                    npc.getCachedModel().map(Model::streamPoints).map(Stream::findFirst).flatMap(Function.identity()).ifPresent(screenLocation -> {
                        image.getGraphics().drawString(npc.getNullSafeName() + npc.getActions(), screenLocation.getX(), screenLocation.getY());
                    });

                });

                SceneElements.streamLoaded().filter(sceneElement -> sceneElement.getName() != null).sorted(Comparator.comparingInt(Locatable::distance)).limit(20).forEach(sceneElement -> {
                    sceneElement.getModel().map(Model::streamPoints).map(Stream::findFirst).flatMap(Function.identity()).ifPresent(screenLocation -> {
                        image.getGraphics().drawString(sceneElement.getNullSafeName() + sceneElement.getActions(), screenLocation.getX(), screenLocation.getY());

                    });
                });*/
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
