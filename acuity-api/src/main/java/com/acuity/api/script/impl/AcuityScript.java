package com.acuity.api.script.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by MadDev on 6/11/17.
 */
public abstract class AcuityScript implements Loopable {

    private ScriptState state = ScriptState.NO_SCRIPT;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public void execute() {
        executor.submit(script);
        state = ScriptState.RUNNING;
    }

    private Runnable script = () -> {
        while (state != ScriptState.NO_SCRIPT) {
            try {
                doLoop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private void doLoop() throws InterruptedException {
        synchronized (this) {
            if (state == ScriptState.PAUSED) {
                Thread.sleep(50);
                return;
            }
            try {
                loop();
            } catch (Exception e) {
                e.printStackTrace();
                state = ScriptState.NO_SCRIPT;
            }
        }
    }

    public void stop() {
        executor.shutdown();
        state = ScriptState.NO_SCRIPT;
    }

    public void pause() {
        state = ScriptState.PAUSED;
    }

    public void resume() {
        state = ScriptState.RUNNING;
    }

    public boolean isPaused() {
        return state == ScriptState.PAUSED;
    }

    public ScriptState getState() {
        return state;
    }

    public void onExit() {
        System.out.println("Script has stopped.");
    }
}
