package cn.edu.zzuli.purchasesalestock.controller;

import cn.edu.zzuli.purchasesalestock.bean.Clerk;
import cn.edu.zzuli.purchasesalestock.bean.Goods;
import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.service.GoodsService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "商品接口")
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    //商品的添加
    @PostMapping("/addGoods")
    @ApiOperation(value = "添加商品",httpMethod = "POST")
    public boolean addGoods(Goods goods){
        if (goodsService.addGoods(goods))
            return true;
        return false;

    }

    //根据商品id删除
    @RequestMapping(value = "/delGoods", method = RequestMethod.POST)
    @ApiOperation(value = "根据id删除商品",httpMethod = "POST")
    public boolean delGoodsById(Integer goodsId){
        if (goodsService.delGoodsById(goodsId))
            return true;
        return false;
    }

    //根据传入条件查询
    @RequestMapping(value = "/getGoods", method = RequestMethod.GET)
    @ApiOperation(value = "条件获取商品",httpMethod = "GET")
    public Msg getAllGoods(@RequestParam(defaultValue = "1") Integer p , Integer goodsId, String goodsChName, String goodsPinyin,
                           String goodsTrivialName, String goodsEnName, Integer goodsBrandId,
                           String goodsMolecularFormula, String goodsCas, Integer goodsAvgPrice,
                           Integer goodsLowPrice, Integer goodsSalePrice1, Integer goodsPrice) {

        PageInfo goods = goodsService.getAllGoods(p, goodsId, goodsChName, goodsPinyin,
                            goodsTrivialName, goodsEnName, goodsBrandId,
                            goodsMolecularFormula, goodsCas, goodsAvgPrice
                            , goodsLowPrice, goodsSalePrice1, goodsPrice);
        if (goods != null)
            return Msg.success().add("goods", goods);
        return Msg.fail();

    }

    @RequestMapping(value = "/updateGoods",method = RequestMethod.POST)
    @ApiOperation(value = "添加商品",httpMethod = "POST")
    public Msg updateGoods(Goods goods){
        if (goods.getGoodsId()!=null){
            goodsService.updateGoods(goods);
            return Msg.success();
        }
        return Msg.fail();
    }

    @PostMapping("/getGood")
    @ApiOperation(value = "根据id得到商品信息",httpMethod = "POST")
    public List<Goods> getGood(Integer goodsId){
        List<Goods> goods = new ArrayList<>();
        if (goodsId!=null){
            goods=goodsService.getGood(goodsId);
            if (goods!=null)
                return goods;
        }
        return goods;
    }
//    随机得到5条商品信息
    @PostMapping("/getGoodRand")
    @ApiOperation(value = "随机得到商品信息",httpMethod = "POST")
    public List<Goods>  getGoodsRand(){
        List<Goods> goods = new ArrayList<>();
        goods = goodsService.getGoodsRand();
        if (goods!=null){
//            for (Goods good:goods){
//                System.out.println(good.getGoodsChName());
//            }
            return goods;
        }
        return goods;
    }
}
