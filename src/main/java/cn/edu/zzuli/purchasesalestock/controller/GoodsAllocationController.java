package cn.edu.zzuli.purchasesalestock.controller;

import cn.edu.zzuli.purchasesalestock.bean.GoodsAllocation;
import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.service.GoodsAllocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/allocation")
@Api(tags = "商品调货接口")
public class GoodsAllocationController {

    @Autowired
    GoodsAllocationService allocationService;

    /**
     * 这个接口是针对 仓库管理员的
     * 这些商品调拨信息 仓库管理员都要看到
     * （包括调拨的商品信息）
     * @param allocationFromId 仓库id
     * @return allocations
     */
    @GetMapping("/get")
    @ApiOperation(value = "条件查询商品调拨信息，binId必传，默认为出库单，传入allocationToId则为入库单",httpMethod = "GET")
    public Msg getAllocations(@RequestParam(value = "binId",required = true) Integer allocationFromId,
                              Integer allocationStatus, Integer allocationToId,
                              @RequestParam(value = "allocationCreateTime",required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime allocationCreateTime,
                              @RequestParam(value = "allocationEndTime",required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime allocationEndTime){
        List<GoodsAllocation> allocations =
                allocationService.getGoodsAllocations(allocationFromId,allocationStatus, allocationToId,allocationCreateTime,allocationEndTime);
        if(allocations != null){
            return Msg.success().add("allocations",allocations);
        }
        return Msg.fail();
    }

}
