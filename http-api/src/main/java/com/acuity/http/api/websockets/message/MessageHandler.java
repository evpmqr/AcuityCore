package com.acuity.http.api.websockets.message;

import com.acuity.http.api.websockets.message.Message;

/**
 * Created by Zachary Herridge on 6/13/2017.
 */
public interface MessageHandler {

    void handle(Message message);

}
