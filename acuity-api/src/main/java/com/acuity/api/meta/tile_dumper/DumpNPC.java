package com.acuity.api.meta.tile_dumper;

import com.acuity.api.rs.wrappers.peers.scene.actors.impl.Npc;

import java.util.List;

/**
 * Created by Zachary Herridge on 7/18/2017.
 */
public class DumpNPC {

    private final List<String> actions;
    private int x;
    private int y;
    private int z;
    private String name;
    private int npcID;


    public DumpNPC(Npc npc, int x, int y, int plane) {
        this.x = x;
        this.y = y;
        this.z = plane;
        this.name = npc.getName();
        this.npcID = npc.getID();
        this.actions = npc.getActions();
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

    public List<String> getActions() {
        return actions;
    }

    public String getName() {
        return name;
    }

    public int getNpcID() {
        return npcID;
    }

}
