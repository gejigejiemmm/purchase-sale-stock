package cn.edu.zzuli.purchasesalestock.service;

import cn.edu.zzuli.purchasesalestock.bean.Send;
import cn.edu.zzuli.purchasesalestock.bean.SendDetail;
import com.github.pagehelper.PageInfo;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public interface SendService {

    PageInfo getSends(Integer p, Integer sendBinId, Integer sendEId, Integer sendToUId,
                      Integer sendId, Integer sendStatus, LocalDateTime sendCreateTime,
                      LocalDateTime sendEndTime);

    SendDetail getDetail(Integer sendId);
}
