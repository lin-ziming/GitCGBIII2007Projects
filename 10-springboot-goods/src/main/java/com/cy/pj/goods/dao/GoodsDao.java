package com.cy.pj.goods.dao;

import com.cy.pj.goods.pojo.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 商品模块持久层对象，负责商品模块的数据访问逻辑的实现
 */
@Mapper
public interface GoodsDao {

    @Update("update tb_goods set name=#{name},remark=#{remark} where id=#{id}")
    int updateGoods(Goods entity);

    @Select("select * from tb_goods where id=#{id}")
    Goods findById(Integer id);

    @Insert("insert into tb_goods(name,remark,createdTime) values(#{name},#{remark},now())")
    int insertGoods(Goods entity);

    /**
     * 基于id删除商品信息
     * @param id
     * @return
     */
    @Delete("delete from tb_goods where id=#{id}")
    int deleteById(Integer id);

    /**
     * 查询所有商品信息
     * @return 所有商品
     * mybatis框架中定义sql映射有两种方式：
     * 1)注解方式（实现简单sql映射）
     * 2)xml方式（复杂sql映射，尤其是sql中包含动态sql时）
     */
    @Select("select id,name,remark,createdTime from tb_goods")//起别名会按别名执行set、get方法
    List<Goods> findGoods();
}
