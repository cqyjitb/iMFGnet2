package yj.kanb.vbgroupheader.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@ExtensionAttribute(disable=true)
@Table(name = "vb_group_h")
public class Vbgroupheader {

    @NotEmpty
    @GeneratedValue
    private String vbgroupId;
    private String bukrs;
    private String works;
    @NotEmpty
    private String groupId;
    private String groupType;

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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }
}
