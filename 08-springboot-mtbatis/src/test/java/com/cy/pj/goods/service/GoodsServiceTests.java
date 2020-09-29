package com.cy.pj.goods.service;

import com.cy.pj.goods.pojo.Goods;
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
        List<Goods> list = goodsService.findObjects();
        list.forEach(goods -> {
            System.out.println(goods);
        });
    }
}
