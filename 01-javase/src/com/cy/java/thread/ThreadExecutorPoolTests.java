package com.cy.java.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadExecutorPoolTests {
    public static void main(String[] args) {
        //创建一个任务队列
        BlockingQueue<Runnable> workQueue=new ArrayBlockingQueue<>(1);
        //穿件线程工厂
        ThreadFactory threadFactory=new ThreadFactory() {
            private AtomicLong al=new AtomicLong(1);//线程安全的对象
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "cgb3007-thread-"+al.getAndIncrement());
            }
        };
        //创建一个线程池
        ThreadPoolExecutor threadPoolExecutor=
                new ThreadPoolExecutor(
                        2, //核心线程数
                        3, //最大线程数
                        60, //线程空闲时间
                        TimeUnit.SECONDS, //时间单位
                        workQueue,    //任务队列
                        threadFactory, //线程工厂
                        new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                String tName=Thread.currentThread().getName();
                System.out.println(tName+"->task-01");
                try {Thread.sleep(3000); } catch (InterruptedException e) {}
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                String tName=Thread.currentThread().getName();
                System.out.println(tName+"->task-02");
                try {Thread.sleep(3000); } catch (InterruptedException e) {}
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                String tName=Thread.currentThread().getName();
                System.out.println(tName+"->task-03");

            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                String tName=Thread.currentThread().getName();
                System.out.println(tName+"->task-04");
                try {Thread.sleep(3000); } catch (InterruptedException e) {}
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                String tName=Thread.currentThread().getName();
                System.out.println(tName+"->task-05");
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                String tName=Thread.currentThread().getName();
                System.out.println(tName+"->task-06");
            }
        });
    }
}
