package com.acuity.api.input;

import com.acuity.api.Events;
import com.acuity.api.rs.events.impl.ActionEvent;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zachary Herridge on 7/27/2017.
 */
public class SmartActions {

    public static SmartActions INSTANCE = new SmartActions();
    private static final Logger logger = LoggerFactory.getLogger(SmartActions.class);

    private Map<String, Multiset<String>> history = new HashMap<>();
    private String lastKey = null;

    @Subscribe
    public void actionProcessed(ActionEvent actionEvent){
        String key = actionEvent.getAction() + "->" + actionEvent.getTarget();
        if (lastKey != null){
            history.computeIfAbsent(lastKey, s -> HashMultiset.create()).add(key);
        }
        logger.debug("History of '{}' = {}", key, history.get(key));
        lastKey = key;
    }

    public void clear(){
        history.clear();
    }

    public void start(){
        Events.getRsEventBus().register(this);
        logger.info("SmartActions started.");
    }
}
