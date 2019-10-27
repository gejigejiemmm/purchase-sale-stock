package cn.edu.zzuli.purchasesalestock.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * halo
 * 我感觉这个销售单应该是一个汇总的表
 *
 * 所以数据库设计也是这样的，
 * 有唯一对应的 订单，配货单，有采货的话，就有采货单
 *
 * 可以在 配货单生成的时候
 * GoodsAllocation -》 生成
 *
 * 目前先这样理解
 */
@Data
public class Sale {
    private Integer saleId;
    private Integer saleType;
    private Integer orderId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saleCreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saleEndTime;

    //或许可以用来判断 交易成功 或者 退单了
    private Integer saleStatus;
    private Integer saleBinId;

    public Sale() {
    }

    public Sale(Integer saleId, Integer saleType, Integer orderId, LocalDateTime saleCreateTime, LocalDateTime saleEndTime, Integer saleStatus, Integer saleBinId) {
        this.saleId = saleId;
        this.saleType = saleType;
        this.orderId = orderId;
        this.saleCreateTime = saleCreateTime;
        this.saleEndTime = saleEndTime;
        this.saleStatus = saleStatus;
        this.saleBinId = saleBinId;
    }
}
