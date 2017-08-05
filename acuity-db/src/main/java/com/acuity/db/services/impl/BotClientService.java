package com.acuity.db.services.impl;

import com.acuity.db.AcuityDB;
import com.acuity.db.domain.vertex.impl.BotClient;
import com.acuity.db.services.DBCollectionService;
import com.arangodb.ArangoCursor;
import com.arangodb.entity.DocumentCreateEntity;
import com.arangodb.model.DocumentCreateOptions;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class BotClientService extends DBCollectionService{

    private static final BotClientService INSTANCE = new BotClientService();

    public static BotClientService getInstance() {
        return INSTANCE;
    }

    public BotClientService() {
        super(AcuityDB.DB_NAME, "BotClient");
    }

    public Optional<BotClient> registerClient(String ownerID){
        DocumentCreateEntity<BotClient> createEntity = getCollection().insertDocument(new BotClient(ownerID), new DocumentCreateOptions().returnNew(true));
        return Optional.ofNullable(createEntity.getNew());
    }

    public void removeClient(String botClientKey){
        getCollection().deleteDocument(botClientKey);
    }

    public List<BotClient> getByOwnerKey(String key) {
        String query = "FOR bot IN BotClient " +
                "FILTER bot.ownerID == @key " +
                "RETURN bot";
        ArangoCursor<BotClient> system = getDB().query(query, Collections.singletonMap("key", key), null, BotClient.class);
        return system.asListRemaining();
    }

    public Optional<BotClient> getByKey(String key) {
        return Optional.ofNullable(getCollection().getDocument(key, BotClient.class));
    }
}
