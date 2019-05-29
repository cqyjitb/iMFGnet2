package yj.kanb.vbgroupheader.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@ExtensionAttribute(disable=true)
@Table(name = "vb_group_h")
public class Vbgroupheader extends BaseDTO {

    @NotEmpty
    @GeneratedValue
    private String vbgroupId;
    private String bukrs;
    private String works;
    @NotEmpty
    private String eqId;
    private String mac;
    private String vbgroupName;
    private Integer switchfreg;
    private String status;

    public String getVbgroupId() {
        return vbgroupId;
    }

    public void setVbgroupId(String vbgroupId) {
        this.vbgroupId = vbgroupId;
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

    public String getEqId() {
        return eqId;
    }

    public void setEqId(String eqId) {
        this.eqId = eqId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getVbgroupName() {
        return vbgroupName;
    }

    public void setVbgroupName(String vbgroupName) {
        this.vbgroupName = vbgroupName;
    }

    public Integer getSwitchfreg() {
        return switchfreg;
    }

    public void setSwitchfreg(Integer switchfreg) {
        this.switchfreg = switchfreg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}