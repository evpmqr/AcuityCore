package com.acuity.api.rs.peers.interfaces;

import com.acuity.api.rs.peers.interfaces.impl.AbstractInterfaceComponent;
import com.acuity.rs.api.RSInterfaceComponent;

import java.util.Arrays;
import java.util.Objects;

public class InterfaceComponent extends AbstractInterfaceComponent {

    private final Interface parent;
    private final RSInterfaceComponent child;

    public InterfaceComponent(Interface parent, RSInterfaceComponent child) {
        super(child);
        this.parent = parent;
        this.child = child;
    }

    /**
     * @return A list of non null grand children of this sub-component.
     */
    public InterfaceComponentChild[] getSubComponents() {
        return Arrays.stream(this.child.getComponents())
                .filter(Objects::nonNull)
                .map(gc -> new InterfaceComponentChild(this, gc))
                .toArray(InterfaceComponentChild[]::new);
    }

    public Interface getParent() {
        return parent;
    }
}
