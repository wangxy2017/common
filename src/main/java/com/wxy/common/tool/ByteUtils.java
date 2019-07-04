package com.wxy.common.tool;

/**
 * @Author wxy
 * @Date 19-7-4 下午3:05
 * @Description TODO
 **/
public class ByteUtils {
    public static String bytesToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length);
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(0xFF & bytes[i]);
            if (temp.length() < 2) {
                sb.append(0);
            }
            sb.append(temp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * hex字符串转byte数组
     *
     * @param hexString 待转换的Hex字符串
     * @return 转换后的byte数组结果
     */
    public static byte[] hexStringToBytes(String hexString) {
        int len = hexString.length();
        byte[] result;
        if (len % 2 == 1) {
            //奇数
            len++;
            result = new byte[(len / 2)];
            hexString = "0" + hexString;
        } else {
            //偶数
            result = new byte[(len / 2)];
        }
        int j = 0;
        for (int i = 0; i < len; i += 2) {
            result[j] = (byte) Integer.parseInt(hexString.substring(i, i + 2), 16);
            j++;
        }
        return result;
    }

}
