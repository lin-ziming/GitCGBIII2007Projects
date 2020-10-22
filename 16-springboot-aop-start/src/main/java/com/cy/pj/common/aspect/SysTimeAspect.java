package com.cy.pj.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SysTimeAspect {
    @Pointcut("bean(mailServiceImpl)")
    public void doTime(){}

    @Before("doTime()")
    public void doBefore(JoinPoint jp){
        System.out.println("time doBefore()");
    }
    @After("doTime()")
    public void doAfter(){
        System.out.println("time doAfter()");
    }
    @AfterReturning("doTime()")
    public void doAfterReturning(){
        System.out.println("time doAfterReturning");
    }
    @AfterThrowing("doTime()")
    public void doAfterThrowing(){
        System.out.println("time doAfterThrowing");
    }
    @Around("doTime()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(System.currentTimeMillis());
        try {
            Object result = joinPoint.proceed();
            System.out.println(System.currentTimeMillis());
            return result;
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }
}
