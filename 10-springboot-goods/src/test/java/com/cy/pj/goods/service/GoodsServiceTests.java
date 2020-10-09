package com.cy.pj.goods.service;

import com.cy.pj.goods.pojo.Goods;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GoodsServiceTests {
    @Autowired
    private GoodsService goodsService;

    @Test
    void testFindGoods(){
        List<Goods> goodsList= goodsService.findGoods();
        //断言测试法（单元测试中常用的一种方法）
        Assertions.assertEquals(true, goodsList.size()>0);
    }
}
