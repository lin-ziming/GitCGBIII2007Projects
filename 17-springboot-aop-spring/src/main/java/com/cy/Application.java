package com.cy;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    /**
     * @Bean注解描述方法是，这个方法的返回值交给spring管理
     * @return
     */
    @Bean //@Bean注解描述方法是，这个方法的返回值交给spring管理
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }

}
