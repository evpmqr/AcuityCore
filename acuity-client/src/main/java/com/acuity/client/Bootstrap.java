package com.acuity.client;

import com.acuity.api.AcuityInstance;
import com.acuity.api.applet.input.MouseMiddleMan;
import com.acuity.api.rs.movement.WorldLocation;
import com.acuity.api.rs.query.SceneElements;
import com.acuity.api.rs.utils.Game;
import com.acuity.api.rs.utils.Movement;
import com.acuity.client.devgui.ScriptRunnerView;
import com.acuity.inject.callbacks.Callbacks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/**
 * Created by Zach on 5/31/2017.
 */
public class Bootstrap {

    public static void main(String[] args) {
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

                while (true){
                    Thread.sleep(500);
                    Callbacks.postFieldChangeCallback("gameStateChanged", -1, null);
                    if (Game.getGameState() == Game.LOGIN_SCREEN){
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}