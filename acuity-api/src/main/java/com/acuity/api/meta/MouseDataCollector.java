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

    public void processCanvasEvent(MouseEvent event){
        printWriter.write(System.currentTimeMillis() + ",canvasEvent," + event.getWhen() + "," + event.getX() + "," + event.getY() + "," + event.getButton() + "," + event.getClickCount() + "," + event.getModifiersEx() + "\n");
    }

    public void processCanvasEvent(KeyEvent event){

    }

    @Subscribe
    public void processRecorderUpdate(MouseRecorderUpdateEvent event){

    }

    @Subscribe
    public void processAction(ActionEvent event){
        printWriter.write(System.currentTimeMillis() + ",actionEvent," + event.getOpcode() + "," + event.getArg0() + "," + event.getArg1() + "," + event.getArg3() + "," + event.getAction() + "," + event.getTarget() + "," + event.getClickX() + "," + event.getClickY() + "\n");
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
