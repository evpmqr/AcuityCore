package com.acuity.rs.api;

//Generated

public interface RSSceneElementComposite extends RSCacheableNode {

    java.lang.String[] getActions();

    int getAmbient();

    int getAmbientSoundId();

    int getAnimationId();

    int getClipType();

    short[] getColors();

    int getContrast();

    int getId();

    int getMapIconId();

    int getMapSceneId();

    int getModelSizeHeight();

    int getModelSizeX();

    int getModelSizeY();

    java.lang.String getName();

    short[] getNewColors();

    int[] getObjectModels();

    int[] getObjectTypes();

    int getOffsetHeight();

    int getOffsetX();

    int getOffsetY();

    int getSizeX();

    int getSizeY();

    short[] getTextureToFind();

    short[] getTextureToReplace();

    int[] getTransformIds();

    int getVarpId();

    int getVarpIndex();

    RSSceneElementComposite invokeGetImpostor();

    void invokeLoadData(RSBuffer var0, int var1);

    boolean isClipped();

    boolean isClipped1();

    boolean isIsRotated();

    boolean isIsSolid();

    boolean isNonFlatShading();
}
