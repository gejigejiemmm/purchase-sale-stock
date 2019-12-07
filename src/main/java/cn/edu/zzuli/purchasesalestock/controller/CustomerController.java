package cn.edu.zzuli.purchasesalestock.controller;


import cn.edu.zzuli.purchasesalestock.bean.Customer;
import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.bean.ShoppingCart;
import cn.edu.zzuli.purchasesalestock.service.impl.CustomerserviceImpl;
import cn.edu.zzuli.purchasesalestock.service.impl.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/customer")
@Api(tags = "用户增删查改")
public class CustomerController{

    @Autowired
    private CustomerserviceImpl service;

    @Autowired
    private ShoppingCartService cartService;


    /*
    *  time:2019/10/19 17:15
    *  author:肖明珂
    *  target:实现用户的添加同时为每一个用户初始化创建一个购物车实体
    *  status:测试已经没有问题
     */
    @PostMapping(value = "/addCustomer")
    @ApiOperation(value = "添加用户",httpMethod = "POST")
    public Msg addCustomer(Customer customer){
        customer.setCustomerNo(90001+service.selectNumber()); //用户编号的生成90001加上数据库中用户的数目
        Integer no = 90001+service.selectNumber();
        boolean result = service.saveCustomer(customer);
        Customer cc = service.selectByNo(no);//取出用户，初始化购物车需要用到用户id
        ShoppingCart cart = new ShoppingCart();//初始化购物车实体类
        cart.setShoppingCartPersonId(cc.getCustomerId());//使用id 建立起购物车和用户之间的关联
        cart.setShoppingCartNo(cc.getCustomerId()+100);
        cart.setShoppingCartTotlePrice(0);
        cart.setShoppingCartDate(LocalDateTime.now());
        boolean result1 = cartService.saveShoppnigCart(cart);//保存创建好的购物车
        if(result&&result1){
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

    /*
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
    */
}
