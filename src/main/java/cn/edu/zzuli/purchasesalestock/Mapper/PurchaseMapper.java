package cn.edu.zzuli.purchasesalestock.Mapper;

import cn.edu.zzuli.purchasesalestock.bean.Purchase;
import cn.edu.zzuli.purchasesalestock.bean.PurchaseDetail;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface PurchaseMapper {
    boolean addPurchase(Purchase purchase);

    boolean addPurchaseDetail(Integer purchaseId, LocalDateTime purchaseCreateTime,
                              LocalDateTime  purchaseEndTime,String purchaseSupplierI);

//得到采购详单信息和 补货的商品信息
    List getInformation(Integer purchaseId);
}
