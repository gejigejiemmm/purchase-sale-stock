package cn.edu.zzuli.purchasesalestock.Mapper;

import cn.edu.zzuli.purchasesalestock.bean.GoodsAllocation;
import cn.edu.zzuli.purchasesalestock.bean.GoodsAllocationDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsAllocationMapper {
    List<GoodsAllocation> getAllocations(Map<String,Object> info);

    GoodsAllocationDetail getDetail(Integer allocationId);

    boolean addAllocation(GoodsAllocation goodsAllocation);

    boolean addDetail(GoodsAllocationDetail detail);

    @Update("UPDATE goods_allocation g SET g.allocation_status = #{status} " +
            "WHERE g.allocation_fromId = #{binId} AND g.allocation_id = #{allocationId}")
    boolean updateGoodsAllocationStatus(Integer binId, Integer allocationId,Integer status);
}
