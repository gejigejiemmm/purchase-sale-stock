package cn.edu.zzuli.purchasesalestock.Mapper;


import cn.edu.zzuli.purchasesalestock.bean.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerMapper {

    boolean saveCustomer(@Param("customerName") String customerName, @Param("customerGradle") String customerGradle, @Param("customerRecord") String customerRecord,
                                @Param("customerMentor") String customerMentor, @Param("customerCollege") String customerCollege, @Param("customerInstitute") String customerInstitute,
                                @Param("customerPayForm") String customerPayForm, @Param("customerSpell") String customerSpell, @Param("customerLocation") String customerLocation,
                                @Param("customerTelphone") String customerTelphone, @Param("customerCompany") String customerCompany, @Param("customerPassword") String customerPassword, @Param("customerNo") String customerNo);

    boolean updateCustomer(Customer customer);

    Customer selectById(Integer id);

    Integer selectNumber();

    Customer selectByNo(String no);

    Customer selectByName(String name);


    @Select("SELECT * FROM customer where customer_name = #{customerName}")
    @ResultMap("customer")
    Customer getCustomerByName(String customerName);

}
