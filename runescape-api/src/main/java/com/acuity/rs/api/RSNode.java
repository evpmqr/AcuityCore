package com.acuity.rs.api;

//Generated

public interface RSNode {

    long getKey();

    com.acuity.rs.api.RSNode getPrevious();

    com.acuity.rs.api.RSNode getNext();

    void invokeUnlink();

    boolean invokeLinked();
}
