package com.cy.pj.sys.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@SpringBootTest
public class MD5Test {
    /**
     * MD5加密算法：消息摘要算法
     * 1)不可逆(严格来说只能加密，不能解密)
     * 2)相同内容加密结果也相同
     */
    @Test
    void testMD501(){
        String password="123456";
        String newPassword= DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(newPassword);//e10adc3949ba59abbe56e057f20f883e
        //两次的输出结果一致               //e10adc3949ba59abbe56e057f20f883e

        String salt= UUID.randomUUID().toString();
        System.out.println("salt="+salt);//salt=f7982ba9-9ca4-4d0f-beb1-4df594fee4b3
        newPassword= DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(newPassword);//e10adc3949ba59abbe56e057f20f883e
    }
}
