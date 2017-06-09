package com.acuity.inject.callbacks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zachary Herridge on 6/7/2017.
 */
public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    public static void callHook(String name, int index, Object object){
        logger.trace("'{}' called with index={} and object={}", name, index, object);
    }
}
