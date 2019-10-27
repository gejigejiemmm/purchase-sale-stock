package cn.edu.zzuli.purchasesalestock.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class PurchaseDetail {
    private Integer pdetailId;
    private Integer purchaseId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime purchaseCreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime purchaseEndTime;
    private String purchaseSupplierI;
    public PurchaseDetail(){}

    public PurchaseDetail(Integer pdetailId, Integer purchaseId, LocalDateTime purchaseCreateTime,
                          LocalDateTime purchaseEndTime, String purchaseSupplierI) {
        this.pdetailId = pdetailId;
        this.purchaseId = purchaseId;
        this.purchaseCreateTime = purchaseCreateTime;
        this.purchaseEndTime = purchaseEndTime;
        this.purchaseSupplierI = purchaseSupplierI;
    }
}
