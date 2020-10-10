package com.cy.pj.module.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice //此注解描述的类为spring web模块的全局异常处理类
//@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    //@ExceptionHandler 注解描述的方法是spring web模块中的异常处理方法
    //直接在Controller类中的定义的异常处理方法只能处理这个Controller类中出现的一些异常
    @ExceptionHandler(ArithmeticException.class)
//    @ResponseBody
    public String doHandleArithmeticException(ArithmeticException e){
        e.printStackTrace();
        System.out.println("GlobalExceptionHandler.doHandleArithmeticException()");
        return "计算异常："+e.getMessage();
    }

    @ExceptionHandler(RuntimeException.class)
//    @ResponseBody
    public String doHandleRuntimeException(ArithmeticException e){
        e.printStackTrace();
        System.out.println("GlobalExceptionHandler.doHandleRuntimeException");
        return "计算异常："+e.getMessage();
    }

    @ExceptionHandler(Throwable.class)
//    @ResponseBody
    public String doHandleThrowable(Throwable e){
        e.printStackTrace();
        System.out.println("GlobalExceptionHandler.doHandleThrowable");
        //发邮件，发短信，服务端后台报警（播放音乐）
        return "计算异常："+e.getMessage();
    }

}
