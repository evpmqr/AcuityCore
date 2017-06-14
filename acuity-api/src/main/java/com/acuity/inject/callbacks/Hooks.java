package com.acuity.inject.callbacks;

import com.acuity.api.AcuityInstance;
import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.events.OverheadPrayerChangeEvent;
import com.acuity.rs.api.RSPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zachary Herridge on 6/7/2017.
 */
public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @ClientInvoked
    public static void callHook(String name, int index, Object object){
        logger.trace("'{}' called with index={} and object={}", name, index, object);
        switch (name){
            case "playerPrayerChange":
                AcuityInstance.getEventBus().post(new OverheadPrayerChangeEvent(((RSPlayer) object).getWrapper()));
                break;
        }
    }
}
