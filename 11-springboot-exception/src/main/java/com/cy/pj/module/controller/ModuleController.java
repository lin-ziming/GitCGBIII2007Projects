package com.cy.pj.module.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ModuleController {//代表任意一个模块Controller

    @RequestMapping("doCompute/{n1}/{n2}")
    @ResponseBody
    public String doCompute(@PathVariable Integer n1, @PathVariable Integer n2){
//        try{
            Integer result=n1/n2;
            return "result is"+result;
//        }catch (ArithmeticException e){
//            e.printStackTrace();//打印在控制台
//            //log.info("exception{}",e.getMessage()); //打印在日志
////            return "异常输入";
//            return "exception:"+e.getMessage();
//        }
    }

    //@ExceptionHandler 注解描述的方法是spring web模块中的异常处理方法
    //直接在Controller类中的定义的异常处理方法只能处理这个Controller类中出现的一些异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public String doHandleArithmeticException(ArithmeticException e){
        e.printStackTrace();
        System.out.println("ModuleController.doHandleArithmeticException()");
        return "计算异常："+e.getMessage();
    }

}
