package yj.core.wiplines.dto;

/**Auto Generated By Hap Code Generator**/
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;
@ExtensionAttribute(disable=true)
@Table(name = "wip_lines")
public class Lines extends BaseDTO {
     @NotEmpty
      private String werks; //工厂

      private String deptId; //部门ID

     @Id
     @GeneratedValue
      private Long lineId; //产线ID

     @NotEmpty
      private String lineCode; //产线编码

     @NotEmpty
      private String descriptions; //产线描述

      private Long plineId; //父产线ID

      private String arbpl; //工作中心

      private String enableFlag; //是否启用

      private Date startDate; //生效日期

      private Date endDate; //失效日期

      private Long taktTime; //生产节拍

      private String lineHeader;

      private String lineHeaderEn;

      private String headerPhone;

      @NotNull
      private String onlinetype;//上线类型 0：批次 1：二维码

      private String segOprName;//工序段名称

      private String pkgBindFlag;//包装绑定工件二维码。1：绑定，0：不绑定

      private Long pointNum; //工序数量

      private Date creationDate; //创建时间

      private String execoffFlag;

      private String cgroup;//终检组

     @NotNull
      private Long createdBy; //创建人

      private Date lastUpdatedDate; //更新时间

      private Long lastUpdatedBy; //更新人

      private String tzflg; //调账标识 1：调账 0：不调账

    private String pkgtype;//装箱方式 0：批次 1：二维码
    @Transient
    private String name;
    @Transient
    private String ktext;
    @Transient
    private String unitCode;
    @Transient
    private Long unitId;
    @Transient
    private Long parentId;

    private String uname;

    private String pdescriptions; //产线描述

    private String lineheader;//产线负责人

    private String headerphone;//负责人电话

    private String lineheaderEn;//负责人英文

    private Float oeerate;//设计OEE

    private String cggroupname;//产线组名称

    public String getCggroupname() {
        return cggroupname;
    }

    public void setCggroupname(String cggroupname) {
        this.cggroupname = cggroupname;
    }

    public Float getOeerate() {
        return oeerate;
    }

    public void setOeerate(Float oeerate) {
        this.oeerate = oeerate;
    }

    public String getLineheaderEn() {
        return lineheaderEn;
    }

    public void setLineheaderEn(String lineheaderEn) {
        this.lineheaderEn = lineheaderEn;
    }

    public String getLineheader() {
        return lineheader;
    }

    public void setLineheader(String lineheader) {
        this.lineheader = lineheader;
    }

    public String getHeaderphone() {
        return headerphone;
    }

    public void setHeaderphone(String headerphone) {
        this.headerphone = headerphone;
    }

    public String getCgroup() {
        return cgroup;
    }

    public void setCgroup(String cgroup) {
        this.cgroup = cgroup;
    }

    public String getPkgBindFlag() {
        return pkgBindFlag;
    }

    public void setPkgBindFlag(String pkgBindFlag) {
        this.pkgBindFlag = pkgBindFlag;
    }

    public String getExecoffFlag() {
        return execoffFlag;
    }

    public void setExecoffFlag(String execoffFlag) {
        this.execoffFlag = execoffFlag;
    }

    public void setWerks(String werks){
         this.werks = werks;
     }

     public String getWerks(){
         return werks;
     }

     public void setDeptId(String deptId){
         this.deptId = deptId;
     }

     public String getDeptId(){
         return deptId;
     }

     public void setLineId(Long lineId){
         this.lineId = lineId;
     }

     public Long getLineId(){
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

     public void setPlineId(Long plineId){
         this.plineId = plineId;
     }

     public Long getPlineId(){
         return plineId;
     }

     public void setArbpl(String arbpl){
         this.arbpl = arbpl;
     }

     public String getArbpl(){
         return arbpl;
     }

     public void setEnableFlag(String enableFlag){
         this.enableFlag = enableFlag;
     }

     public String getEnableFlag(){
         return enableFlag;
     }

     public void setStartDate(Date startDate){
         this.startDate = startDate;
     }

     public Date getStartDate(){
         return startDate;
     }

     public void setEndDate(Date endDate){
         this.endDate = endDate;
     }

     public Date getEndDate(){
         return endDate;
     }

     public void setTaktTime(Long taktTime){
         this.taktTime = taktTime;
     }

     public Long getTaktTime(){
         return taktTime;
     }

     public void setPointNum(Long pointNum){
         this.pointNum = pointNum;
     }

     public Long getPointNum(){
         return pointNum;
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

     public void setLastUpdatedDate(Date lastUpdatedDate){
         this.lastUpdatedDate = lastUpdatedDate;
     }

     public Date getLastUpdatedDate(){
         return lastUpdatedDate;
     }

     public void setLastUpdatedBy(Long lastUpdatedBy){
         this.lastUpdatedBy = lastUpdatedBy;
     }

     public Long getLastUpdatedBy(){
         return lastUpdatedBy;
     }

    public String getSegOprName() {
        return segOprName;
    }

    public void setSegOprName(String segOprName) {
        this.segOprName = segOprName;
    }

    public String getOnlinetype() {
        return onlinetype;
    }

    public void setOnlinetype(String onlinetype) {
        this.onlinetype = onlinetype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKtext() {
        return ktext;
    }

    public void setKtext(String ktext) {
        this.ktext = ktext;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPdescriptions() {
        return pdescriptions;
    }

    public void setPdescriptions(String pdescriptions) {
        this.pdescriptions = pdescriptions;
    }

    public String getPkgtype() {
        return pkgtype;
    }

    public void setPkgtype(String pkgtype) {
        this.pkgtype = pkgtype;
    }

    public String getLineHeader() {
        return lineHeader;
    }

    public void setLineHeader(String lineHeader) {
        this.lineHeader = lineHeader;
    }

    public String getHeaderPhone() {
        return headerPhone;
    }

    public void setHeaderPhone(String headerPhone) {
        this.headerPhone = headerPhone;
    }

    public String getLineHeaderEn() {
        return lineHeaderEn;
    }

    public void setLineHeaderEn(String lineHeaderEn) {
        this.lineHeaderEn = lineHeaderEn;
    }

    public String getTzflg() {
        return tzflg;
    }

    public void setTzflg(String tzflg) {
        this.tzflg = tzflg;
    }
}
