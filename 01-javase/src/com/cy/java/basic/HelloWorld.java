package com.cy.java.basic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 这类在运行时会被读到内存,除了此类之外还有哪些类会被读到内存,第一个被读到内存的类是谁?
 * 通过配置JVM参数实现:-XX:+TraceClassLoading
 */
public class HelloWorld {//HelloWorld.class
    /**
     * 请问main方法中的参数用途是什么?我们可以给它传值吗?假如是IDEA在哪里进行传值?
     * 程序在运行过程我想了解GC情况?有没有触发GC?假如触发了GC,对哪些内存区域进行了回收?
     * JVM参数:
     * 打印GC详细情况: -XX:+PrintGCDetails
     */
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        System.out.println("welcome");
        System.out.println("helloworld");
        //触发GC的手段
        //1)手动GC
        //System.gc();
        //2)自动GC
        List<byte[]> list=new ArrayList<>();
        for(int i=0;i<10000;i++){
            list.add(new byte[1024*1024]);
            // byte[] array=new byte[1024*1024];// 对象创建越来越多的时候底层会启动GC
            // 内存中的对象何时为会被认为是垃圾对象? 对象不可达时(JVM访问不到了)
        }
        //思考:系统在启动GC时要回收内容中的垃圾对象,此时还可能会导致短时间的程序暂停.
    }
}