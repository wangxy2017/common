package com.wxy.common.encryption;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * HmacSha256Utils
 *
 * @author magic
 * @date 2019/1/14
 */
public class HmacSha256Utils {

    /**
     * byteArray to HexStr
     * @param b 字节数组
     * @return 字符串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1){
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }
    /**
     * sha256_HMAC加密
     * @param message 消息
     * @param secretKey  秘钥
     * @return 加密后字符串
     */
    public static String sha256Encrypt(String message, String secretKey) {
        String hash = "";
        try {
            Mac sha256HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec sk = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            sha256HMAC.init(sk);
            byte[] bytes = sha256HMAC.doFinal(message.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }

}
