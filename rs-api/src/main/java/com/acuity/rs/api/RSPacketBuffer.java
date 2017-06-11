package com.acuity.rs.api;

//Generated

public interface RSPacketBuffer extends RSBuffer {

    com.acuity.rs.api.RSISAACCipher getCipher();

    int invokeReadOpcode();

    void invokeSeed(int var0);

    void invokeWriteHeader(int var0);
}
