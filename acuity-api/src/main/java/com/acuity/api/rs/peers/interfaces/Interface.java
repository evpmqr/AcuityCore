package com.acuity.api.rs.peers.interfaces;

import com.acuity.rs.api.RSInterfaceComponent;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by maddev on 6/10/17.
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
    public InterfaceComponent[] getComponents() {
        return Arrays.stream(this.children)
                .filter(Objects::nonNull)
                .map(child -> new InterfaceComponent(this, null, child))
                .toArray(InterfaceComponent[]::new);
    }

    /**
     *
     * @return Count of total non-null components of this interface.
     */
    public int getCountOfComponents() {
        return (int) Arrays.stream(this.children)
                .filter(Objects::nonNull)
                .map(child -> new InterfaceComponent(this, null, child))
                .count();
    }
}
