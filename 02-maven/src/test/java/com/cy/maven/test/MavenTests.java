package com.example.test;

import org.junit.Test;

public class MavenTests {
    /**
     * 单元测试方法编写需要注意什么?
     * 1)访问修饰符: public
     * 2)返回值类型: void
     * 3)参数列表: 无
     */
    @Test
    public void testMaven(){//底层反射调用此方法
        System.out.println("Test Maven Module");
    }
}