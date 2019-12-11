package cn.edu.zzuli.purchasesalestock.controller;


import cn.edu.zzuli.purchasesalestock.bean.Customer;
import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.bean.ShoppingCart;
import cn.edu.zzuli.purchasesalestock.service.ClerkService;
import cn.edu.zzuli.purchasesalestock.service.impl.CustomerserviceImpl;
import cn.edu.zzuli.purchasesalestock.service.impl.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/customer")
@Api(tags = "用户增删查改")
public class CustomerController{

    @Autowired
    private CustomerserviceImpl service;

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    ClerkService clerkService;


    /*
    *  time:2019/10/19 17:15
    *  author:肖明珂
    *  target:实现用户的添加同时为每一个用户初始化创建一个购物车实体
    *  status:测试已经没有问题
     */
    @PostMapping(value = "/addCustomer")
    @ApiOperation(value = "添加用户",httpMethod = "POST")
    public Msg addCustomer(@RequestParam(value = "customerName", required = false) String customerName,
                           @RequestParam(value = "customerGradle", required = false) String customerGradle,
                           @RequestParam(value = "customerRecord", required = false) String customerRecord,
                           @RequestParam(value = "customerMentor", required = false) String customerMentor,
                           @RequestParam(value = "customerCollege", required = false) String customerCollege,
                           @RequestParam(value = "customerInstitute", required = false) String customerInstitute,
                           @RequestParam(value = "customerPayForm", required = false) String customerPayForm,
                           @RequestParam(value = "customerSpell", required = false) String customerSpell,
                           @RequestParam(value = "customerLocation", required = false) String customerLocation,
                           @RequestParam(value = "customerTelphone", required = false) String customerTelphone,
                           @RequestParam(value = "customerCompany", required = false) String customerCompany,
                           @RequestParam(value = "customerPassword", required = false) String customerPassword,
                           @RequestParam(value = "CustomerInviterPhone", required = false) String inviterPhone){
        Integer clerkId = clerkService.getClerkByPhone(inviterPhone).getClerkId();
        String customerNo = service.saveCustomer(customerName, customerGradle, customerRecord, customerMentor, customerCollege, customerInstitute, customerPayForm, customerSpell, customerLocation, customerTelphone, customerCompany, customerPassword, clerkId);
        ShoppingCart cart = new ShoppingCart();//初始化购物车实体类
        cart.setShoppingCartPersonId(service.selectByNo(customerNo).getCustomerId());//使用id 建立起购物车和用户之间的关联
        cart.setShoppingCartNo(service.selectByNo(customerNo).getCustomerId()+100);
        cart.setShoppingCartTotlePrice(0);
        cart.setShoppingCartDate(LocalDateTime.now());
        boolean result1 = cartService.saveShoppnigCart(cart);//保存创建好的购物车
        if(result1){
            return Msg.success();
        }
        else{
            return Msg.fail().add("error", "服务器错误，请联系系统管理员");
        }
    }

    /*
     *  time:2019/10/19 17:32
     *  author:肖明珂
     *  target:实现用户的查询（id），暂时用于更新信息模态框的回显
     *  status:测试完成
     */
    @GetMapping(value = "/getCustomer")
    @ApiOperation(value = "按照ID查询用户", httpMethod = "GET")
    public Msg updateCustomer(@RequestParam("uid") Integer id){
        Customer customer = service.selectById(id);
        if(customer != null){
            return Msg.success().add("customer", customer);
        }
        else{
            return Msg.fail().add("error", "服务器错误，请联系系统管理员");
        }
    }


    @GetMapping(value = "/loginPage")
    @ApiOperation(value = "前往登录页面", httpMethod = "GET")
    public ModelAndView loginPage()
    {
        ModelAndView mv = new ModelAndView("/behind/login");
        return mv;
    }

    @PostMapping(value = "/loginin")
    @ApiOperation(value = "登录请求", httpMethod = "POST")
    public ModelAndView loginin(@RequestParam(value = "name", required = true) String customerName,
                       @RequestParam(value = "password", required = true) String customerPassword)
    {
        ModelAndView mv = new ModelAndView();
        Customer cc = service.selectByName(customerName);
        if(customerPassword.equals(cc.getCustomerPassword())){
            mv.setViewName("/behind/display");
            return mv;
        }
        else{
            mv.setViewName("/behind/login");
            return mv;
        }
    }

}
