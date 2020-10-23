package com.cy.pj.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Component
@Aspect
public class SysCacheAspect {
    private Map<String,Object> cache=new ConcurrentHashMap<>();
    /**@annotation 为注解方式的切入点表达式(由此注解描述的方法为切入点方法) */
    @Pointcut("@annotation(com.cy.pj.common.annotation.RequiredCache)")
    private void doCache(){}

    @Pointcut("@annotation(com.cy.pj.common.annotation.ClearCache)")
    private void doClearCache(){}

    @AfterReturning("doClearCache()")
    public void doAfterReturning(){//method ok(success)
        cache.clear();
    }

    @Around("doCache()")
    public Object doCacheAround(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("Get Data from cache");
        Object result = cache.get("deptKey");//暂时自己先指定一个key的名字
        if(result!=null) return result;
        result = joinPoint.proceed();
        System.out.println("Put data to cache");
        cache.put("deptKey", result);
        return result;
    }
}
