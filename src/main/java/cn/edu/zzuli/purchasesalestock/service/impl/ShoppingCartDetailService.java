package cn.edu.zzuli.purchasesalestock.service.impl;


import cn.edu.zzuli.purchasesalestock.Mapper.ShoppingCartDetailMapper;
import cn.edu.zzuli.purchasesalestock.bean.ShoppingCart_detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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

    public List<ShoppingCart_detail> getAll(Integer no){
        return mapper.getAll(no);
    }

    public ShoppingCart_detail getOneById(Integer id){
        return mapper.getOneById(id);
    }

    public boolean updateDetailNumber(ShoppingCart_detail detail){
        return mapper.updateDetailNumber(detail);
    }
}
