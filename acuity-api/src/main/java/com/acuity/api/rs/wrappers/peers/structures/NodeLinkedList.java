package com.acuity.api.rs.wrappers.peers.structures;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.rs.api.RSNode;
import com.acuity.rs.api.RSNodeLinkedList;
import com.google.common.base.Preconditions;
import com.google.common.collect.Streams;
import com.sun.istack.internal.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class NodeLinkedList<T extends Node> {

    private RSNodeLinkedList rsNodeLinkedList;

    @ClientInvoked
    public NodeLinkedList(RSNodeLinkedList peer) {
        this.rsNodeLinkedList = Preconditions.checkNotNull(peer);
    }

    @SuppressWarnings("unchecked")
    public Optional<T> getNode(){
        return Optional.ofNullable(rsNodeLinkedList.getNode()).map(rsNode -> (T) rsNode.getWrapper());
    }

    @SuppressWarnings("unchecked")
    public Iterator<? extends RSNode> iterator() {
        return rsNodeLinkedList.iterator();
    }

    @NotNull
    public RSNodeLinkedList getRsNodeLinkedList() {
        return rsNodeLinkedList;
    }
}
