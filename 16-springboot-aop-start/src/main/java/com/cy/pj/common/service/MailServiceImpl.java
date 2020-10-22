package com.cy.pj.common.service;

import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Override
    public boolean sendMail(String msg) {//直接改违背了OCP。(开闭原则->对扩展开放，对修改关闭)
//        long t1=System.currentTimeMillis();
        System.out.println("send->"+msg);
//        int a=1/0;
//        long t2=System.currentTimeMillis();
//        System.out.println("send time:"+(t2-t1));
        return true;
    }
}
/**
 * 自己动手写子类重写父类方法，进行功能扩展
 * CGLIB自动帮你写一个代理子类
 */
class TimeMailServiceImpl extends MailServiceImpl{
    @Override
    public boolean sendMail(String msg) {
        long t1=System.nanoTime();
        /**super调用父类方法*/
        boolean flag=super.sendMail(msg);
        long t2=System.nanoTime();
        System.out.println("send time:"+(t2-t1));
        return flag;
    }
}
/**
 * 自己写兄弟类对目标对象进行功能扩展，这种方式又叫组合
 */
class TimeMailServiceImpl2 implements MailService{
    private MailService mailService;
    public TimeMailServiceImpl2(MailService mailService){
        this.mailService=mailService;
    }
    @Override
    public boolean sendMail(String msg) {
        long t1=System.nanoTime();
        boolean flag = mailService.sendMail(msg);
        long t2=System.nanoTime();
        System.out.println("send time:"+(t2-t1));
        return flag;
    }
}
