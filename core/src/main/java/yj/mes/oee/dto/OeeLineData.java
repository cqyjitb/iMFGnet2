package yj.mes.oee.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;

import javax.persistence.Table;

@ExtensionAttribute(disable=true)
@Table(name = "tb_oee_line_data_jj_realtime")
public class OeeLineData {
    private String companyCode;
    private String companyName;
    private String warehouseCode;
    private String warehouseName;
    private String lineCode;
    private String lineName;
    private String materialCode;
    private String materialName;
    private String shiftDate;
    private String shiftId;
    private String shiftName;
    private String workTeam;
    private String shiftBeginTime;
    private String shiftEndTime;
    private Integer planWorkTimes;
    private Integer  realWorkTimes;
    private Integer stopTimes;
    private Integer planQty;
    private Integer totalQty;
    private Integer qualifiedQty;
    private Integer cycleTimes;
    private String userCnname;
    private String userEnname;
    private String telphoneNo;

    public String getUserCnname() {
        return userCnname;
    }

    public void setUserCnname(String userCnname) {
        this.userCnname = userCnname;
    }

    public String getUserEnname() {
        return userEnname;
    }

    public void setUserEnname(String userEnname) {
        this.userEnname = userEnname;
    }

    public String getTelphoneNo() {
        return telphoneNo;
    }

    public void setTelphoneNo(String telphoneNo) {
        this.telphoneNo = telphoneNo;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(String shiftDate) {
        this.shiftDate = shiftDate;
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public String getWorkTeam() {
        return workTeam;
    }

    public void setWorkTeam(String workTeam) {
        this.workTeam = workTeam;
    }

    public String getShiftBeginTime() {
        return shiftBeginTime;
    }

    public void setShiftBeginTime(String shiftBeginTime) {
        this.shiftBeginTime = shiftBeginTime;
    }

    public String getShiftEndTime() {
        return shiftEndTime;
    }

    public void setShiftEndTime(String shiftEndTime) {
        this.shiftEndTime = shiftEndTime;
    }

    public Integer getPlanWorkTimes() {
        return planWorkTimes;
    }

    public void setPlanWorkTimes(Integer planWorkTimes) {
        this.planWorkTimes = planWorkTimes;
    }

    public Integer getRealWorkTimes() {
        return realWorkTimes;
    }

    public void setRealWorkTimes(Integer realWorkTimes) {
        this.realWorkTimes = realWorkTimes;
    }

    public Integer getStopTimes() {
        return stopTimes;
    }

    public void setStopTimes(Integer stopTimes) {
        this.stopTimes = stopTimes;
    }

    public Integer getPlanQty() {
        return planQty;
    }

    public void setPlanQty(Integer planQty) {
        this.planQty = planQty;
    }

    public Integer getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Integer totalQty) {
        this.totalQty = totalQty;
    }

    public Integer getQualifiedQty() {
        return qualifiedQty;
    }

    public void setQualifiedQty(Integer qualifiedQty) {
        this.qualifiedQty = qualifiedQty;
    }

    public Integer getCycleTimes() {
        return cycleTimes;
    }

    public void setCycleTimes(Integer cycleTimes) {
        this.cycleTimes = cycleTimes;
    }
}
