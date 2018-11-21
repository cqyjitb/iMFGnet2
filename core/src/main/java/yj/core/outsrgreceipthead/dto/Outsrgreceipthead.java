package yj.core.outsrgreceipthead.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
@ExtensionAttribute(disable=true)
@Table(name = "wip_outsrgreceipthead")
public class Outsrgreceipthead extends BaseDTO {
     @NotEmpty
      private String receiptnm; //外协收货单号

     @Id
     @GeneratedValue
      private String werks; //工厂

      private String lifnr; //供应商编号

      private String matnr; //物料编号

      private String status; //单据状态

      private Date zdpdat; //收货过账日期

      private Date zdptim; //收货过账时间

      private Long zdpuser; //收货过账账号

      private String prtflag; //打印标识

      private Date zipdat;

      private Date ziptim;

      private Long zipuser;

    public Date getZipdat() {
        return zipdat;
    }

    public void setZipdat(Date zipdat) {
        this.zipdat = zipdat;
    }

    public Date getZiptim() {
        return ziptim;
    }

    public void setZiptim(Date ziptim) {
        this.ziptim = ziptim;
    }

    public Long getZipuser() {
        return zipuser;
    }

    public void setZipuser(Long zipuser) {
        this.zipuser = zipuser;
    }

    public void setReceiptnm(String receiptnm){
         this.receiptnm = receiptnm;
     }

     public String getReceiptnm(){
         return receiptnm;
     }

     public void setWerks(String werks){
         this.werks = werks;
     }

     public String getWerks(){
         return werks;
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

     public void setStatus(String status){
         this.status = status;
     }

     public String getStatus(){
         return status;
     }

     public void setZdpdat(Date zdpdat){
         this.zdpdat = zdpdat;
     }

     public Date getZdpdat(){
         return zdpdat;
     }

     public void setZdptim(Date zdptim){
         this.zdptim = zdptim;
     }

     public Date getZdptim(){
         return zdptim;
     }

     public void setZdpuser(Long zdpuser){
         this.zdpuser = zdpuser;
     }

     public Long getZdpuser(){
         return zdpuser;
     }

     public void setPrtflag(String prtflag){
         this.prtflag = prtflag;
     }

     public String getPrtflag(){
         return prtflag;
     }

     }
