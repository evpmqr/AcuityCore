package com.acuity.control.server;

import com.acuity.db.AcuityDB;

import java.net.UnknownHostException;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class ControlServerBootstrap {

    public static void main(String[] args) {
        try {
            AcuityDB.init();
            AcuityWSServer acuityWSServer = new AcuityWSServer(8015);
            acuityWSServer.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
