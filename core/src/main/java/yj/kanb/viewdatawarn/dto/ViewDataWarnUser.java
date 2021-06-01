package yj.kanb.viewdatawarn.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ExtensionAttribute(disable=true)
@Table(name = "yj_view_data_warn_user")
public class ViewDataWarnUser {
    @Id
    @GeneratedValue
    private String userCode;
    private String warnType;
    private String warnLevel;
    private String userName;
    private String werks;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public String getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(String warnLevel) {
        this.warnLevel = warnLevel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }
}
