package com.cy.pj.goods.service;

import com.cy.pj.goods.pojo.Goods;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GoodsService {
    List<Goods> findObjects();
}
