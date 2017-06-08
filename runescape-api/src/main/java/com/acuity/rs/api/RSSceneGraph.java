package com.acuity.rs.api;

//Generated

public interface RSSceneGraph {

    com.acuity.rs.api.RSSceneTile[][][] getTiles();

    com.acuity.rs.api.RSSceneElement[] getTempMarkers();

    void invokeGroundObjectSpawned(int var0, int var1, int var2, int var3, RSRenderable var4, int var5, int var6);
}
