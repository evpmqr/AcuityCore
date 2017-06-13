package com.acuity.http.api.websockets.message;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Zachary Herridge on 6/13/2017.
 */
public class MessageFuture implements Future<Message> {

    private Message response = null;

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return true;
    }

    @Override
    public boolean isCancelled() {
        return true;
    }

    @Override
    public boolean isDone() {
        return response != null;
    }

    @Override
    public Message get() {
        return response;
    }

    public void setResponse(Message response) {
        this.response = response;
    }

    @Override
    public Message get(long timeout, TimeUnit unit)  {
        return null;
    }
}
