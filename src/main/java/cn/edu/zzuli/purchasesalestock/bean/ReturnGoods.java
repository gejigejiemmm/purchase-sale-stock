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

    public Integer getReturnGoodsId() {
        return returnGoodsId;
    }

    public void setReturnGoodsId(Integer returnGoodsId) {
        this.returnGoodsId = returnGoodsId;
    }

    public Integer getReturnGoodsNo() {
        return returnGoodsNo;
    }

    public void setReturnGoodsNo(Integer returnGoodsNo) {
        this.returnGoodsNo = returnGoodsNo;
    }

    public LocalDateTime getReturnGoodsData() {
        return returnGoodsData;
    }

    public void setReturnGoodsData(LocalDateTime returnGoodsData) {
        this.returnGoodsData = returnGoodsData;
    }

    public double getReturnGoodsFee() {
        return returnGoodsFee;
    }

    public void setReturnGoodsFee(double returnGoodsFee) {
        this.returnGoodsFee = returnGoodsFee;
    }

    public Integer getReturnGoodsOrderId() {
        return returnGoodsOrderId;
    }

    public void setReturnGoodsOrderId(Integer returnGoodsOrderId) {
        this.returnGoodsOrderId = returnGoodsOrderId;
    }

    public ReturnGoods() {
        super();
    }
}
