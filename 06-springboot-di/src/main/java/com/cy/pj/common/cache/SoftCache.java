package com.cy.pj.common.cache;

import org.springframework.stereotype.Component;

@Component //这里没有指定bean的名字,则默认为类名首字母小写.
//@Component("cache")
//@Component("softCache")
public class SoftCache implements  Cache{

}