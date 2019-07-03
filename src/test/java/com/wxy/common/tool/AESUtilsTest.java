package com.wxy.common.tool;

import com.wxy.common.encryption.AESUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author wxy
 * @Date 19-6-29 下午5:35
 * @Description AESUtilsTest
 **/
@Slf4j
public class AESUtilsTest {
    @Test
    public void TestEncrypt() {
        String content = "hello world !";
        String password = "12345";
        String encrypt = AESUtils.encrypt(content, password);
        log.info("AES encrypt = {}", encrypt);
    }

    @Test
    public void TestDecrypt() {
        String content = "hello world !";
        String password = "12345";
        log.info("content = {}", content);
        String encrypt = AESUtils.encrypt(content, password);
        log.info("AES encrypt = {}", encrypt);
        String decrypt = AESUtils.decrypt(encrypt, password);
        log.info("AES decrypt = {}", decrypt);
    }
}
