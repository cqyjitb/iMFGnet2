package yj.core.qcaudithead.dto;

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
@Table(name = "wip_qcaudithead")
public class Qcaudithead extends BaseDTO {

    private static final long serialVersionUID = 2776430709705510697L;

     @Id
     @GeneratedValue
      private String werks; //工厂

     @NotEmpty
      private String recordid; //记录ID

      private String sourcetype; //来源 Q1=机加在制；Q2=线边库毛坯；Q3=成品返工；Y1=压铸在制；Y2=压铸返工；

      private String deptResponsible; //责任部门

      private String lineid; //责任首工作中心/产线

      private String lineDesc; //责任首工作中心/产线描述

      private String shift; //责任班组

      private String kunnr; //客户编码

      private String kunnrDesc; //客户名称

      private String zpgdbar; //订单号/工序流转卡

      private String matnr; //成品物料

      private String maktx; //成品物料描述

      private String matnr2; //毛坯物料

      private String maktx2; //毛坯物料描述

      private String zqxdm;

      private String tlevelcode;

      private String charg; //生产批次

      private String sfflg; //班标

      private String diecd; //模号

      private Date gstrp; //生产日期

      private String vornr; //责任工序/工位

      private String responsible; //责任人

      private Double dfectQty; //不合格件数

      private String gmein; //计量单位

      private String oaPresscls; //流程类别 1=机加质管审核；2=机加副总审核；3=压铸质管审核；4=压铸副总审核；

      private String oaStatus; //OA流程状态 0=未提交；1=流程中；2=已归档；

      private String defctType; //不合格品类别 A，B，C（缺陷类别）

      private String handleResults1; //是否报废 0=不报废；1=报废

      private String handleResults2; //是否返工返修 0=不返工；1=返工

      private String handleResults3; //是否让步接收 0=不让步；1=让步

      private Double scrapQty; //报废数量

      private Double rworkQty; //返工返修数量

      private Double conssQty; //让步接收数量

      private String dfectTxt; //不合格品描述

      private String reportDept; //报告部门

      private String reportor; //报告人

      private Date reportDate; //报告时间

      private String auditTxt; //评审结论

      private String qcEnger; //产品质量工程师

      private String auditor1; //评审人员1责任车间主任

      private String auditHandle1; //评审意见1责任车间主任

      private Date auditDate1; //评审日期1责任车间主任

      private String auditor2; //评审人员2责任部门部长

      private String auditHandle2; //评审意见2责任部门部长

      private Date auditDate2; //评审日期2责任部门部长

      private String auditor3; //评审人员3压铸质量工程师

      private String auditHandle3; //评审意见3压铸质量工程师

      private Date auditDate3; //评审日期3压铸质量工程师

      private String auditor4; //评审人员4机加质量工程师

      private String auditHandle4; //评审意见4机加质量工程师

      private Date auditDate4; //评审日期4机加质量工程师

      private String auditor5; //评审人员5压铸技术员

      private String auditHandle5; //评审意见5压铸技术员

      private Date auditDate5; //评审日期5压铸技术员

      private String auditor6; //评审人员6汽车技术开发部技术员

      private String auditHandle6; //评审意见6汽车技术开发部技术员

      private Date auditDate6; //评审日期6汽车技术开发部技术员

      private String auditor7; //评审人员7压铸质管部长

      private String auditHandle7; //评审意见7压铸质管部长

      private Date auditDate7; //评审日期7压铸质管部长

      private String auditor8; //评审人员8机加质管部长

      private String auditHandle8; //评审意见8机加质管部长

      private Date auditDate8; //评审日期8机加质管部长

      private String auditor9; //评审人员9主管副总

      private String auditHandle9; //评审意见9主管副总

      private Date auditDate9; //评审日期9主管副总

      private String auditor10; //审批人10总经理

      private String auditHandle10; //审批意见10总经理

      private Date auditDate10; //审批日期10总经理

      private String handleResults; //责任部门处理结果

      private String qmNotification; //质量通知单

      private String qmNotntype; //质量通知单类型

      private Date qmNotncrtdt; //质量通知单创建日期

      private String reworkOrder; //返工返修订单

      private Date reworkOrdercrtdt; //返工返修订单创建日期

      private Double rwkQty; //返工返修数量

      private Double rwkQlfqty; //合格数量

      private Double rwkScrapqty; //报废数量

      private String customerConfirm; //客户确认

      private String status;//0：未处理 1：部分处理 2：完全处理

