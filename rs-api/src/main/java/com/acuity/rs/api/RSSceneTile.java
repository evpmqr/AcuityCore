package com.acuity.rs.api;

//Generated

public interface RSSceneTile extends RSNode {

    com.acuity.rs.api.RSSceneBoundaryDecor getBoundaryDecor();

    com.acuity.rs.api.RSSceneTileDecor getGroundObject();

    com.acuity.rs.api.RSItemLayer getItemLayer();

    com.acuity.rs.api.RSSceneElement[] getMarkers();

    com.acuity.rs.api.RSSceneTileModel getModel();

    com.acuity.rs.api.RSSceneTilePaint getPaint();

    int getPlane();

    int getSceneX();

    int getSceneY();

    com.acuity.rs.api.RSSceneBoundary getWallObject();
}
