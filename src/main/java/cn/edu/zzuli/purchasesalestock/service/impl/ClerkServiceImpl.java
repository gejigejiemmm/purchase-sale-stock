package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.Mapper.ClerkMapper;
import cn.edu.zzuli.purchasesalestock.bean.Clerk;
import cn.edu.zzuli.purchasesalestock.service.ClerkService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

@Service
public class ClerkServiceImpl implements ClerkService {

    @Autowired
    ClerkMapper clerkMapper;

    @Override
    public boolean addClerk(@Param("clerkName") String clerkName, @Param("clerkCompany") String clerkCompany, @Param("clerkPosition") String clerkPosition,
                            @Param("clerkArea") String clerkArea, @Param("clerkSpell") String clerkSpell, @Param("clerkBirthday") LocalDate clerkBirthday, @Param("clerkTelphone") String clerkTelphone,
                            @Param("clerkLocation") String clerkLocation, @Param("clerkPassword") String clerkPassword) {
        String clerkNo = "";
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        clerkNo = clerkNo + formatter.format(date);
        Integer num = clerkMapper.getAllClerks().size() % 1000;
        clerkNo = clerkNo + num.toString();
        if (clerkMapper.addClerk(clerkName,clerkNo,clerkCompany,clerkPosition,clerkArea,clerkSpell,clerkBirthday,clerkTelphone,clerkLocation, clerkPassword))
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
    public Collection<Clerk> getClerkByConsitionsByLimit(@Param("clerkCompany") String clerkCompany, @Param("clerkPosition") String clerkPosition, @Param("clerkArea") String clerkArea,
                                      @Param("page") Integer page, @Param("limit") Integer limit)
    {
        Integer offset = (page-1)*limit;
        Collection<Clerk> result = clerkMapper.getClerkByConsitionsByLimit(clerkCompany, clerkPosition, clerkArea, offset, limit);
        for(Clerk cc : result){
            cc.setClerkPassword(null);
        }
        return result;
    }

    @Override
    public boolean updateClerk(@Param("clerkId") Integer clerkId, @Param("clerkName") String clerkName, @Param("clerkCompany") String clerkCompany,
                               @Param("clerkPosition") String clerkPosition, @Param("clerkArea") String clerkArea,
                               @Param("clerkSpell") String clerkSpell, @Param("clerkBirthday") LocalDate clerkBirthday,
                               @Param("clerkTelphone") String clerkTelphone, @Param("clerkLocation") String clerkLocation, @Param("clerkPassword") String clerkPassword)
    {
        return clerkMapper.updateClerk(clerkId, clerkName, clerkCompany, clerkPosition, clerkArea, clerkSpell, clerkBirthday, clerkTelphone, clerkLocation, clerkPassword);
    }

    @Override
    public Collection<Clerk> getClerksByLimit(@Param("page") Integer page, @Param("limit")Integer limit)
    {
        Integer offset = (page-1)*limit;
        Collection<Clerk> result = clerkMapper.getClerksByLimit(offset, limit);
        for(Clerk cc : result){
            cc.setClerkPassword(null);
        }
        return result;
    }

    @Override
    public Collection<Clerk> getAllClerks()
    {
        Collection<Clerk> result = clerkMapper.getAllClerks();
        for(Clerk cc : result){
            cc.setClerkPassword(null);
        }
        return result;
    }

    @Override
    public Collection<Clerk> getClerkByConsitions(@Param("clerkCompany") String clerkCompany, @Param("clerkPosition") String clerkPosition, @Param("clerkArea") String clerkArea)
    {
        return clerkMapper.getClerkByConsitions(clerkCompany, clerkPosition, clerkArea);
    }

    @Override
    public Clerk getClerkByNo(Integer clerkNo)
    {
        return clerkMapper.getClerkByNo(clerkNo);
    }
}
