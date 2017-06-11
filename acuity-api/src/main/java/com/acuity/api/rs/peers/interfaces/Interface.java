package com.acuity.api.rs.peers.interfaces;

import com.acuity.rs.api.RSInterfaceComponent;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by MadDev, June 10, 2017
 */
public class Interface {

    private final RSInterfaceComponent[] children;

    public Interface(RSInterfaceComponent[] children) {
        this.children = children;
    }

    /**
     *
     * @return List of non-null components of the parent interface.
     */
    public List<InterfaceComponent> getComponents() {
        return Arrays.stream(this.children)
                .filter(Objects::nonNull)
                .map(child -> new InterfaceComponent(this, null, child))
                .collect(Collectors.toList());
    }

    /**
     *
     * @return Count of total non-null components of this interface.
     */
    public int getCountOfComponents() {
        return (int) Arrays.stream(this.children)
                .filter(Objects::nonNull)
                .count();
    }
}
