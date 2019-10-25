package cn.edu.zzuli.purchasesalestock.Mapper;

import cn.edu.zzuli.purchasesalestock.bean.GoodsAllocation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsAllocationMapper {
    List<GoodsAllocation> getAllocations(Map<String,Object> info);
}
