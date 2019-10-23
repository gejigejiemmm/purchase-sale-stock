package cn.edu.zzuli.purchasesalestock.service.impl;


import cn.edu.zzuli.purchasesalestock.Mapper.ShoppingCartDetailMapper;
import cn.edu.zzuli.purchasesalestock.bean.ShoppingCart_detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ShoppingCartDetailService {

    @Autowired
    private ShoppingCartDetailMapper mapper;

    public boolean additem(ShoppingCart_detail detail){
        return mapper.additem(detail);
    }

    public boolean deleteitem(Integer id){
        return mapper.deleteitem(id);
    }

    public Collection<Integer> getAll(Integer no){
        return mapper.getAll(no);
    }
}
