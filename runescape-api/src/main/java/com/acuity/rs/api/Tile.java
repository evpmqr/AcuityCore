package com.acuity.rs.api;

import com.acuity.rs.mapping.Inject;

/**
 * Created by Zach on 5/31/2017.
 */

@Inject("Tile")
public interface Tile {

    @Inject("y")
    int getY();
}
