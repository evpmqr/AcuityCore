package com.acuity.api.rs.peers;

import com.acuity.rs.api.RSNode;
import com.acuity.rs.api.RSSceneTile;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class Node {

    private static final Logger logger = LoggerFactory.getLogger(Node.class);

    private RSNode rsNode;

    public Node(@NotNull RSNode peer) {
        this.rsNode = Preconditions.checkNotNull(peer);
    }


    public RSNode getRsNode() {
        return rsNode;
    }
}
