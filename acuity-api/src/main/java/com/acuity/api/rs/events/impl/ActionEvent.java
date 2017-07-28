package com.acuity.api.rs.events.impl;

/**
 * Created by Zachary Herridge on 7/6/2017.
 */
public class ActionEvent {

    private final int opcode;
    private final int arg0;
    private final int arg1;
    private final int arg2;
    private final String action;
    private final String target;
    private final int clickX;
    private final int clickY;

    public ActionEvent(int opcode, int arg0, int arg1, int arg2, String action, String target, int clickX, int clickY) {
        this.opcode = opcode;
        this.arg0 = arg0;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.action = action;
        this.target = target;
        this.clickX = clickX;
        this.clickY = clickY;
    }

    public int getOpcode() {
        return opcode;
    }

    public int getArg0() {
        return arg0;
    }

    public int getArg1() {
        return arg1;
    }

    public int getArg2() {
        return arg2;
    }

    public String getAction() {
        return action;
    }

    public String getTarget() {
        return target;
    }

    public int getClickX() {
        return clickX;
    }

    public int getClickY() {
        return clickY;
    }

    @Override
    public String toString() {
        return "ActionEvent{" +
                "opcode=" + opcode +
                ", arg0=" + arg0 +
                ", arg1=" + arg1 +
                ", arg2=" + arg2 +
                ", action='" + action + '\'' +
                ", target='" + target + '\'' +
                ", clickX=" + clickX +
                ", clickY=" + clickY +
                '}';
    }
}
