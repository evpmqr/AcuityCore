package com.acuity.api.rs.wrappers.structures;


import com.acuity.rs.api.RSHashTable;
import com.acuity.rs.api.RSNodeTable;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zach on 6/11/2017.
 */
public class HashTable {

    private static final Logger logger = LoggerFactory.getLogger(HashTable.class);

    private RSHashTable rsHashTable;

    public HashTable(RSHashTable peer) {
        this.rsHashTable = Preconditions.checkNotNull(peer);
    }

    public RSHashTable getRsHashTable() {
        return rsHashTable;
    }
}
