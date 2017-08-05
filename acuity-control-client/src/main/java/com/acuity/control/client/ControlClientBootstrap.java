package com.acuity.control.client;

import java.net.URISyntaxException;

/**
 * Created by Zach on 8/5/2017.
 */
public class ControlClientBootstrap {


    public static void main(String[] args) {
        try {
            AcuityWSClient.getInstance().start("ws://localhost:8015");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
