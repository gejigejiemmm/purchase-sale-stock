package cn.edu.zzuli.purchasesalestock.bean;

import java.util.List;

public class AreaItem {

    private Integer areaItemId;

    //区域名
    private String areaName;

    //该区域所管辖的大学名称集合
    private List<String> collegeNames;

    //当前区域属于哪些仓库配货（id）
    private List<Integer> binid;

    //区域所属id，对应area
    private Integer areaId;

    public AreaItem(Integer areaItemId, String areaName, List<String> collegeNames, List<Integer> binid, Integer areaId) {
        this.areaItemId = areaItemId;
        this.areaName = areaName;
        this.collegeNames = collegeNames;
        this.binid = binid;
        this.areaId = areaId;
    }

    public AreaItem() {
        super();
    }

    @Override
    public String toString() {
        return "AreaItem{" +
                "areaItemId=" + areaItemId +
                ", areaName='" + areaName + '\'' +
                ", collegeNames=" + collegeNames +
                ", binid=" + binid +
                ", areaId=" + areaId +
                '}';
    }
}
