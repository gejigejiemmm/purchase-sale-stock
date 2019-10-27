package cn.edu.zzuli.purchasesalestock.bean;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseItem {
    private Integer itemId;
    private Integer itemPurchaseId;
    private Integer itemOrderId;
    private Integer itemGoodsId;
    private List<Goods> goods;
    public PurchaseItem(){}
    public PurchaseItem(Integer itemId, Integer itemPurchaseId, Integer itemOrderId, Integer itemGoodsId) {
        this.itemId = itemId;
        this.itemPurchaseId = itemPurchaseId;
        this.itemOrderId = itemOrderId;
        this.itemGoodsId = itemGoodsId;
    }
}
