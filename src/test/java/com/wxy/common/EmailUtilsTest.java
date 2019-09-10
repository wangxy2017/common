package com.wxy.common;

import com.wxy.common.tool.EmailUtils;
import com.wxy.common.tool.IDUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.mail.MessagingException;

/**
 * @Author wxy
 * @Date 19-6-27 下午4:50
 * @Description IDUtils测试
 **/
@Slf4j
public class EmailUtilsTest {

    @Test
    public void TestSend() throws MessagingException {
        EmailUtils.sendEmail("243548880@qq.com", "测试", "测试");
    }
}
