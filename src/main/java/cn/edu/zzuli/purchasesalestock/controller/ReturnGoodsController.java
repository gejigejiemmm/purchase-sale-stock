package cn.edu.zzuli.purchasesalestock.controller;


import cn.edu.zzuli.purchasesalestock.Mapper.OrderMapper;
import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.bean.OrderDetail;
import cn.edu.zzuli.purchasesalestock.bean.ReturnGoods;
import cn.edu.zzuli.purchasesalestock.service.impl.OrderServiceImpl;
import cn.edu.zzuli.purchasesalestock.service.impl.ReturnGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/returngoods")
@Api(tags = "退货单")
public class ReturnGoodsController {

    @Autowired
    private ReturnGoodsService returnGoodsService;

    @Autowired
    private OrderMapper orderMapper;


    /*
     *  time:2019/10/25 15:54
     *  author:肖明珂
     *  target:完成退货单的生成
     *  status:测试
     *  description：对于订单配送前后进行退货操作，按照订单号进行退货，前台传进来订单号
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "一个退货单的生成",httpMethod = "POST")
    public Msg createReturnGoods(@RequestParam(value = "oid", required = true) Integer order_id){
        //获取到当前订单实体类
        OrderDetail orderDetail = orderMapper.getDetail(order_id);
        //使用mapper里面的方法改变要退货的订单的状态
        Map<String, Object> update = new HashMap<String, Object>();
        update.put("orderStatus", 250);
        update.put("orderId", order_id);
        boolean result = orderMapper.updateOrder(update);
        //开始封装要生成的退货单实体类
        ReturnGoods returnGoods = new ReturnGoods();
        returnGoods.setReturnGoodsData(LocalDateTime.now());
        returnGoods.setReturnGoodsFee(orderDetail.getOrder().getOrderPrices());
        returnGoods.setReturnGoodsOrderId(orderDetail.getOrderId());
        boolean result1 = returnGoodsService.addReturnGoods(returnGoods);
        if(result && result1){
            return Msg.success();
        }
        else{
            return Msg.fail().add("error", "系统繁忙，请稍后重试！");
        }
    }
}