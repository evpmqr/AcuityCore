package com.acuity.db.domain.vertex.impl.botclient;

import com.acuity.db.domain.vertex.Vertex;
import com.acuity.db.services.impl.AcuityAccountService;
import com.acuity.db.services.impl.BotClientConfigService;

import java.time.LocalDateTime;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class BotClient extends Vertex {

    private String ownerID; // TODO: 8/6/2017 Rename to key
    private String configID;
    private LocalDateTime connectionTime = LocalDateTime.now();

    public BotClient(String key, String ownerKey) {
        this._key = key;
        this.ownerID = AcuityAccountService.getInstance().getCollectionName() + "/" + ownerKey;
        this.configID = BotClientConfigService.getInstance().getCollectionName() + "/" + key;
    }

    public BotClient() {
    }

    public String getOwnerID() {
        return ownerID;
    }

    public LocalDateTime getConnectionTime() {
        return connectionTime;
    }

    @Override
    public String toString() {
        return "BotClient{" +
                "ownerID='" + ownerID + '\'' +
                ", connectionTime=" + connectionTime +
                '}';
    }
}
