package com.acuity.rs.api;

import com.acuity.api.rs.wrappers.structures.NodeTable;

//Generated

public interface RSNodeTable {

    RSNode[] getBuckets();

    RSNode getHead();

    int getIndex();

    int getSize();

    RSNode getTail();

    NodeTable getWrapper();
}
