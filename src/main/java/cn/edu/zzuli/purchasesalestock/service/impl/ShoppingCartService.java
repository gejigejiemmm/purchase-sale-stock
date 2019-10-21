package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.Mapper.ShoppingCartMapper;
import cn.edu.zzuli.purchasesalestock.bean.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartMapper mapper;

    public boolean saveShoppnigCart(ShoppingCart cart)
    {
        return mapper.saveShoppnigCart(cart);
    }
}
