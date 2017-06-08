package com.acuity.rs.api;

//Generated

public interface RSHashTable {

    com.acuity.rs.api.RSNode[] getBuckets();

    com.acuity.rs.api.RSNode getHead();

    int getSize();

    int getIndex();

    com.acuity.rs.api.RSNode getTail();
}
