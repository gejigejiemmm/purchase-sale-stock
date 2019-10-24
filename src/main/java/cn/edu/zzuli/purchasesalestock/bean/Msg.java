package cn.edu.zzuli.purchasesalestock.bean;


import java.util.HashMap;
import java.util.Map;

public class Msg {

    /**
     * -0 未知
     * -100 成功
     * -200 失败
     */
    private int code;

    /**
     * 传给前端的信息
     */
    private String msg;

    /**
     * 传给前端的数据
     */
    private Map<String , Object> data = new HashMap<>();

    public static Msg success() {
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("success ヽ(ﾟ▽ﾟ)ノ");
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("error ：）");
        return result;
    }

    public Msg add(String key , Object value) {
        this.getData().put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> extend) {
        this.data = extend;
    }


}
