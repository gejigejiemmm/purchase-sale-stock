package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.Mapper.OrderMapper;
import cn.edu.zzuli.purchasesalestock.bean.Order;
import cn.edu.zzuli.purchasesalestock.service.OrderService;
import cn.edu.zzuli.purchasesalestock.utils.BaseUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public boolean updateOrder(Order order) {
        Map<String,Object> info = new HashMap<>();
        BaseUtils.initInfo(info,"orderId",order.getOrderId(),"orderStatus",order.getOrderStatus(),
                "orderPrices",order.getOrderPrices(),"orderEndTime",order.getOrderEndTime());
        if(orderMapper.updateOrder(info))
            return true;
        return false;
    }
}
