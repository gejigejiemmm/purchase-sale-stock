package cn.edu.zzuli.purchasesalestock.bean;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ReturnGoods_detail {

    private Integer returnGoods_did;
    private Integer returnGoods_dno;
    private Integer returnGoods_dgoodid;
    private double returnGoods_dgoodPrice;
    private String returnGoods_dresult;
    private LocalDateTime returnGoods_ddate;

    public ReturnGoods_detail(Integer returnGoods_did, Integer returnGoods_dno, Integer returnGoods_dgoodid, double returnGoods_dgoodPrice, String returnGoods_dresult, LocalDateTime returnGoods_ddate) {
        this.returnGoods_did = returnGoods_did;
        this.returnGoods_dno = returnGoods_dno;
        this.returnGoods_dgoodid = returnGoods_dgoodid;
        this.returnGoods_dgoodPrice = returnGoods_dgoodPrice;
        this.returnGoods_dresult = returnGoods_dresult;
        this.returnGoods_ddate = returnGoods_ddate;
    }

    public ReturnGoods_detail() {
        super();
    }

}