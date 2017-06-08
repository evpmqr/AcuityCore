package com.acuity.rs.api;

//Generated

public interface RSSocket extends java.lang.Runnable {

    java.io.InputStream getInputStream();

    byte[] getOutbuffer();

    int getOutbufLen();

    java.io.OutputStream getOutputStream();

    java.net.Socket getSocket();

    int invokeAvailable();

    void invokeFinalize();

    void invokeQueueForWrite(byte var0, int var1, int var2);

    void invokeRead(byte var0, int var1, int var2);

    int invokeReadByte();
}
