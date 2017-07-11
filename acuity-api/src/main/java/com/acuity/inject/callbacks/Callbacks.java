package com.acuity.inject.callbacks;

import com.acuity.api.AcuityInstance;
import com.acuity.api.Events;
import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.meta.MouseDataCollector;
import com.acuity.api.rs.events.impl.ActionEvent;
import com.acuity.api.rs.events.impl.GameStateChangeEvent;
import com.acuity.api.rs.events.impl.GameTickEvent;
import com.acuity.api.rs.events.impl.MouseRecorderUpdateEvent;
import com.acuity.api.rs.events.impl.drawing.GameDrawEvent;
import com.acuity.api.rs.events.impl.drawing.InGameDrawEvent;
import com.acuity.api.rs.utils.Game;
import com.acuity.rs.api.RSAxisAlignedBoundingBox;
import com.acuity.rs.api.RSConnection;
import com.acuity.rs.api.RSModel;
import com.acuity.rs.api.RSRenderable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Zachary Herridge on 6/7/2017.
 */
public class Callbacks {

    private static final Logger logger = LoggerFactory.getLogger(Callbacks.class);

    public static final GameTickEvent GAME_TICK_EVENT = new GameTickEvent();

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
            case "mouseRecorderAccessed":
                Events.getRsEventBus().post(new MouseRecorderUpdateEvent());
                break;
        }
    }

    @ClientInvoked
    public static void processActionCallback(int arg2, int arg3, int opcode, int arg1, String action, String target, int clickX, int clickY){
        Events.getRsEventBus().post(new ActionEvent(opcode, arg1, arg2, arg3, action, target, clickX, clickY));
    }

    @ClientInvoked
    public static void tick() {
        GameTickEvent.incrementTick();
        Events.getRsEventBus().post(GAME_TICK_EVENT);
    }

    @ClientInvoked
    public static void queueForWriteCallback(RSConnection connectionInstance, byte[] writeBuffer, int offset, int length){
        logger.trace("Queued for write buffer={} offset={} length={}", writeBuffer == null ? "null" : "[" + writeBuffer.length + "]", offset, length);
    }

    public static void addAxisAlignedBoundingBoxCallback(RSModel rsModel, int i, int i2, int i3, int i4){
        System.out.println();
    }

    public static void generateLegacy2DBoundingBoxCallback(int i, int i2, int i3, int i4, int i5, int i6, int i7){
        System.out.println();
    }

    public static void aabbMouseTargetCalcCallBack(RSModel rsModel, int i, int i2, int i3){
        try {
            Field ah = rsModel.getClass().getField("ah");
            ah.setBoolean(rsModel, true);
            System.out.println("set");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public static void boundingBoxUpdated(RSRenderable rsRenderable, RSAxisAlignedBoundingBox rsAxisAlignedBoundingBox){
        System.out.println();
    }

    @ClientInvoked
    public static void drawCallback(Image image) {
        try {
            if (Game.getGameState() == Game.IN_GAME) {
                Events.getRsEventBus().post(new InGameDrawEvent(image));
            }
            else {
                Events.getRsEventBus().post(new GameDrawEvent(image));
            }
        }
        catch (Exception e){
            logger.error("Error during drawing.", e);
        }
    }
}
