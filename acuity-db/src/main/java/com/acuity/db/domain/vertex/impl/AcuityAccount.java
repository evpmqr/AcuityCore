package com.acuity.db.domain.vertex.impl;

import com.acuity.db.domain.common.EncryptedString;
import com.acuity.db.domain.vertex.Vertex;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class AcuityAccount extends Vertex {

    private String email;
    private String displayName;
    private String passwordHash;

    private EncryptedString passwordEncryptionKey;

    private int rank;

    public AcuityAccount() {
    }

    public AcuityAccount(String email, String displayName, String passwordHash, EncryptedString passwordEncryptionKey) {
        this.email = email;
        this.displayName = displayName;
        this.passwordHash = passwordHash;
        this.passwordEncryptionKey = passwordEncryptionKey;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public EncryptedString getPasswordEncryptionKey() {
        return passwordEncryptionKey;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public int getRank() {
        return rank;
    }



    @Override
    public String toString() {
        return "AcuityAccount{" +
                "_key='" + _key + '\'' +
                ", email='" + email + '\'' +
                ", displayName='" + displayName + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }

    public interface Rank {
        int USER = 0;
        int ADMIN = 1001;
    }
}
