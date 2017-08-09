package com.acuity.security;

import javafx.util.Pair;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

/**
 * Created by Zachary Herridge on 8/9/2017.
 */
public class Encryption {

    public static final byte[] SALT = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
    public static final BASE64Decoder DECODER = new BASE64Decoder();
    public static final BASE64Encoder ENCODER = new BASE64Encoder();

    private static final String VALID = "ABCDEFQHIJKLMNOPQRSTUVWXYZabcdefqhijklmnop!@#$%^&*()_+;:'[]\\|1234567890";
    private static SecureRandom instanceStrong;

    public static synchronized String generateEncryptionKey() throws NoSuchAlgorithmException {
        if (instanceStrong == null) instanceStrong = SecureRandom.getInstanceStrong();
        String result = "";
        for (int i = 0; i < 128; i++) {
            result += VALID.charAt(instanceStrong.nextInt(VALID.length() - 1));
        }
        return result;
    }

    public static byte[] genereateSalt() throws Exception {
        byte[] salt = new byte[8];
        SecureRandom.getInstanceStrong().nextBytes(salt);
        return salt;
    }

    public static SecretKeySpec getSecrete(String password, byte[] salt) throws Exception{
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), "AES");
    }

    public static String decrypt(SecretKey secret, byte[] iv, byte[] encryptedText) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
        return new String(cipher.doFinal(encryptedText), "UTF-8");

    }

    public static Pair<byte[], byte[]> encrypt(SecretKey key, String plainText) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        AlgorithmParameters params = cipher.getParameters();
        byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
        byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
        return new Pair<>(iv, encrypted);
    }
}
