package cn.edu.zzuli.purchasesalestock.Mapper;


import cn.edu.zzuli.purchasesalestock.bean.ShoppingCart;
import cn.edu.zzuli.purchasesalestock.bean.Supplier;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShoppingCartMapper {

    public boolean saveShoppnigCart(ShoppingCart cart);


}
