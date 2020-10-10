package com.cy.pj.common.web;

import com.cy.pj.common.pojo.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ArithmeticException.class)
    public ResponseResult doHandlerArithmeticException(ArithmeticException e){
        e.printStackTrace();
        log.info("exception {}", e.getMessage());
        return new ResponseResult(e);//封装异常结果
    }
}
