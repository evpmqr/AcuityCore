package com.acuity.rs.api;

//Generated

public interface RSItemComposite extends RSCacheableNode {

    int getFemaleOffset();

    java.lang.String getName();

    int getYan2d();

    int getNotedTemplate();

    int getFemaleModel();

    int getFemaleModel1();

    int getOffsetX2d();

    int getFemaleModel2();

    int getInventoryModel();

    int getContrast();

    int getZan2d();

    int getFemaleHeadModel();

    int getTeam();

    short[] getColourToReplaceWith();

    int[] getCountObj();

    int[] getCountCo();

    int getId();

    int getOffsetY2d();

    int getFemaleHeadModel2();

    int getMaleHeadModel2();

    int getZoom2d();

    int getMaleHeadModel();

    short[] getTextToReplaceWith();

    int getAmbient();

    boolean isIsMembers();

    int getMaleOffset();

    short[] getColourToReplace();

    int getNote();

    java.lang.String[] getGroundActions();

    int getPrice();

    java.lang.String[] getInventoryActions();

    short[] getTextureToReplace();

    int getResizeX();

    int getResizeZ();

    int getResizeY();

    int getMaleModel();

    int getXan2d();

    int getMaleModel1();

    int getMaleModel2();

    int getIsStackable();

    RSModelComposite invokeGetWornModelData(boolean var0);

    void invokeLoadBuffer(RSBuffer var0);

    boolean invokeGetEquipmentModel(boolean var0);

    void invokePopulateFromBuffer(RSBuffer var0, int var1);

    RSModel invokeGetModel(int var0);
}
