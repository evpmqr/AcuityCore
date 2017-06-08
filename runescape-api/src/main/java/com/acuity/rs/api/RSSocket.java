package com.acuity.rs.api;

//Generated

public interface RSSocket extends java.lang.Runnable {

    java.net.Socket getSocket();

    int getOutbufLen();

    java.io.InputStream getInputStream();

    java.io.OutputStream getOutputStream();

    byte[] getOutbuffer();

    int invokeAvailable();

    void invokeFinalize();

    void invokeRead(byte var0, int var1, int var2);

    int invokeReadByte();

    void invokeQueueForWrite(byte var0, int var1, int var2);
}
