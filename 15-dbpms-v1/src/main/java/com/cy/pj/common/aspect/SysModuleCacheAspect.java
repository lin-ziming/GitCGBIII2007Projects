package com.cy.pj.common.aspect;

import com.cy.pj.common.annotation.ClearCache;
import com.cy.pj.common.annotation.RequiredCache;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Aspect
/**cache切面*/
public class SysModuleCacheAspect {
    /**
     * 这里希望每个业务模块都有自己对应的cache
     * 1)外层map的key为对应着具体模块的cache
     * 2)内存模块key为存储数据时使用的kdy */
    private Map<String,Map<String,Object>> cacheMap=new ConcurrentHashMap<>();
    /**@annotation 为注解方式的切入点表达式(由此注解描述的方法为切入点方法) */
    @Pointcut("@annotation(com.cy.pj.common.annotation.RequiredCache)")
    private void doCache(){}

    @Pointcut("@annotation(com.cy.pj.common.annotation.ClearCache)")
    private void doClearCache(){}

    @AfterReturning("doClearCache()")
    public void doAfterReturning(JoinPoint joinPoint) throws NoSuchMethodException {//method ok(success)
        String cacheName=getCacheName(ClearCache.class,joinPoint);
        Map<String, Object> cache = cacheMap.get(cacheName);
        cache.clear();
    }

    private Method getTargetMethod(JoinPoint joinPoint) throws NoSuchMethodException {
        //1.获取目标方法
        Class<?> targetClass=joinPoint.getTarget().getClass();
        MethodSignature ms= (MethodSignature) joinPoint.getSignature();
        Method targetMethod = targetClass.getMethod(ms.getName(), ms.getParameterTypes());
        return targetMethod;
    }

    private String getCacheName(Class<? extends Annotation> annoClass, JoinPoint joinPoint) throws NoSuchMethodException {
        //1.获取目标方法
        Method targetMethod = getTargetMethod(joinPoint);
        //2.基于注解类型获取目标方法上的注解对象
        Annotation annotation=targetMethod.getAnnotation(annoClass);
        String cacheName=null;
        //3.基于注解对象类型获取注解上的名字
        if(annotation instanceof RequiredCache){
            RequiredCache requiredCache= (RequiredCache) targetMethod.getAnnotation(annoClass);
            cacheName=requiredCache.name();
        }else if(annotation instanceof ClearCache){
            ClearCache clearCache= (ClearCache) targetMethod.getAnnotation(annoClass);
            cacheName=clearCache.name();
        }
        return cacheName;
    }

    private String getCacheKey(Class<? extends Annotation> annoClass, JoinPoint joinPoint) throws NoSuchMethodException {
        //1.获取目标方法
        Method targetMethod=getTargetMethod(joinPoint);
        //2.基于注解类型获取目标方法上的注解对象
        Annotation annotation=targetMethod.getAnnotation(annoClass);
        String key=null;
        //3.基于注解对象类型获取注解上的名字
        if(annotation instanceof RequiredCache){
            RequiredCache requiredCache= (RequiredCache) targetMethod.getAnnotation(annoClass);
            key=requiredCache.key();
        }
        return key;
    }

    @Around("doCache()")//@Around中方法的参数连接点的类型只能写ProceedingJoinPoint
    public Object doCacheAround(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("Get Data from cache");
        Map<String, Object> cache=null;
        synchronized (SysModuleCacheAspect.class) {//最好用双重校验(双重检测)机制
            String cacheName = getCacheName(RequiredCache.class, joinPoint);
            cache = cacheMap.get(cacheName);
            if (cache == null) {
                cache = new ConcurrentHashMap<>();
                cacheMap.put(cacheName, cache);
            }
        }
        String key=getCacheKey(RequiredCache.class,joinPoint);
        Object result = cache.get(key);
        if(result!=null) return result;
        result = joinPoint.proceed();
        System.out.println("Put data to cache");
        cache.put(key, result);
        return result;
    }
}
