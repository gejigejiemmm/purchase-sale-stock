package cn.edu.zzuli.purchasesalestock.controller;


import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.bean.ShoppingCart_detail;
import cn.edu.zzuli.purchasesalestock.service.impl.ShoppingCartDetailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/cartDetail")
public class ShoppingCartDetailController {

    @Autowired
    private ShoppingCartDetailService detailService;


    @RequestMapping("/additem")
    @ApiOperation(value = "在购物车中添加一条记录",httpMethod = "POST")
    public Msg additem(ShoppingCart_detail detail){
        detail.setShoppingcart_dgoodsDate(LocalDateTime.now());
        boolean result = detailService.additem(detail);
        if(result){
            return Msg.success();
        }
        else{
            return Msg.fail().add("error", "服务器错误，请联系系统管理员");
        }
    }

    @RequestMapping("delete")
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
}
