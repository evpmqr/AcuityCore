package com.acuity.db.services;

import com.acuity.bcrypt.BCrypt;
import com.acuity.db.AcuityDB;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.arangodb.ArangoCollection;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.DocumentCreateEntity;
import com.arangodb.model.DocumentCreateOptions;

import java.util.Collections;
import java.util.Optional;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class AcuityAccountService {

    private static final AcuityAccountService INSTANCE = new AcuityAccountService();

    public static AcuityAccountService getInstance() {
        return INSTANCE;
    }

    public ArangoCollection getCollection(){
        return AcuityDB.getDB().db("_system").collection("AcuityUsers");
    }

    public Optional<AcuityAccount> registerAccount(String email, String username, String password) throws ArangoDBException{
        AcuityAccount acuityAccount = new AcuityAccount(email, username, BCrypt.hashpw(password, BCrypt.gensalt()));
        DocumentCreateEntity<AcuityAccount> acuityAccountDocumentCreateEntity = getCollection().insertDocument(acuityAccount, new DocumentCreateOptions().returnNew(true));
        return Optional.ofNullable(acuityAccountDocumentCreateEntity.getNew());
    }

    public boolean checkLogin(String email, String password){
        return getAccountByEmail(email).map(acuityAccount -> BCrypt.checkpw(password, acuityAccount.getPasswordHash())).orElse(false);
    }

    public Optional<AcuityAccount> getAccountByEmail(String email){
        String query = "FOR user IN AcuityUsers " +
                "FILTER user.email == @email " +
                "LIMIT 1 " +
                "RETURN user";
        ArangoCursor<AcuityAccount> system = AcuityDB.getDB().db("_system").query(query, Collections.singletonMap("email", email), null, AcuityAccount.class);
        return system.asListRemaining().stream().findFirst();
    }

}
