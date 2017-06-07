package com.acuity.client;

import com.acuity.client.rs.RSAppletLoader;
import com.acuity.client.rs.RSStub;
import com.acuity.rs.api.RSClient;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

/**
 * Created by Zach on 5/31/2017.
 */
public class Acuity {

    public static void main(String[] args) {
        RSAppletLoader rsAppletLoader = new RSAppletLoader();
        try {
            Applet applet = rsAppletLoader.loadApplet();

            JFrame frame = new JFrame();
            frame.setSize(new Dimension(500, 500));
            frame.setVisible(true);
            frame.getContentPane().add(applet);

            RSStub rsStub = new RSStub(rsAppletLoader.getRsConfig(), applet);
            applet.setStub(rsStub);
            applet.init();
            applet.start();

            while (true){
                Thread.sleep(3000);
                System.out.println("GameState: " + ((RSClient) applet).getGameState());;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
