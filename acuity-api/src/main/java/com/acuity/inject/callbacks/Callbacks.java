package com.acuity.inject.callbacks;

import com.acuity.api.AcuityInstance;
import com.acuity.api.Events;
import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.events.impl.GameStateChangeEvent;
import com.acuity.api.rs.events.impl.OverheadPrayerChangeEvent;
import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.query.Npcs;
import com.acuity.api.rs.query.SceneElements;
import com.acuity.api.rs.utils.Game;
import com.acuity.api.rs.utils.LocalPlayer;
import com.acuity.api.rs.utils.Scene;
import com.acuity.api.rs.utils.Varps;
import com.acuity.api.rs.wrappers.peers.engine.Varpbit;
import com.acuity.api.rs.wrappers.peers.rendering.Model;
import com.acuity.api.rs.wrappers.peers.scene.SceneTile;
import com.acuity.rs.api.RSPlayer;
import com.acuity.rs.api.RSRenderable;
import com.acuity.rs.api.RSSceneElementComposite;
import com.acuity.rs.api.RSVarpbit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by Zachary Herridge on 6/7/2017.
 */
public class Callbacks {

    private static final Logger logger = LoggerFactory.getLogger(Callbacks.class);

    @ClientInvoked
    public static void postFieldChangeCallback(String name, int index, Object object) {
        logger.debug("'{}' called with index={} and object={}", name, index, object);
        switch (name) {
            case "playerPrayerChange":
                Events.getRsEventBus().post(new OverheadPrayerChangeEvent(((RSPlayer) object).getWrapper()));
                break;
            case "gameStateChanged":
                Events.getRsEventBus().post(new GameStateChangeEvent(AcuityInstance.getClient().getGameState()));
                break;
        }
    }

    @ClientInvoked
    public static void tick() {

    }

    @ClientInvoked
    public static void drawCallback(Image image) {
        try {
            if (Game.getGameState() == Game.IN_GAME) {
                SceneElements.streamLoaded().forEach(element -> {

                });

/*                Npcs.streamLoaded().sorted(Comparator.comparingInt(Locatable::distance)).limit(20).forEach(npc -> {
                    npc.getCachedModel().map(Model::streamPoints).map(Stream::findFirst).flatMap(Function.identity()).ifPresent(point -> {
                        image.getGraphics().drawString(npc.getNullSafeName() + npc.getActions(), (int) point.getX(), (int) point.getY());
                    });

                });

                SceneElements.streamLoaded().filter(sceneElement -> sceneElement.getName() != null).sorted(Comparator.comparingInt(Locatable::distance)).limit(20).forEach(sceneElement -> {
                    sceneElement.getModel().map(Model::streamPoints).map(Stream::findFirst).flatMap(Function.identity()).ifPresent(point -> {
                        image.getGraphics().drawString(sceneElement.getNullSafeName() + sceneElement.getActions(), (int) point.getX(), (int) point.getY());

                    });
                });*/
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
