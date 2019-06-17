package yj.kanb.wippassrate.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@ExtensionAttribute(disable=true)
@Table(name = "wip_pass_rate")
public class PassRate extends BaseDTO{
    @Id
    private String werks;//工厂
    @NotEmpty
    private String deptId;//车间
    @NotEmpty
    private String lineId;//产线
    @NotEmpty
    private String matnr;//物料编码

    private String maktx;//物料描述
    @NotEmpty
    private Date erdat;//生产日期

    private Integer gmnga;//装箱数

    private Integer xmnga;//工废数

    private Integer rmnga;//料废数

    private String gmein;//单位

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
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

    public Date getErdat() {
        return erdat;
    }

    public void setErdat(Date erdat) {
        this.erdat = erdat;
    }

    public Integer getGmnga() {
        return gmnga;
    }

    public void setGmnga(Integer gmnga) {
        this.gmnga = gmnga;
    }

    public Integer getXmnga() {
        return xmnga;
    }

    public void setXmnga(Integer xmnga) {
        this.xmnga = xmnga;
    }

    public Integer getRmnga() {
        return rmnga;
    }

    public void setRmnga(Integer rmnga) {
        this.rmnga = rmnga;
    }

    public String getGmein() {
        return gmein;
    }

    public void setGmein(String gmein) {
        this.gmein = gmein;
    }
}
