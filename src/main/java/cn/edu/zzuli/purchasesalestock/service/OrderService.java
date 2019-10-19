package cn.edu.zzuli.purchasesalestock.service;

import cn.edu.zzuli.purchasesalestock.bean.Order;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    /**
     * 获取所有订单信息
     */
    PageInfo getAllOrders(Integer p,Integer orderUId, Integer orderBinId, Integer orderId,
                          Integer orderStatus, LocalDateTime orderCreateTime, LocalDateTime orderEndTime);

    boolean updateOrder(Order order);
}
