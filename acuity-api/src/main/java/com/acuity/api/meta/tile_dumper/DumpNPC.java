package com.acuity.api.meta.tile_dumper;

import com.acuity.api.rs.wrappers.peers.scene.actors.impl.Npc;
import org.bson.Document;

/**
 * Created by Zachary Herridge on 7/18/2017.
 */
public class DumpNPC {

    private String _id;

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
        this._id = x + ":" + y + ":" + z + ":" + name + ":" + npcID;
    }

    public Document toUpdate(){
        Document update =  new Document("$set",
                new Document()
                        .append("x", x)
                        .append("y", y)
                        .append("z", z)
                        .append("n", name)
                        .append("ni", npcID));
        return update;
    }

    public String getID() {
        return _id;
    }
}
