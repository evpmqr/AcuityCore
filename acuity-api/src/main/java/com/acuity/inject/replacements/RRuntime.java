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
    public static Process exec(String command) throws IOException {
        logger.warn("RS executing command '{}' via Runtime.", command);
        return Runtime.getRuntime().exec(command);
    }
}
