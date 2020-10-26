package com.cy.pj.common.aspect;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.util.IPUtils;
import com.cy.pj.sys.pojo.SysLog;
import com.cy.pj.sys.service.SysLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * @Aspect 注解描述的对象为一个切面对象，在切面对象中定义
 * 1)切入点(Pointcut):织入扩展功能的一些连接点的集合
 * 2)通知方法(Advice):封装了扩展逻辑的方法
 */
//@Order(1)
@Slf4j
@Aspect
@Component
//@Scope("singleton")
//@Lazy    //加了以上两个注解启动时延迟创建，否则对象是在启动时创建
public class SysLogAspect {
    /**通过Pointcut定义一个切入点,@annotation方式为定义切入点的一种方式，
     * 在这里表示业务对象中由com.cy.pj.common.annotation.RequiredLog
     * 注解描述的方法为一些切入点方法 */
    @Pointcut("@annotation(com.cy.pj.common.annotation.RequiredLog)")
    public void doLog(){}//doLog 方法仅仅是@Pointcut注解的一个载体，方法体内不需要写任何内容


    /**
     * @Around 注解描述的方法可以在目标方法执行之前和之后做功能扩展，叫做通知方法
     * @param joinPoint 封装了目标方法信息的一个对象(连接点对象)
     * @return 目标方法的执行结果
     * @throws Throwable
     */
    @Around("doLog()")
    public Object doAround(ProceedingJoinPoint joinPoint)throws Throwable{
        try {
            long t1 = System.currentTimeMillis();
            Object result = joinPoint.proceed();//去调用目标方法，其返回值为目标方法返回值
            long t2 = System.currentTimeMillis();
            System.out.println("time:" + (t2 - t1));
            //将正常的用户行为日志写入到数据库、
            saveSysLog(joinPoint, t2 - t1);
            return result;//返回给控制层
        }catch (Throwable e){
            //saveErrorLog(...);//也可以将错误日志写入到数据库
            logError(joinPoint,e.getMessage());
            throw e;
        }
    }

    /**
     * 将错误日志进行输出并记录
     * @param joinPoint
     * @param exceptionMsg
     * @throws JsonProcessingException
     */
    private void logError(ProceedingJoinPoint joinPoint,String exceptionMsg) throws JsonProcessingException {
        String targetClassName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String params = new ObjectMapper().writeValueAsString(joinPoint.getArgs());
        log.error("msg->{}->{}-{}",targetClassName+"."+methodName,params,exceptionMsg);
    }

    @Autowired
    private SysLogService sysLogService;

    private void saveSysLog(ProceedingJoinPoint joinPoint,long time) throws NoSuchMethodException, JsonProcessingException {
        //1.获取用户行为日志
        //获取目标对象类型
        Class<?> targetClass = joinPoint.getTarget().getClass();
        //获取目标方法的签名信息
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        //获取目标方法？(类中方法的唯一标识是：方法名+参数列表)
        Method targetMethod=
                targetClass.getDeclaredMethod(ms.getName(),ms.getParameterTypes());
        //获取RequiredLog注解
        RequiredLog requiredLog=targetMethod.getAnnotation(RequiredLog.class);
        //获取操作名(RequiredLog中operation的值)
        String operation=requiredLog.value();
        //2.封装日志信息
        SysLog entity=new SysLog();
        entity.setUsername("cgb");//将来这个位置为登录用户名
        entity.setIp(IPUtils.getIpAddr());
        entity.setOperation(operation);//为目标方法指定的一个名字
        entity.setMethod(targetClass.getName()+"."+targetMethod.getName());//类全名+方法名
        //entity.setParams(Arrays.toString(joinPoint.getArgs()));//调用方法时传递实际参数
        entity.setParams(new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
        entity.setTime(time);
        entity.setCreatedTime(new Date());
        //3.保存用户行为值
        sysLogService.saveObject(entity);
//        new Thread(){//一个线程操作系统分配的内存大概是1M，线程启动时
//            // 要调用操作系统的进程，由操作系统的进程来分配线程
//            // 启动线程，底层依托于操作系统
//            //同时大量启动线程的问题：1.频繁创建线程很消耗系统资源，速度慢
//            //2.可能会造成内存溢出
//
//            //异步写日志（自己new thread,借助池中线程，但非tomcat线程池中的线程）
//            //绝对不能让tomcat做这些事情，因为tomcat中的线程的核心作用是：接受、处理客户端的请求
//            //不要把耗时操作交给tomcat池中线程
//            @Override
//            public void run() {
//                sysLogService.saveObject(entity);
//            }
//        }.start();
    }

}
