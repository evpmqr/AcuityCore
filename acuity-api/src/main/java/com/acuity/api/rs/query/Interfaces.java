package com.acuity.api.rs.query;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.peers.Client;
import com.acuity.api.rs.peers.interfaces.Interface;
import com.acuity.api.rs.peers.interfaces.InterfaceComponent;
import com.acuity.rs.api.RSInterfaceComponent;

import java.util.*;
import java.util.stream.Collectors;

public class Interfaces {


    /**
     *
     * @return Null safe list of all the parent interfaces, containing
     * their sub components (Sub components may be null!).
     */
    public static List<Interface> getInterfaces() {
        final Client client = AcuityInstance.getClient();
        final RSInterfaceComponent[][] interfaces = client.getInterfaces();
        if(interfaces == null || interfaces.length == 0) {
            return Collections.EMPTY_LIST;
        }
        return Arrays.stream(interfaces)
                .filter(Objects::nonNull)
                .map(Interface::new)
                .collect(Collectors.toList());
    }

    /**
     *
     * @return Null safe list of all the child components of the interfaces.
     */
    public static List<InterfaceComponent> getInterfaceComponents() {
        final List<InterfaceComponent> components = new ArrayList<>();
        for (Interface parent : getInterfaces()) {
            components.addAll(parent.getComponents());
        }
        return components;
    }

    /**
     *
     * @param parentIndex
     * @param childIndex
     * @return InterfaceComponent, looked up by the finding the
     * interface by index in the Interfaces, then the InterfaceComponent
     * is inside the array returned by the parent.
     */
    public static Optional<InterfaceComponent> get(int parentIndex, int childIndex) {
        try {
            final List<Interface> parentInterfaces = getInterfaces();
            final Interface parent = parentInterfaces.get(parentIndex);
            return Optional.of(parent.getComponents().get(childIndex));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
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
    public static Optional<InterfaceComponent> get(int parentIndex, int childIndex, int grandchildIndex) {
        return Optional.ofNullable(get(parentIndex, childIndex)
                .map(InterfaceComponent::getSubComponents)
                .map(interfaceComponents -> interfaceComponents.get(grandchildIndex))
                .orElse(null));
    }

}
