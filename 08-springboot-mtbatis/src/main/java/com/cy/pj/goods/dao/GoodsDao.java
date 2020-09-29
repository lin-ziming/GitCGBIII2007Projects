package com.cy.pj.goods.dao;

import com.cy.pj.goods.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**商品模块持久层对象，基于此对象的实现类操作商品库中的数据
 * @Mapper MyBatis框架中定义的，用于描述持久层接口，告诉mybatis
 * 这个接口的实现类有mybatis创建，并且交给spring框架管理。
 * */
@Mapper
public interface GoodsDao {

    List<Goods> findGoods();
}
