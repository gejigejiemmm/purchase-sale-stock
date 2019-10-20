package cn.edu.zzuli.purchasesalestock.bean;

import lombok.Data;

@Data
public class Bin {

    private Integer binId;
    private String binName;
    private Integer binAdminId;
    private String binTips;
    private String binAddressId;

    public Bin(){}

    public Bin(Integer binId, String binName, Integer binAdminId, String binTips, String binAddressId) {
        this.binId = binId;
        this.binName = binName;
        this.binAdminId = binAdminId;
        this.binTips = binTips;
        this.binAddressId = binAddressId;
    }
}
