package cn.edu.zzuli.purchasesalestock.service.impl;


import cn.edu.zzuli.purchasesalestock.Mapper.CustomerMapper;
import cn.edu.zzuli.purchasesalestock.bean.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerserviceImpl {

    @Autowired
    private CustomerMapper mapper;

    public boolean saveCustomer(Customer customer){
        return mapper.saveCustomer(customer);
    }

    public boolean updateCustomer(Customer costomer){
        return mapper.updateCustomer(costomer);
    }

    public Customer selectById(Integer id){
        return mapper.selectById(id);
    }
}
