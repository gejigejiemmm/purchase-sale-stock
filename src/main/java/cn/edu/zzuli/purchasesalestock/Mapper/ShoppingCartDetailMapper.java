package cn.edu.zzuli.purchasesalestock.Mapper;


import cn.edu.zzuli.purchasesalestock.bean.ShoppingCart_detail;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

@Mapper
public interface ShoppingCartDetailMapper {

    public boolean additem(ShoppingCart_detail detail);

    public boolean deleteitem(Integer id);

    public Collection<ShoppingCart_detail> getAll(Integer no);
}
