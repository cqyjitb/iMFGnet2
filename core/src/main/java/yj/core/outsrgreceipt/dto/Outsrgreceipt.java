package yj.core.outsrgreceipt.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
@ExtensionAttribute(disable=true)
@Table(name = "wip_outsrgreceipt")
public class Outsrgreceipt extends BaseDTO {
     @NotEmpty
      private String receiptnm; //外协收货单号

     @NotEmpty
      private String item; //外协收货单行项目

     @Id
     @GeneratedValue
      private String werks; //工厂

      private String zpgdbar; //工序流转卡

      private String vornr; //外协工序号

      private String issuenm; //外协发料单号

      private String issuenmitem; //外协发料单行项目

      private String vsnda; //生产版本

      private String ebeln; //采购订单

      private String ebelp; //采购订单项目 

      private String ktsch; //标准文本码

      private String txz01; //工序说明

      private String lifnr; //供应商编号

      private String matnr; //物料编号

      private String diecd; //模号

      private String sfflg; //班标

      private Double menge; //采购订单数量

      private String matkl; //物料组 

      private Double ttreceipts; //总计收货数量

      private Double zshnum; //合格数量

      private Double zlfnum; //料废数量

      private Double zgfnum; //工废数量

      private Double zlost; //遗失数量

      private Double zthnum; //退回数量

      private String gmein; //计量单位

      private String charg; //批次

      private String status; //单据行状态

      private String mblnr; //物料凭证

      private String zeile; //物料凭证项目

      private String mjahr; //物料年度

      private String rueck; //操作完成的确认编号 

      private String rmzhl; //确认计数器 

      private String deductntenm; //扣款通知单号

      private Date zdsdat; //收货扫码日期

      private Date zdstim; //收货扫码时间

      private Long zdsuser; //收货扫码账号


     public void setReceiptnm(String receiptnm){
         this.receiptnm = receiptnm;
     }

     public String getReceiptnm(){
         return receiptnm;
     }

     public void setItem(String item){
         this.item = item;
     }

     public String getItem(){
         return item;
     }

     public void setWerks(String werks){
         this.werks = werks;
     }

     public String getWerks(){
         return werks;
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

     public void setIssuenm(String issuenm){
         this.issuenm = issuenm;
     }

     public String getIssuenm(){
         return issuenm;
     }

     public void setIssuenmitem(String issuenmitem){
         this.issuenmitem = issuenmitem;
     }

     public String getIssuenmitem(){
         return issuenmitem;
     }

     public void setVsnda(String vsnda){
         this.vsnda = vsnda;
     }

     public String getVsnda(){
         return vsnda;
     }

     public void setEbeln(String ebeln){
         this.ebeln = ebeln;
     }

     public String getEbeln(){
         return ebeln;
     }

     public void setEbelp(String ebelp){
         this.ebelp = ebelp;
     }

     public String getEbelp(){
         return ebelp;
     }

     public void setKtsch(String ktsch){
         this.ktsch = ktsch;
     }

     public String getKtsch(){
         return ktsch;
     }

     public void setTxz01(String txz01){
         this.txz01 = txz01;
     }

     public String getTxz01(){
         return txz01;
     }

     public void setLifnr(String lifnr){
         this.lifnr = lifnr;
     }

     public String getLifnr(){
         return lifnr;
     }

     public void setMatnr(String matnr){
         this.matnr = matnr;
     }

     public String getMatnr(){
         return matnr;
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

     public void setMenge(Double menge){
         this.menge = menge;
     }

     public Double getMenge(){
         return menge;
     }

     public void setMatkl(String matkl){
         this.matkl = matkl;
     }

     public String getMatkl(){
         return matkl;
     }

     public void setTtreceipts(Double ttreceipts){
         this.ttreceipts = ttreceipts;
     }

     public Double getTtreceipts(){
         return ttreceipts;
     }

     public void setZshnum(Double zshnum){
         this.zshnum = zshnum;
     }

     public Double getZshnum(){
         return zshnum;
     }

     public void setZlfnum(Double zlfnum){
         this.zlfnum = zlfnum;
     }

     public Double getZlfnum(){
         return zlfnum;
     }

     public void setZgfnum(Double zgfnum){
         this.zgfnum = zgfnum;
     }

     public Double getZgfnum(){
         return zgfnum;
     }

     public void setZlost(Double zlost){
         this.zlost = zlost;
     }

     public Double getZlost(){
         return zlost;
     }

     public void setZthnum(Double zthnum){
         this.zthnum = zthnum;
     }

     public Double getZthnum(){
         return zthnum;
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

     public void setStatus(String status){
         this.status = status;
     }

     public String getStatus(){
         return status;
     }

     public void setMblnr(String mblnr){
         this.mblnr = mblnr;
     }

     public String getMblnr(){
         return mblnr;
     }

     public void setZeile(String zeile){
         this.zeile = zeile;
     }

     public String getZeile(){
         return zeile;
     }

     public void setMjahr(String mjahr){
         this.mjahr = mjahr;
     }

     public String getMjahr(){
         return mjahr;
     }

     public void setRueck(String rueck){
         this.rueck = rueck;
     }

     public String getRueck(){
         return rueck;
     }

     public void setRmzhl(String rmzhl){
         this.rmzhl = rmzhl;
     }

     public String getRmzhl(){
         return rmzhl;
     }

     public void setDeductntenm(String deductntenm){
         this.deductntenm = deductntenm;
     }

     public String getDeductntenm(){
         return deductntenm;
     }

     public void setZdsdat(Date zdsdat){
         this.zdsdat = zdsdat;
     }

     public Date getZdsdat(){
         return zdsdat;
     }

     public void setZdstim(Date zdstim){
         this.zdstim = zdstim;
     }

     public Date getZdstim(){
         return zdstim;
     }

     public void setZdsuser(Long zdsuser){
         this.zdsuser = zdsuser;
     }

     public Long getZdsuser(){
         return zdsuser;
     }

     }
