package com.wxy.common.encryption;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * @Author wangxy
 * @Date 2019/6/17 16:19
 * @Description 3DES 加密算法
 **/
public class DESUtils {

    private static final String CHARSET = "UTF-8";
    /**
     * 加密
     *
     * @param src 密文
     * @param key 秘钥
     * @return
     */
    public static String encrypt(String src, String key) {
        try {
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes(CHARSET));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
            byte[] b = cipher.doFinal(src.getBytes(CHARSET));
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(b);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     *
     * @param src 密文
     * @param key 秘钥
     * @return
     */
    public static String decrypt(String src, String key) {
        try {
            // --通过 base64,将字符串转成 byte 数组
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytesrc = decoder.decodeBuffer(src);
            // --解密的 key
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes(CHARSET));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.DECRYPT_MODE, securekey);
            byte[] retByte = cipher.doFinal(bytesrc);
            return new String(retByte, CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
