package cn.edu.zzuli.purchasesalestock.controller;


import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.bean.ShoppingCart;
import cn.edu.zzuli.purchasesalestock.bean.ShoppingCart_detail;
import cn.edu.zzuli.purchasesalestock.service.impl.ShoppingCartDetailService;
import cn.edu.zzuli.purchasesalestock.service.impl.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@RequestMapping("/shoppingCart")
@RestController
@Api(tags = "购物车")
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
    @RequestMapping(value = "/addCart",method = RequestMethod.POST)
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

    /*
     *  time:2019/10/21 19:43
     *  author:肖明珂
     *  target:实现用户购物车中条目信息的查看（所有）
     *  status:测试已经没有问题
     *  description：用户可以查看购物车中所有的商品（需要传入购物车对应的no也可以是用户id+100二者等价）
     */
    @RequestMapping(value = "/getallitems",method = RequestMethod.GET)
    @ApiOperation(value = "查询用户购物车数据",httpMethod = "GET")
    public Msg getallitems(@RequestParam(value = "no", required = true) Integer id){
        List<ShoppingCart_detail> result = detailService.getAll(id);
        if(result==null){
            return Msg.success().add("error", "暂无数据");
        }
        else{
            return Msg.success().add("items", result);
        }
    }

    @RequestMapping(value = "/additem",method = RequestMethod.POST)
    @ApiOperation(value = "在购物车中添加一条记录",httpMethod = "POST")
    public Msg additem(@RequestParam(value = "number", required = true) Integer shoppingcart_dgoodsNumber,
                       @RequestParam(value = "gid", required = true) Integer shoppingcart_dgoodsId,
                       @RequestParam(value = "totlePrice", required = true) Integer shoppingcart_dtotlePrice,
                       @RequestParam(value = "uid", required = true) Integer customer_id){
        ShoppingCart_detail detail = new ShoppingCart_detail();
        detail.setShoppingcart_dgoodsDate(LocalDateTime.now());
        detail.setShoppingcart_dgoodsNumber(shoppingcart_dgoodsNumber);
        detail.setShoppingcart_dno(customer_id+100);
        detail.setShoppingcart_dgoodsId(shoppingcart_dgoodsId);
        detail.setShoppingcart_dtotlePrice(shoppingcart_dtotlePrice);
        boolean result = detailService.additem(detail);
        if(result){
            return Msg.success();
        }
        else{
            return Msg.fail().add("error", "服务器错误，请联系系统管理员");
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ApiOperation(value = "删除购物车中的某一条记录",httpMethod = "POST")
    public Msg deleteitem(@RequestParam(value = "did", required = true) Integer did){
        boolean result = detailService.deleteitem(did);
        if(result){
            return Msg.success();
        }
        else{
            return Msg.fail().add("error", "服务器错误，请联系系统管理员");
        }
    }

    @RequestMapping(value = "/decrease",method = RequestMethod.POST)
    @ApiOperation(value = "购物车中的某一条记录的购买数量减一",httpMethod = "POST")
    public Msg decreaseProduce(@RequestParam(value = "detailid", required = true) Integer id){
        ShoppingCart_detail detail = detailService.getOneById(id);
        if((detail!=null)&&(detail.getShoppingcart_dgoodsNumber()>0)){
            detail.setShoppingcart_dgoodsNumber(detail.getShoppingcart_dgoodsNumber()-1);
            boolean result = detailService.updateDetailNumber(detail);
            if(result){
                return Msg.success();
            }
            else{
                return Msg.fail().add("error", "系统繁忙，请稍后重试！");
            }
        }else{
            return Msg.fail().add("error", "商品数量为零，不可减少！");
        }

    }


    @RequestMapping(value = "/increase",method = RequestMethod.POST)
    @ApiOperation(value = "购物车中的某一条记录的购买数量加一",httpMethod = "POST")
    public Msg increaseProduce(@RequestParam(value = "detailid", required = true) Integer id){
        ShoppingCart_detail detail = detailService.getOneById(id);
        if(detail!=null){
            detail.setShoppingcart_dgoodsNumber(detail.getShoppingcart_dgoodsNumber()+1);
            boolean result = detailService.updateDetailNumber(detail);
            if(result){
                return Msg.success();
            }
            else{
                return Msg.fail().add("error", "系统繁忙，请稍后重试！");
            }
        }else{
            return Msg.fail().add("error", "系统繁忙，请稍后重试！");
        }

    }


    /*
     *  time:2019/10/24 9:00
     *  author:肖明珂
     *  target:对应用复选框选中商品的删除
     *  status:测试
     *  description：前段需要传回选中商品的id（多个用%链接）
     */
    @RequestMapping(value = "/deleteAll",method = RequestMethod.POST)
    @ApiOperation(value = "对应用复选框选中商品的删除",httpMethod = "POST")
    private Msg deleteAll(String args){
        String[] arg = args.split("%");
        LinkedList<Integer> idList = new LinkedList<Integer>();
        for(String str : arg){
            Integer item = Integer.parseInt(str);
            idList.add(item);
        }
        boolean result = detailService.deleteAll(idList);
        if(result){
            return Msg.success();
        }
        else{
            return Msg.fail().add("error", "系统繁忙，请稍后重试！");
        }
    }




}
