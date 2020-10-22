package com.cy.pj.common.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 封装了扩展业务逻辑的对象，这样的对象在原生的aop中需要在advisor中注册
 */
public class LogAdvice implements MethodInterceptor {
    /**
     * 此方法可以在目标业务方法执行之前和之后添加扩展逻辑
     * @param methodInvocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("start:"+System.nanoTime());
        Object result = methodInvocation.proceed();//执行目标方法
        System.out.println("end:"+System.nanoTime());
        return result;
    }//Advice
}
