package cn.edu.zzuli.purchasesalestock.service;

import cn.edu.zzuli.purchasesalestock.bean.Bin;

import java.util.List;

/**
 * 这样更规范一些，
 * 也可以不这样写，直接写service
 */
public interface BinService {

    List<Bin> getBins();

    boolean addBin(Bin bin);

    boolean ifMoreFifty(Integer goodsId, String goodsChName, String goodsPinyin,
                       String goodsTrivialName, String goodsEnName);
}
