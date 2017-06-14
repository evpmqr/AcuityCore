package com.acuity.api.rs.wrappers.structures;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.rs.api.RSNode;
import com.acuity.rs.api.RSNodeLinkedList;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;

import java.util.Iterator;
import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class NodeLinkedList implements java.lang.Iterable{

    private RSNodeLinkedList rsNodeLinkedList;

    @ClientInvoked
    public NodeLinkedList(RSNodeLinkedList peer) {
        this.rsNodeLinkedList = Preconditions.checkNotNull(peer);
    }

    public Optional<Node> getNode(){
        return Optional.ofNullable(rsNodeLinkedList.getNode()).map(RSNode::getWrapper);
    }

    @Override
    public Iterator iterator() {
        return rsNodeLinkedList.iterator();
    }

    @NotNull
    public RSNodeLinkedList getRsNodeLinkedList() {
        return rsNodeLinkedList;
    }
}
