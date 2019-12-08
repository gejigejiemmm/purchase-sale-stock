package cn.edu.zzuli.purchasesalestock.Mapper.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class SendProvider {

    //条件查询
    public String getSendByInfo(Map<String,Object> info){

        SQL sql = new SQL();
        sql.SELECT("*")
                .FROM("send")
                .WHERE("send_binId = #{sendBinId}");
        if (info.get("sendEId") != null){
            sql.WHERE("send_eId = #{sendEId}");
        }
        if (info.get("sendToUId") != null){
            sql.WHERE("send_toUId = #{sendToUId}");
        }
        if (info.get("sendCreateTime") != null){
            sql.WHERE("send_startTime >= #{sendCreateTime}");
        }
        if (info.get("sendEndTime") != null){
            sql.WHERE("send_endTime <= #{sendEndTime}");
        }
        if (info.get("sendStatus") != null){
            sql.WHERE("send_status = #{sendStatus}");
        }
        if (info.get("sendId") != null){
            sql.WHERE("send_id = #{sendId}");
        }

        return sql.toString();

    }

}
