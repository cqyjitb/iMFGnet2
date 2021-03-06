package yj.core.qcaudithead.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;
@ExtensionAttribute(disable=true)
@Table(name = "wip_qcauditprocessheader")
public class Qcauditprocessheader extends BaseDTO {
     @Id
     @GeneratedValue
      private String werks; //工厂

     @NotEmpty
      private String recordid; //记录ID

      private Double confirmQty; //确认报废数

      private String status;//0:未处理    1：部分处理     2：完全处理
      private String attr1;

      private String attr2;

      private String attr3;

      private String attr4;

      private String attr5;

      private String attr6;

      private String attr7;

      private String attr8;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

     public void setConfirmQty(Double confirmQty){
         this.confirmQty = confirmQty;
     }

     public Double getConfirmQty(){
         return confirmQty;
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

     public void setAttr5(String attr5){
         this.attr5 = attr5;
     }

     public String getAttr5(){
         return attr5;
     }

     public void setAttr6(String attr6){
         this.attr6 = attr6;
     }

     public String getAttr6(){
         return attr6;
     }

     public void setAttr7(String attr7){
         this.attr7 = attr7;
     }

     public String getAttr7(){
         return attr7;
     }

     public void setAttr8(String attr8){
         this.attr8 = attr8;
     }

     public String getAttr8(){
         return attr8;
     }

     }
