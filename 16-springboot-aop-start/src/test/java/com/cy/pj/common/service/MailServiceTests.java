package com.cy.pj.common.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailServiceTests {
    @Autowired
    MailService mailService;
    @Test
    void testSendMail02(){
        mailService.sendMail("hello");
    }
    @Test
    void testSendMail01(){
        //自动动手写的子类测试
        new TimeMailServiceImpl().sendMail("hello cbg2007");
        //自己动手写的兄弟类测试
        new TimeMailServiceImpl2(new MailServiceImpl()).sendMail("hello cbg2007");
    }
}
