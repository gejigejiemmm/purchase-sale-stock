package cn.edu.zzuli.purchasesalestock.Mapper;


import cn.edu.zzuli.purchasesalestock.bean.Customer;
import org.apache.ibatis.annotations.Mapper;
<<<<<<< Updated upstream
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
=======
>>>>>>> Stashed changes

@Mapper
public interface CustomerMapper {

    boolean saveCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    Customer selectById(Integer id);

    Integer selectNumber();

    Customer selectByNo(String no);

    Customer selectByName(String name);


    @Select("SELECT * FROM customer where customer_name = #{customerName}")
    @ResultMap("customer")
    Customer getCustomerByName(String customerName);

}
