package com.acuity.control.server.sessions;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class Sessions {

    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    public static Session createSession(){
        String sessionKey = UUID.randomUUID().toString() + ":" + ThreadLocalRandom.current().nextInt(0, 1000000);
        Session session = new Session(sessionKey);
        sessionMap.put(sessionKey, session);
        return session;
    }


    public static void closeSession(Session session){
        closeSession(session.getSessionKey());
    }

    public static void closeSession(String key){
        sessionMap.remove(key);
    }

}
