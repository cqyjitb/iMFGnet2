package yj.core.wipdot.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ExtensionAttribute(disable=true)
@Table(name = "wip_dot")
public class Dot {
    @NotEmpty
    private String werks;//工厂
    @NotEmpty
    private Long lineId;//生产线ID
    @NotEmpty
    private Integer pointId;//工序ID
    @Id
    @GeneratedValue
    private Integer dotId;//采集点ID
    @NotEmpty
    private String dotCode;//采集点编码
    @NotEmpty
    private String descriptions;//采集点描述

    private String firstFlag;//首工序标识

    private String endFlag;//末工序标识

    private String monitorFlag;//监控工序标识

    private String enableFlag;//是否生效
    @NotEmpty
    private Date startDate; //生效日期

    private Date endDate; //失效日期

    private String alertFlag;//前工序漏采处理模式
    @NotNull
    private Date creationDate; //创建时间
    @NotEmpty
    private String createdBy; //创建人

    private Date lastUpdatedDate; //更新时间

    private String lastUpdatedBy; //更新人

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public Integer getDotId() {
        return dotId;
    }

    public void setDotId(Integer dotId) {
        this.dotId = dotId;
    }

    public String getDotCode() {
        return dotCode;
    }

    public void setDotCode(String dotCode) {
        this.dotCode = dotCode;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getFirstFlag() {
        return firstFlag;
    }

    public void setFirstFlag(String firstFlag) {
        this.firstFlag = firstFlag;
    }

    public String getEndFlag() {
        return endFlag;
    }

    public void setEndFlag(String endFlag) {
        this.endFlag = endFlag;
    }

    public String getMonitorFlag() {
        return monitorFlag;
    }

    public void setMonitorFlag(String monitorFlag) {
        this.monitorFlag = monitorFlag;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAlertFlag() {
        return alertFlag;
    }

    public void setAlertFlag(String alertFlag) {
        this.alertFlag = alertFlag;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
