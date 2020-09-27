package com.cy.pj.common.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DefaultCacheTests {
    /**
     *  @Autowired 注解描述的属性由spring框架按照一定规则为其注入值（赋值）
     *  赋值过程是怎样的？
     *  1)依赖查找？(请问查找规则是什么？)
     *  2)依赖注入？(需要借助什么技术?)
     */
    @Autowired
    private DefaultCache defaultCache;
    @Test
    void testDefaultCache(){
        System.out.println(defaultCache.toString());
    }
}