package cn.edu.zzuli.purchasesalestock.bean;

import lombok.Data;

//商品信息
@Data
public class Goods {
    private Integer goodsId;
    private String goodsChName;
    private String goodsTrivialName;
    private String goodsEnName;
    private String goodsTypeId;
    private Integer goodsNo;
    private String goodsCas;
    private String goodsPinyin;
    private String goodsMolecularFormula;
    private String goodsBrandId;
    private String goodsModelId;
    private String goodsUnit;
    private String goodsForm;
    private String goodsCondition;
    private String goodsBin;
    private String goodsLocation;
    private String goodsInitPrice;
    private String goodsAvgPrice;
    private String goodsLowPrice;
    private String goodsSalePrice1;
    private String goodsSalePrice2;
    private String goodsPrice;
    private String goodsImageUrl;
    private String goodsProducersId;
    private String goodsSupplierId;

    public Goods(){}

    public Goods
            (Integer goodsId, String goodsChName, String goodsTrivialName,
             String goodsEnName, String goodsTypeId, Integer goodsNo, String goodsCas,
             String goodsPinyin, String goodsMolecularFormula, String goodsBrandId,
             String goodsModelId, String goodsUnit, String goodsForm, String goodsCondition,
             String goodsBin, String goodsLocation, String goodsInitPrice, String goodsAvgPrice,
             String goodsLowPrice, String goodsSalePrice1, String goodsSalePrice2, String goodsPrice,
             String goodsImageUrl, String goodsProducersId, String goodsSupplierId
            ) {
        this.goodsId = goodsId;
        this.goodsChName = goodsChName;
        this.goodsTrivialName = goodsTrivialName;
        this.goodsEnName = goodsEnName;
        this.goodsTypeId = goodsTypeId;
        this.goodsNo = goodsNo;
        this.goodsCas = goodsCas;
        this.goodsPinyin = goodsPinyin;
        this.goodsMolecularFormula = goodsMolecularFormula;
        this.goodsBrandId = goodsBrandId;
        this.goodsModelId = goodsModelId;
        this.goodsUnit = goodsUnit;
        this.goodsForm = goodsForm;
        this.goodsCondition = goodsCondition;
        this.goodsBin = goodsBin;
        this.goodsLocation = goodsLocation;
        this.goodsInitPrice = goodsInitPrice;
        this.goodsAvgPrice = goodsAvgPrice;
        this.goodsLowPrice = goodsLowPrice;
        this.goodsSalePrice1 = goodsSalePrice1;
        this.goodsSalePrice2 = goodsSalePrice2;
        this.goodsPrice = goodsPrice;
        this.goodsImageUrl = goodsImageUrl;
        this.goodsProducersId = goodsProducersId;
        this.goodsSupplierId = goodsSupplierId;
    }
}
