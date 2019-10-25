package cn.edu.zzuli.purchasesalestock.service;

import cn.edu.zzuli.purchasesalestock.bean.GoodsAllocation;
import cn.edu.zzuli.purchasesalestock.bean.GoodsAllocationDetail;

import java.time.LocalDateTime;
import java.util.List;

public interface GoodsAllocationService {
    List<GoodsAllocation> getGoodsAllocations(Integer allocationFromId,Integer allocationStatus, Integer allocationToId,Integer allocationEId,
                                              LocalDateTime allocationCreateTime,LocalDateTime allocationEndTime);

    GoodsAllocationDetail getDetail(Integer allocationId);

    boolean addAllicationsAndDetail(GoodsAllocation goodsAllocation, Integer allocationType, String stallocationDesc);
}
