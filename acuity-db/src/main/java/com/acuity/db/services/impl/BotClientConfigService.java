package com.acuity.db.services.impl;

import com.acuity.db.AcuityDB;
import com.acuity.db.domain.vertex.impl.bot_clients.BotClientConfig;
import com.acuity.db.services.DBCollectionService;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.DocumentCreateEntity;
import com.arangodb.model.DocumentCreateOptions;
import com.arangodb.model.DocumentUpdateOptions;

import java.util.Optional;

/**
 * Created by Zach on 8/5/2017.
 */
public class BotClientConfigService extends DBCollectionService<BotClientConfig> {

    private static final BotClientConfigService INSTANCE = new BotClientConfigService();

    public static BotClientConfigService getInstance() {
        return INSTANCE;
    }

    public BotClientConfigService() {
        super(AcuityDB.DB_NAME, "BotClientConfig", BotClientConfig.class);
    }

    public Optional<BotClientConfig> registerConfig(String acuityID, String botClientKey){
        BotClientConfig botClientConfig = new BotClientConfig(acuityID, botClientKey);
        DocumentCreateEntity<BotClientConfig> entity = getCollection().insertDocument(botClientConfig, new DocumentCreateOptions().returnNew(true));
        return Optional.ofNullable(entity.getNew());
    }

    public void assignScript(String configKey, String scriptID){
        BaseDocument value = new BaseDocument();
        value.addAttribute("assignedScriptID", scriptID);
        getCollection().updateDocument(configKey, value, new DocumentUpdateOptions().keepNull(true));
    }

    public void assignProxy(String configKey, String proxyID){
        BaseDocument value = new BaseDocument();
        value.addAttribute("assignedProxyID", proxyID);
        getCollection().updateDocument(configKey, value, new DocumentUpdateOptions().keepNull(true));
    }

    public void removeConfig(String configKey) {
        getCollection().deleteDocument(configKey);
    }
}
