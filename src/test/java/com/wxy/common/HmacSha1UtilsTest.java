package com.wxy.common;

import com.wxy.common.encryption.HmacSha1Utils;
import com.wxy.common.test.AutoValues;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author wxy
 * @Date 19-10-14 下午6:43
 * @Description TODO
 **/
@Slf4j
public class HmacSha1UtilsTest {
    @Test
    public void TestSha256Encrypt() {
        String message = AutoValues.nextStr();
        String key = AutoValues.nextStr();
        String encrypt = HmacSha1Utils.HmacSHA1Encrypt(message, key);
        log.debug("消息 = {}，密钥 = {}，加密结果 = {}", message, key, encrypt);
    }
}
