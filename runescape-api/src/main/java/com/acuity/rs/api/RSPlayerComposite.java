package com.acuity.rs.api;

import com.acuity.rs.mapping.Inject;

/**
 * Created by Zachary Herridge on 6/2/2017.
 */
@Inject("RSPlayerComposite")
public interface RSPlayerComposite {

    @Inject("equipmentIds")
    int[] getEquipmentIds();

    @Inject("hash")
    long getHash();

    @Inject("bodyPartColors")
    int[] getBodyPartColors();

    @Inject("female")
    boolean isFemale();

    @Inject("transformedNpcId")
    int getTransformedNpcId();
}
