package cn.edu.zzuli.purchasesalestock.Mapper;

import cn.edu.zzuli.purchasesalestock.Mapper.provider.SendProvider;
import cn.edu.zzuli.purchasesalestock.bean.Send;
import cn.edu.zzuli.purchasesalestock.bean.SendDetail;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

@Mapper
public interface SendMapper {

    //halo 花里胡哨一波，试试mybatis注解版的条件查询
    //不级联的results
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
    @SelectProvider(type = SendProvider.class,method = "getSendByInfo")
    List<Send> getSends(Map<String,Object> info);

    //用来使用@one 里边的select
    @Select("SELECT * FROM send WHERE send_id = #{sendId}")
    @ResultMap("send")
    Send getSendById(Integer sendId);

    //级联的results,一对一，使用 @One注解来级联
    @Results(
            id = "detail",
            value = {
                    @Result(id = true,property = "sendDetailId",column = "sedetail_id"),
                    @Result(property = "orderId",column = "send_order_id"),
                    @Result(property = "sendId",column = "send_id"),
                    @Result(one = @One(select = "cn.edu.zzuli.purchasesalestock.Mapper.CustomerMapper.selectById"),
                            property = "customer",column = "send_toUId"),
                    @Result(one = @One(select = "getSendById"),
                            property = "send",column = "send_id"),
            }
    )
    @Select("SELECT s.send_toUId,d.*\n" +
            "FROM send_detail d \n" +
            "JOIN send s ON s.send_id = d.send_id\n" +
            "JOIN customer c ON c.customer_id = s.send_toUId\n" +
            "WHERE d.send_id = #{sendId}")
    SendDetail getDetail(Integer sendId);

}
