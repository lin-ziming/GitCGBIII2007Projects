package com.cy.pj.goods.dao;

import java.util.List;
import java.util.Map;

/**
 * 商品模块数据访问层接口
 */
public interface GoodsDao {
    /**
     * 查询所有商品信息，将每一行记录存储到一个map对象，然后将多个存储到list集合
     */
    List<Map<String,Object>> findGoods();
}
