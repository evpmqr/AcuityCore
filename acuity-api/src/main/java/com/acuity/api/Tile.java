package com.acuity.api;

/**
 * Created by Zach on 5/31/2017.
 */
public class Tile {

    private com.acuity.rs.api.Tile tile;

    public Tile(com.acuity.rs.api.Tile tile) {
        this.tile = tile;
    }

    public int getY(){
        return this.tile.getY();
    }
}
