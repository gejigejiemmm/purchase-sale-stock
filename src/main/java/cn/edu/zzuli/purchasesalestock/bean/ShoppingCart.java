package cn.edu.zzuli.purchasesalestock.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ShoppingCart {

    private Integer shoppingCartId;
    private Integer shoppingCartNo;
    private double shoppingCartTotlePrice;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime shoppingCartDate;
    private Integer shoppingCartPersonId;

    public ShoppingCart(Integer shoppingCartId, Integer shoppingCartNo, double shoppingCartTotlePrice, LocalDateTime shoppingCartDate, Integer shoppingCartPersonId) {
        this.shoppingCartId = shoppingCartId;
        this.shoppingCartNo = shoppingCartNo;
        this.shoppingCartTotlePrice = shoppingCartTotlePrice;
        this.shoppingCartDate = shoppingCartDate;
        this.shoppingCartPersonId = shoppingCartPersonId;
    }

    public ShoppingCart() {
        super();
    }
}
