package yj.core.marc.dto;

/**
 * Auto Generated By Hap Code Generator
 **/

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@ExtensionAttribute(disable = true)
@Table(name = "sap_marc")
public class Marc extends BaseDTO {
    @NotEmpty
    private String matnr;

    @Id
    @GeneratedValue
    private String werks;

    private String mtart;

    private String matkl;

    private String meins;

    private String groes;

    private Double brgew;

    private Double ntgew;

    private String gewei;

    private String mmsta;

    private String mstae;

    private String lvorm;

    private Double xumrez;

    private Double tumrez;

    private Double ausss;

    private Double bstmi;

    private Double bstma;

    private Double mabst;

    private Double bstrf;

    private Double eisbe;

    private Double dzeit;

    private String lgpro;

    private String lgfsb;

    private String bwtty;

    private String dispo;

    private String rgekz;

    private String fevor;

    private String maktx;

    private Double flgrg;

    private String fifof;

    private String mhhzf;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    private String attr5;

    private String attr6;

    private String attr7;

    private String zbtif; //半托盘发货标志  1:允许

    private String yzcbarf;

    private Date creationDate; //创建时间

    private Long createdBy; //创建人

    private double onLineUpCap;

    private double onLineDownCap;

    private String customer;//客户名称

    private String sgqxFlag;//是否手工录入质量数据

    private Double unitPrice;//单件损失金额


    @Transient
    private Date lastUpdatedDate; //更新时间

    private Long lastUpdatedBy; //更新人


    private String bukrs;//公司
    private Long fileidA;
    private Long fileidB;
    private String fileidAName;
    private String fileidBName;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getSgqxFlag() {
        return sgqxFlag;
    }

    public void setSgqxFlag(String sgqxFlag) {
        this.sgqxFlag = sgqxFlag;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getOnLineUpCap() {
        return onLineUpCap;
    }

    public void setOnLineUpCap(double onLineUpCap) {
        this.onLineUpCap = onLineUpCap;
    }

    public double getOnLineDownCap() {
        return onLineDownCap;
    }

    public void setOnLineDownCap(double onLineDownCap) {
        this.onLineDownCap = onLineDownCap;
    }

    public Long getFileidA() {
        return fileidA;
    }

    public void setFileidA(Long fileidA) {
        this.fileidA = fileidA;
    }

    public Long getFileidB() {
        return fileidB;
    }

    public void setFileidB(Long fileidB) {
        this.fileidB = fileidB;
    }

    public String getFileidAName() {
        return fileidAName;
    }

    public void setFileidAName(String fileidAName) {
        this.fileidAName = fileidAName;
    }

    public String getFileidBName() {
        return fileidBName;
    }

    public void setFileidBName(String fileidBName) {
        this.fileidBName = fileidBName;
    }

    public String getBukrs() {
        return bukrs;
    }

    public void setBukrs(String bukrs) {
        this.bukrs = bukrs;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getWerks() {
        return werks;
    }

    public void setMtart(String mtart) {
        this.mtart = mtart;
    }

    public String getMtart() {
        return mtart;
    }

    public void setMatkl(String matkl) {
        this.matkl = matkl;
    }

    public String getMatkl() {
        return matkl;
    }

    public void setMeins(String meins) {
        this.meins = meins;
    }

    public String getMeins() {
        return meins;
    }

    public void setGroes(String groes) {
        this.groes = groes;
    }

    public String getGroes() {
        return groes;
    }

    public void setBrgew(Double brgew) {
        this.brgew = brgew;
    }

    public Double getBrgew() {
        return brgew;
    }

    public void setNtgew(Double ntgew) {
        this.ntgew = ntgew;
    }

    public Double getNtgew() {
        return ntgew;
    }

    public void setGewei(String gewei) {
        this.gewei = gewei;
    }

    public String getGewei() {
        return gewei;
    }

    public void setMmsta(String mmsta) {
        this.mmsta = mmsta;
    }

    public String getMmsta() {
        return mmsta;
    }

    public void setMstae(String mstae) {
        this.mstae = mstae;
    }

    public String getMstae() {
        return mstae;
    }

    public void setLvorm(String lvorm) {
        this.lvorm = lvorm;
    }

    public String getLvorm() {
        return lvorm;
    }

    public void setXumrez(Double xumrez) {
        this.xumrez = xumrez;
    }

    public Double getXumrez() {
        return xumrez;
    }

    public void setTumrez(Double tumrez) {
        this.tumrez = tumrez;
    }

    public Double getTumrez() {
        return tumrez;
    }

    public void setAusss(Double ausss) {
        this.ausss = ausss;
    }

    public Double getAusss() {
        return ausss;
    }

    public void setBstmi(Double bstmi) {
        this.bstmi = bstmi;
    }

    public Double getBstmi() {
        return bstmi;
    }

    public void setBstma(Double bstma) {
        this.bstma = bstma;
    }

    public Double getBstma() {
        return bstma;
    }

    public void setMabst(Double mabst) {
        this.mabst = mabst;
    }

    public Double getMabst() {
        return mabst;
    }

    public void setBstrf(Double bstrf) {
        this.bstrf = bstrf;
    }

    public Double getBstrf() {
        return bstrf;
    }

    public void setEisbe(Double eisbe) {
        this.eisbe = eisbe;
    }

    public Double getEisbe() {
        return eisbe;
    }

    public void setDzeit(Double dzeit) {
        this.dzeit = dzeit;
    }

    public Double getDzeit() {
        return dzeit;
    }

    public void setLgpro(String lgpro) {
        this.lgpro = lgpro;
    }

    public String getLgpro() {
        return lgpro;
    }

    public void setLgfsb(String lgfsb) {
        this.lgfsb = lgfsb;
    }

    public String getLgfsb() {
        return lgfsb;
    }

    public void setBwtty(String bwtty) {
        this.bwtty = bwtty;
    }

    public String getBwtty() {
        return bwtty;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    public String getDispo() {
        return dispo;
    }

    public void setRgekz(String rgekz) {
        this.rgekz = rgekz;
    }

    public String getRgekz() {
        return rgekz;
    }

    public void setFevor(String fevor) {
        this.fevor = fevor;
    }

    public String getFevor() {
        return fevor;
    }

    public void setMaktx(String maktx) {
        this.maktx = maktx;
    }

    public String getMaktx() {
        return maktx;
    }

    public void setFlgrg(Double flgrg) {
        this.flgrg = flgrg;
    }

    public Double getFlgrg() {
        return flgrg;
    }

    public void setFifof(String fifof) {
        this.fifof = fifof;
    }

    public String getFifof() {
        return fifof;
    }

    public void setMhhzf(String mhhzf) {
        this.mhhzf = mhhzf;
    }

    public String getMhhzf() {
        return mhhzf;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    public String getAttr4() {
        return attr4;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }

    public String getAttr5() {
        return attr5;
    }

    public void setAttr6(String attr6) {
        this.attr6 = attr6;
    }

    public String getAttr6() {
        return attr6;
    }

    public void setAttr7(String attr7) {
        this.attr7 = attr7;
    }

    public String getAttr7() {
        return attr7;
    }

    public String getYzcbarf() {
        return yzcbarf;
    }

    public void setYzcbarf(String yzcbarf) {
        this.yzcbarf = yzcbarf;
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
    public Long getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Override
    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getZbtif() {
        return zbtif;
    }

    public void setZbtif(String zbtif) {
        this.zbtif = zbtif;
    }
}
