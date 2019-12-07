package cn.edu.zzuli.purchasesalestock.Mapper;

import cn.edu.zzuli.purchasesalestock.bean.Address;
import cn.edu.zzuli.purchasesalestock.bean.Bin;
import cn.edu.zzuli.purchasesalestock.bean.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface BinMapper {

    //@Select("select * from bin")
    List<Bin> getBins();

    boolean addBin(Bin bin);

    int getGoodsCounts(Map<String, Object> info);

    List<Map<Integer,Integer>> GoodsInfoInBinById(List<Integer> list,Integer binId);
//    List<Goods> getGoods(Goods goods);

    List<Bin> getAddressById(Integer addressId);

    /**
     * 减少仓库中对应的库存
     * @param binId 仓库编号
     * @param list 订单中所有的 商品id
     * @return
     */
    boolean deleteGoodsCount(Integer binId, List<Integer> list);

}
