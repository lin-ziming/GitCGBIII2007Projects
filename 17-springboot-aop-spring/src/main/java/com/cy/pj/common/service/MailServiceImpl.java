package com.cy.pj.common.service;

import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Override
    public boolean sendMsg(String message) {
        System.out.println("send + "+message);
        return false;
    }
}
