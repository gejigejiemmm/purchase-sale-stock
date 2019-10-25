package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.Mapper.GoodsMapper;
import cn.edu.zzuli.purchasesalestock.bean.Goods;
import cn.edu.zzuli.purchasesalestock.service.GoodsService;
import cn.edu.zzuli.purchasesalestock.utils.BaseUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

//    商品信息的添加
    @Override
    public boolean addGoods(Goods goods) {
        if(goodsMapper.addGoods(goods))
            return true;
        return false;
    }

    @Override
    public List<Goods> getGoodsByChName(String goodsChName) {

        List<Goods> goods =goodsMapper.getGoodsByChName(goodsChName);
        return goods;
    }

    @Override
    public boolean delGoodsById(Integer goodsId) {
        if(goodsMapper.delGoodsById(goodsId))
            return true;
        return false;
    }

    @Override
    public PageInfo getAllGoods(Integer p, Integer goodsId, String goodsChName, String goodsPinyin,
                                   String goodsTrivialName, String goodsEnName, Integer goodsBrandId,
                                   String goodsMolecularFormula, String goodsCas, Integer goodsAvgPrice,
                                   Integer goodsLowPrice, Integer goodsSalePrice1, Integer goodsPrice) {
        Map<String,Object> info = new HashMap<>();
        //开始分页查询，一页有多个
        PageHelper.startPage(p,8);

        BaseUtils.initInfo(info,"goodsId",goodsId,"goodsChName",goodsChName,"goodsPinyin",goodsPinyin
        ,"goodsTrivialName",goodsTrivialName,"goodsEnName",goodsEnName,"goodsBrandId",goodsBrandId,
                "goodsMolecularFormula",goodsMolecularFormula,"goodsCas",goodsCas,"goodsAvgPrice",goodsAvgPrice,
        "goodsLowPrice",goodsLowPrice,"goodsSalePrice1",goodsSalePrice1,"goodsPrice",goodsPrice);

        List<Goods> allGoods = goodsMapper.getAllGoods(info);
        //分页信息
        PageInfo pageInfo = new PageInfo(allGoods);
        return pageInfo;
    }


    @Override
    public boolean updateGoods(Goods goods) {
        Map<String,Object> info = new HashMap<>();

        BaseUtils.initInfo(info,"goodsId",goods.getGoodsId(),"goodsChName",goods.getGoodsChName(),
                "goodsTrivialName",goods.getGoodsTrivialName(),"goodsEnName",goods.getGoodsEnName(),
                "goodsTypeId",goods.getGoodsTypeId(),"goodsNo",goods.getGoodsNo(),"goodsCas",goods.getGoodsCas(),
                "goodsPinyin",goods.getGoodsPinyin(),"goodsMolecularFormula",goods.getGoodsMolecularFormula(),
                "goodsUnit",goods.getGoodsUnit(),"goodsBrandId",goods.getGoodsBrandId(),"goodsForm",goods.getGoodsForm(),
                "goodsCondition",goods.getGoodsCondition(),"goodsLocation",goods.getGoodsLocation(),"goodsInitPrice",
                goods.getGoodsInitPrice(),"goodsAvgPrice",goods.getGoodsAvgPrice(),"goodsLowPrice",goods.getGoodsLowPrice(),
                "goodsSalePrice1",goods.getGoodsSalePrice1(),"goodsSalePrice2",goods.getGoodsSalePrice2(),"goodsPrice",
                goods.getGoodsPrice(),"goodsImageUrl",goods.getGoodsImageUrl(),"goodsCounts",goods.getGoodsCounts());

        if (goodsMapper.updateGoods(info))
            return true;
        return false;
    }

    @Override
    public List<Goods> getGood(Integer goodsId) {
        return goodsMapper.getGoods(goodsId);
    }
}
