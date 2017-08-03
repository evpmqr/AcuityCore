package com.acuity.control_server;

import java.net.UnknownHostException;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class ControlServerBootstrap {

    public static void main(String[] args) {
        try {
            AcuityWSServer acuityWSServer = new AcuityWSServer(8015);
            acuityWSServer.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
