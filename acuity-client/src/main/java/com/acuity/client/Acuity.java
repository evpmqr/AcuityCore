package com.acuity.client;

import com.acuity.client.rs.RSAppletLoader;
import com.acuity.client.rs.RSStub;
import com.acuity.rs.api.RSClient;
import com.acuity.rs.api.RSNPC;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

/**
 * Created by Zach on 5/31/2017.
 */
public class Acuity {

    private static RSClient client;

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

            client = (RSClient) applet;

            while (true){
                Thread.sleep(3000);

                if ((client.getGameState() > 10)) {
                    RSNPC[] npcs = client.getNpcs();
                    System.out.println("NPCS: " + npcs.length);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO: 6/8/2017 placeholder
    public static RSClient getClient() {
        return client;
    }
}
