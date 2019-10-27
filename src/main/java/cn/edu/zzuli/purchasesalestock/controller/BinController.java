package cn.edu.zzuli.purchasesalestock.controller;

import cn.edu.zzuli.purchasesalestock.bean.Bin;
import cn.edu.zzuli.purchasesalestock.bean.Goods;
import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.service.BinService;
import cn.edu.zzuli.purchasesalestock.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bin")
@Api(tags = "仓库接口")
public class BinController {

    @Autowired
    BinService binService;
    @Autowired
    GoodsService goodsService;

    @GetMapping("/getAll")
    @ApiOperation(value = "获取所有的仓库信息",httpMethod = "GET")
    public Msg getBins(){
        List<Bin> bins = binService.getBins();
        return Msg.success().add("bins",bins);
    }

    @PostMapping("/addBin")
    @ApiOperation(value = "添加仓库",httpMethod = "POST")
    public Msg addBin(Bin bin){
        if (binService.addBin(bin))
            return Msg.success();
        return Msg.fail();
    }

    @PostMapping("/getGood")
    @ApiOperation(value = "得到数量小于50的商品信息",httpMethod = "POST")
    public List getGood(Integer goodsId, String goodsChName, String goodsPinyin,
                               String goodsTrivialName, String goodsEnName,Integer goodsBin){
        List<Goods> goods = new ArrayList<>();
        if (binService.ifMoreFifty(goodsId,goodsChName,goodsPinyin,goodsTrivialName,goodsEnName,goodsBin)){
            goods = goodsService.getGood(goodsId);
            return goods;
        }else {
            List<String> strs=new ArrayList<String>();
            strs.add("该商品数量大于50，无需补充");
            return strs;
        }

    }

    /**
     * 前端或者后端使用定时器操作！来定时从该接口拿信息 鸽子
     *
     * 从 仓库里 开始查询 数量小于50的商品信息List<Goods>  √
     * 返给前端 这个时候仓管员或者采购员看到了这些补货的商品信息  √
     *
     * 开始点击 补货（采购）按钮来 进行补货操作
     *
     * 这个时候 要生成 一个 采购单，和详单   √
     *
     * 并且 将这些要补货的商品 id  和 订单id 一一对应在 补货单items表中  √
     *
     * 下次查询采购单的时候，把采购单对应的 商品查出来  √
     *
     * halo
     */

}
