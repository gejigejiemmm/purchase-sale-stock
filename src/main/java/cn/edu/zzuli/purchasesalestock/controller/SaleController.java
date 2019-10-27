package cn.edu.zzuli.purchasesalestock.controller;

import cn.edu.zzuli.purchasesalestock.bean.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/sale")
@Api(tags = "销售单接口")
public class SaleController {

    /**
     * 我觉得这个 controller 里应该没有太多方法
     *
     * 一个条件查询应该够了（坐等打脸？？）
     * @return
     */
    @GetMapping("/get")
    @ApiOperation(value = "条件获取销售单信息，saleType 是销售单类型, binId是仓库类型，所有值非必须")
    public Msg getSales(Integer saleType,Integer saleStatu,Integer binId,
                        @RequestParam(value = "saleCreateTime",required = false)
                        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime saleCreateTime,
                        @RequestParam(value = "saleEndTime",required = false)
                        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime saleEndTime) {

        return Msg.success();
    }

}
