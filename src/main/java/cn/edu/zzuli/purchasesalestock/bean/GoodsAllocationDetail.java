package cn.edu.zzuli.purchasesalestock.bean;

import lombok.Data;

@Data
public class GoodsAllocationDetail {
    private Integer adetailId;
    private Integer allocationId;
    private Integer allocationType;
    private String allocationDesc;

    //所对应的 商品挑拨表实体类
    private GoodsAllocation goodsAllocation;

    public GoodsAllocationDetail() {
    }

    public GoodsAllocationDetail(Integer adetailId, Integer allocationId, Integer allocationType, String allocationDesc) {
        this.adetailId = adetailId;
        this.allocationId = allocationId;
        this.allocationType = allocationType;
        this.allocationDesc = allocationDesc;
    }
}
