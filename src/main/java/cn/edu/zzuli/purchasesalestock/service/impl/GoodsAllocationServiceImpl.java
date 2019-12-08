package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.Mapper.BinMapper;
import cn.edu.zzuli.purchasesalestock.Mapper.GoodsAllocationMapper;
import cn.edu.zzuli.purchasesalestock.Mapper.OrderMapper;
import cn.edu.zzuli.purchasesalestock.bean.Goods;
import cn.edu.zzuli.purchasesalestock.bean.GoodsAllocation;
import cn.edu.zzuli.purchasesalestock.bean.GoodsAllocationDetail;
import cn.edu.zzuli.purchasesalestock.service.GoodsAllocationService;
import cn.edu.zzuli.purchasesalestock.utils.AllocationType;
import cn.edu.zzuli.purchasesalestock.utils.BaseUtils;
import cn.edu.zzuli.purchasesalestock.utils.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsAllocationServiceImpl implements GoodsAllocationService {

    @Autowired
    GoodsAllocationMapper allocationMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    BinMapper binMapper;

    @Override
    public List<GoodsAllocation> getGoodsAllocations(Integer allocationFromId, Integer allocationStatus, Integer allocationToId,Integer allocationEId,
                                                     LocalDateTime allocationCreateTime, LocalDateTime allocationEndTime) {
        Map<String,Object> info = new HashMap();
        BaseUtils.initInfo(info,"allocationFromId",allocationFromId,"allocationStatus",allocationStatus,
        "allocationToId",allocationToId,"allocationEId",allocationEId,"allocationCreateTime",allocationCreateTime,
                "allocationEndTime",allocationEndTime);

        return allocationMapper.getAllocations(info);
    }

    @Override
    public GoodsAllocationDetail getDetail(Integer allocationId) {
        return allocationMapper.getDetail(allocationId);
    }

    /**
     * 配货单 == 调拨表
     * 当配货员点击开始配货的商品后，应该生成配货单和配货单详情。
     *
     * 这里再讲一下逻辑：
     * 配货员一开始查看的是订单信息（属于配货员这个仓库的），注意是订单
     * 当配货员点击 配货 才开始生成配货单和配货单详情。并修改 订单的状态-》配货中
     *
     * 如果配货员 想查看自己已经接收的任务的话，那么他要查看的是这些配货单信息
     * 如果配货员发现，商品不够了，或者配货完成了，
     * 那么配货员要去点击 货物不足，完成两个按钮 来修改配货单的状态！ 这些放在 updateAllcations 里边
     *
     * halo
     * @param goodsAllocation
     * @param allocationType
     * @param stallocationDesc
     * @return
     */
    @Override
    @Transactional
    public boolean addAllicationsAndDetail(GoodsAllocation goodsAllocation, Integer allocationType,
                                           String stallocationDesc) {

        System.out.println(goodsAllocation);
        if(goodsAllocation != null) {
            //初始化 添加信息
            //修改配货单状态为 开始配货 --400 ，负责人id为 该配货员id
            goodsAllocation.setAllocationStatus(AllocationType.BEFORE_ALLOCATION.getStatus());

            //判断对应的订单是否有货
            isHaveGoods(goodsAllocation.getAllocationFromId(),goodsAllocation.getAllocationOrderId(),goodsAllocation);

            if (!allocationMapper.addAllocation(goodsAllocation))
                return false;
            //配货单类型 为 出库单
            GoodsAllocationDetail detail = new GoodsAllocationDetail(null,goodsAllocation.getAllocationId(),
                    AllocationType.OUT_ALLOCATION.getStatus(),stallocationDesc);
            //更改商品订单状态为 开始配货
            Map<String,Object> info = new HashMap<>();
            BaseUtils.initInfo(info,"orderId",goodsAllocation.getAllocationOrderId(),
                    "orderStatus", OrderType.ALLCATION.getStatus());
            System.out.println(info);

            System.out.println(goodsAllocation.getAllocationStatus());
            //生成配货单 配货详情单，更改商品订单状态
            if (allocationMapper.addDetail(detail) && orderMapper.updateOrder(info))
                return true;
        }

        return false;
    }

    @Override
    public boolean finishAllocation(Integer binId, Integer allocationId) {

        if (allocationMapper.updateGoodsAllocationStatus(binId,allocationId,
                AllocationType.FINISH_ALLOCATION.getStatus())){
            return true;
        }

        return false;
    }


    /**
     * 判断配货表所对应的订单
     * 是否有货
     *
     * 如果没货调整 （订单也可以调整，先不搞？鸽子halo）配货单的状态为缺货
     *
     *      配货员想查看配货单缺货的信息时
     *          可以点进配货单详情界面，里边记得添加 有无缺货 这条信息
     *          缺货的话，列出详情， （这里可以选择通知采购员，也可以不通知）
     *          不缺货的话，写无
     *          （当然，也可以不查看，后端可以试试加上 手机通知采购员）
     *      采购员可以直接查看
     *              状态为缺货的配货单
     *              查看过程同 配货员
     *
     *
     * 有货的话，改变配货单状态为 开始配货
     */
    public void isHaveGoods(Integer binId, Integer orderId,GoodsAllocation goodsAllocation){
        //获取订单对应商品id和个数（OrderItems 表）
        List<Goods> goodsIdAndCount = orderMapper.getOrdersItmesId(orderId);

        //将货物id放入到list集合中，以便查询
        List<Integer> goodsId = new ArrayList<>();
        for (Goods goods : goodsIdAndCount){
            goodsId.add(goods.getGoodsId());
        }
        //对应的仓库查询是否有货  没货调整 配货单状态， 有货 调整商品数量
        List<Map<Integer, Integer>> goodsInfo = binMapper.GoodsInfoInBinById(goodsId, binId);

        int flag = 0;
        //判断是否有货
        for (Goods goods :goodsIdAndCount){
            Integer count = goodsInfo.get(flag).get("goodsId");
            if(count == 0 || goods.getGoodsCounts() > goodsInfo.get(flag).get("goodsCount")){
                //设定为缺货状态
                goodsAllocation.setAllocationStatus(AllocationType.NO_GOODS.getStatus());
                return ;
            }
            flag++;
        }

        //去修改仓库中的数量
        binMapper.deleteGoodsCount(binId,goodsId);

    }
}
