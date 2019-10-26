package cn.edu.zzuli.purchasesalestock.utils;

public enum OrderType {

    //已经退货
    RETURN("用户已退货", 250),


    //配送状态
    BEFORE("商家已接单，请等待配货",100),
    ALLCATION("配货中",150),
    BEGIN("正在派送中",200),
    END("派送已完成",233),

    //支付状态
    TEACHER_AND_NO_PAY("导师代付（暂未支付）",300),
    ALI_PAY("支付宝支付",333),
    SUM_PAY("超过额度结算",335);



    private String msg;
    private int status;

    private OrderType(String msg, int status) {
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
