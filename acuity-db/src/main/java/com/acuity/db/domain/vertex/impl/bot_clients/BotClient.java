package com.acuity.db.domain.vertex.impl.bot_clients;

import com.acuity.db.domain.vertex.Vertex;
import com.acuity.db.domain.vertex.impl.RSAccount;
import com.acuity.db.services.impl.AcuityAccountService;

import java.time.LocalDateTime;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class BotClient extends Vertex {

    private String ownerID; // TODO: 8/6/2017 Rename to key
    private LocalDateTime connectionTime = LocalDateTime.now();

    private RSAccount assignedAccount;
    private BotClientConfig clientConfig;

    public BotClient(String key, String ownerKey) {
        this._key = key;
        this.ownerID = AcuityAccountService.getInstance().getCollectionName() + "/" + ownerKey;
    }

    public BotClient() {
    }

    public String getOwnerID() {
        return ownerID;
    }

    public LocalDateTime getConnectionTime() {
        return connectionTime;
    }

    public RSAccount getAssignedAccount() {
        return assignedAccount;
    }

    public BotClientConfig getClientConfig() {
        return clientConfig;
    }

    @Override
    public String toString() {
        return "BotClient{" +
                "ownerID='" + ownerID + '\'' +
                ", connectionTime=" + connectionTime +
                '}';
    }
}
