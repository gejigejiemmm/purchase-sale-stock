package cn.edu.zzuli.purchasesalestock.Mapper;

import cn.edu.zzuli.purchasesalestock.bean.Clerk;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.Collection;

@Mapper
public interface ClerkMapper {
    boolean addClerk(@Param("clerkName") String clerkName, @Param("clerkNo") String clerkNo, @Param("clerkCompany") String clerkCompany, @Param("clerkPosition") String clerkPosition,
                     @Param("clerkArea") String clerkArea, @Param("clerkSpell") String clerkSpell, @Param("clerkBirthday") LocalDate clerkBirthday, @Param("clerkTelphone") String clerkTelphone,
                     @Param("clerkLocation") String clerkLocation, @Param("clerkPassword") String clerkPassword);

    boolean delClerkById(Integer clerkId);

    Collection<Clerk> getClerkByConsitionsByLimit(@Param("clerkCompany") String clerkCompany, @Param("clerkPosition") String clerkPosition, @Param("clerkArea") String clerkArea,
                               @Param("offset") Integer offset, @Param("limit") Integer limit);

    boolean updateClerk(@Param("clerkId") Integer clerkId, @Param("clerkName") String clerkName, @Param("clerkCompany") String clerkCompany,
                        @Param("clerkPosition") String clerkPosition, @Param("clerkArea") String clerkArea,
                        @Param("clerkSpell") String clerkSpell, @Param("clerkBirthday") LocalDate clerkBirthday,
                        @Param("clerkTelphone") String clerkTelphone, @Param("clerkLocation") String clerkLocation, @Param("clerkPassword") String clerkPassword);

    Collection<Clerk> getClerksByLimit(@Param("offset") Integer offset, @Param("limit")Integer limit);

    Collection<Clerk> getAllClerks();

    Collection<Clerk> getClerkByConsitions(@Param("clerkCompany") String clerkCompany, @Param("clerkPosition") String clerkPosition, @Param("clerkArea") String clerkArea);

    Clerk getClerkByNo(String clerkNo);
}
