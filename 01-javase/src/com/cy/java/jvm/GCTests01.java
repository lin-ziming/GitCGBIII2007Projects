package com.cy.java.jvm;
import java.util.HashMap;
import java.util.Map;

class Point{
    //对象在GC之前会执行此方法
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize()");
    }
}
//-XX:+PrintGC
public class GCTests01 {
    public static void main(String[] args) {
        Map<String,Object> beanPool=new HashMap<>();//假设是用于存储对象的一个池
        Point p1=new Point();
        beanPool.put("key1",p1);
        p1=null;
        //beanPool.clear();
        System.gc();//启动GC
        //你们是否了解垃圾回收的一个基本步骤?做标记,从内存中清除对象,整理内存.
    }
}