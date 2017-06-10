package com.acuity.api.rs.peers;

import com.acuity.rs.api.RSNode;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class Node<T extends RSNode> extends Wrapper<T>{

    private static final Logger logger = LoggerFactory.getLogger(Node.class);

    public Node(@NotNull T peer) {
        super(peer);
    }
}
