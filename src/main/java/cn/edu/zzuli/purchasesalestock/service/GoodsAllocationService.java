package cn.edu.zzuli.purchasesalestock.service;

import cn.edu.zzuli.purchasesalestock.bean.GoodsAllocation;

import java.time.LocalDateTime;
import java.util.List;

public interface GoodsAllocationService {
    List<GoodsAllocation> getGoodsAllocations(Integer allocationFromId,Integer allocationStatus, Integer allocationToId,
                                              LocalDateTime allocationCreateTime,LocalDateTime allocationEndTime);
}
