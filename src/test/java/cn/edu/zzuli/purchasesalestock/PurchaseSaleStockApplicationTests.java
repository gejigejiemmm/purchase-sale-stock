package cn.edu.zzuli.purchasesalestock;

import cn.edu.zzuli.purchasesalestock.Mapper.*;
import cn.edu.zzuli.purchasesalestock.bean.*;
import cn.edu.zzuli.purchasesalestock.utils.AllocationType;
import cn.edu.zzuli.purchasesalestock.utils.BaseUtils;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Update;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseSaleStockApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    @Lazy
    BinMapper binMapper;

    @Lazy
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

    @Autowired
    @Lazy
    TeacherMapper teacherMapper;

    @Test
    public void Teacher(){


        List<Teacher> teachers = teacherMapper.getTeachers();
        System.out.println(teachers);


        Boolean isSpend = teacherMapper.updateBalance(1, 30.0);
        System.out.println(isSpend);
        Teacher teacherById = teacherMapper.getTeacherById(1);
        System.out.println(teacherById);
    }

    @Autowired
    @Lazy
    SendMapper sendMapper;

    @Test
    public void testSendMapper(){
        Map<String,Object> info = new HashMap<>();
//        BaseUtils.initInfo(info,"sendBinId",1,"sendToUId",1,"sendEId",1,"sendStatus",400,
//            "sendId",1,"sendCreateTime","2019-12-08 20:51:28","sendEndTime","2019-12-10 22:05:33");
//        List<Send> sends = sendMapper.getSends(info);
//        System.out.println(sends);
        BaseUtils.initInfo(info,"sendId",1,"sendStatus",401,"sendEndTime","2019-12-11 22:05:33");

        sendMapper.updateSend(info);
//
        SendDetail detail = sendMapper.getDetail(1);
        System.out.println(detail);

    }

    @Lazy
    @Autowired
    CustomerMapper customerMapper;

    @Test
    public void testCustomer(){
        Customer 范向辉 = customerMapper.getCustomerByName("范向辉");
        System.out.println(范向辉);
    }

}
