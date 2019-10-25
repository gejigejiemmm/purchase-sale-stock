package cn.edu.zzuli.purchasesalestock.Mapper;

import cn.edu.zzuli.purchasesalestock.bean.Clerk;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface ClerkMapper {
    boolean addClerk(String clerkName, Integer clerkNo, String clerkCompany, String clerkPosition,
                     String clerkArea, String clerkSpell, LocalDate clerkBirthday, String clerkTelphone,
                     String clerkLocation);

    boolean delClerkById(Integer clerkId);

    List<Clerk> getClerk(Map<String, Object> info);

    boolean updateClerk(Map<String, Object> info);
}
