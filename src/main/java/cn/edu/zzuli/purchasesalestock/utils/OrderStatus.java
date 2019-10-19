package cn.edu.zzuli.purchasesalestock.utils;

public enum OrderStatus {
    BEFORE("商家已接单，请等待配货",100),
    BEGIN("正在派送中",200),
    END("派送已完成",900);

    private String msg;
    private int status;

    private OrderStatus(String msg, int status) {
        this.msg = msg;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public int getStatus() {
        return status;
    }

    public static String getMsgByStatus(Integer status) {
        for (OrderStatus order : OrderStatus.values()) {
            if(order.getStatus() == status)
                return order.getMsg();
        }
        return null;
    }
}
