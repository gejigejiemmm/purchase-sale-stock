package cn.edu.zzuli.purchasesalestock.Mapper;

import cn.edu.zzuli.purchasesalestock.bean.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    List<Order> getALlOrders(Map<String, Object> info);
}
