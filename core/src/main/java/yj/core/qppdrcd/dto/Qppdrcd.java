package yj.core.qppdrcd.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hand.hap.system.dto.BaseDTO;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
@ExtensionAttribute(disable=true)
@Table(name = "wip_qppdrcd")
public class Qppdrcd extends BaseDTO {
     @Id
     @GeneratedValue
      private String rcdid; //记录号

     @NotEmpty
      private String rcdtype; //记录类型

     @NotEmpty
      private String werks; //工厂

     @NotEmpty
      private String fevor; //车间

     @NotEmpty
      private String fevortxt; //车间名称

     @NotEmpty
      private String zxhbar; //毛坯筐码

     @NotEmpty
      private String lineId; //产线编码

     @NotEmpty
      private String lineName; //产线名称

     @NotEmpty
      private String matnr; //物料号

     @NotEmpty
      private String maktx; //物料描述

      private Double num; //账面数量

      private Double pdnum; //盘点数量

     @NotEmpty
      private String operator; //盘点者员工编号

      private String rcddat; //盘点日期

      private String  zbeiz;

      private String pddatbefore;

      private String pddatafter;

    public String getPddatbefore() {
        return pddatbefore;
    }

    public void setPddatbefore(String pddatbefore) {
        this.pddatbefore = pddatbefore;
    }

    public String getPddatafter() {
        return pddatafter;
    }

    public void setPddatafter(String pddatafter) {
        this.pddatafter = pddatafter;
    }

    @Transient
    private Long createdBy; //创建人
    @Transient
    private Date creationDate; //创建时间

    @Override
    public Long getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getZbeiz() {
        return zbeiz;
    }

    public void setZbeiz(String zbeiz) {
        this.zbeiz = zbeiz;
    }

    public void setRcdid(String rcdid){
         this.rcdid = rcdid;
     }

     public String getRcdid(){
         return rcdid;
     }

     public void setRcdtype(String rcdtype){
         this.rcdtype = rcdtype;
     }

     public String getRcdtype(){
         return rcdtype;
     }

     public void setWerks(String werks){
         this.werks = werks;
     }

     public String getWerks(){
         return werks;
     }

     public void setFevor(String fevor){
         this.fevor = fevor;
     }

     public String getFevor(){
         return fevor;
     }

     public void setFevortxt(String fevortxt){
         this.fevortxt = fevortxt;
     }

     public String getFevortxt(){
         return fevortxt;
     }

     public void setZxhbar(String zxhbar){
         this.zxhbar = zxhbar;
     }

     public String getZxhbar(){
         return zxhbar;
     }

     public void setLineId(String lineId){
         this.lineId = lineId;
     }

     public String getLineId(){
         return lineId;
     }

     public void setLineName(String lineName){
         this.lineName = lineName;
     }

     public String getLineName(){
         return lineName;
     }

     public void setMatnr(String matnr){
         this.matnr = matnr;
     }

     public String getMatnr(){
         return matnr;
     }

     public void setMaktx(String maktx){
         this.maktx = maktx;
     }

     public String getMaktx(){
         return maktx;
     }

     public void setNum(Double num){
         this.num = num;
     }

     public Double getNum(){
         return num;
     }

     public void setPdnum(Double pdnum){
         this.pdnum = pdnum;
     }

     public Double getPdnum(){
         return pdnum;
     }

     public void setOperator(String operator){
         this.operator = operator;
     }

     public String getOperator(){
         return operator;
     }

    public String getRcddat() {
        return rcddat;
    }

    public void setRcddat(String rcddat) {
        this.rcddat = rcddat;
    }
}
