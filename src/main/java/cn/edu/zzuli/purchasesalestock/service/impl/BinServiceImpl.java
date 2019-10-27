package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.Mapper.BinMapper;
import cn.edu.zzuli.purchasesalestock.bean.Bin;
import cn.edu.zzuli.purchasesalestock.service.BinService;
import cn.edu.zzuli.purchasesalestock.utils.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BinServiceImpl implements BinService {

    @Autowired
    BinMapper binMapper;

    @Override
    public List<Bin> getBins() {
        return binMapper.getBins();
    }

    @Override
    public boolean addBin(Bin bin) {
        if (binMapper.addBin(bin))
            return true;
        return false;
    }


//    判断商品数量是否大于50
    @Override
    public boolean ifMoreFifty(Integer goodsId, String goodsChName, String goodsPinyin,
                              String goodsTrivialName, String goodsEnName,Integer goodsBin) {
        Map<String, Object> info = new HashMap<>();
        BaseUtils.initInfo(info,"goodsId",goodsId,"goodsChName",goodsChName,
                                      "goodsPinyin",goodsPinyin,"goodsTrivialName",goodsTrivialName,
                                      "goodsEnName",goodsEnName,"goodsBin",goodsBin);
        Integer counts = binMapper.getGoodsCounts(info);
        if (counts<50){
            return true;
        }
        return false;



    }

}
