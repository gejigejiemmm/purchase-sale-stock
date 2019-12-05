package cn.edu.zzuli.purchasesalestock.Mapper;

import cn.edu.zzuli.purchasesalestock.bean.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsMapper {

    boolean addGoods(Goods goods); //商品所有信息的添加

    List<Goods> getGoodsByChName(String goodsChName);//根据商品中文名称查询

    boolean delGoodsById(Integer goodsId); //根据id删除

    List<Goods> getAllGoods(Map<String, Object> info);

    boolean updateGoods(Map<String, Object> info);

    List<Goods> getGoods(Integer goodsId);

    List<Goods> getGoodsRand();//随机获取商品信息


}
