package cn.edu.zzuli.purchasesalestock.bean;

import lombok.Data;

@Data
public class SendDetail {

    private Integer sendDetailId;
    private Integer orderId;
    private Integer sendId;

    private Send send;
//    private Order order;
    private Customer customer;

}
