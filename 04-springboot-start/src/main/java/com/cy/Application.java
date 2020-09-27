package com.cy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication 注解用于描述springboot项目的启动类,由此注解描述的类为springboot入口对象,我们
 * 可以通过这个入口来启动springboot项目.这个入口类有什么特点吗?
 * 1)由@SpringBootApplication注解描述?(这样的启动类,在springboot项目中只能有一个)
 * 2)包一个main方法,在方法中来完成springboot项目的初始化?
 *
 * FAQ:请问初始化时springboot底层都会做什么?
 * 1)加载类(通过ClassLoader将指定位置的类读到内存->底层通过线程调用IO从磁盘读取到内存)
 * 2)对类进行分析(创建字节码对象-Class类型,通过反射获取器配置信息)
 * 3)对于指定配置(例如由spring特定注解描述)的对象存储其配置信息(借助BeanDefinition对象存储)
 * 4)基于BeanDefinition对象中class的配置构建类的实例(Bean对象),从进行bean对象的管理.
 *
 * 思考:浏览器中输入一个url(例如http://www.baidu.com)然后执行回车,底层会做点什么?
 * 1)将url发送给域名服务器(DNS),通过DNS解析域名获取一个IP地址(IP地址是网络中计算机的唯一标识)
 * 2)通过IP地址找到网络中的计算机服务器,再通过端口号定位到具体的web服务器(例如tomcat).(端口号是计算机中应用程序唯一标识)
 * 3)web服务器可以通过线程调用io读取网络中http协议中的数据,并对数据进行解析,然后封装到request对象.
4)web服务器调用JAVAEE规范中的对象(Filter,Servlet)处理请求.请求处理结束再响应一个结果传递到客户端.
 */

@SpringBootApplication
public class Application {//Application.class

	public static void main(String[] args) {//Main Thread

		SpringApplication.run(Application.class, args);
	}

}