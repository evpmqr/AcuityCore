package com.acuity.api.rs.peers.interfaces;

import com.acuity.api.rs.peers.interfaces.impl.AbstractInterfaceComponent;
import com.acuity.rs.api.RSInterfaceComponent;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by MadDev, June 10, 2017
 */
public class InterfaceComponent extends AbstractInterfaceComponent {

    public InterfaceComponent(RSInterfaceComponent peer) {
        super(peer);
    }

    /**
     * @return A list of non null grand children of this sub-component.
     */
    public InterfaceComponent[] getComponents() {
        return Arrays.stream(rsInterfaceComponent.getComponents())
                .map(child -> child != null ? new InterfaceComponent(child) : null)
                .toArray(InterfaceComponent[]::new);
    }
}
