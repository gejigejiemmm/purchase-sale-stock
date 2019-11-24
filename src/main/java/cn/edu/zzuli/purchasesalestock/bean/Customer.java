package cn.edu.zzuli.purchasesalestock.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Customer {

    private Integer customerId;
    private String customerName;
    private String customerGradle;
    private String customerRecord;
    private String customerMentor;
    private String customerCollege;
    private String customerInstitute;
    private String customerPayForm;
    private Integer customerNo;
    private String customerSpell;
    private Integer customerLocation;
    private Integer customerLocationStandby1;
    private Integer customerLocationStandby2;
    private String customerTelphone;
    private String customerCompany;
    private Integer customerWarehouse;
    private Integer customerInviter;
    private String customerPassword;

    public Customer(Integer customerId, String customerName, String customerGradle, String customerRecord, String customerMentor, String customerCollege, String customerInstitute, String customerPayForm, Integer customerNo, String customerSpell, Integer customerLocation, Integer customerLocationStandby1, Integer customerLocationStandby2, String customerTelphone, String customerCompany, Integer customerWarehouse, Integer customerInviter) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerGradle = customerGradle;
        this.customerRecord = customerRecord;
        this.customerMentor = customerMentor;
        this.customerCollege = customerCollege;
        this.customerInstitute = customerInstitute;
        this.customerPayForm = customerPayForm;
        this.customerNo = customerNo;
        this.customerSpell = customerSpell;
        this.customerLocation = customerLocation;
        this.customerLocationStandby1 = customerLocationStandby1;
        this.customerLocationStandby2 = customerLocationStandby2;
        this.customerTelphone = customerTelphone;
        this.customerCompany = customerCompany;
        this.customerWarehouse = customerWarehouse;
        this.customerInviter = customerInviter;
    }

    public Customer() {
        super();
    }
}
