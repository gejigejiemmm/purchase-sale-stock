package cn.edu.zzuli.purchasesalestock;

import cn.edu.zzuli.purchasesalestock.Mapper.BinMapper;
import cn.edu.zzuli.purchasesalestock.Mapper.OrderMapper;
import cn.edu.zzuli.purchasesalestock.bean.Goods;
import cn.edu.zzuli.purchasesalestock.bean.Order;
import cn.edu.zzuli.purchasesalestock.utils.AllocationType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseSaleStockApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    BinMapper binMapper;

    @Autowired
    OrderMapper orderMapper;


    @Test
    public void testIsHaveGoods(){
        Integer orderId = 1;
        Integer binId = 1;

        //去对应仓库搜索 是否有货。 鸽子 halo

        //获取订单对应商品id和个数（OrderItems 表）
        List<Goods> goodsIdAndCount = orderMapper.getOrdersItmesId(orderId);

        //将货物id放入到list集合中，以便查询
        List<Integer> goodsId = new ArrayList<>();
        for (Goods goods : goodsIdAndCount){
            goodsId.add(goods.getGoodsId());
        }
        //对应的仓库查询是否有货  没货调整 配货单状态， 有货 调整商品数量
        List<Map<Integer, Integer>> goodsInfo = binMapper.GoodsInfoInBinById(goodsId, binId);

        System.out.println(goodsInfo);

//        System.out.println(goodsInfo.get(0).get("goodsId"));

        int flag = 0;

        //判断是否有货
        for (Goods goods :goodsIdAndCount){
            Integer count = goodsInfo.get(flag).get("goodsId");
//            System.out.println("货物号:"+goods.getGoodsId()+"数量:"+goods.getGoodsCounts()+"仓库有："+goodsInfo.get(flag).get("goodsCount") );

            if(count == 0 || goods.getGoodsCounts() > goodsInfo.get(flag).get("goodsCount")){
                //设定为缺货状态
                System.out.println("缺货");
                return;
            }

            flag++;
        }

        //去修改仓库中的数量
        binMapper.deleteGoodsCount(binId,goodsId);

    }

}
