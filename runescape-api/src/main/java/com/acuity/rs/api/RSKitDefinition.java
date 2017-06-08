package com.acuity.rs.api;

//Generated

public interface RSKitDefinition extends RSCacheableNode {

    boolean isNonSelectable();

    int[] getModelIds();

    short[] getRetextureToReplace();

    int getBodyPartId();

    short[] getRetextureToFind();

    int[] getModels();

    short[] getRecolorToReplace();

    short[] getRecolorToFind();

    boolean invokeReady();

    RSModelComposite invokeGetModelData();
}
