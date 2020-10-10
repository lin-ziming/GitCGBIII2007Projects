package com.cy.pj.module.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice //此注解描述的类为spring web模块的全局异常处理类
public class GlobalExceptionHandler {

    //@ExceptionHandler 注解描述的方法是spring web模块中的异常处理方法
    //直接在Controller类中的定义的异常处理方法只能处理这个Controller类中出现的一些异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public String doHandleArithmeticException(ArithmeticException e){
        e.printStackTrace();
        System.out.println("GlobalExceptionHandler.doHandleArithmeticException()");
        return "计算异常："+e.getMessage();
    }

}
