package com.cy.pj.common.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CacheTests {
    /**
     * @Autowired 可以描述属性,构造方法,set方法等,描述属性时,用于告诉spring框架,按属性类型从spring容器查找对应的对象进行赋值.假如找到一个类型
     * 匹配的对象则直接通过反射技术进行赋值.假如有多个还会按属性名字进行匹配查找(从spring容器查找哪个bean的名字
     * 与这个属性名相同),有相同名字的Bean则直接注入,没有则抛异常.当然我们也可以指定注入哪个名字的bean对象.此时
     * 需要借助@Qualifier指定要注入的bean对象的名字
     */
    @Autowired
    @Qualifier("softCache") //配合@Autowired注解使用,可以描述属性,构造方法,set方法等方法的参数
    private Cache cache;

    @Test
    void testCache(){
        System.out.println(cache);
    }
}