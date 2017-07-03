package com.acuity.inject.replacements;

import com.acuity.api.annotations.ClientInvoked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * Created by Zachary Herridge on 6/30/2017.
 */
public class RGarbageCollector {

    private static final Logger logger = LoggerFactory.getLogger(RGarbageCollector.class);

    @ClientInvoked
    public static GarbageCollectorMXBean gcBean;

    @ClientInvoked
    public static List<GarbageCollectorMXBean> getBeans(){
        logger.trace("RS requested all gc beans.");
        return ManagementFactory.getGarbageCollectorMXBeans();
    }

    @ClientInvoked
    public static void gcBeanUpdated(){
        logger.trace("GC bean updated.");
    }
}
