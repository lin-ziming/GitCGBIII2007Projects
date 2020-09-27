package com.cy.pj.common.pool;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Scope("prototype")//prototype表示多例作用域，此类实例与Lazy无关，默认何时需要何时创建，并且不会存储到spring的对象池
//@Scope //@Scope注解用于定义对象作用域,默认为单例(Singleton)作用域(一个JVM内存中名字相同的Bean只能有一个-Key相同的Bean)
//@Lazy //描述的类的对象可以延迟其创建,何时需要何时创建(按需加载)
@Component
public class ObjectPool {
    public ObjectPool(){
        System.out.println("ObjectPool()");
    }
    @PostConstruct
    public void init(){
        System.out.println("init()");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("destroy()");
    }
}