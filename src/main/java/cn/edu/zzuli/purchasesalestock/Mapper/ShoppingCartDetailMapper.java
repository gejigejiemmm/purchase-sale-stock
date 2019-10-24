package cn.edu.zzuli.purchasesalestock.Mapper;


import cn.edu.zzuli.purchasesalestock.bean.ShoppingCart_detail;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Mapper
public interface ShoppingCartDetailMapper {

    public boolean additem(ShoppingCart_detail detail);

    public boolean deleteitem(Integer id);

    public List<ShoppingCart_detail> getAll(Integer no);

    public ShoppingCart_detail getOneById(Integer id);

    public boolean updateDetailNumber(ShoppingCart_detail detail);

    public boolean deleteAll(LinkedList<Integer> list);
}
