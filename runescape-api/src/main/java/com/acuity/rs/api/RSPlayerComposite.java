package com.acuity.rs.api;

/**
 * Created by Zachary Herridge on 6/2/2017.
 */

public interface RSPlayerComposite {


    int[] getEquipmentIds();


    long getHash();


    int[] getBodyPartColors();


    boolean isFemale();


    int getTransformedNpcId();
}
