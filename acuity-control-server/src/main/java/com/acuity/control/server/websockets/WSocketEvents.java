package com.acuity.control.server.websockets;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class WSocketEvents {

    public static class SocketOpened {

    }

    public static class SocketClosed {

        private final int code;
        private final String reason;
        private final boolean remote;

        public SocketClosed(int code, String reason, boolean remote) {
            this.code = code;
            this.reason = reason;
            this.remote = remote;
        }

        public int getCode() {
            return code;
        }

        public String getReason() {
            return reason;
        }

        public boolean isRemote() {
            return remote;
        }
    }

    public static class SocketError {
        private Throwable throwable;

        public SocketError(Throwable throwable) {
            this.throwable = throwable;
        }

        public Throwable getThrowable() {
            return throwable;
        }
    }

}
