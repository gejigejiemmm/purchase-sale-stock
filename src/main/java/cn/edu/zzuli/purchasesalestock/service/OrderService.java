package cn.edu.zzuli.purchasesalestock.service;

import cn.edu.zzuli.purchasesalestock.bean.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    List<Order> getAllOrders(Integer orderUId, Integer orderBinId, Integer orderId,
                             Integer orderStatus,LocalDateTime orderCreateTime, LocalDateTime orderEndTime);
}
