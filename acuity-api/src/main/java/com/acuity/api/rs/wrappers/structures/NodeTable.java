package com.acuity.api.rs.wrappers.structures;


import com.acuity.rs.api.RSNodeTable;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zach on 6/11/2017.
 */
public class NodeTable<T extends Node> {

    private static final Logger logger = LoggerFactory.getLogger(NodeTable.class);

    private RSNodeTable rsHashTable;

    public NodeTable(RSNodeTable peer) {
        this.rsHashTable = Preconditions.checkNotNull(peer);
    }

    public RSNodeTable getRsHashTable() {
        return rsHashTable;
    }
}
