package com.cy.pj.common.aspect;

import com.cy.pj.common.service.MailServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect    //告诉spring这是一个切面对象，这样的对象要包含(1.切入点 2.扩展逻辑advice)(封装了扩展逻辑的对象)
@Component //表示在spring中做一个注册
public class SysLogAspect {
    /**
     * 定义切入点
     * bean表达式为spring中的一种粗粒度切入点表达式，即不能精确到具体方法
     * 这里mailServiceImpl名字为spring容器中一个bean对象的名字
     */
    @Pointcut("bean(mailServiceImpl)")
    public void doLogPointCut(){}//这个方法仅仅是承载注解的载体

    /**
     * 按照Aspect规范定义一个@Around通知
     * //@Around("bean(mailServiceImpl)") //直接在advice注解内部定义切入点表达式
     * //@Around("doLogPointCut()")//也可以在advice注解内部通过方法引用切入点表达式
     * //对于@Around注解描述的方法 其规范要求：
     * 1)返回值类型为Object(用于封装目标方法的执行结果)
     * 2)参数类型为ProceedingJoinPoint
     * 3)抛出的异常为Throwable
     * 4)在@Around注解描述的方法内部，可以手动调用目标方法
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("doLogPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        long t1=System.currentTimeMillis();
        Object result=joinPoint.proceed();
        long t2=System.currentTimeMillis();
        System.out.println("time:"+(t2-t1));
        return result;
    }
}
