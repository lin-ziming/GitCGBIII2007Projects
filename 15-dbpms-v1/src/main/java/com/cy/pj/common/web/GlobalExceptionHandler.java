package com.cy.pj.common.web;

import com.cy.pj.common.pojo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
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
        log.error("exception msg {}",e.getMessage());
        return new JsonResult(e);
    }

    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public JsonResult doHandleShiroException(
            ShiroException e) {
        JsonResult r=new JsonResult();
        r.setState(0);
        if(e instanceof UnknownAccountException) {
            r.setMessage("账户不存在");
        }else if(e instanceof LockedAccountException) {
            r.setMessage("账户已被禁用");
        }else if(e instanceof IncorrectCredentialsException) {
            r.setMessage("密码不正确");
        }else if(e instanceof AuthorizationException) {
            r.setMessage("没有此操作权限");
        }else {
            r.setMessage("系统维护中");
        }
        e.printStackTrace();
        return r;
    }
}
