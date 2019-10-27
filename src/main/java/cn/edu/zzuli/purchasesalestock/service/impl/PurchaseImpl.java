package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.Mapper.PurchaseMapper;
import cn.edu.zzuli.purchasesalestock.bean.Purchase;
import cn.edu.zzuli.purchasesalestock.bean.PurchaseDetail;
import cn.edu.zzuli.purchasesalestock.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseImpl implements PurchaseService {
    @Autowired
    PurchaseMapper purchaseMapper;
    @Override
    public boolean addPurchase(Purchase purchase) {
        if (purchaseMapper.addPurchase(purchase))
            return true;
        return false;
    }

    @Override
    public boolean addPurchaseDetail(Integer purchaseId, LocalDateTime purchaseCreateTime,
                                     LocalDateTime  purchaseEndTime,String purchaseSupplierI) {
        if (purchaseMapper.addPurchaseDetail(purchaseId,purchaseCreateTime,purchaseEndTime,purchaseSupplierI))
            return true;
        return false;
    }

    @Override
    @Transactional
    public boolean addPruchaseAndDetail(Purchase purchase,Integer purchaseId, LocalDateTime purchaseCreateTime,
                                        LocalDateTime  purchaseEndTime,String purchaseSupplierI) {
        if (purchase!=null&&purchaseId!=null){
            if (purchaseMapper.addPurchase(purchase)&&
                purchaseMapper.addPurchaseDetail(purchaseId,purchaseCreateTime,
                        purchaseEndTime,purchaseSupplierI)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List getInformation(Integer purchaseId) {
        List list = purchaseMapper.getInformation(purchaseId);
        if (list!=null){
            return list;
        }return null;
    }
}
