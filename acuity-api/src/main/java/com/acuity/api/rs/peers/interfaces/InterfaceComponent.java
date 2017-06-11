package com.acuity.api.rs.peers.interfaces;

import com.acuity.api.rs.peers.interfaces.impl.AbstractInterfaceComponent;
import com.acuity.rs.api.RSInterfaceComponent;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by MadDev, June 10, 2017
 */
public class InterfaceComponent extends AbstractInterfaceComponent {

    private final Interface root;
    private final InterfaceComponent parent;
    private final RSInterfaceComponent child;

    public InterfaceComponent(Interface root, InterfaceComponent parent, RSInterfaceComponent child) {
        super(child);
        this.root = root;
        this.parent = parent;
        this.child = child;
    }

    /**
     * @return A list of non null grand children of this sub-component.
     */
    public List<InterfaceComponent> getSubComponents() {
        return Arrays.stream(this.child.getComponents())
                .filter(Objects::nonNull)
                .map(gc -> new InterfaceComponent(root, this, gc))
                .collect(Collectors.toList());
    }

    public InterfaceComponent getParent() {
        return parent;
    }

    public Interface getRoot() {
        return root;
    }
}
