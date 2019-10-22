package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.Mapper.OrderMapper;
import cn.edu.zzuli.purchasesalestock.bean.*;
import cn.edu.zzuli.purchasesalestock.service.OrderService;
import cn.edu.zzuli.purchasesalestock.utils.BaseUtils;
import cn.edu.zzuli.purchasesalestock.utils.OrderType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    /**
     * 条件查询订单 根据 参数查询
     * 参数不为 null 符合条件
     * 全为 null 查询所有
     * @return
     */
    @Override
    public PageInfo getAllOrders(Integer p,Integer orderUId, Integer orderBinId, Integer orderId,
                                    Integer orderStatus, LocalDateTime orderCreateTime, LocalDateTime orderEndTime) {
        Map<String,Object> info = new HashMap<>();
        //开始分页查询，一页有多个
        PageHelper.startPage(p,8);
        //将需要查找条件信息放到 map 集合里
        BaseUtils.initInfo(info,"orderUId",orderUId,"orderBinId",orderBinId,"orderId",orderId,
                "orderStatus",orderStatus,"orderCreateTime",orderCreateTime,"orderEndTime",orderEndTime);
        List<Order> orders = orderMapper.getALlOrders(info);
        //分页信息
        PageInfo pageInfo = new PageInfo(orders);
        return pageInfo;
    }

    //更新
    public boolean updateOrder(Order order) {
        Map<String,Object> info = new HashMap<>();
        BaseUtils.initInfo(info,"orderId",order.getOrderId(),"orderStatus",order.getOrderStatus(),
                "orderPrices",order.getOrderPrices(),"orderEndTime",order.getOrderEndTime());
        if(orderMapper.updateOrder(info))
            return true;
        return false;
    }

    /**
     * 根据传入 ID  获取 一个订单详情信息
     * @param orderId
     * @return
     */
    public OrderDetail getDetail(Integer orderId) {
        OrderDetail detail = orderMapper.getDetail(orderId);
        if(detail != null){
            return detail;
        }
        return null;
    }

    /**
     * 下单（用户单点下单，只接受一个商品,商品可以是多数量）
     * 创建的订单的时候
     * 还要创建订单详情信息
     * 所以 插入两张表的数据，保持一致性
     * 甚至还要有商品详情表
     * @param order
     * @return
     */
    @Transactional
    public boolean addOrdersAndDetail(Order order,Integer orderType,String orderIphone,
                                      String orderCuslocation,Integer goodsId, Integer goodsCounts) {
        if(order != null && orderType != null && goodsId != null) {
            OrderDetail detail = new OrderDetail();
            if(!(initOrderInfo(order,orderType,orderIphone,orderCuslocation,detail))){
                return false;
            }
            //获取商品 id
            //根据 该用户收款位置 所对应的仓库
            //去对应的仓库减库存，修改订单的所属仓库  这三行应该放在配货的时候修改！！！！！！！

            //初始化 商品详情信息，考虑面向的用户更多的是通过购物车方式下单
            //这里直接把信息封装到 ShoppingCart_detail 实体类里边
            ShoppingCart_detail item = new ShoppingCart_detail();
            item.setShoppingcart_dgoodsId(goodsId);
            item.setShoppingcart_dgoodsNumber(goodsCounts);
            List<ShoppingCart_detail> list =  new ArrayList();
            list.add(item);

            //添加订单和订单详情  和 商品详情表
            if(orderMapper.addOrderDetail(detail) &&
                    orderMapper.addOrderItems( list,detail.getOrderId())) {
                return true;
            }
        }
        return false;
    }


    public boolean initOrderInfo(Order order,Integer orderType,String orderIphone,
                        String orderCuslocation,OrderDetail detail ){
        //因为这个时候 订单还没开始  配货所以甚至订单状态为 BEFORE  (订单状态)
        order.setOrderStatus(OrderType.BEFORE.getStatus());
        //先添加订单，以便获取订单id
        if(!orderMapper.addOrder(order)) {
            return false;
        }
        //初始化订单详情信息
        detail.setOrderId(order.getOrderId());
        detail.setOrder(order);
        detail.setOrderIphone(orderIphone);
        detail.setOrderCuslocation(orderCuslocation);
        //此 orderType 为结算方式
        detail.setOrderType(orderType);
        return true;
    }
}
