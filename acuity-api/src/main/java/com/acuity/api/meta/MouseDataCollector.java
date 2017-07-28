package com.acuity.api.meta;

import com.acuity.api.Events;
import com.acuity.api.rs.events.impl.ActionEvent;
import com.acuity.api.rs.events.impl.MouseRecorderUpdateEvent;
import com.google.common.eventbus.Subscribe;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Zachary Herridge on 7/6/2017.
 */
public class MouseDataCollector {

    public static final MouseDataCollector INSTANCE = new MouseDataCollector();

    private PrintWriter printWriter;

    private synchronized void write(String log){
        printWriter.write(log);
    }
    
    @Subscribe
    public void processRecorderUpdate(MouseRecorderUpdateEvent event){
        write(event.getTimeMillis() + ",mouseRecorded," + event.getLastX() + "," + event.getLastY());
    }

    @Subscribe
    public void processKeyboardEvent(KeyEvent event){
        write(System.currentTimeMillis() + ",keyEvent," + event.getID() + ","+ event.getWhen() + "," + event.getKeyCode() + "," + event.getKeyChar() + "," + event.getExtendedKeyCode() + "," + event.getKeyLocation() + "," + event.getModifiers() + "," + event.getModifiersEx() + "\n");
    }

    @Subscribe
    public void processMouseEvent(MouseEvent event){
        write(System.currentTimeMillis() + ",mouseEvent," + event.getID() + ","+ event.getWhen() + "," + event.getX() + "," + event.getY() + "," + event.getButton() + "," + event.getClickCount() + "," + event.getModifiers() + "," + event.getModifiersEx() + "\n");
    }

    @Subscribe
    public void processAction(ActionEvent event){
        write(System.currentTimeMillis() + ",actionEvent," + event.getOpcode() + "," + event.getArg0() + "," + event.getArg1() + "," + event.getArg3() + "," + event.getAction() + "," + event.getTarget() + "," + event.getClickX() + "," + event.getClickY() + "\n");
    }

    public void start(){
        try {
            File file = new File("mouseLogs-" + System.currentTimeMillis() + ".txt");
            if (!file.exists()) file.createNewFile();
            printWriter = new PrintWriter(file);
            Events.getRsEventBus().register(this);
            Events.getAcuityEventBus().register(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        Events.getRsEventBus().unregister(this);
        Events.getAcuityEventBus().unregister(this);
    }
}
