package cn.edu.zzuli.purchasesalestock.Mapper;


import cn.edu.zzuli.purchasesalestock.bean.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {

    public boolean saveCustomer(Customer customer);

    public boolean updateCustomer(Customer customer);

    public Customer selectById(Integer id);

    public Integer selectNumber();

    public Customer selectByNo(Integer no);
}
