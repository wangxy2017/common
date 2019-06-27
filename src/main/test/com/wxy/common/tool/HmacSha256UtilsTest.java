package com.wxy.common.tool;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.UUID;

/**
 * @Author wxy
 * @Date 19-6-27 下午6:53
 * @Description HmacSha256Utils测试
 **/
@Slf4j
public class HmacSha256UtilsTest {

    /**
     * sha256_HMAC加密
     */
    @Test
    public void TestSha256Encrypt() {
        String message = UUID.randomUUID().toString();
        String key = UUID.randomUUID().toString();
        String encrypt = HmacSha256Utils.sha256Encrypt(message, key);
        log.debug("消息 = {}，密钥 = {}，加密结果 = {}", message, key, encrypt);
    }
}
