package yj.core.wipproductscfg.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
@ExtensionAttribute(disable=true)
@Table(name = "wip_products_cfg")
public class ProductsCfg extends BaseDTO {
     @Id
     @GeneratedValue
      private String werks;

     @NotNull
      private Long lineId;

     @NotEmpty
      private String pmatnr;

     @NotEmpty
      private String matnr;
     @NotEmpty
      private  String lgort;
     @NotEmpty
      private String lgortassy;
     @NotEmpty
      private String lgortrew;
     @NotEmpty
      private int sxmax;
     @NotEmpty
      private int sxmin;

      private String kunnr;

      private Date creationDate; //创建时间

      private Long createdBy; //创建人

      private Date lastUpdatedDate; //更新时间

      private Long lastUpdatedBy; //更新人


     public void setWerks(String werks){
         this.werks = werks;
     }

     public String getWerks(){
         return werks;
     }

     public void setLineId(Long lineId){
         this.lineId = lineId;
     }

     public Long getLineId(){
         return lineId;
     }

     public void setPmatnr(String pmatnr){
         this.pmatnr = pmatnr;
     }

     public String getPmatnr(){
         return pmatnr;
     }

     public void setMatnr(String matnr){
         this.matnr = matnr;
     }

     public String getMatnr(){
         return matnr;
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

    public String getLgort() {
        return lgort;
    }

    public void setLgort(String lgort) {
        this.lgort = lgort;
    }

    public String getLgortassy() {
        return lgortassy;
    }

    public void setLgortassy(String lgortassy) {
        this.lgortassy = lgortassy;
    }

    public String getLgortrew() {
        return lgortrew;
    }

    public void setLgortrew(String lgortrew) {
        this.lgortrew = lgortrew;
    }

    public int getSxmax() {
        return sxmax;
    }

    public void setSxmax(int sxmax) {
        this.sxmax = sxmax;
    }

    public int getSxmin() {
        return sxmin;
    }

    public void setSxmin(int sxmin) {
        this.sxmin = sxmin;
    }

    public String getKunnr() {
        return kunnr;
    }

    public void setKunnr(String kunnr) {
        this.kunnr = kunnr;
    }
}
