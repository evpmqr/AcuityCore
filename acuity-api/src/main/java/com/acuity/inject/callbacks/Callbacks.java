package com.acuity.inject.callbacks;

import com.acuity.api.AcuityInstance;
import com.acuity.api.Events;
import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.events.GameStateChangeEvent;
import com.acuity.api.rs.events.OverheadPrayerChangeEvent;
import com.acuity.rs.api.RSPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

/**
 * Created by Zachary Herridge on 6/7/2017.
 */
public class Callbacks {

    private static final Logger logger = LoggerFactory.getLogger(Callbacks.class);

    @ClientInvoked
    public static void postFieldChangeCallback(String name, int index, Object object){
        logger.debug("'{}' called with index={} and object={}", name, index, object);
        switch (name){
            case "playerPrayerChange":
                Events.getRsEventBus().post(new OverheadPrayerChangeEvent(((RSPlayer) object).getWrapper()));
                break;
            case "gameStateChanged":
                Events.getRsEventBus().post(new GameStateChangeEvent(AcuityInstance.getClient().getGameState()));
                break;
        }
    }

    @ClientInvoked
    public static void tick(){

    }

    @ClientInvoked
    public static void drawCallback(Image image){

    }
}
