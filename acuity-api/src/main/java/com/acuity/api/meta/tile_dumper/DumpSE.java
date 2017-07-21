package com.acuity.api.meta.tile_dumper;

import com.acuity.api.rs.wrappers.common.SceneElement;
import org.bson.Document;

/**
 * Created by Zachary Herridge on 7/18/2017.
 */
public class DumpSE {

    private String _id;
    private int x,y,z;
    private int seID;
    private String name;
    private int rotation;

    public DumpSE(SceneElement sceneElement, int x, int y, int plane) {
        this.x = x;
        this.y = y;
        this.z = plane;
        this.name = sceneElement.getName();
        this.seID = sceneElement.getID();
        this.rotation = sceneElement.getOrientation();

        this._id = x + ":" + y + ":" + z + ":" + name + ":" + seID + ":" + rotation;
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

    public String getName() {
        return name;
    }

    public int getRotation() {
        return rotation;
    }

    public Document toUpdate(){
        Document update =  new Document("$set",
                new Document()
                        .append("x", x)
                        .append("y", y)
                        .append("z", z)
                        .append("o", rotation)
                        .append("n", name)
                        .append("i", seID));
        return update;
    }

    public String getID() {
        return _id;
    }
}
