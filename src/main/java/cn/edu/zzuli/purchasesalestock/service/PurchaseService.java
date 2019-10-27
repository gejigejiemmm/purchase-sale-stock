package cn.edu.zzuli.purchasesalestock.service;

import cn.edu.zzuli.purchasesalestock.bean.Purchase;
import cn.edu.zzuli.purchasesalestock.bean.PurchaseDetail;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseService {
    boolean addPurchase(Purchase purchase);

    boolean addPurchaseDetail(Integer purchaseId, LocalDateTime purchaseCreateTime,
                              LocalDateTime  purchaseEndTime,String purchaseSupplierI);

    boolean addPruchaseAndDetail(Purchase purchase,Integer purchaseId, LocalDateTime purchaseCreateTime,
                                 LocalDateTime  purchaseEndTime,String purchaseSupplierI);
    List getInformation(Integer purchaseId);
}
