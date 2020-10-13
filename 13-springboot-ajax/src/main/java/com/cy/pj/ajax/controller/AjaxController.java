package com.cy.pj.ajax.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AjaxController {

    private List<String> list=new ArrayList<>();//假设这是存储名字的数据库

    @PostMapping("doSave")
    public String doSave(String name){
        list.add(name);
        return "save ok";
    }

    @GetMapping("doCheck")
    public String doCheck(String name){
        return list.contains(name) ? name+"已存在" : name+"可注册";
    }

    @GetMapping("doAjaxGet")
//    @CrossOrigin
    public String doAjaxGet() throws InterruptedException {
        System.out.println("===");
        Thread.sleep(5000);//模拟一个耗时操作
        return "Ajax Get Request Result";
    }
}
