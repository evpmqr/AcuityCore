package com.acuity.db.services.impl;

import com.acuity.db.AcuityDB;
import com.acuity.db.domain.vertex.impl.RSAccount;
import com.acuity.db.services.DBCollectionService;
import com.acuity.security.AcuityEncryption;
import com.arangodb.entity.DocumentDeleteEntity;
import com.arangodb.entity.MultiDocumentEntity;
import javafx.util.Pair;

import java.util.Collections;
import java.util.Set;

/**
 * Created by Zach on 8/6/2017.
 */
public class RSAccountService extends DBCollectionService<RSAccount> {

    private static final RSAccountService INSTANCE = new RSAccountService();

    public static RSAccountService getInstance() {
        return INSTANCE;
    }

    public RSAccountService() {
        super(AcuityDB.DB_NAME, "RSAccount", RSAccount.class);
    }

    public MultiDocumentEntity<DocumentDeleteEntity<Void>> deleteAccounts(Set<RSAccount> accounts) {
        MultiDocumentEntity<DocumentDeleteEntity<Void>> result = getCollection().deleteDocuments(accounts);
        accounts.forEach(account -> RSAccountAssignmentService.getInstance().removeByFromID(account.getID()));
        return result;
    }

    public void addRSAccount(String ownerID, String email, String ign, String password, String acuityPassword, String accountEncryptionIV, String accountEncryptionKey) throws Exception {
        Pair<byte[], byte[]> result = AcuityEncryption.encryptRSPassword(password, acuityPassword, accountEncryptionIV, accountEncryptionKey);
        RSAccount rsAccount = new RSAccount(ownerID, email, ign, result.getKey(), result.getValue());
        insert(rsAccount);
    }

    public void deleteUnusableAccounts(String ownerID) {
        String query =
                "FOR account in RSAccount\n" +
                "filter account.ownerID == @ownerID && (account.banned == true || account.locked == true)\n" +
                "REMOVE account IN RSAccount\n";

        getDB().query(query, Collections.singletonMap("ownerID", ownerID), null, RSAccount.class);
    }
}
