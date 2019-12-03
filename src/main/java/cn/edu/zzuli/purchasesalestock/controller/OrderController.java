package cn.edu.zzuli.purchasesalestock.controller;

import cn.edu.zzuli.purchasesalestock.bean.*;
import cn.edu.zzuli.purchasesalestock.service.OrderService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

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
    public Msg getOrders(@RequestParam(value = "p",required = false,defaultValue = "1") Integer p,
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
    @ApiOperation(value = "用户单点下单",httpMethod = "POST")
    public Msg addOrders(Order order,@RequestParam("orderType") Integer orderType,
                         String orderIphone, String orderCuslocation,Integer goodsId, Integer goodsCounts) {
        if(orderService.addOrdersAndDetail(order,orderType,orderIphone,orderCuslocation,goodsId,goodsCounts)) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @PostMapping("/update")
    @ApiOperation(value = "条件修改订单信息",httpMethod = "POST")
    public Msg update(Order order) {
        //判断 用户id是否传入
        if(order.getOrderId() != null){
            orderService.updateOrder(order);
            return
        Msg.success();
    }

        return Msg.fail();
}


    @GetMapping("/detail")
    @ApiOperation(value = "传入订单号获取订单详情",httpMethod = "GET")
    public Msg getDetail(Integer orderId){
        return Msg.success().add("detail",orderService.getDetail(orderId));
    }

    /**
     * 从购物车批量下单
     * 目前有些问题，现在的商品添加是直接拉去购物车获取的
     * 按道理来讲，应该是要获取 勾选的 商品的信息，这个看前端怎么传，
     * 鸽子
     * @return
     */
    @PostMapping("/addFromShoppingcart")
    @ApiOperation(value = "从购物车批量下单",httpMethod = "POST")
    public Msg addFromShoppingcart(Order order,@RequestParam("orderType") Integer orderType,
                                   String orderIphone, String orderCuslocation,Integer goodsId, Integer goodsCounts) {
        if(orderService.addFromShoppingcart(order,orderType,orderIphone,orderCuslocation,goodsId,goodsCounts)) {
            return Msg.success();
        }
        return Msg.fail();
    }


    /**
     * 目前这样想：
     * （根据ip获取地理位置失败，可以获取ip但不知道ip的所属位置，有第三方库，但是均不支持商用，商用要交钱。。。）
     *
     * 用户在点击商品页面的时候，前端发送 Ajax请求到这里来查询，用户的所属仓库
     *
     * 这里可以获取，用户信息设置的第一个收货信息，根据该信息获取最近仓库（去数据库查与之匹配的仓库）
     *
     * 如果收货位置不准确或没有匹配项，用户也可以自己选择离自己最近的发货位置（很low，但目前毫无办法确定最近仓库）
     *
     * @return
     */
    @GetMapping("/getUserAddress")
    @ApiOperation(value = "获取用户当前默认首地址对应的仓库和地址,返回200状态码让用户自己输入",httpMethod = "GET")
    public Msg getUserAddress(HttpSession session){
        //获取该用户最近（百分九十是默认的首地址）地址
        List<Bin> bins = orderService.getAddress(session);

        //这个时候，就让用户自己输入吧！
        if (bins == null || bins.size() == 0){
            return Msg.fail();
        }

        return Msg.success().add("bins",bins);
    }


}
