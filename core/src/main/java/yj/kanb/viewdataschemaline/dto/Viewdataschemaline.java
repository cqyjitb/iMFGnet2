package yj.kanb.viewdataschemaline.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ExtensionAttribute(disable=true)
@Table(name = "vb_line_group_h")
public class Viewdataschemaline extends BaseDTO {

    @Id
    @GeneratedValue
    private String groupId;
    private String product;
    private String workshopId;
    private String bukrs;
    private String works;

    @NotEmpty
    private String matnr;
    private String maktx;

    private String shift;
    private String shifttimebegin;
    private String shifttimeend;
    private String lineLeader;
    private String leaderPhone;
    private Double cycletime;
    private String workshopName;
    private Double planqty;
    private Double actqty;
    private Double insufqty;
    private Double qcRate;
    private Double oeeRate;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(String workshopId) {
        this.workshopId = workshopId;
    }

    public String getBukrs() {
        return bukrs;
    }

    public void setBukrs(String bukrs) {
        this.bukrs = bukrs;
    }

    public String getWorks() {
        return works;
    }

    public void setWorks(String works) {
        this.works = works;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getMaktx() {
        return maktx;
    }

    public void setMaktx(String maktx) {
        this.maktx = maktx;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getShifttimebegin() {
        return shifttimebegin;
    }

    public void setShifttimebegin(String shifttimebegin) {
        this.shifttimebegin = shifttimebegin;
    }

    public String getShifttimeend() {
        return shifttimeend;
    }

    public void setShifttimeend(String shifttimeend) {
        this.shifttimeend = shifttimeend;
    }

    public String getLineLeader() {
        return lineLeader;
    }

    public void setLineLeader(String lineLeader) {
        this.lineLeader = lineLeader;
    }

    public String getLeaderPhone() {
        return leaderPhone;
    }

    public void setLeaderPhone(String leaderPhone) {
        this.leaderPhone = leaderPhone;
    }

    public Double getCycletime() {
        return cycletime;
    }

    public void setCycletime(Double cycletime) {
        this.cycletime = cycletime;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public Double getPlanqty() {
        return planqty;
    }

    public void setPlanqty(Double planqty) {
        this.planqty = planqty;
    }

    public Double getActqty() {
        return actqty;
    }

    public void setActqty(Double actqty) {
        this.actqty = actqty;
    }

    public Double getInsufqty() {
        return insufqty;
    }

    public void setInsufqty(Double insufqty) {
        this.insufqty = insufqty;
    }

    public Double getQcRate() {
        return qcRate;
    }

    public void setQcRate(Double qcRate) {
        this.qcRate = qcRate;
    }

    public Double getOeeRate() {
        return oeeRate;
    }

    public void setOeeRate(Double oeeRate) {
        this.oeeRate = oeeRate;
    }
}