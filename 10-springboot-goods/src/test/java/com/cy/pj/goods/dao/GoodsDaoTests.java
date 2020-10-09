package com.cy.pj.goods.dao;

import com.cy.pj.goods.pojo.Goods;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.font.TrueTypeFont;

import java.util.List;

@SpringBootTest
public class GoodsDaoTests {

    @Autowired
    private GoodsDao goodsDao;

    @Test
    void testDeleteById(){
        int rows=goodsDao.deleteById(12);//rows 为删除行数
        Assertions.assertEquals(true, rows>0);
    }

    @Test
    void testFindGoods(){
        List<Goods> goodsList=goodsDao.findGoods();
        for (Goods g : goodsList) {
            System.out.println(g);
        }
    }
}
