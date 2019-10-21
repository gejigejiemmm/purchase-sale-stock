package cn.edu.zzuli.purchasesalestock.controller;


import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.bean.ShoppingCart;
import cn.edu.zzuli.purchasesalestock.bean.ShoppingCart_detail;
import cn.edu.zzuli.purchasesalestock.service.impl.ShoppingCartDetailService;
import cn.edu.zzuli.purchasesalestock.service.impl.ShoppingCartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;

@RequestMapping("/shoppingCart")
@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private ShoppingCartDetailService detailService;

    /*
     *  time:2019/10/21 11:09
     *  author:肖明珂
     *  target:实现用户购物车的创建
     *  status:测试已经没有问题
     *  description：用户注册完成以后，用户购物车自动创建，使用用户作为连接，no用作流水单号，用于单个物品与购物车连接的纽带
     */
    @RequestMapping("/addCart")
    @ApiOperation(value = "初始化创建购物车",httpMethod = "POST")
    public Msg addShoppingCart(@RequestParam(value = "uid", required = false) Integer uid){
        ShoppingCart cart = new ShoppingCart();
        cart.setShoppingCartPersonId(uid);
        cart.setShoppingCartNo(uid+100);
        cart.setShoppingCartTotlePrice(0);
        cart.setShoppingCartDate(LocalDateTime.now());
        boolean result = cartService.saveShoppnigCart(cart);
        if(result){
            return Msg.success();
        }
        else{
            return Msg.fail().add("error", "服务器错误，请联系系统管理员");
        }
    }


    @RequestMapping("/getallitems")
    public Msg getallitems(@RequestParam(value = "no", required = true) Integer id){
        Collection<ShoppingCart_detail> result = detailService.getAll(id);
        if(result==null){
            return Msg.success().add("error", "暂无数据");
        }
        else{
            return Msg.success().add("items", result);
        }
    }


}
