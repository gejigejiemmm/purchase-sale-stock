package cn.edu.zzuli.purchasesalestock.Mapper;


import cn.edu.zzuli.purchasesalestock.bean.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShoppingCartMapper {

    public boolean saveShoppnigCart(ShoppingCart cart);

}
