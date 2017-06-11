package com.acuity.rs.api;

//Generated

public interface RSNode {

    long getKey();

    com.acuity.rs.api.RSNode getNext();

    com.acuity.rs.api.RSNode getPrevious();

    boolean invokeLinked();

    void invokeUnlink();
}
