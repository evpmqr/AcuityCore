package com.acuity.db.domain.vertex.impl;

import com.acuity.db.domain.vertex.Vertex;
import com.acuity.security.Encryption;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class AcuityAccount extends Vertex {

    private String email;
    private String displayName;
    private String passwordHash;
    private String accountEncryptionIV;
    private String accountEncryptionKey;
    private int rank;

    public AcuityAccount() {
    }

    public AcuityAccount(String email, String displayName, String passwordHash, byte[] accountEncryptionIV, byte[] accountEncryptionKey) {
        this.email = email;
        this.displayName = displayName;
        this.passwordHash = passwordHash;
        this.accountEncryptionIV = Encryption.ENCODER.encode(accountEncryptionIV);
        this.accountEncryptionKey = Encryption.ENCODER.encode(accountEncryptionKey);
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAccountEncryptionIV() {
        return accountEncryptionIV;
    }

    public String getAccountEncryptionKey() {
        return accountEncryptionKey;
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
