package com.acuity.rs.api;

//Generated

public interface RSPlayerComposite {

    int[] getEquipmentIds();

    long getHash();

    int[] getBodyPartColors();

    boolean isFemale();

    int getTransformedNpcId();

    RSModel invokeGetModel(RSAnimationSequence var0, int var1, RSAnimationSequence var2, int var3);
}
