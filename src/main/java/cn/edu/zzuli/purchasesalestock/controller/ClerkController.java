package cn.edu.zzuli.purchasesalestock.controller;

import cn.edu.zzuli.purchasesalestock.bean.Clerk;
import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.service.ClerkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("/clerk")
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
                        @RequestParam(value = "clerkLocation",required = false)String clerkLocation,
                        @RequestParam(value = "clerkPassword",required = false)String clerkPassword){
        if (clerkService.addClerk(clerkName,clerkNo,clerkCompany,clerkPosition,
            clerkArea,clerkSpell,clerkBirthday,clerkTelphone,clerkLocation, clerkPassword))
            return Msg.success();
        return Msg.fail();
    }

    @PostMapping("/delClerk")
    @ApiOperation(value = "删除销售员信息",httpMethod = "POST")
    public Msg delClerkById(@RequestParam(value = "clerkId",required = false) Integer clerkId){
        if (clerkService.delClerkById(clerkId))
            return Msg.success();
        return Msg.fail();

    }

    @PostMapping("/getClerkByConsitionsByLimit")
    @ApiOperation(value = "根据传入条件查询销售员(分页)",httpMethod = "POST")
    public Msg getClerk(@RequestParam(value = "clerkCompany",required = false) String clerkCompany,
                        @RequestParam(value = "clerkPosition",required = false) String clerkPosition,
                        @RequestParam(value = "clerkArea",required = false) String clerkArea,
                        @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                        @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit){
        Collection<Clerk> clerks = clerkService.getClerkByConsitionsByLimit(clerkCompany, clerkPosition, clerkArea, page, limit);
        Integer count = clerkService.getClerkByConsitions(clerkCompany, clerkPosition, clerkArea).size();
        if (clerks!=null){
            return Msg.success().add("data",clerks).add("count", count);
        }
        else{
            return Msg.fail();
        }
    }

    @PostMapping("/updateClerk")
    @ApiOperation(value = "修改员工信息",httpMethod = "POST")
    public Msg updateClerk(@RequestParam(value = "clerkId",required = false)Integer clerkId,
                            @RequestParam(value = "clerkName",required = false)String clerkName,
                           @RequestParam(value = "clerkCompany",required = false)String clerkCompany,
                           @RequestParam(value = "clerkPosition",required = false)String clerkPosition,
                           @RequestParam(value = "clerkArea",required = false)String clerkArea,
                           @RequestParam(value = "clerkSpell",required = false)String clerkSpell,
                           @RequestParam(value = "clerkBirthday", required = false)
                               @DateTimeFormat(pattern="yyyy-MM-dd")
                                       LocalDate clerkBirthday,
                           @RequestParam(value = "clerkTelphone",required = false) String clerkTelphone,
                           @RequestParam(value = "clerkLocation",required = false)String clerkLocation,
                           @RequestParam(value = "clerkPassword",required = false)String clerkPassword)
    {
        boolean result = clerkService.updateClerk(clerkId, clerkName, clerkCompany, clerkPosition, clerkArea, clerkSpell, clerkBirthday, clerkTelphone, clerkLocation, clerkPassword);
        if(result) {
            return Msg.success();
        }
        else{
            return Msg.fail().add("error", "网络故障，请稍后重试");
        }
    }

    @GetMapping(value = "/loginPage")
    @ApiOperation(value = "前往登录页面", httpMethod = "GET")
    public ModelAndView loginPage()
    {
        ModelAndView mv = new ModelAndView("/behind/login");
        return mv;
    }

    @GetMapping(value = "/getClerksByLimit")
    @ApiOperation(value = "雇员的分页查询", httpMethod = "GET")
    public Msg getClerksByLimit(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                                @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit)
    {
        Collection<Clerk> result = clerkService.getClerksByLimit(page, limit);
        Integer count = clerkService.getAllClerks().size();
        if(result != null){
            return Msg.success().add("data", result).add("count", count);
        }
        else{
            return Msg.fail().add("error", "网络异常，请稍后重试");
        }
    }

}
