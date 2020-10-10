package com.cy.pj.module.controller;

import com.cy.pj.common.pojo.ResponseResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArithmeticController {

    @RequestMapping("/doCompute/{n1}/{n2}")
    public ResponseResult doCompute(@PathVariable Integer n1, @PathVariable Integer n2){
        Integer result=n1/n2;
        ResponseResult r= new ResponseResult("计算结果："+result);
        r.setData(result);
        return r;
    }
}
