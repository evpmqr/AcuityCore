package com.acuity.control.server.websockets;

import com.google.common.eventbus.SubscriberExceptionContext;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class WSocketEvent {

    private WSocketEvent() {
    }

    public static class Opened {

    }

    public static class LoginComplete {

    }

    public static class Closed {

        private final int code;
        private final String reason;
        private final boolean remote;

        public Closed(int code, String reason, boolean remote) {
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

    public static class Error {
        private Throwable throwable;
        private SubscriberExceptionContext subscriberExceptionContext;

        public Error(Throwable throwable, SubscriberExceptionContext subscriberExceptionContext) {
            this.throwable = throwable;
            this.subscriberExceptionContext = subscriberExceptionContext;
        }

        public Throwable getThrowable() {
            return throwable;
        }

        public SubscriberExceptionContext getSubscriberExceptionContext() {
            return subscriberExceptionContext;
        }
    }

}
