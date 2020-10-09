package com.cy.pj.goods.service;

import com.cy.pj.goods.pojo.Goods;

import java.util.List;

/**
 * 用于定义商品业务逻辑操作的接口
 */
public interface GoodsService {

    int updateGoods(Goods entity);

    Goods findById(Integer id);

    int saveGoods(Goods entity);

    int deleteById(Integer id);

    List<Goods> findGoods();
}
