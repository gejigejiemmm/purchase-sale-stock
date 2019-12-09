package cn.edu.zzuli.purchasesalestock.service;

import cn.edu.zzuli.purchasesalestock.bean.Send;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public interface SendService {

    List<Send> getSends(Integer sendBinId, Integer sendEId,Integer sendToUId,
                        Integer sendId,Integer sendStatus,LocalDateTime sendCreateTime,
                        LocalDateTime sendEndTime);
}
