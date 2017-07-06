package com.acuity.inject.replacements;

import com.acuity.api.annotations.ClientInvoked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Zachary Herridge on 7/3/2017.
 */
public class RRuntime {

    private static final Logger logger = LoggerFactory.getLogger(RRuntime.class);

    @ClientInvoked
    public static Process exec(Runtime runtime, String command) throws IOException {
        logger.warn("RS executing command '{}' via Runtime.", command);
        return runtime.exec(command);
    }

    @ClientInvoked
    public static long totalMemory(Runtime runtime){
        logger.debug("RS requesting total memory via Runtime.");
        return runtime.totalMemory();
    }

    @ClientInvoked
    public static long maxMemory(Runtime runtime){
        logger.debug("RS requesting max memory via Runtime.");
        return runtime.maxMemory();
    }

    @ClientInvoked
    public static int availableProcessors(Runtime runtime){
        logger.debug("RS requesting available processors via Runtime.");
        return runtime.availableProcessors();
    }

    @ClientInvoked
    public static long freeMemory(Runtime runtime){
        logger.debug("RS requesting free memory via Runtime.");
        return runtime.freeMemory();
    }
}
