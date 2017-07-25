package com.acuity.api.meta.tile_dumper;

import com.acuity.api.rs.wrappers.common.SceneElement;

import java.util.List;

/**
 * Created by Zachary Herridge on 7/18/2017.
 */
public class DumpSE {

    private final List<String> actions;
    private int x,y,z;
    private int seID;
    private String name;
    private int rotation;
    private int uid;
    private int flag;

    public DumpSE(SceneElement sceneElement, int x, int y, int plane) {
        this.x = x;
        this.y = y;
        this.z = plane;
        this.name = sceneElement.getName();
        this.seID = sceneElement.getID();
        this.rotation = sceneElement.getOrientation();
        this.actions = sceneElement.getActions();
        this.uid = sceneElement.getUID().getUID();
        this.flag = sceneElement.getFlag();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getSeID() {
        return seID;
    }

    public List<String> getActions() {
        return actions;
    }

    public String getName() {
        return name;
    }

    public int getRotation() {
        return rotation;
    }

    public int getFlag() {
        return flag;
    }

    public int getUid() {
        return uid;
    }
}
