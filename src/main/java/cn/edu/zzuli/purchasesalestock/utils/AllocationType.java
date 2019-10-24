package cn.edu.zzuli.purchasesalestock.utils;

public enum AllocationType {

    //配货状态
    BEFORE_ALLOCATION("尚未开始调货",400),
    NO_GOODS("缺货",404),
    FINISH_ALLOCATION("调货完成",410),

    //商品调拨类型
    OUT_ALLOCATION("商品出库",460),
    IN_ALLOCATION("商品入库",480);


    private String msg;
    private int status;

    private AllocationType(String msg, int status) {
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
        for (OrderType order : OrderType.values()) {
            if(order.getStatus() == status)
                return order.getMsg();
        }
        return null;
    }
}
