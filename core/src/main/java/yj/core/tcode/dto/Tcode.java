package yj.core.tcode.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;
@ExtensionAttribute(disable=true)
@Table(name = "sap_tcode")
public class Tcode extends BaseDTO {
     @Id
     @GeneratedValue
      private String userName;

      private  String app0001;

      private String app0002;

    private String app0003;

    private String app0004;

    private String app0005;

    private String app0006;

    private String app0007;

    private String app0008;

    private String app0009;

    private String app0010;

    private String app0011;

    private String app0012;

    private String app0013;

    private String app0014;

    private String app0015;


     public void setUserName(String userName){
         this.userName = userName;
     }

     public String getUserName(){
         return userName;
     }

    public String getApp0001() {
        return app0001;
    }

    public void setApp0001(String app0001) {
        this.app0001 = app0001;
    }

    public String getApp0002() {
        return app0002;
    }

    public void setApp0002(String app0002) {
        this.app0002 = app0002;
    }

    public String getApp0003() {
        return app0003;
    }

    public void setApp0003(String app0003) {
        this.app0003 = app0003;
    }

    public String getApp0004() {
        return app0004;
    }

    public void setApp0004(String app0004) {
        this.app0004 = app0004;
    }

    public String getApp0005() {
        return app0005;
    }

    public void setApp0005(String app0005) {
        this.app0005 = app0005;
    }

    public String getApp0006() {
        return app0006;
    }

    public void setApp0006(String app0006) {
        this.app0006 = app0006;
    }

    public String getApp0007() {
        return app0007;
    }

    public void setApp0007(String app0007) {
        this.app0007 = app0007;
    }

    public String getApp0008() {
        return app0008;
    }

    public void setApp0008(String app0008) {
        this.app0008 = app0008;
    }

    public String getApp0009() {
        return app0009;
    }

    public void setApp0009(String app0009) {
        this.app0009 = app0009;
    }

    public String getApp0010() {
        return app0010;
    }

    public void setApp0010(String app0010) {
        this.app0010 = app0010;
    }

    public String getApp0011() {
        return app0011;
    }

    public void setApp0011(String app0011) {
        this.app0011 = app0011;
    }

    public String getApp0012() {
        return app0012;
    }

    public void setApp0012(String app0012) {
        this.app0012 = app0012;
    }

    public String getApp0013() {
        return app0013;
    }

    public void setApp0013(String app0013) {
        this.app0013 = app0013;
    }

    public String getApp0014() {
        return app0014;
    }

    public void setApp0014(String app0014) {
        this.app0014 = app0014;
    }

    public String getApp0015() {
        return app0015;
    }

    public void setApp0015(String app0015) {
        this.app0015 = app0015;
    }
}