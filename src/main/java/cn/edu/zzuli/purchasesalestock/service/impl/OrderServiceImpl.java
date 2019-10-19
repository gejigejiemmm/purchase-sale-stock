package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.Mapper.OrderMapper;
import cn.edu.zzuli.purchasesalestock.bean.Order;
import cn.edu.zzuli.purchasesalestock.service.OrderService;
import cn.edu.zzuli.purchasesalestock.utils.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Order> getAllOrders(Integer orderUId, Integer orderBinId, Integer orderId,
                                    Integer orderStatus, LocalDateTime orderCreateTime, LocalDateTime orderEndTime) {
        Map<String,Object> info = new HashMap<>();
        //将需要查找条件信息放到 map 集合里
        BaseUtils.initInfo(info,"orderUId",orderUId,"orderBinId",orderBinId,"orderId",orderId,
                "orderStatus",orderStatus,"orderCreateTime",orderCreateTime,"orderEndTime",orderEndTime);
        return orderMapper.getALlOrders(info);
    }
}
