package com.acuity.rs.api;

//Generated

public interface RSNodeTable {

    com.acuity.rs.api.RSHashTable getTable();

    com.acuity.rs.api.RSNodeQueue getQueue();

    void invokePut(RSCacheableNode var0, long var1);

    void invokeRemove(long var0);

    void invokeReset();

    RSCacheableNode invokeGet(long var0);
}
