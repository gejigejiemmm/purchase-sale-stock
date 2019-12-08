package cn.edu.zzuli.purchasesalestock.Mapper;

import cn.edu.zzuli.purchasesalestock.Mapper.provider.SendProvider;
import cn.edu.zzuli.purchasesalestock.bean.Send;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface SendMapper {

    //halo 花里胡哨一波，试试mybatis注解版的条件查询
    @Results(
            id = "send",
            value = {
                    @Result(id = true,property = "sendId",column = "send_id"),
                    @Result(property = "sendToUId",column = "send_toUId"),
                    @Result(property = "sendEId",column = "send_eId"),
                    @Result(property = "sendCreateTime",column = "send_startTime"),
                    @Result(property = "sendEndTime",column = "send_endTime"),
                    @Result(property = "sendStatus",column = "send_status"),
                    @Result(property = "sendBinId",column = "send_binId"),
            }
    )
//    @Select("SELECT * FROM send WHERE send_binId = #{binId}")
    @SelectProvider(type = SendProvider.class,method = "getSendByInfo")
    List<Send> getSends(Map<String,Object> info);
}
