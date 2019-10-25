package cn.edu.zzuli.purchasesalestock.controller;

import cn.edu.zzuli.purchasesalestock.bean.Clerk;
import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.service.ClerkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ClerkController {
    @Autowired
    ClerkService clerkService;

    @PostMapping("/addClerk")
    @ApiOperation(value = "添加销售员",httpMethod = "POST")
    public Msg addClerk(@RequestParam(value = "clerkName",required = false)String clerkName,
                        @RequestParam(value = "clerkNo",required = false)Integer clerkNo,
                        @RequestParam(value = "clerkCompany",required = false)String clerkCompany,
                        @RequestParam(value = "clerkPosition",required = false)String clerkPosition,
                        @RequestParam(value = "clerkArea",required = false)String clerkArea,
                        @RequestParam(value = "clerkSpell",required = false)String clerkSpell,
                        @RequestParam(value = "clerkBirthday", required = false)
                        @DateTimeFormat(pattern="yyyy-MM-dd")
                                LocalDate clerkBirthday,
                        @RequestParam(value = "clerkTelphone",required = false) String clerkTelphone,
                        @RequestParam(value = "clerkLocation",required = false)String clerkLocation ){
        if (clerkService.addClerk(clerkName,clerkNo,clerkCompany,clerkPosition,
            clerkArea,clerkSpell,clerkBirthday,clerkTelphone,clerkLocation))
            return Msg.success();
        return Msg.fail();
    }

    @PostMapping("/delClerk")
    @ApiOperation(value = "删除销售员信息",httpMethod = "POST")
    public Msg delClerkById(Integer clerkId){
        if (clerkService.delClerkById(clerkId))
            return Msg.success();
        return Msg.fail();

    }
    @PostMapping("/getClerk")
    @ApiOperation(value = "根据传入条件查询销售员",httpMethod = "POST")
    public Msg getClerk(Integer clerkId, String clerkName, String clerkCompany, String clerkPosition,
                        String clerkArea, String clerkSpell, String clerkBirthday, String clerkTelphone,
                        String clerkLocation){
        List<Clerk> clerk = clerkService.getClerk(clerkId, clerkName, clerkCompany,
                                                  clerkPosition, clerkArea, clerkSpell,
                                                  clerkBirthday, clerkTelphone, clerkLocation);
        if (clerk!=null)
            return Msg.success().add("clerk",clerk);
        return Msg.fail();
    }

    @PostMapping("/updateClerk")
    @ApiOperation(value = "修改员工信息",httpMethod = "POST")
    public Msg updateClerk(Clerk clerk){
        if (clerk.getClerkId()!=null){
            clerkService.updateClerk(clerk);
            return Msg.success();
        }
        return Msg.fail();
    }
}
