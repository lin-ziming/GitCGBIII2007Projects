package com.cy.pj.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SysTimeAspect {
    @Pointcut("bean(sysUserServiceImpl)")
    public void doTime(){}

    @Before("doTime()")
    public void doBefore(JoinPoint jp){
        System.out.println("@Before");
    }
    @After("doTime()")
    public void doAfter(){
        System.out.println("@After");
    }
    @AfterReturning("doTime()")
    public void doAfterReturning(){
        System.out.println("@AfterReturning");
    }
    @AfterThrowing("doTime()")
    public void doAfterThrowing(){
        System.out.println("@AfterThrowing");
    }
    @Around("doTime()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            System.out.println("开始："+System.currentTimeMillis());
            System.out.println("@Around.before");
            Object result = joinPoint.proceed();
            System.out.println("@Around.after");
            System.out.println("结束："+System.currentTimeMillis());
            return result;
        } catch (Throwable e) {
            System.out.println("@Around.exception");
            throw e;
        }finally {
            System.out.println("finally");
        }
    }
}
