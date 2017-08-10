package com.acuity.security;

import javafx.util.Pair;

/**
 * Created by Zachary Herridge on 8/9/2017.
 */
public class AcuityEncryption {

    public static String decryptRSPassword(String password, String passwordIV, String acuityPassword, String rsAccountEIV, String rsAccountEKey) throws Exception{
        String key = getRSAccountEKey(acuityPassword, rsAccountEIV, rsAccountEKey);
        return Encryption.decrypt(Encryption.getSecrete(key, Encryption.SALT), Encryption.DECODER.decodeBuffer(passwordIV), Encryption.DECODER.decodeBuffer(password));
    }

    public static Pair<byte[], byte[]> encryptRSPassword(String password, String acuityPassword, String rsAccountEIV, String rsAccountEKey) throws Exception{
        String key = getRSAccountEKey(acuityPassword, rsAccountEIV, rsAccountEKey);
        return Encryption.encrypt(Encryption.getSecrete(key, Encryption.SALT), password);
    }

    private static String getRSAccountEKey(String acuityPassword, String accountEncryptionIV, String accountEncryptionKey) throws Exception{
        return Encryption.decrypt(Encryption.getSecrete(acuityPassword, Encryption.SALT), Encryption.DECODER.decodeBuffer(accountEncryptionIV) , Encryption.DECODER.decodeBuffer(accountEncryptionKey));
    }
}
