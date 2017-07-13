package com.acuity.api.meta.tile_dumper;

import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * Created by Zach on 7/12/2017.
 */
public class DumpTile {

    private String _id;
    private int x,y,z;
    private int flag;

    public DumpTile(int x, int y, int z, int flag) {
        this._id = x + ":" + y + ":" + z;
        this.x = x;
        this.y = y;
        this.z = z;
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public Document toUpdate(){
        Document update =  new Document("$set",
                new Document()
                        .append("x", x)
                        .append("y", y)
                        .append("z", z)
                        .append("flag", flag));
        return update;
    }

    public String getID() {
        return _id;
    }

    @Override
    public String toString() {
        return "DumpTile{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", flag=" + flag +
                '}';
    }

    public boolean isLoaded() {
        return false;
    }
}
