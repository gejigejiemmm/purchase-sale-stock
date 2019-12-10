package cn.edu.zzuli.purchasesalestock.utils;


public enum SendType {

    //理想送货状态，双方都确认收货，派送完成。
    BEFORE_SEND("配货完毕，等待送货",500),
    BEGIN_SEND("正在送货",510),
    FINISH_CUSTOMER("用户确认收货",520),
    FINISH_SENDER("派送员确认收货", 530),

    //可能会出现的情况
    FINISH_CUSTOMER_NOBUTTON("用户收货但是没有点击确认",540),
    CUSTOMER_NOACCEPT("用户拒收",550),
    RETURN_BEGIN("开始退货",560),
    RETURN_FINISH("退货完成",570);



    private String msg;
    private int status;

    private SendType(String msg, int status) {
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
