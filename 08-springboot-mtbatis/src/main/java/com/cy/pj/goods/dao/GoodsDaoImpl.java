package com.cy.pj.goods.dao;

import com.cy.pj.goods.pojo.Goods;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义GoodsDao接口实现类，在此实现类中基于SqlSession对象
 * 实现与数据库的会话
 */
@Repository
public class GoodsDaoImpl implements GoodsDao {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;

    public GoodsDaoImpl() {
        System.out.println("GoodsDaoImpl()");;
    }

    @Override
    public List<Goods> findGoods() {
        System.out.println("===findGoods()===");
        //1.获取sql对象
        SqlSession session=sqlSessionFactory.openSession();
        //namespace+elementId
        String statement="com.cy.pj.goods.dao.GoodsDao.findGoods";
        List<Goods> list=session.selectList(statement);
        session.close();
        return list;
    }
}
