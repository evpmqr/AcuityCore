package com.acuity.api.rs.events.impl;

import com.acuity.api.rs.events.RSEvent;

/**
 * Created by Zachary Herridge on 7/28/2017.
 */
public class MenuInsertEvent implements RSEvent{
    private final int opcode;
    private final int arg0;
    private final int arg1;
    private final int arg2;
    private final String action;
    private final String target;

    public MenuInsertEvent(int opcode, int arg0, int arg1, int arg2, String action, String target) {

        this.opcode = opcode;
        this.arg0 = arg0;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.action = action;
        this.target = target;
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
}
