package yj.core.wipshotinput.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import net.shibboleth.utilities.java.support.annotation.constraint.NotEmpty;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@ExtensionAttribute(disable=true)
@Table(name = "wip_shot_input")
public class ShotInput extends BaseDTO {
    @Id
    private String werks;//工厂
    @NotEmpty
    private String fevor;//生产车间

    private String txt;//生产车间名称
    @NotEmpty
    private String arbpl;//工作中心

    private String ktext;//工作中心描述
    @NotEmpty
    private String prdDate;//生产日期
    @NotEmpty
    private String shifts;//班次

    private String sClass;//班组

    private Long shotStart;//起始压射号

    private Long shotEnd;//终止压射号

    private Integer shotNum;//压铸次数

    private Integer yeild;//合格品数量

    private Integer wasteNum;//废品数量

    private Integer differentNum;//差异数

    private String brgew;//浇注重量-KG

    private String prdDateAfter;//开始日期
    private String prdDateBefore;//结束日期
    private Long checkError;//验证压射号数
    @Transient
    private String shiftSeq;//班次轮班

    public String getShiftSeq() {
        return shiftSeq;
    }

    public void setShiftSeq(String shiftSeq) {
        this.shiftSeq = shiftSeq;
    }

    public Long getCheckError() {
        return checkError;
    }

    public void setCheckError(Long checkError) {
        this.checkError = checkError;
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

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
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

    public String getArbpl() {
        return arbpl;
    }

    public void setArbpl(String arbpl) {
        this.arbpl = arbpl;
    }

    public String getKtext() {
        return ktext;
    }

    public void setKtext(String ktext) {
        this.ktext = ktext;
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

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
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

    public Integer getShotNum() {
        return shotNum;
    }

    public void setShotNum(Integer shotNum) {
        this.shotNum = shotNum;
    }

    public Integer getYeild() {
        return yeild;
    }

    public void setYeild(Integer yeild) {
        this.yeild = yeild;
    }

    public Integer getWasteNum() {
        return wasteNum;
    }

    public void setWasteNum(Integer wasteNum) {
        this.wasteNum = wasteNum;
    }

    public Integer getDifferentNum() {
        return differentNum;
    }

    public void setDifferentNum(Integer differentNum) {
        this.differentNum = differentNum;
    }

    public String getBrgew() {
        return brgew;
    }

    public void setBrgew(String brgew) {
        this.brgew = brgew;
    }
}
