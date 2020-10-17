package com.cy.pj.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Aspect 注解描述的对象为一个切面对象，在切面对象中定义
 * 1)切入点(Pointcut):织入扩展功能的一些连接点的集合
 * 2)通知方法(Advice):分装了扩展逻辑的方法
 */
@Aspect
@Component
public class SysLogAspect {
    /**通过Pointcut定义一个切入点,@annotation方式为定义切入点的一种方式，
     * 在这里表示业务对象中由com.cy.pj.common.annotation.RequiredLog
     * 注解描述的方法为一些切入点方法 */
    @Pointcut("@annotation(com.cy.pj.common.annotation.RequiredLog)")
    public void doLog(){}//doLog 方法仅仅是@Pointcut注解的一个载体，方法体内不需要写任何内容

    /**
     * @Around 注解描述的方法可以在目标方法执行之前和之后做功能扩展
     * @param joinPoint 封装了目标方法信息的一个对象(连接点对象)
     * @return 目标方法的执行结果
     * @throws Throwable
     */
    @Around("doLog()")
    public Object doAround(ProceedingJoinPoint joinPoint)throws Throwable{
        long t1=System.currentTimeMillis();
        Object result = joinPoint.proceed();//去调用目标方法，其返回值为目标方法返回值
        long t2=System.currentTimeMillis();
        System.out.println("time:"+(t2-t1));
        return result;
    }
}
