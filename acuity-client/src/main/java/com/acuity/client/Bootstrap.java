package com.acuity.client;

import com.acuity.api.AcuityInstance;
import com.acuity.api.Events;
import com.acuity.api.rs.events.impl.drawing.InGameDrawEvent;
import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.query.Npcs;
import com.acuity.api.rs.query.SceneElements;
import com.acuity.api.rs.wrappers.peers.rendering.Model;
import com.acuity.client.devgui.ScriptRunnerView;
import com.google.common.eventbus.Subscribe;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by Zach on 5/31/2017.
 */
public class Bootstrap {

    @Subscribe
    public void testDraw(InGameDrawEvent event){
        Npcs.streamLoaded().sorted(Comparator.comparingInt(Locatable::distance)).limit(20).forEach(npc -> {
            npc.getCachedModel().map(Model::streamPoints).map(Stream::findFirst).flatMap(Function.identity()).ifPresent(screenLocation -> {
                event.getGraphics().drawString(npc.getNullSafeName() + npc.getActions(), screenLocation.getX(), screenLocation.getY());
            });

        });

        SceneElements.streamLoaded().filter(sceneElement -> !sceneElement.getNullSafeName().equals("null")).sorted(Comparator.comparingInt(Locatable::distance)).limit(20).forEach(sceneElement -> {
            sceneElement.getModel().map(Model::streamPoints).map(Stream::findFirst).flatMap(Function.identity()).ifPresent(screenLocation -> {
                event.getGraphics().drawString(sceneElement.getNullSafeName() + sceneElement.getActions(), screenLocation.getX(), screenLocation.getY());
            });
        });
    }

    public Bootstrap() {
        EventQueue.invokeLater(() -> {
            try {
                JFrame frame = new JFrame();
                frame.setSize(new Dimension(800, 600));
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);

                AcuityInstance.init();
                frame.getContentPane().add(AcuityInstance.getAppletManager().getClient().getApplet());
                AcuityInstance.boot();

                new ScriptRunnerView().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Events.getRsEventBus().register(this);
    }

    public static void main(String[] args) {
        new Bootstrap();
    }
}