package cn.edu.zzuli.purchasesalestock.bean;

import lombok.Data;

@Data
public class OrderDetail{

    private Integer oDetailId;
    //对应 的订单 id
    private Integer orderId;
    //对应的 订单 实体
    private Order order;
    //订单对应的 派送单号
    private Integer sendId;
    //一个 派送单类 ...

    private Integer orderType;
    private String orderDesc;

    private String orderIphone;
    private String orderCuslocation;

    public OrderDetail(){}

    public OrderDetail(Integer oDetailId, Integer orderId, Order order, Integer sendId, Integer orderType, String orderDesc, String orderIphone, String orderCuslocation) {
        this.oDetailId = oDetailId;
        this.orderId = orderId;
        this.order = order;
        this.sendId = sendId;
        this.orderType = orderType;
        this.orderDesc = orderDesc;
        this.orderIphone = orderIphone;
        this.orderCuslocation = orderCuslocation;
    }
}
