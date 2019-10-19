package cn.edu.zzuli.purchasesalestock.controller;

import cn.edu.zzuli.purchasesalestock.bean.Bin;
import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.service.BinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bin")
@Api(tags = "仓库接口")
public class BinController {

    @Autowired
    BinService binService;

    @GetMapping("/getAll")
    @ApiOperation(value = "获取所有的仓库信息",httpMethod = "GET")
    public Msg getBins(){
        List<Bin> bins = binService.getBins();
        return Msg.success().add("bins",bins);
    }

}
