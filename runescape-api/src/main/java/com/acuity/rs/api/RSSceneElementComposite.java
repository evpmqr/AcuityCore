package com.acuity.rs.api;

//Generated

public interface RSSceneElementComposite extends RSCacheableNode {

    int[] getObjectTypes();

    int[] getObjectModels();

    java.lang.String[] getActions();

    int getOffsetHeight();

    boolean isIsRotated();

    int getModelSizeX();

    int getModelSizeY();

    int getId();

    short[] getNewColors();

    int getClipType();

    int getSizeX();

    int getSizeY();

    int getMapSceneId();

    short[] getTextureToReplace();

    short[] getTextureToFind();

    int getModelSizeHeight();

    int getContrast();

    int getAmbientSoundId();

    java.lang.String getName();

    short[] getColors();

    int getVarpId();

    boolean isNonFlatShading();

    int getMapIconId();

    int getAmbient();

    int getAnimationId();

    int getVarpIndex();

    int getOffsetY();

    int getOffsetX();

    int[] getTransformIds();

    boolean isClipped();

    boolean isIsSolid();

    boolean isClipped1();

    void invokeLoadData(RSBuffer var0, int var1);

    RSSceneElementComposite invokeGetImpostor();
}
