package yj.core.wipshotnum.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import net.shibboleth.utilities.java.support.annotation.constraint.NotEmpty;
import org.omg.PortableInterceptor.INACTIVE;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**Auto Generated By Hap Code Generator**/
@ExtensionAttribute(disable=true)
@Table(name = "wip_shotnum")
public class Shotnum extends BaseDTO {
    @Id
    private String werks;//工厂
    @NotEmpty
    private String arbpl;//工作中心
    @NotEmpty
    private String prdDate;//生产日期
    @NotEmpty
    private String shifts;//班次
    @NotEmpty
    private String crdat;//创建日期

    private String sClass;//班组

    private String zpgdbar;//机台派工单

    private String ktext;//压铸机台号

    private String matnr;//物料号

    private String maktx;//物料描述

    private String mdno;//模号

    @NotEmpty
    private Long shotStart;//起始压射号
    @NotEmpty
    private Long shotEnd;//终止压射号
    @NotEmpty
    private String crnam;//创建者
    @Transient
    private String fevor;//生产车间
    @Transient
    private String txt;//生产车间名称
    @Transient
    private String plnbez;//物料编号
    @Transient
    private Integer mdnum;//模具型腔数量
    private String prdDateAfter;//开始日期
    private String prdDateBefore;//结束日期
    private String total;//汇总查询
    private Integer shotNum;//压铸次数
    @Transient
    private String aufnr;//订单
    @Transient
    private Integer yeild;//合格品数量
    @Transient
    private Long createdBy; //创建人
    @Transient
    private Date creationDate; //创建时间

    private Long brgew;//浇注重量-KG

    @Transient
    private String veran;//车间

    private String userName;//创建人

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVeran() {
        return veran;
    }

    public void setVeran(String veran) {
        this.veran = veran;
    }

    public Long getBrgew() {
        return brgew;
    }

    public void setBrgew(Long brgew) {
        this.brgew = brgew;
    }

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
    private Integer wasteNum;//废品数量

    public Integer getWasteNum() {
        return wasteNum;
    }

    public void setWasteNum(Integer wasteNum) {
        this.wasteNum = wasteNum;
    }

    public Integer getYeild() {
        return yeild;
    }

    public void setYeild(Integer yeild) {
        this.yeild = yeild;
    }

    public String getAufnr() {
        return aufnr;
    }

    public void setAufnr(String aufnr) {
        this.aufnr = aufnr;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getArbpl() {
        return arbpl;
    }

    public void setArbpl(String arbpl) {
        this.arbpl = arbpl;
    }

    public String getPrdDate() {
        return prdDate;
    }

    public void setPrdDate(String prdDate) {
        this.prdDate = prdDate;
    }

    public String getShifts() {
        return shifts;
    }

    public void setShifts(String shifts) {
        this.shifts = shifts;
    }

    public String getCrdat() {
        return crdat;
    }

    public void setCrdat(String crdat) {
        this.crdat = crdat;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public String getZpgdbar() {
        return zpgdbar;
    }

    public void setZpgdbar(String zpgdbar) {
        this.zpgdbar = zpgdbar;
    }

    public String getKtext() {
        return ktext;
    }

    public void setKtext(String ktext) {
        this.ktext = ktext;
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

    public String getMdno() {
        return mdno;
    }

    public void setMdno(String mdno) {
        this.mdno = mdno;
    }

    public Long getShotStart() {
        return shotStart;
    }

    public void setShotStart(Long shotStart) {
        this.shotStart = shotStart;
    }

    public Long getShotEnd() {
        return shotEnd;
    }

    public void setShotEnd(Long shotEnd) {
        this.shotEnd = shotEnd;
    }

    public String getCrnam() {
        return crnam;
    }

    public void setCrnam(String crnam) {
        this.crnam = crnam;
    }

    public String getFevor() {
        return fevor;
    }

    public void setFevor(String fevor) {
        this.fevor = fevor;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getPlnbez() {
        return plnbez;
    }

    public void setPlnbez(String plnbez) {
        this.plnbez = plnbez;
    }

    public Integer getMdnum() {
        return mdnum;
    }

    public void setMdnum(Integer mdnum) {
        this.mdnum = mdnum;
    }

    public String getPrdDateAfter() {
        return prdDateAfter;
    }

    public void setPrdDateAfter(String prdDateAfter) {
        this.prdDateAfter = prdDateAfter;
    }

    public String getPrdDateBefore() {
        return prdDateBefore;
    }

    public void setPrdDateBefore(String prdDateBefore) {
        this.prdDateBefore = prdDateBefore;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getShotNum() {
        return shotNum;
    }

    public void setShotNum(Integer shotNum) {
        this.shotNum = shotNum;
    }
}
