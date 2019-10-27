package cn.edu.zzuli.purchasesalestock.controller;

import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.bean.Purchase;
import cn.edu.zzuli.purchasesalestock.bean.PurchaseDetail;
import cn.edu.zzuli.purchasesalestock.service.PurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/purchase")
@Api(tags = "采购单接口")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;
    @PostMapping("/addPurchase")
    @ApiOperation(value = "添加采购信息",httpMethod = "POST")
    public Msg addPurchase(Purchase purchase){
        if (purchaseService.addPurchase(purchase))
            return Msg.success();
        return Msg.fail();
    }

    @PostMapping("/addPurchaseDetail")
    @ApiOperation(value = "添加采购详单信息",httpMethod = "POST")
    public Msg addPurchaseDetail(Integer purchaseId,
                                 @RequestParam(value = "purchaseCreateTime", required = false)
                                 @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
                                 LocalDateTime purchaseCreateTime,
                                 @RequestParam(value = "purchaseEndTime", required = false)
                                 @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
                                 LocalDateTime  purchaseEndTime,
                                 String purchaseSupplierI){
        if (purchaseService.addPurchaseDetail(purchaseId,purchaseCreateTime,purchaseEndTime,purchaseSupplierI))
            return Msg.success();
        return Msg.fail();
    }
    @PostMapping("/addPurchaseAndDetail")
    @ApiOperation(value = "同时添加采购单和详单信息",httpMethod = "POST")
    public Msg addPurchaseAndDetail(Purchase purchase,
                                    Integer purchaseId,
                                    @RequestParam(value = "purchaseCreateTime", required = false)
                                    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
                                    LocalDateTime purchaseCreateTime,
                                    @RequestParam(value = "purchaseEndTime", required = false)
                                    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
                                    LocalDateTime  purchaseEndTime,
                                    String purchaseSupplierI){

        if (purchaseService.addPruchaseAndDetail(purchase,purchaseId,purchaseCreateTime,
                                                 purchaseEndTime,purchaseSupplierI)){
            return Msg.success();
        }
        return Msg.fail();
    }

    @GetMapping("/getInformation")
    @ApiOperation(value = "获取采购单和货物以及采购详单信息",httpMethod = "GET")
    public Msg getInformation(Integer purchaseId){
        if (purchaseService.getInformation(purchaseId)!=null)
            return Msg.success().add("getInformation", purchaseService.getInformation(purchaseId));
        return Msg.fail();
//        return purchaseService.getInformation(purchaseId);
    }
}
