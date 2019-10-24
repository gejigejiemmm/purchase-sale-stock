package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.Mapper.ClerkMapper;
import cn.edu.zzuli.purchasesalestock.bean.Clerk;
import cn.edu.zzuli.purchasesalestock.service.ClerkService;
import cn.edu.zzuli.purchasesalestock.utils.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClerkServiceImpl implements ClerkService {
    @Autowired
    ClerkMapper clerkMapper;
    @Override
    public boolean addClerk(String clerkName, Integer clerkNo, String clerkCompany, String clerkPosition,
                            String clerkArea, String clerkSpell, LocalDate clerkBirthday, String clerkTelphone,
                            String clerkLocation ) {
        if (clerkMapper.addClerk(clerkName,clerkNo,clerkCompany,clerkPosition,clerkArea,clerkSpell,clerkBirthday,clerkTelphone,clerkLocation))
            return true;
        return false;
    }

    @Override
    public boolean delClerkById(Integer clerkId) {
        if (clerkMapper.delClerkById(clerkId))
            return true;
        return false;
    }

    @Override
    public List<Clerk> getClerk(Integer clerkId, String clerkName, String clerkCompany,
                                String clerkPosition, String clerkArea, String clerkSpell,
                                String clerkBirthday, String clerkTelphone, String clerkLocation) {
        Map<String, Object> info = new HashMap<>();
        BaseUtils.initInfo(info, "clerkId", clerkId, "clerkName", clerkName, "clerkCompany", clerkCompany,
                "clerkPosition", clerkPosition, "clerkArea", clerkArea, "clerkSpell", clerkSpell,
                "clerkBirthday", clerkBirthday, "clerkTelphone", clerkTelphone, "clerkLocation", clerkLocation);
        List<Clerk> clerk = clerkMapper.getClerk(info);
        return clerk;
    }

    @Override
    public boolean updateClerk(Clerk clerk) {
        Map<String, Object> info = new HashMap<>();
        BaseUtils.initInfo(info, "clerkId", clerk.getClerkId(), "clerkName",clerk.getClerkName(),"clerkNo", clerk.getClerkNo(), "clerkCompany", clerk.getClerkCompany(),
                "clerkPosition", clerk.getClerkPosition(), "clerkArea", clerk.getClerkArea(), "clerkSpell", clerk.getClerkSpell(),
                "clerkBirthday", clerk.getClerkBirthday(), "clerkTelphone", clerk.getClerkTelphone(), "clerkLocation", clerk.getClerkLocation());
        if (clerkMapper.updateClerk(info))
            return true;
        return false;
    }
}
