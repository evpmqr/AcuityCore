package com.acuity.api.rs.wrappers.structures;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.rs.api.RSNode;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class Node {

    private static final Logger logger = LoggerFactory.getLogger(Node.class);

    private RSNode rsNode;

    @ClientInvoked
    public Node(@NotNull RSNode peer) {
        this.rsNode = Preconditions.checkNotNull(peer);
    }

    public Optional<? extends Node> getNext(){
        return Optional.ofNullable(rsNode.getNext()).map(RSNode::getWrapper);
    }

    public Optional<? extends Node> getPrevious(){
        return Optional.ofNullable(rsNode.getPrevious()).map(RSNode::getWrapper);
    }

    public long getKey(){
        return rsNode.getKey();
    }

    @NotNull
    public RSNode getRsNode() {
        return rsNode;
    }
}
