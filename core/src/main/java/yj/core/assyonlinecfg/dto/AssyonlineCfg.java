package yj.core.assyonlinecfg.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;
@ExtensionAttribute(disable=true)
@Table(name = "wip_assyonline_cfg")
public class AssyonlineCfg extends BaseDTO {
     @Id
     @GeneratedValue
      private String werks; //工厂

     @NotEmpty
      private String lineId; //分零件产线ID

      private String lgort; //分零件库存地点

      private String lineId2; //装配线产线ID

      private String operationCode; //工序代码


     public void setWerks(String werks){
         this.werks = werks;
     }

     public String getWerks(){
         return werks;
     }

     public void setLineId(String lineId){
         this.lineId = lineId;
     }

     public String getLineId(){
         return lineId;
     }

     public void setLgort(String lgort){
         this.lgort = lgort;
     }

     public String getLgort(){
         return lgort;
     }

     public void setLineId2(String lineId2){
         this.lineId2 = lineId2;
     }

     public String getLineId2(){
         return lineId2;
     }

     public void setOperationCode(String operationCode){
         this.operationCode = operationCode;
     }

     public String getOperationCode(){
         return operationCode;
     }

     }
