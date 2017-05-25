package com.qk.applibrary.util.safe;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * 作者：zhoubenhua
 * 时间：2016-10-26 13:23
 * 功能:rsa加密和解密算法
 */
public class RSA {
    private static final String TAG = "RSA";
    private static PublicKey getPublicKeyFromX509(String algorithm,
                                                  String bysKey) throws  Exception {
        byte[] decodedKey = Base64.decode(bysKey);
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodedKey);

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(x509);
    }

    /**
     * 加密
     * @param content 要加密字符串
     * @param publicKey 公钥
     * @return 加密之后字符串
     */
    public static String encrypt(String content, String publicKey) {
        try {
            PublicKey pubkey = getPublicKeyFromX509(TAG, publicKey);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubkey);
            byte plaintext[] = content.getBytes("UTF-8");
            byte[] output = cipher.doFinal(plaintext);
            String s = new String(Base64.encode(output));
            return s;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
