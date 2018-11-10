package yj.core.zudlist.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

@ExtensionAttribute(disable=true)
@Table(name = "wip_zudlist")
public class Zudlist extends BaseDTO {
     @Id
     @GeneratedValue
      private String zudnum; //单据号

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
      private String matnr2; //机加物料号

      private String zgjbar; //工件二维码

      private Long zdnum; //不合格品数量

      private String gmein; //单位

      private String charg2; //机加批次

      private String charg; //毛坯批次

      private String diecd; //模号

      private String sfflg; //压铸班标

      private String gstrp; //生产日期

      private String zqxdm;

      private String zissuetxt; //不良原因

      private String zbpjc; //客户简称

      private String zoplo; //工位

      private String zbanz; //工位

      private String zbanc; //班组

      private String rspart; //责任部门

      private String rsname; //责任人

      private String reviewc; //评审结论

      private String mark; //备注

      private String rueck;//确认号

      private String rmzhl;//计数器
    @Transient
     private String lineId;
    @Transient
     private String udtype; //不合格审理单类型
    @Transient
    private String arbpr; //工作中心
    @Transient
    private String rcode;//取件原因
    @Transient
    private String vornr_old;//取件工序
    @Transient
    private Long createdBy;
    @Transient
    private Date creationDate;

    @Transient
    private String maktx;

    @Transient
    private String kunnr;

    @Transient
    private String name1;//客户名称

    @Transient
    private String name;//部门名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKunnr() {
        return kunnr;
    }

    public void setKunnr(String kunnr) {
        this.kunnr = kunnr;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    /*添加字段 918100064 */
    @Transient
    private Long unitId;//生产车间Id
    private String creationDateBefore;
    private String creationDateAfter;
    @Transient
    private String sortl;//客户名称
    @Transient
    private String zotype;//取件原因编号
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

    public String getSortl() {
        return sortl;
    }

    public void setSortl(String sortl) {
        this.sortl = sortl;
    }

    public String getZotype() {
        return zotype;
    }

    public void setZotype(String zotype) {
        this.zotype = zotype;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getMaktx() {
        return maktx;
    }

    public void setMaktx(String maktx) {
        this.maktx = maktx;
    }

    public void setZudnum(String zudnum){
         this.zudnum = zudnum;
     }

     public String getZudnum(){
         return zudnum;
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

     public void setZdnum(Long zdnum){
         this.zdnum = zdnum;
     }

     public Long getZdnum(){
         return zdnum;
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

     public void setZoplo(String zoplo){
         this.zoplo = zoplo;
     }

     public String getZoplo(){
         return zoplo;
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

     public void setRspart(String rspart){
         this.rspart = rspart;
     }

     public String getRspart(){
         return rspart;
     }

     public void setRsname(String rsname){
         this.rsname = rsname;
     }

     public String getRsname(){
         return rsname;
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

    public String getUdtype() {
        return udtype;
    }

    public void setUdtype(String udtype) {
        this.udtype = udtype;
    }

    public String getArbpr() {
        return arbpr;
    }

    public void setArbpr(String arbpr) {
        this.arbpr = arbpr;
    }

    public String getGstrp() {
        return gstrp;
    }

    public void setGstrp(String gstrp) {
        this.gstrp = gstrp;
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

    public String getRueck() {
        return rueck;
    }

    public void setRueck(String rueck) {
        this.rueck = rueck;
    }

    public String getRmzhl() {
        return rmzhl;
    }

    public void setRmzhl(String rmzhl) {
        this.rmzhl = rmzhl;
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
}
