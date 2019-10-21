package cn.edu.zzuli.purchasesalestock.controller;

import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.bean.Order;
import cn.edu.zzuli.purchasesalestock.service.OrderService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/order")
@Api(tags = "订单接口")
public class OrderController {

    @Autowired
    OrderService orderService;

    //这也太恶心了吧啧啧
    //可以使用 Order 直接接收的，但是觉得没这清楚，凑合凑合
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ApiOperation(value = "条件获取订单信息，默认为查询所有，所有参数都非必须",httpMethod = "GET")
    public Msg getOrders(@RequestParam(value = "customerId",required = false,defaultValue = "1") Integer p,
                         @RequestParam(value = "customerId",required = false) Integer orderUId,
                         @RequestParam(value = "orderBinId",required = false)Integer orderBinId,
                         @RequestParam(value = "orderId",required = false)Integer orderId,
                         @RequestParam(value = "orderStatus",required = false) Integer orderStatus,
                         @RequestParam(value = "orderCreateTime", required = false)
                             @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime orderCreateTime,
                         @RequestParam(value = "orderEndTime", required = false)
                             @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime orderEndTime){

        PageInfo orders = orderService.getAllOrders(p,orderUId,orderBinId,orderId,orderStatus,orderCreateTime,orderEndTime);
        if(orders != null)
            return Msg.success().add("orders",orders);
        return Msg.fail();
    }

    @PostMapping("/add")
    public Msg addOrders(Order order) {
        //System.out.println(order);
        return Msg.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "条件修改订单信息",httpMethod = "POST")
    public Msg update(Order order) {
        //判断 用户id是否传入
        if(order.getOrderId() != null){
            orderService.updateOrder(order);
            return Msg.success();
        }

        return Msg.fail();
    }



}