    @Transient
    private String kurztext;//缺陷描述
    private String tlevelcode2;//缺陷代码

    @Transient
    private String ztext;//二级缺陷描述

    @Transient
    private String name;//部门名称
    private String gstrp2; //生产日期
    private String reportDate2; //报告时间

    @Transient
    private Long createdBy;

    @Transient
    private Date creationDate;

    @Transient
    private Long lastUpdatedBy;

    @Transient
    private  Date lastUpdateDate;


    public String getTlevelcode2() {
        return tlevelcode2;
    }

    public void setTlevelcode2(String tlevelcode2) {
        this.tlevelcode2 = tlevelcode2;
    }

    public String getKurztext() {
        return kurztext;
    }

    public void setKurztext(String kurztext) {
        this.kurztext = kurztext;
    }

    public String getZtext() {
        return ztext;
    }

    public void setZtext(String ztext) {
        this.ztext = ztext;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGstrp2() {
        return gstrp2;
    }

    public void setGstrp2(String gstrp2) {
        this.gstrp2 = gstrp2;
    }

    public String getReportDate2() {
        return reportDate2;
    }

    public void setReportDate2(String reportDate2) {
        this.reportDate2 = reportDate2;
    }

    public void setWerks(String werks){
         this.werks = werks;
     }

     public String getWerks(){
         return werks;
     }

     public void setRecordid(String recordid){
         this.recordid = recordid;
     }

     public String getRecordid(){
         return recordid;
     }

     public void setSourcetype(String sourcetype){
         this.sourcetype = sourcetype;
     }

     public String getSourcetype(){
         return sourcetype;
     }

     public void setDeptResponsible(String deptResponsible){
         this.deptResponsible = deptResponsible;
     }

     public String getDeptResponsible(){
         return deptResponsible;
     }

     public void setLineid(String lineid){
         this.lineid = lineid;
     }

     public String getLineid(){
         return lineid;
     }

     public void setLineDesc(String lineDesc){
         this.lineDesc = lineDesc;
     }

     public String getLineDesc(){
         return lineDesc;
     }

     public void setShift(String shift){
         this.shift = shift;
     }

     public String getShift(){
         return shift;
     }

     public void setKunnr(String kunnr){
         this.kunnr = kunnr;
     }

     public String getKunnr(){
         return kunnr;
     }

     public void setKunnrDesc(String kunnrDesc){
         this.kunnrDesc = kunnrDesc;
     }

     public String getKunnrDesc(){
         return kunnrDesc;
     }

     public void setZpgdbar(String zpgdbar){
         this.zpgdbar = zpgdbar;
     }

     public String getZpgdbar(){
         return zpgdbar;
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

     public void setMatnr2(String matnr2){
         this.matnr2 = matnr2;
     }

     public String getMatnr2(){
         return matnr2;
     }

     public void setMaktx2(String maktx2){
         this.maktx2 = maktx2;
     }

     public String getMaktx2(){
         return maktx2;
     }

     public void setCharg(String charg){
         this.charg = charg;
     }

     public String getCharg(){
         return charg;
     }

     public void setSfflg(String sfflg){
         this.sfflg = sfflg;
     }

     public String getSfflg(){
         return sfflg;
     }

     public void setDiecd(String diecd){
         this.diecd = diecd;
     }

     public String getDiecd(){
         return diecd;
     }

     public void setGstrp(Date gstrp){
         this.gstrp = gstrp;
     }

     public Date getGstrp(){
         return gstrp;
     }

     public void setVornr(String vornr){
         this.vornr = vornr;
     }

     public String getVornr(){
         return vornr;
     }

     public void setResponsible(String responsible){
         this.responsible = responsible;
     }

     public String getResponsible(){
         return responsible;
     }

     public void setDfectQty(Double dfectQty){
         this.dfectQty = dfectQty;
     }

     public Double getDfectQty(){
         return dfectQty;
     }

     public void setGmein(String gmein){
         this.gmein = gmein;
     }

     public String getGmein(){
         return gmein;
     }

     public void setOaPresscls(String oaPresscls){
         this.oaPresscls = oaPresscls;
     }

     public String getOaPresscls(){
         return oaPresscls;
     }

     public void setOaStatus(String oaStatus){
         this.oaStatus = oaStatus;
     }

     public String getOaStatus(){
         return oaStatus;
     }

     public void setDefctType(String defctType){
         this.defctType = defctType;
     }

     public String getDefctType(){
         return defctType;
     }

     public void setHandleResults1(String handleResults1){
         this.handleResults1 = handleResults1;
     }

     public String getHandleResults1(){
         return handleResults1;
     }

     public void setHandleResults2(String handleResults2){
         this.handleResults2 = handleResults2;
     }

     public String getHandleResults2(){
         return handleResults2;
     }

     public void setHandleResults3(String handleResults3){
         this.handleResults3 = handleResults3;
     }

     public String getHandleResults3(){
         return handleResults3;
     }

     public void setScrapQty(Double scrapQty){
         this.scrapQty = scrapQty;
     }

     public Double getScrapQty(){
         return scrapQty;
     }

     public void setRworkQty(Double rworkQty){
         this.rworkQty = rworkQty;
     }

     public Double getRworkQty(){
         return rworkQty;
     }

    public Double getConssQty() {
        return conssQty;
    }

    public void setConssQty(Double conssQty) {
        this.conssQty = conssQty;
    }

    public void setDfectTxt(String dfectTxt){
         this.dfectTxt = dfectTxt;
     }

     public String getDfectTxt(){
         return dfectTxt;
     }

     public void setReportDept(String reportDept){
         this.reportDept = reportDept;
     }

     public String getReportDept(){
         return reportDept;
     }

     public void setReportor(String reportor){
         this.reportor = reportor;
     }

     public String getReportor(){
         return reportor;
     }

     public void setReportDate(Date reportDate){
         this.reportDate = reportDate;
     }

     public Date getReportDate(){
         return reportDate;
     }

     public void setAuditTxt(String auditTxt){
         this.auditTxt = auditTxt;
     }

     public String getAuditTxt(){
         return auditTxt;
     }

     public void setQcEnger(String qcEnger){
         this.qcEnger = qcEnger;
     }

     public String getQcEnger(){
         return qcEnger;
     }

     public void setAuditor1(String auditor1){
         this.auditor1 = auditor1;
     }

     public String getAuditor1(){
         return auditor1;
     }

     public void setAuditHandle1(String auditHandle1){
         this.auditHandle1 = auditHandle1;
     }

     public String getAuditHandle1(){
         return auditHandle1;
     }

     public void setAuditDate1(Date auditDate1){
         this.auditDate1 = auditDate1;
     }

     public Date getAuditDate1(){
         return auditDate1;
     }

     public void setAuditor2(String auditor2){
         this.auditor2 = auditor2;
     }

     public String getAuditor2(){
         return auditor2;
     }

     public void setAuditHandle2(String auditHandle2){
         this.auditHandle2 = auditHandle2;
     }

     public String getAuditHandle2(){
         return auditHandle2;
     }

     public void setAuditDate2(Date auditDate2){
         this.auditDate2 = auditDate2;
     }

     public Date getAuditDate2(){
         return auditDate2;
     }

     public void setAuditor3(String auditor3){
         this.auditor3 = auditor3;
     }

     public String getAuditor3(){
         return auditor3;
     }

     public void setAuditHandle3(String auditHandle3){
         this.auditHandle3 = auditHandle3;
     }

     public String getAuditHandle3(){
         return auditHandle3;
     }

     public void setAuditDate3(Date auditDate3){
         this.auditDate3 = auditDate3;
     }

     public Date getAuditDate3(){
         return auditDate3;
     }

     public void setAuditor4(String auditor4){
         this.auditor4 = auditor4;
     }

     public String getAuditor4(){
         return auditor4;
     }

     public void setAuditHandle4(String auditHandle4){
         this.auditHandle4 = auditHandle4;
     }

     public String getAuditHandle4(){
         return auditHandle4;
     }

     public void setAuditDate4(Date auditDate4){
         this.auditDate4 = auditDate4;
     }

     public Date getAuditDate4(){
         return auditDate4;
     }

     public void setAuditor5(String auditor5){
         this.auditor5 = auditor5;
     }

     public String getAuditor5(){
         return auditor5;
     }

     public void setAuditHandle5(String auditHandle5){
         this.auditHandle5 = auditHandle5;
     }

     public String getAuditHandle5(){
         return auditHandle5;
     }

     public void setAuditDate5(Date auditDate5){
         this.auditDate5 = auditDate5;
     }

     public Date getAuditDate5(){
         return auditDate5;
     }

     public void setAuditor6(String auditor6){
         this.auditor6 = auditor6;
     }

     public String getAuditor6(){
         return auditor6;
     }

     public void setAuditHandle6(String auditHandle6){
         this.auditHandle6 = auditHandle6;
     }

     public String getAuditHandle6(){
         return auditHandle6;
     }

     public void setAuditDate6(Date auditDate6){
         this.auditDate6 = auditDate6;
     }

     public Date getAuditDate6(){
         return auditDate6;
     }

     public void setAuditor7(String auditor7){
         this.auditor7 = auditor7;
     }

     public String getAuditor7(){
         return auditor7;
     }

     public void setAuditHandle7(String auditHandle7){
         this.auditHandle7 = auditHandle7;
     }

     public String getAuditHandle7(){
         return auditHandle7;
     }

     public void setAuditDate7(Date auditDate7){
         this.auditDate7 = auditDate7;
     }

     public Date getAuditDate7(){
         return auditDate7;
     }

     public void setAuditor8(String auditor8){
         this.auditor8 = auditor8;
     }

     public String getAuditor8(){
         return auditor8;
     }

     public void setAuditHandle8(String auditHandle8){
         this.auditHandle8 = auditHandle8;
     }

     public String getAuditHandle8(){
         return auditHandle8;
     }

     public void setAuditDate8(Date auditDate8){
         this.auditDate8 = auditDate8;
     }

     public Date getAuditDate8(){
         return auditDate8;
     }

     public void setAuditor9(String auditor9){
         this.auditor9 = auditor9;
     }

     public String getAuditor9(){
         return auditor9;
     }

     public void setAuditHandle9(String auditHandle9){
         this.auditHandle9 = auditHandle9;
     }

     public String getAuditHandle9(){
         return auditHandle9;
     }

     public void setAuditDate9(Date auditDate9){
         this.auditDate9 = auditDate9;
     }

     public Date getAuditDate9(){
         return auditDate9;
     }

     public void setAuditor10(String auditor10){
         this.auditor10 = auditor10;
     }

     public String getAuditor10(){
         return auditor10;
     }

     public void setAuditHandle10(String auditHandle10){
         this.auditHandle10 = auditHandle10;
     }

     public String getAuditHandle10(){
         return auditHandle10;
     }

     public void setAuditDate10(Date auditDate10){
         this.auditDate10 = auditDate10;
     }

     public Date getAuditDate10(){
         return auditDate10;
     }

     public void setHandleResults(String handleResults){
         this.handleResults = handleResults;
     }

     public String getHandleResults(){
         return handleResults;
     }

     public void setQmNotification(String qmNotification){
         this.qmNotification = qmNotification;
     }

     public String getQmNotification(){
         return qmNotification;
     }

     public void setQmNotntype(String qmNotntype){
         this.qmNotntype = qmNotntype;
     }

     public String getQmNotntype(){
         return qmNotntype;
     }

     public void setQmNotncrtdt(Date qmNotncrtdt){
         this.qmNotncrtdt = qmNotncrtdt;
     }

     public Date getQmNotncrtdt(){
         return qmNotncrtdt;
     }

     public void setReworkOrder(String reworkOrder){
         this.reworkOrder = reworkOrder;
     }

     public String getReworkOrder(){
         return reworkOrder;
     }

     public void setReworkOrdercrtdt(Date reworkOrdercrtdt){
         this.reworkOrdercrtdt = reworkOrdercrtdt;
     }

     public Date getReworkOrdercrtdt(){
         return reworkOrdercrtdt;
     }

     public void setRwkQty(Double rwkQty){
         this.rwkQty = rwkQty;
     }

     public Double getRwkQty(){
         return rwkQty;
     }

     public void setRwkQlfqty(Double rwkQlfqty){
         this.rwkQlfqty = rwkQlfqty;
     }

     public Double getRwkQlfqty(){
         return rwkQlfqty;
     }

     public void setRwkScrapqty(Double rwkScrapqty){
         this.rwkScrapqty = rwkScrapqty;
     }

     public Double getRwkScrapqty(){
         return rwkScrapqty;
     }

     public void setCustomerConfirm(String customerConfirm){
         this.customerConfirm = customerConfirm;
     }

     public String getCustomerConfirm(){
         return customerConfirm;
     }

    public String getZqxdm() {
        return zqxdm;
    }

    public void setZqxdm(String zqxdm) {
        this.zqxdm = zqxdm;
    }

    public String getTlevelcode() {
        return tlevelcode;
    }

    public void setTlevelcode(String tlevelcode) {
        this.tlevelcode = tlevelcode;
    }

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

    @Override
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Override
    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    @Override
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
