package cn.edu.zzuli.purchasesalestock.Mapper;

import cn.edu.zzuli.purchasesalestock.bean.ReturnGoods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReturnGoodsMapper {

    public boolean addReturnGoods(ReturnGoods returnGoods);
}
