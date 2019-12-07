package cn.edu.zzuli.purchasesalestock.Mapper;

import cn.edu.zzuli.purchasesalestock.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper{

    List<Order> getALlOrders(Map<String, Object> info);

    boolean updateOrder(Map<String, Object> info);

    boolean addOrder(Order order);

    boolean addOrderDetail(OrderDetail orderDetail);

    OrderDetail getDetail(Integer orderId);

    boolean addOrderItems(@Param("list") List<ShoppingCart_detail> goosDetails,@Param("orderId") Integer orderId);

    public boolean deleteOrderAndDetail(Integer order_id);


    //获取该订单的 商品信息
    public List<Goods> getOrdersItmesId(Integer orderId);
}
