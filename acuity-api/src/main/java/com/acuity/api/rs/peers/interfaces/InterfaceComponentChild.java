package com.acuity.api.rs.peers.interfaces;

import com.acuity.rs.api.RSInterfaceComponent;

/**
 * Created by maddev on 6/10/17.
 */
public class InterfaceComponentChild {

    private InterfaceComponent parent;
    private RSInterfaceComponent child;

    InterfaceComponentChild(InterfaceComponent parent, RSInterfaceComponent child) {
        this.parent = parent;
        this.child = child;
    }


    public InterfaceComponent getParent() {
        return parent;
    }
}
