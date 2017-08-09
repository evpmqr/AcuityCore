package com.acuity.db.services.impl;

import com.acuity.db.AcuityDB;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.services.DBCollectionService;
import com.acuity.security.Encryption;
import com.acuity.security.bcrypt.BCrypt;
import com.arangodb.ArangoCursor;
import com.arangodb.model.DocumentCreateOptions;
import javafx.util.Pair;

import java.util.Collections;
import java.util.Optional;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class AcuityAccountService extends DBCollectionService<AcuityAccount> {

    private static final AcuityAccountService INSTANCE = new AcuityAccountService();

    public static AcuityAccountService getInstance() {
        return INSTANCE;
    }

    public AcuityAccountService() {
        super(AcuityDB.DB_NAME, "AcuityAccount", AcuityAccount.class);
    }

    public Optional<AcuityAccount> registerAccount(String email, String username, String password) throws Exception {
        Pair<byte[], byte[]> encrypt = Encryption.encrypt(Encryption.getSecrete(password, Encryption.SALT), Encryption.generateEncryptionKey());
        AcuityAccount insert = new AcuityAccount(email, username, BCrypt.hashpw(password, BCrypt.gensalt()), encrypt.getKey(), encrypt.getValue());
        AcuityAccount result = getCollection().insertDocument(insert, new DocumentCreateOptions().returnNew(true)).getNew();
        return Optional.ofNullable(result);
    }

    public Optional<AcuityAccount> checkLogin(String email, String password){
        return getAccountByEmail(email).map(acuityAccount -> BCrypt.checkpw(password, acuityAccount.getPasswordHash()) ? acuityAccount : null);
    }

    public Optional<AcuityAccount> getAccountByEmail(String email){
        String query = "FOR user IN AcuityAccount " +
                "FILTER user.email == @email " +
                "LIMIT 1 " +
                "RETURN user";
        ArangoCursor<AcuityAccount> system = getDB().query(query, Collections.singletonMap("email", email), null, AcuityAccount.class);
        return system.asListRemaining().stream().findFirst();
    }

}
