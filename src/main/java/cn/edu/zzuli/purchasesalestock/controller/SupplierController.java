package cn.edu.zzuli.purchasesalestock.controller;


import cn.edu.zzuli.purchasesalestock.bean.Supplier;
import cn.edu.zzuli.purchasesalestock.service.impl.SupplierServiceImpl;
import io.swagger.annotations.Api;
import cn.edu.zzuli.purchasesalestock.bean.Msg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@RequestMapping("/supplier")
@Api(tags = "供应商接口")
public class SupplierController {

    @Autowired
    private SupplierServiceImpl supplierService;

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部供应商",httpMethod = "GET")
    public Msg getAllSuppliers()
    {
        Collection<Supplier> result = supplierService.getAllSuppliers();
        if(result!=null){
            return Msg.success().add("data", result);
        }
        else{
            return Msg.fail().add("error", "网络异常，请稍后重试");
        }
    }

    @RequestMapping(value = "addSupplier", method = RequestMethod.POST)
    @ApiOperation(value = "添加供应商",httpMethod = "POST")
    public Msg addSupplier(@RequestParam(value = "name", required = true) String supplierName,
                           @RequestParam(value = "unit", required = true) String supplierUnit,
                           @RequestParam(value = "spell", required = true) String supplierSpell,
                           @RequestParam(value = "type", required = true) String supplierType,
                           @RequestParam(value = "brand", required = true) String supplierBrand,
                           @RequestParam(value = "location", required = true) String supplierLocation,
                           @RequestParam(value = "phone", required = true) String supplierTelphone)
    {
        boolean result = supplierService.addSupplier(supplierName, supplierUnit, supplierSpell, supplierType, supplierBrand, supplierLocation,supplierTelphone);
        if(result){
            return Msg.success().add("flag", "操作成功");
        }
        else{
            return Msg.fail().add("error", "网络异常，请稍后重试");
        }
    }

    @RequestMapping(value = "updateSupplier", method = RequestMethod.POST)
    @ApiOperation(value = "修改供应商信息",httpMethod = "POST")
    public Msg updateSupplier(@RequestParam(value = "name", required = true) String supplierName,
                           @RequestParam(value = "unit", required = true) String supplierUnit,
                           @RequestParam(value = "spell", required = true) String supplierSpell,
                           @RequestParam(value = "type", required = true) String supplierType,
                           @RequestParam(value = "brand", required = true) String supplierBrand,
                           @RequestParam(value = "location", required = true) String supplierLocation,
                           @RequestParam(value = "phone", required = true) String supplierTelphone,
                              @RequestParam(value = "id", required = true) Integer id)
    {
        boolean result = supplierService.updateSupplier(supplierName, supplierUnit, supplierSpell, supplierType, supplierBrand, supplierLocation,supplierTelphone,id);
        if(result){
            return Msg.success().add("flag", "操作成功");
        }
        else{
            return Msg.fail().add("error", "网络异常，请稍后重试");
        }
    }


    @RequestMapping(value = "/getone", method = RequestMethod.GET)
    @ApiOperation(value = "获取指定ID供应商用于更新回显",httpMethod = "GET")
    public Msg getOneSupplier(@RequestParam(value = "id") Integer id)
    {
        Supplier result = supplierService.selectById(id);
        if(result!=null){
            return Msg.success().add("data", result);
        }
        else{
            return Msg.fail().add("error", "网络异常，请稍后重试");
        }
    }


}
