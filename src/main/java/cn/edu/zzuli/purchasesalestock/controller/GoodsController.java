package cn.edu.zzuli.purchasesalestock.controller;

import cn.edu.zzuli.purchasesalestock.bean.Goods;
import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    //商品的添加
    @RequestMapping("/addGoods")
    public boolean addGoods(Goods goods){
        if (goodsService.addGoods(goods))
            return true;
        return false;

    }
//    根据商品中文名称查询
    @RequestMapping("/getGoodsByChName")
    public List<Goods> getGoodsByChName(String goodsChName){
       return goodsService.getGoodsByChName(goodsChName);
    }

//根据商品id删除
    @RequestMapping("/delGoods")
    public boolean delGoodsById(Integer goodsId){
        if (goodsService.delGoodsById(goodsId))
            return true;
        return false;
    }
    //    根据传入条件查询
    @RequestMapping(value = "/getAllGoods", method = RequestMethod.GET)
    public Msg getAllGoods(Integer goodsId, String goodsChName, String goodsPinyin,
                           String goodsTrivialName, String goodsEnName, Integer goodsBrandId,
                           String goodsMolecularFormula, String goodsCas, Integer goodsAvgPrice,
                           Integer goodsLowPrice, Integer goodsSalePrice1, Integer goodsPrice) {
            List<Goods> goods =
                    goodsService.getAllGoods(goodsId, goodsChName, goodsPinyin,
                                    goodsTrivialName, goodsEnName, goodsBrandId,
                                    goodsMolecularFormula, goodsCas, goodsAvgPrice
                                    , goodsLowPrice, goodsSalePrice1, goodsPrice);
            if (goods != null)
                return Msg.success().add("goods", goods);
            return Msg.fail();

    }

    @RequestMapping("/updateGoods")
    public Msg updateGoods(Goods goods){
        if (goods.getGoodsId()!=null){
            goodsService.updateGoods(goods);
            return Msg.success();
        }
        return Msg.fail();
    }
}
