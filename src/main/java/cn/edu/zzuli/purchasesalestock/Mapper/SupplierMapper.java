package cn.edu.zzuli.purchasesalestock.Mapper;


import cn.edu.zzuli.purchasesalestock.bean.Supplier;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

@Mapper
public interface SupplierMapper {

    public boolean addSupplier(Supplier supplier);

    public boolean updateSupplier(Supplier supplier);

    public Collection<Supplier> getAllSuppliers();

    public Supplier selectById(Integer id);

    public Collection<Supplier> getSuppliersByConditins(String supplierType);

    public Collection<String> getAllContions();
}
