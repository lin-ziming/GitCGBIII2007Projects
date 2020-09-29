package com.cy.pj.goods.service;

import com.cy.pj.goods.dao.GoodsDao;
import com.cy.pj.goods.pojo.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private static final Logger log= LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    GoodsDao goodsDao;

    @Override
    public List<Goods> findObjects() {
        long t1=System.currentTimeMillis();
        List<Goods> list = goodsDao.findGoods();
        long t2=System.currentTimeMillis();
//        System.out.println("运行时间："+ (endTime-startTime) +"ms毫秒");
        System.out.println(log.getClass().getName());
        log.info("findGoods()->t2-t1={}",t2-t1);
        return list;
    }
}
