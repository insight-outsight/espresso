package org.ootb.espresso.facilities;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AES128Utils {

    private static final String KEY_ALGORITHM = "AES";
    private static final String algorithmStr = "AES/CBC/PKCS7Padding";
    private static final String defautCharSet = "UTF-8";

    // 默认对称解密算法初始向量 iv
    private static final byte[] iv = { 0x40, 0x41, 0x30, 0x32, 0x30, 0x33, 0x30, 0x34, 0x30, 0x35, 0x30, 0x36, 0x30,
            0x37, 0x30, 0x38 };

    private static final String ivs = "x8wW(*re4WE6hjfg";
    private static final String key = "BNert-=389tyT2";

    static {
        // 这个地方调用BouncyCastleProvider, 让java支持PKCS7Padding
        Security.addProvider(new BouncyCastleProvider());
    }

    private static SecretKey getKeyWithZeroPaddingIfNecessary(byte[] keyBytes, int keyBaseLength)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {

        // 如果密钥不足，那么就补足
        int groups = keyBytes.length / keyBaseLength + (keyBytes.length % keyBaseLength != 0 ? 1 : 0);
        byte[] temp = new byte[groups * keyBaseLength];
        Arrays.fill(temp, (byte) 0x0);
        System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
        keyBytes = temp;
//		SecretKeySpec key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);

        KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
//		kgen.init(128, new SecureRandom(keyBytes));
//		fixed Linux操作系统下每次AES加密结果都不一致的原因
//		SecureRandom 实现随操作系统本身的內部状态，除非调用方在调用 getInstance 方法之后又调用了 setSeed 方法；
//		该实现在 windows 上每次生成的 key 都相同，但是在 solaris 或部分 linux 系统上则不同。
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(keyBytes);
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();

        return secretKey;

    }

    public static String encryptBase64String(String content) throws Exception {
        return encryptBase64String(content, key, ivs);
    }

    public static String encryptBase64String(String content, String keyString, String ivs) throws Exception {
        byte[] encryptedText = encrypt(content.getBytes(defautCharSet), keyString.getBytes(), ivs.getBytes());
        return Base64.encodeBase64String(encryptedText);
    }

    public static String decryptBase64String(String encryptedContent) throws Exception {
        return decryptBase64String(encryptedContent, key, ivs);
    }

    public static String decryptBase64String(String encryptedContent, String keyString, String ivs) throws Exception {
        byte[] contentBytes = Base64.decodeBase64(encryptedContent);
        byte[] decryptedText = decrypt(contentBytes, keyString.getBytes(), ivs.getBytes());
        return new String(decryptedText, defautCharSet);
    }

    public static byte[] encrypt(byte[] content, byte[] keyString) throws Exception {
        byte[] encryptedText = encrypt(content, keyString, iv);
        return encryptedText;
    }

    public static byte[] decrypt(byte[] encryptedData, byte[] keyString) throws Exception {
        byte[] encryptedText = decrypt(encryptedData, keyString, iv);
        return encryptedText;
    }

    public static byte[] encrypt(byte[] content, byte[] keyString, byte[] ivs) throws Exception {
        byte[] encryptedText = null;
        try {
            SecretKey key = getKeyWithZeroPaddingIfNecessary(keyString, 8);
//			System.out.println("encrypt IV：" + new String(ivs));

            // cipher is not thread safe
            Cipher cipher = Cipher.getInstance(algorithmStr);
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(ivs));
            encryptedText = cipher.doFinal(content);
        } catch (Exception e) {
            throw e;
        }
//		System.out.println("keyLengt:"+keyString.length+",restulLeng:"+encryptedText.length);
        return encryptedText;
    }

    public static byte[] decrypt(byte[] content, byte[] keyString, byte[] ivs) throws Exception {
        byte[] encryptedText = null;
        try {
            SecretKey key = getKeyWithZeroPaddingIfNecessary(keyString, 8);
//			System.out.println("decrypt IV：" + new String(ivs));

            // cipher is not thread safe
            Cipher cipher = Cipher.getInstance(algorithmStr);
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivs));
            encryptedText = cipher.doFinal(content);
        } catch (Exception e) {
            throw e;
        }
        return encryptedText;
    }

    public static void main(String[] args) throws Exception {
        String content = "18234567";
        String keyString = "VEert-=3895ET2";

        // 加密字符串
        System.out.println("加密前的：" + content);
        System.out.println("加密密钥：" + keyString);
        // 加密方法
        String encryptedContent = AES128Utils.encryptBase64String(content, keyString, ivs);
        System.out.println("加密后的内容：" + encryptedContent);
        // 解密方法
        String decryptedContent = AES128Utils.decryptBase64String(encryptedContent, keyString, ivs);
        System.out.println("解密后的内容：" + decryptedContent);
        System.out.println(decryptedContent.equals(content));
        System.out.println("===============");
        System.out.println(encryptBase64String(77 + ""));
        System.out.println(decryptBase64String(encryptBase64String(77 + "")));
        System.out.println(decryptBase64String("sBrVGPsKwal7XjP3VX39/A=="));

    }

}