package cn.edu.zzuli.purchasesalestock.service;

import cn.edu.zzuli.purchasesalestock.bean.Clerk;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ClerkService {
    boolean addClerk(String clerkName, Integer clerkNo, String clerkCompany, String clerkPosition,
                     String clerkArea, String clerkSpell, LocalDate clerkBirthday, String clerkTelphone,
                     String clerkLocation );

    boolean delClerkById(Integer clerkId);

    List<Clerk> getClerk(Integer clerkId, String clerkName, String clerkCompany, String clerkPosition,
                         String clerkArea, String clerkSpell, String clerkBirthday, String clerkTelphone,
                         String clerkLocation);

    boolean updateClerk(Clerk clerk);
}
