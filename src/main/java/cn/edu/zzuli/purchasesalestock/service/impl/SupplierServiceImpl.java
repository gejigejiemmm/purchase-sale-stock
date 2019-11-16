package cn.edu.zzuli.purchasesalestock.service.impl;


import cn.edu.zzuli.purchasesalestock.Mapper.SupplierMapper;
import cn.edu.zzuli.purchasesalestock.bean.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SupplierServiceImpl {

    @Autowired
    private SupplierMapper supplierMapper;

    public boolean addSupplier(String supplierName, String supplierUnit, String supplierSpell,
                               String supplierType, String supplierBrand, String supplierLocation, String supplierTelphone)
    {
        Supplier supplier = new Supplier();
        supplier.setSupplierName(supplierName);
        supplier.setSupplierUnit(supplierUnit);
        supplier.setSupplierSpell(supplierSpell);
        supplier.setSupplierType(supplierType);
        supplier.setSupplierBrand(supplierBrand);
        supplier.setSupplierLocation(supplierLocation);
        supplier.setSupplierTelphone(supplierTelphone);
        return supplierMapper.addSupplier(supplier);
    }

    public boolean updateSupplier(String supplierName, String supplierUnit, String supplierSpell,
                                  String supplierType, String supplierBrand, String supplierLocation, String supplierTelphone, Integer id)
    {
        Supplier supplier = new Supplier();
        supplier.setSupplierName(supplierName);
        supplier.setSupplierUnit(supplierUnit);
        supplier.setSupplierSpell(supplierSpell);
        supplier.setSupplierType(supplierType);
        supplier.setSupplierBrand(supplierBrand);
        supplier.setSupplierLocation(supplierLocation);
        supplier.setSupplierTelphone(supplierTelphone);
        supplier.setSupplierId(id);
        return supplierMapper.updateSupplier(supplier);
    }

    public Collection<Supplier> getAllSuppliers()
    {
        return supplierMapper.getAllSuppliers();
    }

    public Supplier selectById(Integer id)
    {
        return supplierMapper.selectById(id);
    }

    public Collection<Supplier> getSuppliersByConditins(String supplierType)
    {
        return supplierMapper.getSuppliersByConditins(supplierType);
    }

    public Collection<String> getAllContions()
    {
        return supplierMapper.getAllContions();
    }
}
