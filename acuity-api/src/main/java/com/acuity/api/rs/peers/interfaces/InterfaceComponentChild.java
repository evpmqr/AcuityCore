package com.acuity.api.rs.peers.interfaces;

import com.acuity.api.rs.peers.interfaces.impl.AbstractInterfaceComponent;
import com.acuity.rs.api.RSInterfaceComponent;

/**
 * Created by maddev on 6/10/17.
 */
public class InterfaceComponentChild extends AbstractInterfaceComponent {

    private InterfaceComponent parent;
    private RSInterfaceComponent child;

    InterfaceComponentChild(InterfaceComponent parent, RSInterfaceComponent child) {
        super(child);
        this.parent = parent;
        this.child = child;
    }

    public InterfaceComponent getParent() {
        return parent;
    }
}
