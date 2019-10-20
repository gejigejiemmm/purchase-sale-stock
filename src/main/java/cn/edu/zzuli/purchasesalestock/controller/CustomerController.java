package cn.edu.zzuli.purchasesalestock.controller;


import cn.edu.zzuli.purchasesalestock.bean.Customer;
import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.service.impl.CustomerserviceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

@RestController
@RequestMapping("/customer")
@Api(tags = "用户增删查改")
public class CustomerController{

    @Autowired
    private CustomerserviceImpl service;


    /*
    *  time:2019/10/19 17:15
    *  author:肖明珂
    *  target:实现用户的添加
    *  status:测试已经没有问题
     */
    @PostMapping(value = "/addCustomer")
    @ApiOperation(value = "添加用户",httpMethod = "POST")
    public Msg addCustomer(Customer customer){
        boolean result = service.saveCustomer(customer);
        if(result){
            return Msg.success();
        }
        else{
            return Msg.fail();
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
            return Msg.fail();
        }
    }

}
