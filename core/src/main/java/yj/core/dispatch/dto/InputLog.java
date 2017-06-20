package yj.core.dispatch.dto;

/**Auto Generated By Hap Code Generator**/

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;


@ExtensionAttribute(disable=true)
@Table(name = "confirmation_input_log")
public class InputLog extends BaseDTO {
     @Id
     @GeneratedValue
      private Long id;

    /**
     *条码
     */
    private String barcode; //条码

    private String orderno; //订单

    private String dispatch; //派工单号

    private String operation; //工序

    private Double yeild; //合格数量

    private Double workScrap; //工废数量

    private Double rowScrap; //料废数量

    private String classno; //班次

    private String classgrp; //班组

    private String shopCode; //车间编码

    private String line; //生产线

    private String modelNo;//模号

    private String plant; //工厂

    private String  postingDate; //过账日期

    private String postingDateAfter;

    private String postingDateBefore ;

    private String  dispatchLogicID; //派工单流水号

    private String attr1; //属性1

    private String attr2; //属性2

    private String attr3; //属性3

    private String attr4; //属性4

    private String attr5; //属性5

    private String attr6; //属性6

    private String attr7; //属性7

    private String attr8; //属性8

    private String attr9; //属性9

    private String attr10; //属性10

    private String attr11; //属性11

    private String attr12; //属性12

    private String attr13; //属性13

    private String attr14; //属性14

    private String attr15; //属性15

    private String creatDate;

    private String creatDateAfter;

    private String creatDateBefore;

    @Transient
    private String matDesc;//产品名称

    @Transient
    private String material;//产品物料号

    @Transient
    private  String userName;//用户姓名

    @Transient
    private String msgty;//是否成功

    @Transient
    private String msgtx;//信息


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getDispatch() {
        return dispatch;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getYeild() {
        return yeild;
    }

    public void setYeild(Double yeild) {
        this.yeild = yeild;
    }

    public Double getWorkScrap() {
        return workScrap;
    }

    public void setWorkScrap(Double workScrap) {
        this.workScrap = workScrap;
    }

    public Double getRowScrap() {
        return rowScrap;
    }

    public void setRowScrap(Double rowScrap) {
        this.rowScrap = rowScrap;
    }

    public String getClassno() {
        return classno;
    }

    public void setClassno(String classno) {
        this.classno = classno;
    }

    public String getClassgrp() {
        return classgrp;
    }

    public void setClassgrp(String classgrp) {
        this.classgrp = classgrp;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    public String getAttr5() {
        return attr5;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }

    public String getAttr6() {
        return attr6;
    }

    public void setAttr6(String attr6) {
        this.attr6 = attr6;
    }

    public String getAttr7() {
        return attr7;
    }

    public void setAttr7(String attr7) {
        this.attr7 = attr7;
    }

    public String getAttr8() {
        return attr8;
    }

    public void setAttr8(String attr8) {
        this.attr8 = attr8;
    }

    public String getAttr9() {
        return attr9;
    }

    public void setAttr9(String attr9) {
        this.attr9 = attr9;
    }

    public String getAttr10() {
        return attr10;
    }

    public void setAttr10(String attr10) {
        this.attr10 = attr10;
    }

    public String getAttr11() {
        return attr11;
    }

    public void setAttr11(String attr11) {
        this.attr11 = attr11;
    }

    public String getAttr12() {
        return attr12;
    }

    public void setAttr12(String attr12) {
        this.attr12 = attr12;
    }

    public String getAttr13() {
        return attr13;
    }

    public void setAttr13(String attr13) {
        this.attr13 = attr13;
    }

    public String getAttr14() {
        return attr14;
    }

    public void setAttr14(String attr14) {
        this.attr14 = attr14;
    }

    public String getAttr15() {
        return attr15;
    }

    public void setAttr15(String attr15) {
        this.attr15 = attr15;
    }

    public String getMatDesc() {
        return matDesc;
    }

    public void setMatDesc(String matDesc) {
        this.matDesc = matDesc;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    public String getPostingDateAfter() {
        return postingDateAfter;
    }

    public void setPostingDateAfter(String postingDateAfter) {
        this.postingDateAfter = postingDateAfter;
    }

    public String getPostingDateBefore() {
        return postingDateBefore;
    }

    public void setPostingDateBefore(String postingDateBefore) {
        this.postingDateBefore = postingDateBefore;
    }

    public String getCreatDateAfter() {
        return creatDateAfter;
    }

    public void setCreatDateAfter(String creatDateAfter) {
        this.creatDateAfter = creatDateAfter;
    }

    public String getCreatDateBefore() {
        return creatDateBefore;
    }

    public void setCreatDateBefore(String creatDateBefore) {
        this.creatDateBefore = creatDateBefore;
    }

    public String getDispatchLogicID() {
        return dispatchLogicID;
    }

    public void setDispatchLogicID(String dispatchLogicID) {
        this.dispatchLogicID = dispatchLogicID;
    }

    public String getMsgty() {
        return msgty;
    }

    public void setMsgty(String msgty) {
        this.msgty = msgty;
    }

    public String getMsgtx() {
        return msgtx;
    }

    public void setMsgtx(String msgtx) {
        this.msgtx = msgtx;
    }
}
