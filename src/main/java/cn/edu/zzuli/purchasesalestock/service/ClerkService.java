package cn.edu.zzuli.purchasesalestock.service;

import cn.edu.zzuli.purchasesalestock.bean.Clerk;

import java.time.LocalDate;
import java.util.Collection;

public interface ClerkService {
    boolean addClerk(String clerkName, String clerkCompany, String clerkPosition,
                     String clerkArea, String clerkSpell, LocalDate clerkBirthday, String clerkTelphone,
                     String clerkLocation, String clerkPassword);

    boolean delClerkById(Integer clerkId);

    Collection<Clerk> getClerkByConsitionsByLimit(String clerkCompany, String clerkPosition, String clerkArea, Integer page, Integer limit);

    boolean updateClerk(Integer clerkId, String clerkName, String clerkCompany, String clerkPosition, String clerkArea,
                        String clerkSpell, LocalDate clerkBirthday, String clerkTelphone, String clerkLocation, String clerkPassword);

    Collection<Clerk> getClerksByLimit(Integer offset, Integer limit);

    Collection<Clerk> getAllClerks();

    Collection<Clerk> getClerkByConsitions(String clerkCompany, String clerkPosition, String clerkArea);

    Clerk getClerkByNo(Integer clerkNo);
}
