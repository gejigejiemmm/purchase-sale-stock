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
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;



@RestController
@RequestMapping("/supplier")
@Api(tags = "供应商接口")
public class SupplierController {

    @Autowired
    private SupplierServiceImpl supplierService;

    @RequestMapping(value = "/goToPage", method = RequestMethod.GET)
    public ModelAndView goToPage()
    {
        ModelAndView mv = new ModelAndView("supplier");
        return mv;
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部供应商",httpMethod = "GET")
    public Msg getAllSuppliers()
    {
        Collection<Supplier> result = supplierService.getAllSuppliers();
        if(result!=null){
            return Msg.success().add("data", result).add("count", result.size());
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
                           @RequestParam(value = "phone", required = true) String supplierTelphone, @RequestParam(value = "id", required = true) Integer id)
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

    @RequestMapping(value = "/getInitTypes", method = RequestMethod.GET)
    @ApiOperation(value = "用于获取数据库中供应商的品牌",httpMethod = "GET")
    public Msg getinitTypes()
    {
        Collection<String> result = supplierService.getAllContions();
        if(result!=null){
            return Msg.success().add("data", result);
        }
        else{
            return Msg.fail().add("error", "网络异常，请稍后重试");
        }
    }


    @RequestMapping(value = "/selectByType", method = RequestMethod.GET)
    @ApiOperation(value = "查询经营不同类别商品的商户信息",httpMethod = "GET")
    public Msg getinitTypes(@RequestParam(value = "type", required = true) String type,
                            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                            @RequestParam(value = "limte", required = false, defaultValue = "10") Integer limte)
    {
        Collection<Supplier> result = supplierService.getSuppliersByConditins(type, page, limte);
        if(result!=null){
            Integer count = supplierService.getSuppliersByConditinsForCount(type).size();
            return Msg.success().add("data", result).add("count", count);
        }
        else{
            return Msg.fail().add("error", "网络异常，请稍后重试");
        }
    }

    @RequestMapping(value = "/getSuppliersBylimte", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询供应商信息",httpMethod = "GET")
    public Msg getSuppliersBylimte(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                   @RequestParam(value = "limte", required = false, defaultValue = "10") Integer limte)
    {
        Collection<Supplier> result = supplierService.getSuppliersByLimte(page, limte);
        if(result != null){
            return Msg.success().add("data", result);
        }
        else{
            return Msg.fail().add("error", "网络异常，请稍后重试");
        }
    }

    @RequestMapping(value = "/deleteSupplier", method = RequestMethod.POST)
    @ApiOperation(value = "删除供应商",httpMethod = "POST")
    public Msg deleteSupplier(@RequestParam(value = "id", required = true) Integer id)
    {
        Boolean result = supplierService.deleteSupplier(id);
        if(result){
            return Msg.success();
        }
        else{
            return Msg.fail().add("error", "网络异常，请稍后重试");
        }
    }
}
