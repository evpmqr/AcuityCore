package com.acuity.client;

import com.acuity.api.AcuityInstance;
import com.acuity.api.Events;
import com.acuity.api.input.SmartActions;
import com.acuity.api.meta.tile_dumper.TileDumper;
import com.acuity.api.rs.events.impl.drawing.InGameDrawEvent;
import com.acuity.api.rs.query.SceneElements;
import com.acuity.api.rs.wrappers.peers.rendering.Model;
import com.acuity.client.devgui.ScriptRunnerView;
import com.google.common.eventbus.Subscribe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by Zach on 5/31/2017.
 */
public class Bootstrap {

    @Subscribe
    public void testDraw(InGameDrawEvent event){
        SceneElements.streamLoaded().filter(sceneElement -> sceneElement.getNullSafeName().equals("Door")).forEach(sceneElement -> {
            sceneElement.getModel().map(Model::streamPoints).map(Stream::findFirst).flatMap(Function.identity()).ifPresent(screenLocation -> {
                event.getGraphics().drawString(sceneElement.getNullSafeName() + sceneElement.getActions() + " " + sceneElement.getOrientation(), screenLocation.getX(), screenLocation.getY());
            });
        });
    }

    @Subscribe
    public void testKeyEvent(KeyEvent e){
        if (e.getKeyChar() == 'c' && e.isControlDown()){
            SmartActions.INSTANCE.clear();
        }
        else if (e.getKeyChar() == 'a'){
            TileDumper.execute();
        }
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

                SmartActions.INSTANCE.start();

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