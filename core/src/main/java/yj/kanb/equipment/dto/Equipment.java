package yj.kanb.equipment.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@ExtensionAttribute(disable=true)
@Table(name = "vb_equipment")
public class Equipment extends BaseDTO {
    @NotEmpty
    private String bukrs;
    private String works;
    private String mac;
    @Id
    @GeneratedValue
    private String eqId;
    @NotEmpty
    private String workshopId;//工厂
    private String address;
    @Transient
    private String vbgroupName;

    public String getVbgroupName() {
        return vbgroupName;
    }

    public void setVbgroupName(String vbgroupName) {
        this.vbgroupName = vbgroupName;
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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getEqId() {
        return eqId;
    }

    public void setEqId(String eqId) {
        this.eqId = eqId;
    }

    public String getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(String workshopId) {
        this.workshopId = workshopId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
