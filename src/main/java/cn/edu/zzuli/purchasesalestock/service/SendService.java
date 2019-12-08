package cn.edu.zzuli.purchasesalestock.service;

import cn.edu.zzuli.purchasesalestock.bean.Send;

import java.util.List;

public interface SendService {

    List<Send> getSends(Integer binId);
}
