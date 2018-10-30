package yj.core.inoutrecord.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
@ExtensionAttribute(disable=true)
@Table(name = "wip_in_out_record")
public class InOutRecord extends BaseDTO {
     @Id
     @GeneratedValue
      private String zqjjlh; //取件记录号
     @NotEmpty
      private String zwipqid;//在制队列ID
     @NotEmpty
      private String lineId; //条码号

     @NotEmpty
      private String arbpr; //工作中心

     @NotEmpty
      private String zbanz;//班组

     @NotEmpty
      private String zbanc;//班次

     @NotEmpty
      private String zpgdbar; //机加工序流转卡号

     @NotEmpty
      private String vornr; //生产线ID

     @NotEmpty
      private String zxhbar; //箱号条码

     @NotEmpty
      private String zpgdbar2; //压铸工序流转卡号

     @NotEmpty
      private String matnr; //工序ID

     @NotEmpty
      private String matnr2; //工序名称

     @NotEmpty
      private String zgjbar; //状态 0-未取/归还， 1-取出   -1:报废 -2:返修 

      private Double zoutnum; //取出数量

     @NotEmpty
      private Long reflag; //还件标识

     @NotEmpty
      private String gmein; //取件分类

     @NotEmpty
      private String charg; //取件说明

     @NotEmpty
      private Integer zremade;//返工标识 0正常 1返工

      private String diecd; //模号

     @NotEmpty
      private String sfflg; //压铸班标

     @NotEmpty
      private String zotype; //取出原因

     @NotEmpty
      private String zqxdm; //缺陷代码

     @NotEmpty
      private String zissuetxt; //不良原因

     @NotEmpty
      private String zbpjc; //客户简称

     @NotEmpty
      private String zjtgx; //机台

     @NotEmpty
      private String zoplo; //工位

      private Date outdat; //取件日期


      private Long outnam; //取件账号

      private Date redat; //还件日期

      private Long renam;

    private Date creationDate; //创建时间

    private Long createdBy; //创建人

    private Date lastUpdateDate; //更新时间

    private Long lastUpdatedBy; //更新人


    public Integer getZremade() {
        return zremade;
    }

    public void setZremade(Integer zremade) {
        this.zremade = zremade;
    }

    public void setZqjjlh(String zqjjlh){
         this.zqjjlh = zqjjlh;
     }

     public String getZqjjlh(){
         return zqjjlh;
     }

     public void setLineId(String lineId){
         this.lineId = lineId;
     }

     public String getLineId(){
         return lineId;
     }

     public void setArbpr(String arbpr){
         this.arbpr = arbpr;
     }

     public String getArbpr(){
         return arbpr;
     }

     public void setZpgdbar(String zpgdbar){
         this.zpgdbar = zpgdbar;
     }

     public String getZpgdbar(){
         return zpgdbar;
     }

     public void setVornr(String vornr){
         this.vornr = vornr;
     }

     public String getVornr(){
         return vornr;
     }

     public void setZxhbar(String zxhbar){
         this.zxhbar = zxhbar;
     }

     public String getZxhbar(){
         return zxhbar;
     }

     public void setZpgdbar2(String zpgdbar2){
         this.zpgdbar2 = zpgdbar2;
     }

     public String getZpgdbar2(){
         return zpgdbar2;
     }

     public void setMatnr(String matnr){
         this.matnr = matnr;
     }

     public String getMatnr(){
         return matnr;
     }

     public void setMatnr2(String matnr2){
         this.matnr2 = matnr2;
     }

     public String getMatnr2(){
         return matnr2;
     }

     public void setZgjbar(String zgjbar){
         this.zgjbar = zgjbar;
     }

     public String getZgjbar(){
         return zgjbar;
     }

     public void setZoutnum(Double zoutnum){
         this.zoutnum = zoutnum;
     }

     public Double getZoutnum(){
         return zoutnum;
     }

     public void setReflag(Long reflag){
         this.reflag = reflag;
     }

     public Long getReflag(){
         return reflag;
     }

     public void setGmein(String gmein){
         this.gmein = gmein;
     }

     public String getGmein(){
         return gmein;
     }

     public void setCharg(String charg){
         this.charg = charg;
     }

     public String getCharg(){
         return charg;
     }

    public String getDiecd() {
        return diecd;
    }

    public void setDiecd(String diecd) {
        this.diecd = diecd;
    }

    public void setSfflg(String sfflg){
         this.sfflg = sfflg;
     }

     public String getSfflg(){
         return sfflg;
     }

     public void setZotype(String zotype){
         this.zotype = zotype;
     }

     public String getZotype(){
         return zotype;
     }

     public void setZqxdm(String zqxdm){
         this.zqxdm = zqxdm;
     }

     public String getZqxdm(){
         return zqxdm;
     }

     public void setZissuetxt(String zissuetxt){
         this.zissuetxt = zissuetxt;
     }

     public String getZissuetxt(){
         return zissuetxt;
     }

     public void setZbpjc(String zbpjc){
         this.zbpjc = zbpjc;
     }

     public String getZbpjc(){
         return zbpjc;
     }

     public void setZjtgx(String zjtgx){
         this.zjtgx = zjtgx;
     }

     public String getZjtgx(){
         return zjtgx;
     }

     public void setZoplo(String zoplo){
         this.zoplo = zoplo;
     }

     public String getZoplo(){
         return zoplo;
     }

     public void setOutdat(Date outdat){
         this.outdat = outdat;
     }

     public Date getOutdat(){
         return outdat;
     }

     public void setOutnam(Long outnam){
         this.outnam = outnam;
     }

     public Long getOutnam(){
         return outnam;
     }

     public void setRedat(Date redat){
         this.redat = redat;
     }

     public Date getRedat(){
         return redat;
     }

     public void setRenam(Long renam){
         this.renam = renam;
     }

     public Long getRenam(){
         return renam;
     }

    public String getZwipqid() {
        return zwipqid;
    }

    public void setZwipqid(String zwipqid) {
        this.zwipqid = zwipqid;
    }

    public String getZbanz() {
        return zbanz;
    }

    public void setZbanz(String zbanz) {
        this.zbanz = zbanz;
    }

    public String getZbanc() {
        return zbanc;
    }

    public void setZbanc(String zbanc) {
        this.zbanc = zbanc;
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
    public Long getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
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
}