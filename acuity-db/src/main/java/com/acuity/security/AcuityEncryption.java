package com.acuity.security;

import com.acuity.db.domain.common.EncryptedString;

/**
 * Created by Zachary Herridge on 8/9/2017.
 */
public class AcuityEncryption {

    public static String decryptString(EncryptedString encryptedString, String acuityPassword, EncryptedString encryptionKey) throws Exception{
        String key = getRSAccountEKey(acuityPassword, encryptionKey.getIv(), encryptionKey.getEncryptedString());
        return Encryption.decrypt(Encryption.getSecrete(key, Encryption.SALT), Encryption.DECODER.decodeBuffer(encryptedString.getIv()), Encryption.DECODER.decodeBuffer(encryptedString.getEncryptedString()));
    }

    public static EncryptedString encryptString(String password, String acuityPassword, EncryptedString encryptionKey) throws Exception{
        String key = getRSAccountEKey(acuityPassword, encryptionKey.getIv(), encryptionKey.getEncryptedString());
        return Encryption.encrypt(Encryption.getSecrete(key, Encryption.SALT), password);
    }

    private static String getRSAccountEKey(String acuityPassword, String encryptionIV, String encryptionKey) throws Exception{
        return Encryption.decrypt(Encryption.getSecrete(acuityPassword, Encryption.SALT), Encryption.DECODER.decodeBuffer(encryptionIV) , Encryption.DECODER.decodeBuffer(encryptionKey));
    }
}
