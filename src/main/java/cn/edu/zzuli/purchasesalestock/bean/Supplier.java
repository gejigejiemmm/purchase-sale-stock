package cn.edu.zzuli.purchasesalestock.bean;


import lombok.Data;

@Data
public class Supplier {

    private Integer supplierId;
    private String supplierName;//供应商姓名
    private String supplierUnit;//供应商所属单位
    private Integer supplierNo;//供应商编号
    private String supplierSpell;//供应商拼音码
    private String supplierType;//供应商供货类型
    private String supplierBrand;//供应商产品的品牌
    private String supplierLocation;
    private String supplierTelphone;

    public Supplier(Integer supplierId, String supplierName, String supplierUnit, Integer supplierNo, String supplierSpell, String supplierType, String supplierBrand, String supplierLocation, String supplierTelphone) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierUnit = supplierUnit;
        this.supplierNo = supplierNo;
        this.supplierSpell = supplierSpell;
        this.supplierType = supplierType;
        this.supplierBrand = supplierBrand;
        this.supplierLocation = supplierLocation;
        this.supplierTelphone = supplierTelphone;
    }

    public Supplier() {
        super();
    }
}
