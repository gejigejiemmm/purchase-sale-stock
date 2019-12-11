package cn.edu.zzuli.purchasesalestock.service.impl;


import cn.edu.zzuli.purchasesalestock.Mapper.CustomerMapper;
import cn.edu.zzuli.purchasesalestock.bean.Clerk;
import cn.edu.zzuli.purchasesalestock.bean.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CustomerserviceImpl {

    @Autowired
    private CustomerMapper mapper;



    /*
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
     */
    public String saveCustomer(@Param("customerName") String customerName, @Param("customerGradle") String customerGradle, @Param("customerRecord") String customerRecord,
                                @Param("customerMentor") String customerMentor, @Param("customerCollege") String customerCollege, @Param("customerInstitute") String customerInstitute,
                                @Param("customerPayForm") String customerPayForm, @Param("customerSpell") String customerSpell, @Param("customerLocation") String customerLocation,
                                @Param("customerTelphone") String customerTelphone, @Param("customerCompany") String customerCompany, @Param("customerPassword") String customerPassword,
                                @Param("clerkId") Integer clerkId){
        Customer customer = new Customer();
        customer.setCustomerCollege(customerCollege);
        customer.setCustomerCompany(customerCompany);
        customer.setCustomerGradle(customerGradle);
        customer.setCustomerInstitute(customerInstitute);
        customer.setCustomerPassword(customerPassword);
        customer.setCustomerName(customerName);
        customer.setCustomerMentor(customerMentor);
        customer.setCustomerPayForm(customerPayForm);
        customer.setCustomerTelphone(customerTelphone);
        customer.setCustomerSpell(customerSpell);
        customer.setCustomerRecord(customerRecord);
        customer.setCustomerInviter(clerkId);
        String customerNo = "";
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        customerNo = customerNo + formatter.format(date);
        Integer num = mapper.selectNumber() % 1000;
        customerNo = customerNo + num.toString();
        customer.setCustomerNo(customerNo); //用户编号的生成
        mapper.saveCustomer(customerName, customerGradle, customerRecord, customerMentor, customerCollege, customerInstitute, customerPayForm, customerSpell, customerLocation, customerTelphone, customerCompany, customerPassword, customerNo);
        return customerNo;
    }

    public boolean updateCustomer(Customer costomer){
        return mapper.updateCustomer(costomer);
    }

    public Customer selectById(Integer id){
        return mapper.selectById(id);
    }

    public Integer selectNumber(){
        return mapper.selectNumber();
    }

    public Customer selectByNo(String no) {
        return mapper.selectByNo(no);
    }

    public Customer selectByName(String name)
    {
        return mapper.selectByName(name);
    }

}
