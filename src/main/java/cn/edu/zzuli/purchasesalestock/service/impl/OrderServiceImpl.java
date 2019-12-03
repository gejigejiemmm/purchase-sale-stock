package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.Mapper.AddressMapper;
import cn.edu.zzuli.purchasesalestock.Mapper.BinMapper;
import cn.edu.zzuli.purchasesalestock.Mapper.OrderMapper;
import cn.edu.zzuli.purchasesalestock.Mapper.ShoppingCartDetailMapper;
import cn.edu.zzuli.purchasesalestock.bean.*;
import cn.edu.zzuli.purchasesalestock.service.OrderService;
import cn.edu.zzuli.purchasesalestock.utils.BaseUtils;
import cn.edu.zzuli.purchasesalestock.utils.OrderType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    BinMapper binMapper;

    @Autowired
    ShoppingCartDetailMapper shoppingCartDetailMapper;

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

            //减少用户存款 ---没必要？毕竟单点？ 考虑一下

            //根据 该用户收款位置 所对应的仓库  鸽子halo
            //去对应的仓库减库存，修改订单的所属仓库  这应该放在配货的时候修改！！！！！！！

            //初始化 商品详情信息，考虑面向的用户更多的是通过购物车方式下单
            //这里直接把信息封装到 ShoppingCart_detail 实体类里边  这里虽然麻烦了。方便了购物车下单
            ShoppingCart_detail item = new ShoppingCart_detail();
            item.setShoppingcart_dgoodsId(goodsId);
            item.setShoppingcart_dgoodsNumber(goodsCounts);
            List<ShoppingCart_detail> list =  new ArrayList();
            list.add(item);

            //如果是老师代付，去修改老师的数据 （写完登陆接口再补） 鸽子halo
            //添加订单和订单详情  和 商品详情表
            if(orderMapper.addOrderDetail(detail) &&
                    orderMapper.addOrderItems( list,detail.getOrderId())) {
                //记得清空购物车，等接口 鸽子halo
                return true;
            }
        }
        return false;
    }

    /**
     * 通过购物车的方式下单
     * 这是个批量下单
     * 有若干商品，每个商品可以有若干个
     *
     * 逻辑同上面的 addOrdersAndDetail
     * @return
     */
    @Transactional
    public boolean addFromShoppingcart(Order order,Integer orderType,String orderIphone,
                                       String orderCuslocation,Integer goodsId, Integer goodsCounts) {
        if(order != null && orderType != null) {
            OrderDetail detail = new OrderDetail();
            if (!(initOrderInfo(order, orderType, orderIphone, orderCuslocation, detail))) {
                return false;
            }

            //从用户购物车里获取所有商品信息
            //购物车 no 并不是 购物车的id 而是 用户id + 100
            List<ShoppingCart_detail> list = shoppingCartDetailMapper.getAll(order.getOrderUId()+100);
            System.out.println(list);
            //添加订单和订单详情  和 商品详情表
            if(orderMapper.addOrderDetail(detail) &&
                    orderMapper.addOrderItems( list,detail.getOrderId())) {
                return true;
            }

        }
        return false;
    }



    /**
     * 初始化订单信息
     *
     * 建议 一个方法 最多不要超过15行，超过最后提取出来
     * 能复用的代码也可以提取出来
     */
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

    /**
     * 获取当前用户默认的首地址
     *
     * 可能（这个区域，虽然按道理来讲， 他们现在的规模就一个，但他们说要扩大规模，所以。。。我还是用list了）
     * 并不只是一个仓库
     * @return
     */
    public List<Bin> getAddress(HttpSession session) {
        //获取当前用户的状态
        Customer customer = (Customer)session.getAttribute("customer");
        if (customer == null){
            return  null;
        }

        //获取地址
        List<Bin> bins = binMapper.getAddressById(1);
        System.out.println(bins);
        return bins;
    }
}
