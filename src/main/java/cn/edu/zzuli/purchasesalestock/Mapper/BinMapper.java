package cn.edu.zzuli.purchasesalestock.Mapper;

import cn.edu.zzuli.purchasesalestock.bean.Bin;
import cn.edu.zzuli.purchasesalestock.bean.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BinMapper {

    //@Select("select * from bin")
    List<Bin> getBins();

    boolean addBin(Bin bin);

    int getGoodsCounts(Map<String, Object> info);

//    List<Goods> getGoods(Goods goods);
}
