package cn.edu.zzuli.purchasesalestock.bean;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ReturnGoods {

    private Integer returnGoodsId;
    private Integer returnGoodsNo;
    private LocalDateTime returnGoodsData;
    private double returnGoodsFee;
    private Integer returnGoodsOrderId;

    public ReturnGoods(Integer returnGoodsId, Integer returnGoodsNo, LocalDateTime returnGoodsData, double returnGoodsFee, Integer returnGoodsOrderId) {
        this.returnGoodsId = returnGoodsId;
        this.returnGoodsNo = returnGoodsNo;
        this.returnGoodsData = returnGoodsData;
        this.returnGoodsFee = returnGoodsFee;
        this.returnGoodsOrderId = returnGoodsOrderId;
    }

    public ReturnGoods() {
        super();
    }
}
