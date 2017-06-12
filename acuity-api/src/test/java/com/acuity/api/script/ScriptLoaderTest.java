package com.acuity.api.script;

import com.acuity.api.script.impl.AcuityScript;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.Assert.*;

/**
 * Created by Zach on 6/11/2017.
 */
public class ScriptLoaderTest {

    private static Logger logger = LoggerFactory.getLogger(ScriptLoaderTest.class);

    @Test
    public void test() throws Exception {
        new ScriptLoader().loadScriptFromJar(getClass().getResource("script-testing.jar").getFile()).execute();
    }
}