package com.acuity.client;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.peers.Client;
import com.acuity.api.rs.peers.interfaces.Interface;
import com.acuity.api.rs.peers.interfaces.InterfaceComponent;
import com.acuity.api.rs.query.Interfaces;
import com.acuity.rs.api.RSInterfaceComponent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Zach on 5/31/2017.
 */
public class Bootstrap {

    public static void main(String[] args) {
        try {

            JFrame frame = new JFrame();
            frame.setSize(new Dimension(800, 600));
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            AcuityInstance.init();
            frame.getContentPane().add(AcuityInstance.getApplet());

            AcuityInstance.loadClient();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}