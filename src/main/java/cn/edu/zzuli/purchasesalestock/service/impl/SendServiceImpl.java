package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.Mapper.SendMapper;
import cn.edu.zzuli.purchasesalestock.bean.Send;
import cn.edu.zzuli.purchasesalestock.service.SendService;
import cn.edu.zzuli.purchasesalestock.utils.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SendServiceImpl implements SendService {

    @Autowired
    SendMapper sendMapper;

    @Override
    public List<Send> getSends(Integer sendBinId, Integer sendEId, Integer sendToUId,
                               Integer sendId, Integer sendStatus, LocalDateTime sendCreateTime,
                               LocalDateTime sendEndTime) {
        Map<String,Object> info = new HashMap<>();
        BaseUtils.initInfo(info,"sendBinId",sendBinId,"sendEId",sendEId,"sendToUId",sendToUId,
        "sendId",sendId,"sendStatus",sendStatus,"sendCreateTime",sendCreateTime,"sendEndTime",sendEndTime);
        List<Send> sends = sendMapper.getSends(info);
        return sends;
    }

}
