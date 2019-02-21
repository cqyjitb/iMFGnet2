package yj.core.mouldcavity.dto;

/**Auto Generated By Hap Code Generator**/

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ExtensionAttribute(disable=true)
@Table(name = "sap_mouldcavity")
public class Mouldcavity extends BaseDTO {
    @Id
    private String werks;
    @NotEmpty
    private String matnr;

    private String maktx;

    private Integer mdnum;

    private String mdno;

    private String modo2;

    private String mdqrcode;

    private String status;

    @NotNull
    private Long createdBy; //创建人
    @NotNull
    private Date creationDate; //创建时间

    private Long lastUpdatedBy; //更新人

    private Date lastUpdateDate; //更新时间

    @Override
    public Long getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Override
    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    @Override
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
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

    public Integer getMdnum() {
        return mdnum;
    }

    public void setMdnum(Integer mdnum) {
        this.mdnum = mdnum;
    }

    public String getMdno() {
        return mdno;
    }

    public void setMdno(String mdno) {
        this.mdno = mdno;
    }

    public String getModo2() {
        return modo2;
    }

    public void setModo2(String modo2) {
        this.modo2 = modo2;
    }

    public String getMdqrcode() {
        return mdqrcode;
    }

    public void setMdqrcode(String mdqrcode) {
        this.mdqrcode = mdqrcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
