package yj.core.cardhlock.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;

import java.util.Date;

@ExtensionAttribute(disable=true)
@Table(name = "sap_cardhlock")
public class Cardhlock extends BaseDTO {
     @Id
     @GeneratedValue
      private String zpgdbar;

      private String vornr;

      private String attr1;

      private String attr2;

      private String attr3;

      private String attr4;

      private Date creationDate; //创建时间

      private Long createdBy; //创建人




     public void setZpgdbar(String zpgdbar){
         this.zpgdbar = zpgdbar;
     }

     public String getZpgdbar(){
         return zpgdbar;
     }

     public void setAttr1(String attr1){
         this.attr1 = attr1;
     }

     public String getAttr1(){
         return attr1;
     }

     public void setAttr2(String attr2){
         this.attr2 = attr2;
     }

     public String getAttr2(){
         return attr2;
     }

     public void setAttr3(String attr3){
         this.attr3 = attr3;
     }

     public String getAttr3(){
         return attr3;
     }

     public void setAttr4(String attr4){
         this.attr4 = attr4;
     }

     public String getAttr4(){
         return attr4;
     }

    public String getVornr() {
        return vornr;
    }

    public void setVornr(String vornr) {
        this.vornr = vornr;
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

}