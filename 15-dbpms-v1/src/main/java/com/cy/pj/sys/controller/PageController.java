package com.cy.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**通过此controller处理页面请求*/
@Controller
@RequestMapping("/")
public class PageController {
    @GetMapping("doPageUI")
    public String doPageUI(){
        return "common/page";
    }
    @GetMapping("log/log_list")
    public String doLogUI(){
        return "sys/log_list";
    }
    @GetMapping("doIndexUI")
    public String doIndexUI(){
        return "starter";
    }
}
