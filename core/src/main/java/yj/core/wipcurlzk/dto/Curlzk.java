package yj.core.wipcurlzk.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;
import java.util.Date;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
@ExtensionAttribute(disable=true)
@Table(name = "wip_curlzk")
public class Curlzk extends BaseDTO {
     @Id
     @GeneratedValue
      private String lineId; //产线ID

     @NotEmpty
      private String lineCode; //产线编码

     @NotEmpty
      private String descriptions; //产线描述

     @NotEmpty
      private String zpgdbar; //当前机加工序流转卡号

      private String zxhbar;//当前毛坯框码

      private String shift;//当前机加班次

      private Date erdat;//生产日期

      private Date creationDate; //创建时间

     @NotNull
      private Long createdBy; //创建人

      private Date lastUpdateDate; //更新时间

      private Long lastUpdatedBy; //更新人

    //车间看板相关
    @Transient
      private String matnrjj;//机加物料编码
    @Transient
      private String maktxjj;
    @Transient
      private String maktxyz;
    @Transient
      private String matnryz;//压铸物料编码
    @Transient
      private String deptId;//车间组织编码
    @Transient
      private String arbpl;//工作中心
    @Transient
      private Double menge;//流转卡数量
    @Transient
      private String lastUpdateDateStr;

     public void setLineId(String lineId){
         this.lineId = lineId;
     }

     public String getLineId(){
         return lineId;
     }

     public void setLineCode(String lineCode){
         this.lineCode = lineCode;
     }

     public String getLineCode(){
         return lineCode;
     }

     public void setDescriptions(String descriptions){
         this.descriptions = descriptions;
     }

     public String getDescriptions(){
         return descriptions;
     }

     public void setZpgdbar(String zpgdbar){
         this.zpgdbar = zpgdbar;
     }

     public String getZpgdbar(){
         return zpgdbar;
     }

     public void setCreationDate(Date creationDate){
         this.creationDate = creationDate;
     }

     public Date getCreationDate(){
         return creationDate;
     }

     public void setCreatedBy(Long createdBy){
         this.createdBy = createdBy;
     }

     public Long getCreatedBy(){
         return createdBy;
     }

    @Override
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    @Override
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Override
    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Date getErdat() {
        return erdat;
    }

    public void setErdat(Date erdat) {
        this.erdat = erdat;
    }

    public String getZxhbar() {
        return zxhbar;
    }

    public void setZxhbar(String zxhbar) {
        this.zxhbar = zxhbar;
    }

    public String getMatnrjj() {
        return matnrjj;
    }

    public void setMatnrjj(String matnrjj) {
        this.matnrjj = matnrjj;
    }

    public String getMaktxjj() {
        return maktxjj;
    }

    public void setMaktxjj(String maktxjj) {
        this.maktxjj = maktxjj;
    }

    public String getMaktxyz() {
        return maktxyz;
    }

    public void setMaktxyz(String maktxyz) {
        this.maktxyz = maktxyz;
    }

    public String getMatnryz() {
        return matnryz;
    }

    public void setMatnryz(String matnryz) {
        this.matnryz = matnryz;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getArbpl() {
        return arbpl;
    }

    public void setArbpl(String arbpl) {
        this.arbpl = arbpl;
    }

    public Double getMenge() {
        return menge;
    }

    public void setMenge(Double menge) {
        this.menge = menge;
    }

    public String getLastUpdateDateStr() {
        return lastUpdateDateStr;
    }

    public void setLastUpdateDateStr(String lastUpdateDateStr) {
        this.lastUpdateDateStr = lastUpdateDateStr;
    }
}
