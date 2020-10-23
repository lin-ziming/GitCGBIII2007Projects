package com.cy.pj.goods.controller;

import com.cy.pj.goods.pojo.Goods;
import com.cy.pj.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goods/doFindById/{id}")
    public Goods doFindById(@PathVariable("id") Integer id){
        return goodsService.findById(id);
    }

    @PostMapping("/goods/doUpdateGoods")
    public String doUpdateGoods(@RequestBody Goods entity){
        goodsService.updateGoods(entity);
        return "update ok";
    }

    @PostMapping("/goods/doSaveGoods")
    public String doSaveGoods(@RequestBody Goods entity){
        goodsService.saveGoods(entity);
        return "save ok";
    }

    @RequestMapping("goods/doDeleteById/{id}")
    public String doDeleteById(@PathVariable("id") Integer id){
        goodsService.deleteById(id);
        return "delete ok";

    }

    /**查询所有商品信息*/
    @GetMapping("/goods/doFindGoods")
    public List<Goods> doFindGoods(){
        return goodsService.findGoods();
    }
}
