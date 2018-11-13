package yj.core.zwipq.dto;

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
@Table(name = "zwipq")
public class Zwipq extends BaseDTO {
     @Id
     @GeneratedValue
      private String zsxjlh; //上线记录号

      private String lineId; //生产线ID

      private String arbpr; //工作中心

      private String segOprName; //子工序段名称

      private String pkgLineId; //装箱生产线ID

      private String shift; //机加班次

      private String zpgdbar; //上线机加工序流转卡号

      private String zpgdbar1; //下线机加工序流转卡号

      private String vornr; //工序

     @NotEmpty
      private String zxhbar; //压铸工序毛坯筐码

      private String matnr; //BOM组件物料号

      private String matnr2; //机加成品或半成品物料号

      private String zpgdbar2; //压铸工序流转卡号

      private String zgjbar; //工件二维码

      private Double zsxnum; //上线数量

      private String gmein; //单位

      private String lgort; //库存地点

      private String charg; //来料批次

      private Integer zremade;

      private Long zzxkl; //装箱扣料

      private Long zqjkl; //取件扣料

      private Long zoffl; //下线标识

      private Long status;//0：正常 1：挂起

      private String sfflg; //压铸班标

      private String diecd; //模具编号

      private Long qsenq;//队列序号

      private Date creationDate; //创建时间

     @NotNull
      private Long createdBy; //创建人

      private Date lastUpdatedDate; //更新时间

      private Long lastUpdatedBy; //更新人

      private Date offUpdatedDate; //下线时间

      private Long offUpdatedBy; //下线人

    @Transient
      private String check;//机加取件用  选择

      private String cursum;//取件数量

    @Transient
    private Long unitId;
    @Transient
    private String name;
    @Transient
    private String descriptions;
    @Transient
    private String maktx;

    private String attr1After;

    private String attr1Before;

    private Integer online;

    /*添加字段 918100064*/
    @Transient
    private String werks; //工厂
    @Transient
    private String attr1; //生产日期
    @Transient
    private String kunnr; //客户编码
    @Transient
    private String name1; //客户名称
    private String rate; //合格率%  装箱数量/投入数量
    @Transient
    private Integer zoutnum; //待处理数量
    @Transient
    private String pmatnr;//产品物料
    private String pmaktx; //物料描述
    @Transient
    private String shift1;
    @Transient
    private String deptId;//部门Id
    private Integer scrap;//报废数量
    private Integer processed; //在制数量
    @Transient
    private String attr;//生产日期

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public Integer getProcessed() {
        return processed;
    }

    public void setProcessed(Integer processed) {
        this.processed = processed;
    }

    public Integer getScrap() {
        return scrap;
    }

    public void setScrap(Integer scrap) {
        this.scrap = scrap;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Integer getZoutnum() {
        return zoutnum;
    }

    public void setZoutnum(Integer zoutnum) {
        this.zoutnum = zoutnum;
    }

    public String getPmatnr() {
        return pmatnr;
    }

    public void setPmatnr(String pmatnr) {
        this.pmatnr = pmatnr;
    }

    public String getPmaktx() {
        return pmaktx;
    }

    public void setPmaktx(String pmaktx) {
        this.pmaktx = pmaktx;
    }

    public String getShift1() {
        return shift1;
    }

    public void setShift1(String shift1) {
        this.shift1 = shift1;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public Integer getZremade() {
        return zremade;
    }

    public void setZremade(Integer zremade) {
        this.zremade = zremade;
    }

    public Long getQsenq() {
        return qsenq;
    }

    public String getPkgLineId() {
        return pkgLineId;
    }

    public void setPkgLineId(String pkgLineId) {
        this.pkgLineId = pkgLineId;
    }

    public void setQsenq(Long qsenq) {
        this.qsenq = qsenq;
    }

    public void setZsxjlh(String zsxjlh){
         this.zsxjlh = zsxjlh;
     }

     public String getZsxjlh(){
         return zsxjlh;
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

     public void setSegOprName(String segOprName){
         this.segOprName = segOprName;
     }

     public String getSegOprName(){
         return segOprName;
     }

     public void setShift(String shift){
         this.shift = shift;
     }

     public String getShift(){
         return shift;
     }

     public void setZpgdbar(String zpgdbar){
         this.zpgdbar = zpgdbar;
     }

     public String getZpgdbar(){
         return zpgdbar;
     }

     public void setZpgdbar1(String zpgdbar1){
         this.zpgdbar1 = zpgdbar1;
     }

     public String getZpgdbar1(){
         return zpgdbar1;
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

     public void setZpgdbar2(String zpgdbar2){
         this.zpgdbar2 = zpgdbar2;
     }

     public String getZpgdbar2(){
         return zpgdbar2;
     }

     public void setZgjbar(String zgjbar){
         this.zgjbar = zgjbar;
     }

     public String getZgjbar(){
         return zgjbar;
     }

     public void setZsxnum(Double zsxnum){
         this.zsxnum = zsxnum;
     }

     public Double getZsxnum(){
         return zsxnum;
     }

     public void setGmein(String gmein){
         this.gmein = gmein;
     }

     public String getGmein(){
         return gmein;
     }

     public void setLgort(String lgort){
         this.lgort = lgort;
     }

     public String getLgort(){
         return lgort;
     }

     public void setCharg(String charg){
         this.charg = charg;
     }

     public String getCharg(){
         return charg;
     }

     public void setZzxkl(Long zzxkl){
         this.zzxkl = zzxkl;
     }

     public Long getZzxkl(){
         return zzxkl;
     }

     public void setZqjkl(Long zqjkl){
         this.zqjkl = zqjkl;
     }

     public Long getZqjkl(){
         return zqjkl;
     }

     public void setZoffl(Long zoffl){
         this.zoffl = zoffl;
     }

     public Long getZoffl(){
         return zoffl;
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

     public void setOffUpdatedDate(Date offUpdatedDate){
         this.offUpdatedDate = offUpdatedDate;
     }

     public Date getOffUpdatedDate(){
         return offUpdatedDate;
     }

     public void setOffUpdatedBy(Long offUpdatedBy){
         this.offUpdatedBy = offUpdatedBy;
     }

     public Long getOffUpdatedBy(){
         return offUpdatedBy;
     }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getCursum() {
        return cursum;
    }

    public void setCursum(String cursum) {
        this.cursum = cursum;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAttr1After() {
        return attr1After;
    }

    public void setAttr1After(String attr1After) {
        this.attr1After = attr1After;
    }

    public String getAttr1Before() {
        return attr1Before;
    }

    public void setAttr1Before(String attr1Before) {
        this.attr1Before = attr1Before;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }
}
