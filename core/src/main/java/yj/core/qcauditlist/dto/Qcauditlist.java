package yj.core.qcauditlist.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
@ExtensionAttribute(disable=true)
@Table(name = "wip_qcauditlist")
public class Qcauditlist extends BaseDTO {
     @Id
     @GeneratedValue
      private String werks; //工厂

     @NotEmpty
      private String recordid; //记录ID

     @NotEmpty
      private String item;

     @NotEmpty
      private String zqjjlh; //取件记录号

      private String zgjbar; //工件二维码/毛坯二维码/工件流水号

      private String zpgdbar; //工序流转卡号

      private String zxhbar; //毛坯筐码

      private String matnr; //成品物料

      private String charg; //机加生产批次

      private String zbanc; //机加班次

      private String shift; //机加班组

      private Date gstrp; //机加生产日期

      private String matnr2; //毛坯物料

      private String ycharg; //毛坯生产批次

      private String yshift; //压铸班组

      private String yzbanc; //压铸班次

      private String sfflg; //班标

      private String diecd; //模号

      private Date ygstrp; //压铸生产日期

      private String code; //缺陷代码

      private String tlevelcode; //二级缺陷代码

      private Double menge; //原毛坯筐数量

      private Double dfectQty; //不合格件数

      private String gmein; //计量单位


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

     public void setZgjbar(String zgjbar){
         this.zgjbar = zgjbar;
     }

     public String getZgjbar(){
         return zgjbar;
     }

     public void setZpgdbar(String zpgdbar){
         this.zpgdbar = zpgdbar;
     }

     public String getZpgdbar(){
         return zpgdbar;
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

     public void setCharg(String charg){
         this.charg = charg;
     }

     public String getCharg(){
         return charg;
     }

     public void setZbanc(String zbanc){
         this.zbanc = zbanc;
     }

     public String getZbanc(){
         return zbanc;
     }

     public void setShift(String shift){
         this.shift = shift;
     }

     public String getShift(){
         return shift;
     }

     public void setGstrp(Date gstrp){
         this.gstrp = gstrp;
     }

     public Date getGstrp(){
         return gstrp;
     }

     public void setMatnr2(String matnr2){
         this.matnr2 = matnr2;
     }

     public String getMatnr2(){
         return matnr2;
     }

     public void setYcharg(String ycharg){
         this.ycharg = ycharg;
     }

     public String getYcharg(){
         return ycharg;
     }

     public void setYshift(String yshift){
         this.yshift = yshift;
     }

     public String getYshift(){
         return yshift;
     }

     public void setYzbanc(String yzbanc){
         this.yzbanc = yzbanc;
     }

     public String getYzbanc(){
         return yzbanc;
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

     public void setYgstrp(Date ygstrp){
         this.ygstrp = ygstrp;
     }

     public Date getYgstrp(){
         return ygstrp;
     }

     public void setCode(String code){
         this.code = code;
     }

     public String getCode(){
         return code;
     }

     public void setTlevelcode(String tlevelcode){
         this.tlevelcode = tlevelcode;
     }

     public String getTlevelcode(){
         return tlevelcode;
     }

     public void setMenge(Double menge){
         this.menge = menge;
     }

     public Double getMenge(){
         return menge;
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

     }
