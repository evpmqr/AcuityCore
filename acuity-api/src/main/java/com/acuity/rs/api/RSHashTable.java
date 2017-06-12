package com.acuity.rs.api;

import com.acuity.api.rs.wrappers.structures.HashTable;

//Generated

public interface RSHashTable {

    RSNode[] getBuckets();

    RSNode getHead();

    int getIndex();

    int getSize();

    RSNode getTail();

    HashTable getWrapper();
}
