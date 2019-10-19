package cn.edu.zzuli.purchasesalestock.controller;

import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.bean.Order;
import cn.edu.zzuli.purchasesalestock.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/order")
@Api(tags = "订单接口")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Msg getOrders(@RequestParam(value = "customerId",required = false) Integer orderUId,
                         @RequestParam(value = "orderBinId",required = false)Integer orderBinId,
                         @RequestParam(value = "orderId",required = false)Integer orderId,
                         @RequestParam(value = "orderStatus",required = false) Integer orderStatus,
                         @RequestParam(value = "orderCreateTime", required = false)
                             @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime orderCreateTime,
                         @RequestParam(value = "orderEndTime", required = false)
                             @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime orderEndTime){
        List<Order> orders = orderService.getAllOrders(orderUId,orderBinId,orderId,orderStatus,orderCreateTime,orderEndTime);
        if(orders != null)
            return Msg.success().add("orders",orders);
        return Msg.fail();
    }

    @PostMapping("/add")
    public Msg addOrders(Order order){
        //System.out.println(order);
        return Msg.success();
    }
}
