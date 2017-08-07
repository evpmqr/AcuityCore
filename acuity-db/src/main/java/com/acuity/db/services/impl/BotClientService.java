package com.acuity.db.services.impl;

import com.acuity.db.AcuityDB;
import com.acuity.db.domain.vertex.impl.botclient.BotClient;
import com.acuity.db.services.DBCollectionService;
import com.arangodb.entity.DocumentCreateEntity;
import com.arangodb.model.DocumentCreateOptions;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class BotClientService extends DBCollectionService<BotClient> {

    private static final BotClientService INSTANCE = new BotClientService();

    public static BotClientService getInstance() {
        return INSTANCE;
    }

    public BotClientService() {
        super(AcuityDB.DB_NAME, "BotClient", BotClient.class);
    }

    public Optional<BotClient> registerClient(String key, String ownerID){
        DocumentCreateEntity<BotClient> createEntity = getCollection().insertDocument(new BotClient(key, ownerID), new DocumentCreateOptions().returnNew(true));
        return Optional.ofNullable(createEntity.getNew());
    }

    public void removeClient(String botClientKey){
        getCollection().deleteDocument(botClientKey);
    }
}
