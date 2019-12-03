package cn.edu.zzuli.purchasesalestock.bean;

import lombok.Data;

import java.util.List;

@Data
public class Address {

    private Integer addressId;
    //市
    private String addressCity;
    //区
    private String addressTown;
    //乡（可为空）
    private String addressVillage;
    //详细地址
    private String addressDetail;

    //地址所对应的仓库
    private Bin bin;

    public Address(Integer addressId, String addressCity, String addressTown, String addressVillage, String addressDetail) {
        this.addressId = addressId;
        this.addressCity = addressCity;
        this.addressTown = addressTown;
        this.addressVillage = addressVillage;
        this.addressDetail = addressDetail;
    }

    public Address() {
    }
}
