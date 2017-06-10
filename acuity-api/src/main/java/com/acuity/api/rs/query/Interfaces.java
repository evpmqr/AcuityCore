package com.acuity.api.rs.query;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.peers.Client;
import com.acuity.api.rs.peers.interfaces.Interface;
import com.acuity.api.rs.peers.interfaces.InterfaceComponent;
import com.acuity.api.rs.peers.interfaces.InterfaceComponentChild;
import com.acuity.rs.api.RSInterfaceComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Interfaces {


    /**
     *
     * @return Null safe list of all the parent interfaces, containing
     * their sub components (Sub components may be null!).
     */
    public static Interface[] getInterfaces() {
        final Client client = AcuityInstance.getClient();
        final RSInterfaceComponent[][] interfaces = client.getInterfaces();
        if(interfaces == null || interfaces.length == 0) {
            return new Interface[0];
        }
        return Arrays.stream(interfaces)
                .filter(Objects::nonNull)
                .map(Interface::new).toArray(Interface[]::new);
    }

    /**
     *
     * @return Null safe list of all the child components of the interfaces.
     */
    public static InterfaceComponent[] getInterfaceComponents() {
        final List<InterfaceComponent> components = new ArrayList<>();
        for (Interface parent : getInterfaces()) {
            components.addAll(Arrays.asList(parent.getComponents()));
        }
        return components.toArray(new InterfaceComponent[components.size()]);
    }

    /**
     *
     * @param parentIndex
     * @param childIndex
     * @return InterfaceComponent, looked up by the finding the
     * interface by index in the Interfaces, then the InterfaceComponent
     * is inside the array returned by the parent.
     */
    public static InterfaceComponent lookup(int parentIndex, int childIndex) {
        try {
            final Interface[] parentInterfaces = getInterfaces();
            final Interface parent = parentInterfaces[parentIndex];
            return parent.getComponents()[childIndex];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }


    /**
     *
     * @param parentIndex
     * @param childIndex
     * @param grandchildIndex
     * @return InterfaceComponent, looked up by the finding the
     * interface by index in the Interfaces, then the InterfaceComponent
     * is inside the array returned by the parent.
     *
     * This method take it one step further and finds by the index
     * of the children of the interface component.
     *
     * Parent -> Child -> GrandChild
     */
    public static InterfaceComponentChild lookup(int parentIndex, int childIndex, int grandchildIndex) {
        final InterfaceComponent component = lookup(parentIndex, childIndex);
        if(component == null) {
            return null;
        }
        try {
            return component.getSubComponents()[grandchildIndex];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

}
