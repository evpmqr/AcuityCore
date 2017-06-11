package com.acuity.rs.api;

//Generated

public interface RSFileSystem extends RSNode {

    byte[] getBuffer();

    com.acuity.rs.api.RSIndexFile getIndex();

    com.acuity.rs.api.RSIndexData getTable();
}
