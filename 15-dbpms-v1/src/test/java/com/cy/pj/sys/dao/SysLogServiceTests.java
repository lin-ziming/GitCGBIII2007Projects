package com.cy.pj.sys.dao;

import com.cy.pj.common.pojo.PageObject;
import com.cy.pj.sys.pojo.SysLog;
import com.cy.pj.sys.service.SysLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SysLogServiceTests {
    @Autowired
    private SysLogService sysLogService;
    @Test
    void testFindPageObjects(){
        PageObject<SysLog> pageObjects = sysLogService.findPageObjects("admin", 1);
        System.out.println(pageObjects);
    }
}
