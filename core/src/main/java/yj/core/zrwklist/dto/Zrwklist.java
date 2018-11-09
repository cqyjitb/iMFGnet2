package yj.core.zrwklist.dto;

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
@Table(name = "wip_zrwklist")
public class Zrwklist extends BaseDTO {
     @Id
     @GeneratedValue
      private String zrwknum; //单据号

     @NotEmpty
      private String item; //行号

     @NotEmpty
      private String zqjjlh; //取件记录号

     @NotEmpty
      private String zpgdbar; //工序流转卡号

     @NotEmpty
      private String vornr; //取件工序

     @NotEmpty
      private String zxhbar; //毛坯筐码

     @NotEmpty
      private String zpgdbar2; //压铸工序流转卡号

     @NotEmpty
      private String matnr; //毛坯物料号

     @NotEmpty
     private String matnr2; //毛坯物料号

      private String zgjbar; //工件二维码

      private Long zrnum; //返修数量

      private String gmein; //单位

      private String charg2; //机加批次

      private String charg; //毛坯批次

      private String diecd;

      private String sfflg;

      private String zqxdm; //缺陷代码

      private String gstrp; //生产日期

      private String zbanz; //班组

      private String zbanc; //班次

      private Long zrwktimes; //返修次数

      private String reviewc; //评审结论

      private String mark; //备注

    @Transient
    private String lineId;
    @Transient
    private String zotype; //不合格审理单类型
    @Transient
    private Long createdBy;
    @Transient
    private Date creationDate;
    @Transient
    private String rcode;
    @Transient
    private String arbpr;

    @Transient
    private String maktx;

    private String vornr_old;

    /*添加字段 918100064*/
    @Transient
    private Long unitId;//生产车间Id
    private String creationDateBefore;
    private String creationDateAfter;
    @Transient
    private String descriptions;//生产线名称

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getCreationDateBefore() {
        return creationDateBefore;
    }

    public void setCreationDateBefore(String creationDateBefore) {
        this.creationDateBefore = creationDateBefore;
    }

    public String getCreationDateAfter() {
        return creationDateAfter;
    }

    public void setCreationDateAfter(String creationDateAfter) {
        this.creationDateAfter = creationDateAfter;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getRcode() {
        return rcode;
    }

    public void setRcode(String rcode) {
        this.rcode = rcode;
    }

    public String getVornr_old() {
        return vornr_old;
    }

    public void setVornr_old(String vornr_old) {
        this.vornr_old = vornr_old;
    }

    public void setZrwknum(String zrwknum){
         this.zrwknum = zrwknum;
     }

     public String getZrwknum(){
         return zrwknum;
     }

     public void setItem(String item){
         this.item = item;
     }

     public String getItem(){
         return item;
     }

     public void setZqjjlh(String zqjjlh){
         this.zqjjlh = zqjjlh;
     }

     public String getZqjjlh(){
         return zqjjlh;
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

     public void setZgjbar(String zgjbar){
         this.zgjbar = zgjbar;
     }

     public String getZgjbar(){
         return zgjbar;
     }

     public void setZrnum(Long zrnum){
         this.zrnum = zrnum;
     }

     public Long getZrnum(){
         return zrnum;
     }

     public void setGmein(String gmein){
         this.gmein = gmein;
     }

     public String getGmein(){
         return gmein;
     }

     public void setCharg2(String charg2){
         this.charg2 = charg2;
     }

     public String getCharg2(){
         return charg2;
     }

     public void setCharg(String charg){
         this.charg = charg;
     }

     public String getCharg(){
         return charg;
     }

     public void setDiecd(String diecd){
         this.diecd = diecd;
     }

     public String getDiecd(){
         return diecd;
     }

     public void setSfflg(String sfflg){
         this.sfflg = sfflg;
     }

     public String getSfflg(){
         return sfflg;
     }

     public void setZqxdm(String zqxdm){
         this.zqxdm = zqxdm;
     }

     public String getZqxdm(){
         return zqxdm;
     }

    public String getGstrp() {
        return gstrp;
    }

    public void setGstrp(String gstrp) {
        this.gstrp = gstrp;
    }

    public void setZbanz(String zbanz){
         this.zbanz = zbanz;
     }

     public String getZbanz(){
         return zbanz;
     }

     public void setZbanc(String zbanc){
         this.zbanc = zbanc;
     }

     public String getZbanc(){
         return zbanc;
     }

     public void setZrwktimes(Long zrwktimes){
         this.zrwktimes = zrwktimes;
     }

     public Long getZrwktimes(){
         return zrwktimes;
     }

     public void setReviewc(String reviewc){
         this.reviewc = reviewc;
     }

     public String getReviewc(){
         return reviewc;
     }

     public void setMark(String mark){
         this.mark = mark;
     }

     public String getMark(){
         return mark;
     }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getZotype() {
        return zotype;
    }

    public void setZotype(String zotype) {
        this.zotype = zotype;
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

    public String getMatnr2() {
        return matnr2;
    }

    public void setMatnr2(String matnr2) {
        this.matnr2 = matnr2;
    }

    public String getArbpr() {
        return arbpr;
    }

    public void setArbpr(String arbpr) {
        this.arbpr = arbpr;
    }

    public String getMaktx() {
        return maktx;
    }

    public void setMaktx(String maktx) {
        this.maktx = maktx;
    }
}
