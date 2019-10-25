package cn.edu.zzuli.purchasesalestock.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
/**
 * 配货单或者调货单
 *
 * allocation_order_id 对应的虽然是订单，
 * 但逻辑上来讲也是的配货单
 * 因为在 对配货员来将，他是要看着配货商品调货的
 * 而本实体类 是对 商品调整的一个记录
 *
 * 本实体类的信息 应该所属于仓库管理员（仓库管理员查看这些日志信息）
 */
public class GoodsAllocation {
    private Integer allocationId;
    private Integer allocationEId;
    private Integer allocationOrderId;
    private Integer allocationStatus;
    private Integer allocationFromId;
    private Integer allocationToId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime allocationCreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime allocationEndTime;

    //订单对应的商品信息
    private List<Goods> goods;

    public GoodsAllocation() {}

    public GoodsAllocation(Integer allocationId, Integer allocationEId, Integer allocationOrderId,
                           Integer allocationStatus, Integer allocationFromId, Integer allocationToId,
                           LocalDateTime allocationCreateTime, LocalDateTime allocationEndTime) {
        this.allocationId = allocationId;
        this.allocationEId = allocationEId;
        this.allocationOrderId = allocationOrderId;
        this.allocationStatus = allocationStatus;
        this.allocationFromId = allocationFromId;
        this.allocationToId = allocationToId;
        this.allocationCreateTime = allocationCreateTime;
        this.allocationEndTime = allocationEndTime;
    }
}
