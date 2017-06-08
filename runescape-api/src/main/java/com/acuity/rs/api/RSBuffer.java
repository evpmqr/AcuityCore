package com.acuity.rs.api;

//Generated

public interface RSBuffer extends RSNode {

    byte[] getPayload();

    int getOffset();

    void invokeEncryptXtea2(int var0);

    void invokePutByte(int var0);

    void invokeDecryptXtea(int var0);

    int invokeReadShortSmart();

    void invokePutVarInt(int var0);

    void invokeEncryptXtea(int var0, int var1, int var2);

    void invokePutLong(long var0);

    long invokeReadLong();

    java.lang.String invokeReadString();

    void invokeReadBytes(byte var0, int var1, int var2);

    int invokeReadInt();

    void invokePutShort(int var0);

    int invokeReadUnsignedByte();

    void invokePut24bitInt(int var0);

    void invokePutBytes(byte var0, int var1, int var2);

    int invokeReadVarInt();

    int invokeReadShort();

    int invokeReadUnsignedShort();

    int invokeRead24BitInt();

    void invokePutInt(int var0);
}
