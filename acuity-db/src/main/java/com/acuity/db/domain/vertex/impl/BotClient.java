package com.acuity.db.domain.vertex.impl;

import com.acuity.db.domain.vertex.Vertex;

import java.time.LocalDateTime;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class BotClient extends Vertex {

    private String ownerID;
    private LocalDateTime connectionTime = LocalDateTime.now();

    public BotClient(String ownerID) {
        this.ownerID = ownerID;
    }

    public BotClient() {
    }
}
