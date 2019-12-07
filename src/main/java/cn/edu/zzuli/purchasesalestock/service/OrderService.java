package cn.edu.zzuli.purchasesalestock.service;

import cn.edu.zzuli.purchasesalestock.bean.*;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    /**
     * 获取所有订单信息
     */
    PageInfo getAllOrders(Integer p,Integer orderUId, Integer orderBinId, Integer orderId,
                          Integer orderStatus, LocalDateTime orderCreateTime, LocalDateTime orderEndTime);

    boolean updateOrder(Order order);

    OrderDetail getDetail(Integer oDetailId);

    boolean addOrdersAndDetail(Order order,Integer orderType,String orderIphone,
                               String orderCuslocation,Integer goodsId, Integer goodsCounts,
                               Integer customerId,Integer teaId);

    boolean addFromShoppingcart(Order order,Integer orderType,String orderIphone,
                                String orderCuslocation,Integer goodsId, Integer goodsCounts,
                                Integer customerId,Integer teaId);

    List<Bin>  getAddress(HttpSession session);
}
