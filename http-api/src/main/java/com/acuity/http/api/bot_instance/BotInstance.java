package com.acuity.http.api.bot_instance;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class BotInstance {

    private String _id;
    private String acuityAccountID;

    private HashMap<String, Object> botInstanceConfig = new HashMap<>();

    private Date connectionTimestamp = new Date();


}
