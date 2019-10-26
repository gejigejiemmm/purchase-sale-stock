package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.Mapper.ReturnGoodsMapper;
import cn.edu.zzuli.purchasesalestock.bean.ReturnGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnGoodsService {

    @Autowired
    private ReturnGoodsMapper mapper;

    public boolean addReturnGoods(ReturnGoods returnGoods)
    {
        return mapper.addReturnGoods(returnGoods);
    }
}
