package com.cy.pj.goods.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class GoodsDaoTests {

    @Autowired
    private GoodsDao goodsDao;

    @Test
    public void test(){
        List<Map<String,Object>> list=goodsDao.findGoods();
        for(Map<String,Object> map : list){
            System.out.println(map);
        }
    }
}
