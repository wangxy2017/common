package com.wxy.common;

import com.wxy.common.encryption.MD5Utils;
import com.wxy.common.test.AutoValues;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author wxy
 * @Date 19-6-27 下午4:37
 * @Description MD5Utils测试
 **/
@Slf4j
public class MD5UtilsTest {

    /**
     * MD5加密
     */
    @Test
    public void TestMD5Encode() {
        String password = AutoValues.nextStr();
        log.debug("密码 = {}，MD5加密 = {}", password, MD5Utils.MD5Encode(password));
    }

    /**
     * 盐值加密
     */
    @Test
    public void TestEncodeBySalt() {
        String password = AutoValues.nextStr();
        String salt = MD5Utils.getSalt(8);
        log.debug("密码 = {}，盐值 = {}，MD5加密 = {}", password, salt, MD5Utils.MD5Encode(password, salt));
    }
}
