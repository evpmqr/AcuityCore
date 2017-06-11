package com.acuity.api.script.impl;

public abstract class AcuityScript implements Loopable {

    private boolean isPaused;
    private boolean isStopped;

    public void execute() {
        final Thread t = new Thread(script);
        t.setName("AcuityScriptRunner");
        t.start();
    }

    private Runnable script = () -> {
        while (!isStopped) {
            try {
                doLoop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private void doLoop() throws InterruptedException {
        synchronized (this) {
            if (isPaused) {
                Thread.sleep(50);
                return;
            }
            try {
                loop();
            } catch (Exception e) {
                e.printStackTrace();
                isStopped = true;
            }
        }
    }

    public void stop() {
        this.isStopped = true;
    }

    public void pause() {
        this.isPaused = true;
    }
}
