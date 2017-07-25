package com.acuity.api.meta.tile_dumper;

/**
 * Created by Zach on 7/12/2017.
 */
public class DumpTile {

    private int x,y,z;
    private int flag;

    public DumpTile(int x, int y, int z, int flag) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPlane() {
        return z;
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
}
