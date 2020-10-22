package com.cy.pj.module.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ModuleServiceTests {
    @Autowired
    private ModuleService moduleService;
    @Test
    void testFindPermissions(){
        List<String> permission=moduleService.findPermissions();
        permission=moduleService.findPermissions();
        permission=moduleService.findPermissions();
    }
}
