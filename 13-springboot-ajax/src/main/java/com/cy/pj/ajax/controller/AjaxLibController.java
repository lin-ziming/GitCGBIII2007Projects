package com.cy.pj.ajax.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AjaxLibController {

    //@RequestBody 描述方法参数时,表示这个参数的值来自客户端post提交的json字符串
    @PostMapping("doPostJSON")
    public String doPostJSON(@RequestBody Map<String,Object> map){
        System.out.println("map="+map);
        return "post ok";
    }

    @PostMapping("doPost")
    //map默认是用来放响应数据的，使用map作为参数接收请求时，要使用@RequestParam注解对参数进行修饰
    public String doPost(@RequestParam Map<String,Object> map){
        System.out.println("map="+map);
        return "post ok";
    }

    @RequestMapping("doAjax")
    public String doAjax(String msg){
        System.out.println("msg="+msg);
        return msg+"toUpcaseCase():"+msg.toUpperCase();
    }

    @GetMapping("doGet")
    public Map<String,Object> doGet(){
        Map<String,Object> map=new HashMap<>();
        map.put("code", 1);
        map.put("message", "success");
        return map;
    }

}
