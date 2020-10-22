package com.cy.pj.module.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ModuleServiceImpl implements ModuleService{

    @Override
    public List<String> findPermissions() {
        System.out.println("select permissions from database");
        List<String> list=new ArrayList<>();
        list.add("sys:log:delete");
        list.add("sys:log:select");//假设这些数据来自数据库
        return list;
    }
}
