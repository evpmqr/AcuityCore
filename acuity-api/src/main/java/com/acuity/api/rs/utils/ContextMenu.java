package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;
import com.google.common.collect.Streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Created by Zachary Herridge on 7/11/2017.
 */
public class ContextMenu {

    public static Stream<String> streamActions(){
        return Arrays.stream(AcuityInstance.getClient().getContenxtMenuActions(), 0, getRowCount())
                .filter(Objects::nonNull)
                .sorted(Collections.reverseOrder());
    }

    public static Stream<String> streamTargets(){
        return Arrays.stream(AcuityInstance.getClient().getContenxtMenuTargets(), 0, getRowCount())
                .filter(Objects::nonNull)
                .sorted(Collections.reverseOrder());

    }

    public static Stream<String> streamChildren(){
        return Streams.zip(streamActions(), streamTargets(),(action, target) -> action + " " + target);
    }

    public static int getRowCount(){
        return AcuityInstance.getClient().getContextMenurRowCount();
    }
}
