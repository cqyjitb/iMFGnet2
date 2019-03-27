package yj.core.seversetting.dto;

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
@Table(name = "wip_server_setting")
public class ServerSetting extends BaseDTO {
     @NotEmpty
      private String werks; //工厂

     @Id
     @GeneratedValue
      private Long lineId; //产线ID

     @NotEmpty
      private String ipAddress; //数据库IP地址

     @NotEmpty
      private String dbPort; //数据库端口

     @NotEmpty
      private String dbName; //数据库实例名

     @NotEmpty
      private String dbUsername; //数据库用户名

     @NotEmpty
      private String dbPassword; //数据库密码

      private Date lastPushDate; //最后一次下发数据时间

      private Long status; //0-停用；1-启动

      private Date creationDate; //创建时间

     @NotNull
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

     public void setIpAddress(String ipAddress){
         this.ipAddress = ipAddress;
     }

     public String getIpAddress(){
         return ipAddress;
     }

     public void setDbPort(String dbPort){
         this.dbPort = dbPort;
     }

     public String getDbPort(){
         return dbPort;
     }

     public void setDbName(String dbName){
         this.dbName = dbName;
     }

     public String getDbName(){
         return dbName;
     }

     public void setDbUsername(String dbUsername){
         this.dbUsername = dbUsername;
     }

     public String getDbUsername(){
         return dbUsername;
     }

     public void setDbPassword(String dbPassword){
         this.dbPassword = dbPassword;
     }

     public String getDbPassword(){
         return dbPassword;
     }

     public void setLastPushDate(Date lastPushDate){
         this.lastPushDate = lastPushDate;
     }

     public Date getLastPushDate(){
         return lastPushDate;
     }

     public void setStatus(Long status){
         this.status = status;
     }

     public Long getStatus(){
         return status;
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

     }
