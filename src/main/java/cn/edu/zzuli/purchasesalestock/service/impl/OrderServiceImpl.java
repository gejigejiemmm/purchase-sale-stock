package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.Mapper.OrderMapper;
import cn.edu.zzuli.purchasesalestock.bean.Goods;
import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.bean.Order;
import cn.edu.zzuli.purchasesalestock.bean.OrderDetail;
import cn.edu.zzuli.purchasesalestock.service.OrderService;
import cn.edu.zzuli.purchasesalestock.utils.BaseUtils;
import cn.edu.zzuli.purchasesalestock.utils.OrderType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
     * @param p
     * @param orderUId
     * @param orderBinId
     * @param orderId
     * @param orderStatus
     * @param orderCreateTime
     * @param orderEndTime
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
     * @param oDetailId
     * @return
     */
    public OrderDetail getDetail(Integer oDetailId) {
        OrderDetail detail = orderMapper.getDetail(oDetailId);
        if(detail != null){
            return detail;
        }
        return null;
    }

    /**
     * 创建的订单的时候
     * 还要创建订单详情信息
     * 所以 插入两张表的数据，保持一致性
     * @param order
     * @return
     */
    @Transactional
    public boolean addOrdersAndDetail(Order order,Integer orderType) {
        if(order != null && orderType != null ) {
            //初始化订单详情信息
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setOrderType(orderType);
            //因为这个时候 订单还没开始  配货所以甚至订单状态为 BEFORE
            order.setOrderStatus(OrderType.BEFORE.getStatus());
            //商品减库存
            //添加订单和订单详情
            if(orderMapper.addOrder(detail)) {
                return true;
            }
        }
        return false;
    }
}
