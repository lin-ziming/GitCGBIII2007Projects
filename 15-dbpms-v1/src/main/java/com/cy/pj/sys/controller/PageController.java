package com.cy.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**通过此controller处理页面请求*/
@Controller
public class PageController {
    @GetMapping("/doPageUI")
    public String doPageUI(){
        return "common/page";
    }
//    @GetMapping("log/log_list")
//    public String doLogUI(){
//        return "sys/log_list";
//    }
//    @GetMapping("menu/menu_list")
//    public String doMenuUI(){
//        return "sys/menu_list";
//    }

    /**rest风格url,简化view请求处理，但是rest风格的优先级比较低*/
    @GetMapping("/{module}/{moduleUI}")
    public String doModuleUI(@PathVariable String moduleUI){

        return "sys/"+moduleUI;
    }
    @GetMapping("/doIndexUI")
    public String doIndexUI(){
        return "starter";
    }
}
