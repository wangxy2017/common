package com.wxy.common;

import com.wxy.common.security.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author wxy
 * @Date 19-7-9 下午3:13
 * @Description TODO
 **/
@Slf4j
public class JwtUtilsTest {

    @Test
    public void TestSign() {
        String username = "admin";
        String userId = "1";
        String token = JwtUtils.sign(username, userId);
        log.info("生成token = {}", token);
    }

    @Test
    public void TestVerity() {
        String username = "admin";
        String userId = "1";
        String token = JwtUtils.sign(username, userId);
        boolean verity = JwtUtils.verity(token);
        log.info("验证token = {}", verity);
    }

    @Test
    public void TestGetUsername() {
        String username = "admin";
        String userId = "1";
        String token = JwtUtils.sign(username, userId);
        String username1 = JwtUtils.getUsername(token);
        log.info("获取用户名 username = {}", username1);
    }

    @Test
    public void TestGetUserId() {
        String username = "admin";
        String userId = "1";
        String token = JwtUtils.sign(username, userId);
        String userId1 = JwtUtils.getUserId(token);
        log.info("获取用户ID：userId = {}", userId1);
    }
}
