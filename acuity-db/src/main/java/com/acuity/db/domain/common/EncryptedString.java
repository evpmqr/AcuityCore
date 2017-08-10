package com.acuity.db.domain.common;

import com.acuity.security.Encryption;

/**
 * Created by Zachary Herridge on 8/10/2017.
 */
public class EncryptedString {

    private String salt;
    private String encryptedString;
    private String iv;

    public EncryptedString(String salt, String encryptedString, String iv) {
        this.salt = salt;
        this.encryptedString = encryptedString;
        this.iv = iv;
    }

    public EncryptedString(byte[] salt, byte[] encryptedString, byte[] iv) {
        this.salt = Encryption.ENCODER.encode(salt);
        this.encryptedString = Encryption.ENCODER.encode(encryptedString);
        this.iv = Encryption.ENCODER.encode(iv);
    }

    public EncryptedString() {
    }

    public String getSalt() {
        return salt;
    }

    public String getEncryptedString() {
        return encryptedString;
    }

    public String getIv() {
        return iv;
    }
}
