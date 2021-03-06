package cn.edu.zzuli.purchasesalestock.Mapper;


import cn.edu.zzuli.purchasesalestock.bean.Supplier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

@Mapper
public interface SupplierMapper {

    public boolean addSupplier(Supplier supplier);

    public boolean updateSupplier(Supplier supplier);

    public Collection<Supplier> getAllSuppliers();

    public Supplier selectById(Integer id);

    public Collection<Supplier> getSuppliersByConditins(@Param("supplierType")String supplierType, @Param("offset")Integer offset, @Param("limte")Integer limte);

    public Collection<String> getAllContions();

    public Collection<Supplier> getSuppliersByLimte(@Param("offset") Integer offset, @Param("limit")Integer limit);

    public Boolean deleteSupplier(Integer id);

    public Collection<Supplier> getSuppliersByConditinsForCount(String supplierType);

}
