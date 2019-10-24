package cn.edu.zzuli.purchasesalestock.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private Integer orderId;
    private Integer orderUId;
    private Integer orderBinId;
    private Double orderPrices;
    private Integer orderStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderCreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderEndTime;

    //订单对应的商品信息
    private List<Goods> goods;

    public Order() {
    }

    public Order(Integer orderId, Integer orderUId, Integer orderBinId, Double orderPrices, Integer orderStatus, LocalDateTime orderCreateTime, LocalDateTime orderEndTime) {
        this.orderId = orderId;
        this.orderUId = orderUId;
        this.orderBinId = orderBinId;
        this.orderPrices = orderPrices;
        this.orderStatus = orderStatus;
        this.orderCreateTime = orderCreateTime;
        this.orderEndTime = orderEndTime;
    }
}
