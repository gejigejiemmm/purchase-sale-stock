package cn.edu.zzuli.purchasesalestock.bean;

import lombok.Data;

@Data
public class Bin {
    private Integer binId;
    private String binName;
    private Integer binNo;
    private String binAdmin;
    private String binAdminPhone;
    private String binTips;

    public Bin(){}

    public Bin(Integer binId, String binName, Integer binNo, String binAdmin, String binAdminPhone, String binTips) {
        this.binId = binId;
        this.binName = binName;
        this.binNo = binNo;
        this.binAdmin = binAdmin;
        this.binAdminPhone = binAdminPhone;
        this.binTips = binTips;
    }
}
