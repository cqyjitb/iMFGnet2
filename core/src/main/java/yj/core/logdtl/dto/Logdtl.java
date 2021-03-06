package yj.core.logdtl.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

@ExtensionAttribute(disable=true)
@Table(name = "wip_logdtl")
public class Logdtl extends BaseDTO {
     @Id
     @GeneratedValue
      private String id;

     @NotEmpty
      private String logid;

     @NotEmpty
      private String keyword1; //关键字

     @NotEmpty
      private String keyword2;

     @NotEmpty
      private String keyword3;

     @NotEmpty
      private String keyword4;

     @NotEmpty
      private String operation;

     @NotEmpty
      private String msgtype; //消息类型（S,E)

     @NotEmpty
      private String message; //消息

    private Long createdBy; //创建人

    private Date creationDate; //创建时间

    private Long lastUpdatedBy; //更新人

    private Date lastUpdatedDate;

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

    @Override
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Override
    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public void setId(String id){
         this.id = id;
     }

     public String getId(){
         return id;
     }

     public void setLogid(String logid){
         this.logid = logid;
     }

     public String getLogid(){
         return logid;
     }

     public void setKeyword1(String keyword1){
         this.keyword1 = keyword1;
     }

     public String getKeyword1(){
         return keyword1;
     }

     public void setKeyword2(String keyword2){
         this.keyword2 = keyword2;
     }

     public String getKeyword2(){
         return keyword2;
     }

     public void setKeyword3(String keyword3){
         this.keyword3 = keyword3;
     }

     public String getKeyword3(){
         return keyword3;
     }

     public void setKeyword4(String keyword4){
         this.keyword4 = keyword4;
     }

     public String getKeyword4(){
         return keyword4;
     }

     public void setOperation(String operation){
         this.operation = operation;
     }

     public String getOperation(){
         return operation;
     }

     public void setMsgtype(String msgtype){
         this.msgtype = msgtype;
     }

     public String getMsgtype(){
         return msgtype;
     }

     public void setMessage(String message){
         this.message = message;
     }

     public String getMessage(){
         return message;
     }

     }
