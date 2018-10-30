package yj.core.zudhead.dto;

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
@Table(name = "wip_zudhead")
public class Zudhead extends BaseDTO {
     @Id
     @GeneratedValue
      private String zudnum; //单据号

     @NotEmpty
      private String udtype; //不合格审理单类型

     @NotEmpty
      private String lineId; //生产线ID

     @NotEmpty
      private String arbpr; //工作中心

     @NotEmpty
      private String crdat;

     @Transient
      private Long createdBy;

     @Transient
      private Date creationDate;


     public void setZudnum(String zudnum){
         this.zudnum = zudnum;
     }

     public String getZudnum(){
         return zudnum;
     }

     public void setUdtype(String udtype){
         this.udtype = udtype;
     }

     public String getUdtype(){
         return udtype;
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

    public String getCrdat() {
        return crdat;
    }

    public void setCrdat(String crdat) {
        this.crdat = crdat;
    }
}