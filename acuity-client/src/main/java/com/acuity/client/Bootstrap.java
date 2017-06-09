package com.acuity.client;

import com.acuity.api.AcuityInstance;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Zach on 5/31/2017.
 */
public class Bootstrap {

    public static void main(String[] args) {
        try {
            AcuityInstance.init();

            JFrame frame = new JFrame();
            frame.setSize(new Dimension(500, 500));
            frame.setVisible(true);
            frame.getContentPane().add(AcuityInstance.getApplet());

            AcuityInstance.loadClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}