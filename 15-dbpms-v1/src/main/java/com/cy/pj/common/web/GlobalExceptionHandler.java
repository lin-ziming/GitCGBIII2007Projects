package com.cy.pj.common.web;

import com.cy.pj.common.pojo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.slf4j.LoggerFactory.getLogger;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

//    private static final Logger log= getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)//表示此方法专门处理运行时异常RuntimeException
    public JsonResult doHandleRuntimeException(RuntimeException e){
        e.printStackTrace();//控制台输出
        log.error("exception msg {}"+e.getMessage());
        return new JsonResult(e);
    }
}
