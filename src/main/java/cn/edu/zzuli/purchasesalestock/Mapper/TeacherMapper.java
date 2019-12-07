package cn.edu.zzuli.purchasesalestock.Mapper;

import cn.edu.zzuli.purchasesalestock.bean.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//试试注解版
public interface TeacherMapper {

    @Results(
            id = "teacher",
            value = {
                    @Result(id = true,property = "teaId",column = "tea_id"),
                    @Result(property = "teaName",column = "tea_name"),
                    @Result(property = "teaPhone",column = "tea_phone"),
                    @Result(property = "teaArea",column = "tea_area"),
                    @Result(property = "teaUniversity",column = "tea_university"),
                    @Result(property = "teaBalance",column = "tea_balance")
            }
    )
    @Select("SELECT * FROM teacher WHERE tea_id = #{teaId}")
    Teacher getTeacherById(Integer teaId);


    @ResultMap("teacher")
    @Select("SELECT * FROM teacher")
    List<Teacher> getTeachers();

    @Update("UPDATE teacher set tea_balance = tea_balance - #{spend} WHERE tea_id = #{teaId}")
    Boolean updateBalance(Integer teaId,Double spend);
}
