package cn.edu.zzuli.purchasesalestock.bean;

import lombok.Data;

import java.util.List;

@Data
public class Purchase {
    private Integer purchaseId;
    private Integer purchaseOrderId;
    private Integer purchaseEId;
    private Double purchasePrice;
    private Integer purchaseCount;
    private Integer purchaseStatus;
    private PurchaseItem purchaseItem;
    public Purchase(){}

    public Purchase(Integer purchaseId, Integer purchaseOrderId, Integer purchaseEId,
                    Double purchasePrice, Integer purchaseCount, Integer purchaseStatus) {
        this.purchaseId = purchaseId;
        this.purchaseOrderId = purchaseOrderId;
        this.purchaseEId = purchaseEId;
        this.purchasePrice = purchasePrice;
        this.purchaseCount = purchaseCount;
        this.purchaseStatus = purchaseStatus;
    }
}
