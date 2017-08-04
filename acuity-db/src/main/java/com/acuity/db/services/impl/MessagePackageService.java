package com.acuity.db.services.impl;

import com.acuity.db.AcuityDB;
import com.acuity.db.domain.vertex.impl.MessagePackage;
import com.acuity.db.services.DBCollectionService;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class MessagePackageService extends DBCollectionService {

    private static final MessagePackageService INSTANCE = new MessagePackageService();

    public static MessagePackageService getInstance() {
        return INSTANCE;
    }

    public MessagePackageService() {
        super(AcuityDB.DB_NAME, "MessagePackage");
    }

    public void insert(MessagePackage messagePackage){
        getCollection().insertDocument(messagePackage);
    }

    public void delete(MessagePackage message){
        delete(message.getKey());
    }

    public void delete(String messageKey){
        getCollection().deleteDocument(messageKey);
    }

}
