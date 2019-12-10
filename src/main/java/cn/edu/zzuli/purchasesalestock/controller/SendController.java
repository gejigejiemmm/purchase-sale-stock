package cn.edu.zzuli.purchasesalestock.controller;

import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.bean.Send;
import cn.edu.zzuli.purchasesalestock.service.SendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/send")
@Api(tags = "派送单接口")
public class SendController {

    @Autowired
    SendService sendService;


    @GetMapping("/get")
    @ApiOperation(value = "条件获取派送单信息，binId必须，其他非必须",httpMethod = "GET")
    public Msg get(@RequestParam(value = "binId")Integer sendBinId, Integer sendEId,Integer sendToUId,
                   Integer sendId,Integer sendStatus,
                   @RequestParam(value = "sendCreateTime",required = false)
                       @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime sendCreateTime,
                   @RequestParam(value = "sendEndTime",required = false)
                       @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime sendEndTime){

        List<Send> sends = sendService.getSends(sendBinId, sendEId, sendToUId, sendId, sendStatus, sendCreateTime, sendEndTime);
        if (sends != null){
            return Msg.success().add("sends",sends);
        }
        return Msg.fail();
    }


    @GetMapping("/detail")
    @ApiOperation(value = "获取派送单详情，需要传入sendId",httpMethod = "GET")
    public  Msg getDetail(@RequestParam(value = "sendId",required = true) Integer sendId){



        return Msg.success();
    }


}
