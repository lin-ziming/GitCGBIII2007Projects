package com.cy.pj.goods.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;

@SpringBootTest
public class MyBatisTests {
    /**
     * sqlSession此对象为mybatis框架中实现与数据库进行会话的一个对象
     */
    @Autowired
    private SqlSession sqlSession;

    @Test
    void testGetConnection(){
//        Connection conn=null;
        Connection conn = sqlSession.getConnection();//从哪里去取的连接？
        System.out.println("conn="+conn);
        //断言测试：判断conn对象是否不为null，不为空则测试通过
        Assertions.assertNotNull(conn);
    }
}
