package cn.edu.zzuli.purchasesalestock.bean;

import lombok.Data;

import java.util.List;

@Data
public class Area {

    //城市名称
    private String cityName;

    //当前城市划分成区域的个数
    private Integer areaNum;

    //当前城市划分成区域的名称，可以唯一标识区域
    //例如：河南省郑州市A区， 河南省郑州市B区...
    private List<String> areaItemNames;

    //当前区域分管经理（预留）
    private Integer clerkId;

    public Area(String cityName, Integer areaNum, List<String> areaItemNames, Integer clerkId) {
        this.cityName = cityName;
        this.areaNum = areaNum;
        this.areaItemNames = areaItemNames;
        this.clerkId = clerkId;
    }

    public Area() {
        super();
    }

    @Override
    public String toString() {
        return "Area{" +
                "cityName='" + cityName + '\'' +
                ", areaNum=" + areaNum +
                ", areaItemNames=" + areaItemNames +
                ", clerkId=" + clerkId +
                '}';
    }
}
