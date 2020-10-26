package com.cy.pj.common.config;

import org.apache.shiro.mgt.SecurityManager;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration //这个注解描述的类是要交给spring框架管理的一个配置对象(当然也是一个特殊的bean对象)
public class SpringShiroConfig {

    @Bean //此注解描述的方法，其返回值会交给spring管理。
    public SecurityManager securityManager(Realm realm){//此对象为shiro框架的核心
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }
    @Bean //此对象中要配置的一些访问规则(匿名访问的资源，认证的访问的资源)
    public ShiroFilterFactoryBean shiroFilterFactory(SecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean=new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);//securityManager对象负责去检测这个请求是否已经认证
        factoryBean.setLoginUrl("/doLoginUI");//拦截
        Map<String,String> filterMap=new LinkedHashMap<>();
        //静态资源允许匿名访问:"anon"
        filterMap.put("/bower_components/**","anon");//这里的anon对应着shiro中的一些默认过滤器
        filterMap.put("/build/**","anon");
        filterMap.put("/dist/**","anon");
        filterMap.put("/plugins/**","anon");
        filterMap.put("/user/doLogin", "anon");
        filterMap.put("/doLogout", "logout");//logout为shiro框架提供的一个退出过滤器，一旦执行了退出操作，底层会将系统服务端存储的用户信息进行清除并且直接跳转到登陆页面
        //除了匿名访问的资源,其它都要认证("authc")后访问
        filterMap.put("/**","authc");
        factoryBean.setFilterChainDefinitionMap(filterMap);//过滤链的定义
        return factoryBean;
    }
}
