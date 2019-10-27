package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.Mapper.SaleMapper;
import cn.edu.zzuli.purchasesalestock.bean.Sale;
import cn.edu.zzuli.purchasesalestock.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    SaleMapper saleMapper;

    @Override
    public List<Sale> getAllSales() {
        return saleMapper.getSales();
    }
}
