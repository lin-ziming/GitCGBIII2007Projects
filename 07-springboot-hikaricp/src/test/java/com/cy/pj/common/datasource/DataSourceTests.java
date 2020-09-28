package com.cy.pj.common.datasource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataSourceTests {
    /**
     * FAQ?
     * 1)DataSource由谁定义？答：Java官方（甲骨文）
     * 2）DataSource定义了什么？答：定义了从数据库或连接池获取连接Connection的一种规范。
     * 3）我们为什么要耦合于DataSource？答：类与类之间存在依赖（耦合）时，尽量耦合于抽象规范，然后再基于规范的实现获取连接
     * 4）程序运行时这个变量指向的对象类型是谁？答：HikariDataSource。你怎么知道是这个类型？答：（断点，日志）
     */
    @Autowired
    private DataSource dataSource;//interface

    @Test
    public void testConnection() throws SQLException {
        //获取dataSource对象对应的具体类型
        System.out.println(dataSource.getClass().getName());//com.zaxxer.hikari.HikariDataSource
        //通过dataSource获取连接的一个过程是怎样的？
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }
}