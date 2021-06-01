package yj.kanb.viewdatawarn.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ExtensionAttribute(disable=true)
@Table(name = "yj_view_data_warn")
public class ViewDataWarn {
    @Id
    @GeneratedValue
    private String werks;
    private String workshop;
    private String workline;
    private String warnCreateTime;
    private String recevierId;

    private String warnType;
    private String warnContent;
    private String warnStatus;
    private String receiverName;
    private String warnSendTime;
    private String warnLevel;
    private String uuid;

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public String getWorkline() {
        return workline;
    }

    public void setWorkline(String workline) {
        this.workline = workline;
    }

    public String getWarnCreateTime() {
        return warnCreateTime;
    }

    public void setWarnCreateTime(String warnCreateTime) {
        this.warnCreateTime = warnCreateTime;
    }

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public String getWarnContent() {
        return warnContent;
    }

    public void setWarnContent(String warnContent) {
        this.warnContent = warnContent;
    }

    public String getWarnStatus() {
        return warnStatus;
    }

    public void setWarnStatus(String warnStatus) {
        this.warnStatus = warnStatus;
    }

    public String getRecevierId() {
        return recevierId;
    }

    public void setRecevierId(String recevierId) {
        this.recevierId = recevierId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getWarnSendTime() {
        return warnSendTime;
    }

    public void setWarnSendTime(String warnSendTime) {
        this.warnSendTime = warnSendTime;
    }

    public String getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(String warnLevel) {
        this.warnLevel = warnLevel;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
