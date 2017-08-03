package com.acuity.client;

import com.acuity.api.AcuityInstance;
import com.acuity.api.Events;
import com.acuity.api.input.SmartActions;
import com.acuity.api.meta.tile_dumper.TileDumper;
import com.acuity.api.rs.events.impl.drawing.InGameDrawEvent;
import com.acuity.api.rs.query.SceneElements;
import com.acuity.api.rs.utils.LocalPlayer;
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
public class ClientBootstrap {

    @Subscribe
    public void testDraw(InGameDrawEvent event){
        LocalPlayer.get().ifPresent(player -> {
            player.getCachedModel().map(Model::streamPoints).map(Stream::findFirst).flatMap(Function.identity()).ifPresent(screenLocation -> {
                event.getGraphics().drawString("HP: " + player.getHealthPercent(), screenLocation.getX(), screenLocation.getY());
            });
        });

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
        else if (e.getKeyChar() == 'a' && e.getID() == KeyEvent.KEY_TYPED){
            TileDumper.execute();
        }
        else if (e.getKeyChar() == 'n'){
            LocalPlayer.get().ifPresent(player -> {
                System.out.println(player.getHealthPercent());

            });
        }
    }

    public ClientBootstrap() {
        EventQueue.invokeLater(() -> {
            try {
                JFrame frame = new JFrame();
                frame.setSize(new Dimension(800, 600));
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);

                AcuityInstance.init();
                frame.getContentPane().add(AcuityInstance.getAppletManager().getClient().getApplet());
                AcuityInstance.boot();

           //     MouseDataCollector.INSTANCE.start();
           //     SmartActions.INSTANCE.start();

                new ScriptRunnerView().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Events.getRsEventBus().register(this);
        Events.getAcuityEventBus().register(this);
    }

    public static void main(String[] args) {
        new ClientBootstrap();
    }
}