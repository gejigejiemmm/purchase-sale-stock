package cn.edu.zzuli.purchasesalestock.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ShoppingCart_detail {

    private Integer shoppingcart_did;
    private Integer shoppingcart_dno;
    private Integer shoppingcart_dgoodsId;
    private Integer shoppingcart_dgoodsNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime shoppingcart_dgoodsDate;
    private double shoppingcart_dtotlePrice;

    public ShoppingCart_detail(Integer shoppingcart_did, Integer shoppingcart_dno, Integer shoppingcart_dgoodsId, Integer shoppingcart_dgoodsNumber, LocalDateTime shoppingcart_dgoodsDate, double shoppingcart_dtotlePrice) {
        this.shoppingcart_did = shoppingcart_did;
        this.shoppingcart_dno = shoppingcart_dno;
        this.shoppingcart_dgoodsId = shoppingcart_dgoodsId;
        this.shoppingcart_dgoodsNumber = shoppingcart_dgoodsNumber;
        this.shoppingcart_dgoodsDate = shoppingcart_dgoodsDate;
        this.shoppingcart_dtotlePrice = shoppingcart_dtotlePrice;
    }

    public ShoppingCart_detail() {
        super();
    }
}
