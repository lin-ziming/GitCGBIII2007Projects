package com.cy.pj.common.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailServiceTests {
    @Autowired
    private MailService mailService;

    @Test
    void testSendMsg(){
        mailService.sendMsg("hello spring aop");
    }
}
