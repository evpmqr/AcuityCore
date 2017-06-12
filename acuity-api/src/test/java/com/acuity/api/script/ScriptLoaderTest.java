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

    private static Logger logger = LoggerFactory.getLogger(ScriptLoader.class);

    @Test
    public void test(){
        try {
            final AcuityScript script = new ScriptLoader().loadScriptFromJar(getClass().getResource("script-testing.jar").getFile());
            if (script == null) {
                logger.warn("No scripts found.");
                return;
            }
            script.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        Optional<Optional<String>> xx = Optional.of(Optional.ofNullable(null));

        Optional<String> optional = xx.flatMap(Function.identity());
        System.out.println(optional.orElse(null));
    }
}