package com.cy.pj.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 系统底层会将这个切面中的内容转换为Advisor对象
 */
@Aspect
@Component
public class CacheAspect {
    /**
     * 假设这个map就是我们的一个小cache，我们从数据库取出的数据可以存储到此cache
     */
    private Map<String, Object> cache=new ConcurrentHashMap<>();

    @Pointcut("bean(moduleServiceImpl)")
    public void doCache(){}

    @Around("doCache()")
    public Object around(ProceedingJoinPoint joinPoint)throws Throwable{
        //1.从cache取数据
        Object obj = cache.get("userPermission");//这里假设key为userPermission
        if(obj!=null) return obj;
        //2.cache中没有则查数据
        obj=joinPoint.proceed();
        //3.将数据存储到cache
        cache.put("userPermission", obj);
        return obj;
    }
}
