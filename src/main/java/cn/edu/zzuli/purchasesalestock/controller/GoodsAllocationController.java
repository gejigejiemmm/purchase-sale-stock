package cn.edu.zzuli.purchasesalestock.controller;

import cn.edu.zzuli.purchasesalestock.bean.GoodsAllocation;
import cn.edu.zzuli.purchasesalestock.bean.GoodsAllocationDetail;
import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.service.GoodsAllocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/allocation")
@Api(tags = "仓库商品调货接口")
public class GoodsAllocationController {

    @Autowired
    GoodsAllocationService allocationService;

    /**
     * 这个接口是针对 仓库管理员的和配货员的。
     * 这些商品调拨信息 仓库管理员都要看到
     * （包括调拨的商品信息）
     *
     * 配货员要查看自己的任务单，这个时候传入 配货员id就可以
     * 默认为 出库单，也就是配货单之类
     * 传入allocationToId则为入库单，也就是采购单之类的
     * @param allocationFromId 仓库id
     * @return allocations
     */
    @GetMapping("/get")
    @ApiOperation(value = "条件查询商品调拨信息，binId必传，默认为出库单，传入allocationToId则为入库单",httpMethod = "GET")
    public Msg getAllocations(@RequestParam(value = "binId",required = true) Integer allocationFromId,
                              Integer allocationStatus, Integer allocationToId,Integer allocationEId,
                              @RequestParam(value = "allocationCreateTime",required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime allocationCreateTime,
                              @RequestParam(value = "allocationEndTime",required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime allocationEndTime){
        List<GoodsAllocation> allocations =
                allocationService.getGoodsAllocations(allocationFromId,allocationStatus, allocationToId,allocationEId,allocationCreateTime,allocationEndTime);
        if(allocations != null){
            return Msg.success().add("allocations",allocations);
        }
        return Msg.fail();
    }

    /**
     * 点击传入
     * 根据传入的 详情表获取详情信息
     *
     * @param allocationId
     * @return
     */
    @GetMapping("/detail")
    @ApiOperation(value = "获取详情表",httpMethod = "GET")
    public Msg getAllocationsDetail(Integer allocationId){
        GoodsAllocationDetail detail = allocationService.getDetail(allocationId);
        return Msg.success().add("detail",detail);
    }

    /**
     * 这个接口 是调货员 点击开始配货的按钮来触发的
     * 并不属于仓管员
     * @param goodsAllocation
     * @param allocationType
     * @param allocationDesc
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("/配货员点击下单按钮触发接口，Ps：《allocationFromId 是 binId》")
    public Msg addAllocations(GoodsAllocation goodsAllocation,Integer allocationType,String allocationDesc){
        if(allocationService.addAllicationsAndDetail(goodsAllocation,allocationType,allocationDesc)){
            return Msg.success();
        }
        return Msg.fail();
    }



}
