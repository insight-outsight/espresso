package org.ootb.espresso.facilities;


import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.base.Joiner;

public class InputFieldValidatorUtils {

    public static void validateAppKey(String str) {
        validateStringLength(str, "appKey", 16, 16);
    }

    public static void validateAppSecret(String str) {
        validateStringLength(str, "AppSecret", 32, 32);
    }

    public static void validateUid(String str) {
        validateStringLength(str, "uid", 1, 11);
        Long.parseLong(str);
    }

    public static void validateNonce(String str) {
        validateStringLength(str, "nonce", 8, 16);
    }
    
    public static void validateTsn(String str) {
        validateStringLength(str, "tsn", 16, 32);
    }

    public static void validateSig(String str) {
        validateStringLength(str, "sig", 40, 40);
    }

    public static void validateStringLength(String str, String filedName,
            int minLen, int maxLen) {
//        Objects.requireNonNull(str, "field '" + filedName
//                + "' must be not null");

        if (StringUtils.isBlank(str)) {
            throw new IllegalArgumentException("field '" + filedName
                    + "' is blank");
        }

        if (str.length() < minLen) {
            throw new IllegalArgumentException("field '" + filedName
                    + "' length < " + minLen);
        }

        if (str.length() > maxLen) {
            throw new IllegalArgumentException("field '" + filedName
                    + "' , [" + str +"],length > " + maxLen);
        }
    }

    public static void validateCurrentSecond(String currentSecond) {
        if (NumberUtils.toLong(currentSecond, 0) * 1000 < System
                .currentTimeMillis() - 30 * 1000) {
            throw new IllegalArgumentException("field 'currentSecond' ["
                    + currentSecond + "] expired");
        }
    }

    public static boolean verifySignature(String appKey, String appSecret,
            String uid, String nonce, /*String tsn,*/ String currentSeconds, String sig) {
        validateSig(sig);
        String calculatedSig = geneSignature(appKey, appSecret, uid, nonce, /*tsn,*/
                currentSeconds);
        return sig.equals(calculatedSig);
    }

    public static String geneSignature(String appKey, String appSecret,
            String uid, String nonce, /*String tsn,*/ String currentSeconds) {

        validateAppKey(appKey);
        validateAppSecret(appSecret);
        validateUid(uid);
        validateNonce(nonce);
//        validateTsn(tsn);
        validateCurrentSecond(currentSeconds);

        String calculatedSig = new HmacUtils(HmacAlgorithms.HMAC_SHA_1,
                appSecret).hmacHex(Joiner.on("-").join(appKey, uid,
                currentSeconds, nonce));
        return calculatedSig;

    }

    public static void main(String[] args) {
        validateStringLength("aefe7t", "filedName1", 6, 6);
        // validateStringLength(null,"filedName1",6,6);
        //appKey=www1234567890ww1&uid=346364&nonce=25474567457547&currSec=1536723691&sig=261ff73abdce0cf69401cac39476ecc3e67a3850
        String appKey = "www1234567890ww1";
        String appSecret = "rt34656g34ght435242353tt3434fdgd";
        String uid = "346364";
        String nonce = "25474567457547";
//        String tsn = "Evbwr35nethRFidfre";
        String currentSecond = (System.currentTimeMillis() / 1000 - 29) + "";
        System.out.println(currentSecond);
        validateAppKey(appKey);
        validateAppSecret(appSecret);
        validateUid(uid);
        validateNonce(nonce);
        validateCurrentSecond(currentSecond);
        String sigature = geneSignature(appKey, appSecret, uid, nonce,/*tsn,*/
                currentSecond);
        System.out.println(sigature);
        System.out.println(sigature.length());
        System.out.println(verifySignature(appKey, appSecret, uid, nonce,/*tsn,*/
                currentSecond, sigature.replace("a", "0")));
        System.out.println(verifySignature(appKey, appSecret, uid, nonce,/*tsn,*/
                currentSecond, sigature));
    }

}
