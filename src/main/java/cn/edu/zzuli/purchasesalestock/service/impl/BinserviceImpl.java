package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.Mapper.BinMapper;
import cn.edu.zzuli.purchasesalestock.bean.Bin;
import cn.edu.zzuli.purchasesalestock.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinserviceImpl implements BinService {

    @Autowired
    BinMapper binMapper;

    @Override
    public List<Bin> getBins() {
        return binMapper.getBins();
    }
}
