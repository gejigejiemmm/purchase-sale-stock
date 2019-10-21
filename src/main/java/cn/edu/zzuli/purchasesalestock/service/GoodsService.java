package cn.edu.zzuli.purchasesalestock.service;

import cn.edu.zzuli.purchasesalestock.bean.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    boolean addGoods(Goods goods);

    List<Goods> getGoodsByChName(String goodsChName);

    boolean delGoodsById(Integer goodsId);

    List<Goods> getAllGoods(Integer goodsId,String goodsChName,String goodsPinyin,
                            String goodsTrivialName,String goodsEnName,Integer goodsBrandId,
                            String goodsMolecularFormula,String goodsCas,Integer goodsAvgPrice,
                            Integer goodsLowPrice,Integer goodsSalePrice1,Integer goodsPrice);

    boolean updateGoods(Goods goods);
}
