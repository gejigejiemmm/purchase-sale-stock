package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.Mapper.GoodsAllocationMapper;
import cn.edu.zzuli.purchasesalestock.bean.GoodsAllocation;
import cn.edu.zzuli.purchasesalestock.service.GoodsAllocationService;
import cn.edu.zzuli.purchasesalestock.utils.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsAllocationServiceImpl implements GoodsAllocationService {

    @Autowired
    GoodsAllocationMapper allocationMapper;

    @Override
    public List<GoodsAllocation> getGoodsAllocations(Integer allocationFromId, Integer allocationStatus, Integer allocationToId,
                                                     LocalDateTime allocationCreateTime, LocalDateTime allocationEndTime) {
        Map<String,Object> info = new HashMap();
        BaseUtils.initInfo(info,"allocationFromId",allocationFromId,"allocationStatus",allocationStatus,
        "allocationToId",allocationToId,"allocationCreateTime",allocationCreateTime,"allocationEndTime",allocationEndTime);

        System.out.println(info);

        return allocationMapper.getAllocations(info);
    }
}
