package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class MiniMap {

    private static final Logger logger = LoggerFactory.getLogger(MiniMap.class);

    public static int getRotation(){
        return AcuityInstance.getClient().getMapRotation();
    }
}
