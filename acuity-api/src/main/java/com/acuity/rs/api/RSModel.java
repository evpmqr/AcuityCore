package com.acuity.rs.api;


//Generated

import com.acuity.api.rs.wrappers.rendering.Model;

public interface RSModel extends RSRenderable {

    int[] getVerticesX();

    int[] getVerticesY();

    int[] getVerticesZ();

    int[] getXTriangles();

    int[] getYTriangles();

    int[] getZTriangles();

    Model getWrapper();
}
