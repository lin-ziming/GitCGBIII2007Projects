package com.cy.pj.goods.controller;

import com.cy.pj.goods.pojo.Goods;
import com.cy.pj.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods/")
public class GoodsController {
    @Autowired//has a
    private GoodsService goodsService;

    @GetMapping("doFindById/{id}")
    public String doFindById(@PathVariable Integer id,Model model){
        Goods goods=goodsService.findById(id);
        model.addAttribute("g", goods);
        return "goods-update";
    }

    @PostMapping("doUpdateGoods")
    public String doUpdateGoods(Goods entity){//用pojo对象接收客户端参数(参数名需要与entity对象中的相同)
        goodsService.updateGoods(entity);
        return "redirect:/goods/doGoodsUI";
    }

    @PostMapping("doSaveGoods")
    public String doSaveGoods(Goods entity){//用pojo对象接收客户端参数(参数名需要与entity对象中的相同)
        goodsService.saveGoods(entity);
        return "redirect:/goods/doGoodsUI";
    }

    /**返回商品添加页面*/
    //@RequestMapping(value="doGoodsAddUI",method=RequestMethod.GET)
    @GetMapping("doGoodsAddUI")
    public String doGoodsAddUI(){
        return "goods-add";
    }

    @RequestMapping("doDeleteById/{id}")//rest风格(软件架构编码风格)url
    public String doDeleteById(@PathVariable("id") Integer id){//@PathVariable描述参数时表示参数的值来自url
        goodsService.deleteById(id);
        return "redirect:/goods/doGoodsUI";//redirect 表示重定向(客户端再次向服务端发请求)
    }

    @RequestMapping("doGoodsUI")
    public String doGoodsUI(Model model){
        List<Goods> goodsList=goodsService.findGoods();
        model.addAttribute("goodsList", goodsList);
        return "goods";//viewname
        //返回值会交给DispatcherServlet进行处理
        //DispatcherServlet会调用ViewResolver进行视图解析（）
        //
    }
}
