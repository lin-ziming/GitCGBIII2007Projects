package com.cy.pj.goods.service;

import com.cy.pj.goods.dao.GoodsDao;
import com.cy.pj.goods.pojo.Goods;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class GoodsServiceImpl implements GoodsService {

    private static final Logger log= getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public int updateGoods(Goods entity) {//对于update操作，参数entity中需要
        return goodsDao.updateGoods(entity);
    }

    @Override
    public Goods findById(Integer id) {
        return goodsDao.findById(id);
    }

    @Override
    public int saveGoods(Goods entity) {
        return goodsDao.insertGoods(entity);
    }

    @Override
    public int deleteById(Integer id) {
        int rows=goodsDao.deleteById(id);
        return rows;
    }

    @Override
    public List<Goods> findGoods() {

        long t1=System.currentTimeMillis();
        List<Goods> list=goodsDao.findGoods();
        long t2=System.currentTimeMillis();
        log.info("findGoods time-> {}",t2-t1);
        return list;
    }
}
