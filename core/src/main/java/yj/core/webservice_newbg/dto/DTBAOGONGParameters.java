package yj.core.webservice_newbg.dto;

/**
 * Created by 917110140 on 2018/8/11.
 */
public class DTBAOGONGParameters
{

    private String PWERK;//工厂
    private String AUFNR;//订单
    private String VORNR;//工序号 0150
    private String BUDAT;//过账日期(默认当前日期)
    private String GMNGA;//合格品数量
    private String XMNGA;//工废
    private String RMNGA;//料废
    private String ZSCBC;//生产班组 A B C
    private String ZSCX;//生产线
    private String ZMNUM;//模号
    private String DATUM;//日期
    private String ZPGDBAR;//派工单号
    private String ZPGDBH;//派工单编号
    private String RSNUM;//确认号（冲销必须传）
    private String RSPOS;//确认序号（冲销必须传）
    private String REVERSE;//0：正常报工 1：冲销
    private String ATTR1;//操作员ID
    private String ATTR2;//操作员ID
    private String ATTR3;//操作员ID
    private String ATTR4;//班次 1 2 3
    private String ATTR5;//日期序列
    private String ATTR6;//生产日期
    private String ATTR7;//班标
    private String USERNAME;//创建人ID
    private String ARBPL;//工作中心
    private String ZPRTP;//1-正常，2-盘亏调整，3-盘盈调整；
    private String ZTPBAR;//托盘号
    private String AUART;//机加订单类型
    private String FSTVOR;//首工序 ""
    private String LSTVOR;//末工序（"X"）
    private String CHARG;//""

    public String getARBPL() {
        return ARBPL;
    }

    public void setARBPL(String ARBPL) {
        this.ARBPL = ARBPL;
    }

    public String getCHARG() {
        return CHARG;
    }

    public void setCHARG(String CHARG) {
        this.CHARG = CHARG;
    }

    public String getPWERK() {
        return PWERK;
    }

    public void setPWERK(String PWERK) {
        this.PWERK = PWERK;
    }

    public String getAUFNR() {
        return AUFNR;
    }

    public void setAUFNR(String AUFNR) {
        this.AUFNR = AUFNR;
    }

    public String getVORNR() {
        return VORNR;
    }

    public void setVORNR(String VORNR) {
        this.VORNR = VORNR;
    }

    public String getBUDAT() {
        return BUDAT;
    }

    public void setBUDAT(String BUDAT) {
        this.BUDAT = BUDAT;
    }

    public String getGMNGA() {
        return GMNGA;
    }

    public void setGMNGA(String GMNGA) {
        this.GMNGA = GMNGA;
    }

    public String getXMNGA() {
        return XMNGA;
    }

    public void setXMNGA(String XMNGA) {
        this.XMNGA = XMNGA;
    }

    public String getRMNGA() {
        return RMNGA;
    }

    public void setRMNGA(String RMNGA) {
        this.RMNGA = RMNGA;
    }

    public String getZSCBC() {
        return ZSCBC;
    }

    public void setZSCBC(String ZSCBC) {
        this.ZSCBC = ZSCBC;
    }

    public String getZSCX() {
        return ZSCX;
    }

    public void setZSCX(String ZSCX) {
        this.ZSCX = ZSCX;
    }

    public String getZMNUM() {
        return ZMNUM;
    }

    public void setZMNUM(String ZMNUM) {
        this.ZMNUM = ZMNUM;
    }

    public String getDATUM() {
        return DATUM;
    }

    public void setDATUM(String DATUM) {
        this.DATUM = DATUM;
    }

    public String getZPGDBAR() {
        return ZPGDBAR;
    }

    public void setZPGDBAR(String ZPGDBAR) {
        this.ZPGDBAR = ZPGDBAR;
    }

    public String getZPGDBH() {
        return ZPGDBH;
    }

    public void setZPGDBH(String ZPGDBH) {
        this.ZPGDBH = ZPGDBH;
    }

    public String getRSNUM() {
        return RSNUM;
    }

    public void setRSNUM(String RSNUM) {
        this.RSNUM = RSNUM;
    }

    public String getRSPOS() {
        return RSPOS;
    }

    public void setRSPOS(String RSPOS) {
        this.RSPOS = RSPOS;
    }

    public String getREVERSE() {
        return REVERSE;
    }

    public void setREVERSE(String REVERSE) {
        this.REVERSE = REVERSE;
    }

    public String getATTR1() {
        return ATTR1;
    }

    public void setATTR1(String ATTR1) {
        this.ATTR1 = ATTR1;
    }

    public String getATTR2() {
        return ATTR2;
    }

    public void setATTR2(String ATTR2) {
        this.ATTR2 = ATTR2;
    }

    public String getATTR3() {
        return ATTR3;
    }

    public void setATTR3(String ATTR3) {
        this.ATTR3 = ATTR3;
    }

    public String getATTR4() {
        return ATTR4;
    }

    public void setATTR4(String ATTR4) {
        this.ATTR4 = ATTR4;
    }

    public String getATTR5() {
        return ATTR5;
    }

    public void setATTR5(String ATTR5) {
        this.ATTR5 = ATTR5;
    }

    public String getATTR6() {
        return ATTR6;
    }

    public void setATTR6(String ATTR6) {
        this.ATTR6 = ATTR6;
    }

    public String getATTR7() {
        return ATTR7;
    }

    public void setATTR7(String ATTR7) {
        this.ATTR7 = ATTR7;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getZPRTP() {
        return ZPRTP;
    }

    public void setZPRTP(String ZPRTP) {
        this.ZPRTP = ZPRTP;
    }

    public String getZTPBAR() {
        return ZTPBAR;
    }

    public void setZTPBAR(String ZTPBAR) {
        this.ZTPBAR = ZTPBAR;
    }

    public String getAUART() {
        return AUART;
    }

    public void setAUART(String AUART) {
        this.AUART = AUART;
    }

    public String getFSTVOR() {
        return FSTVOR;
    }

    public void setFSTVOR(String FSTVOR) {
        this.FSTVOR = FSTVOR;
    }

    public String getLSTVOR() {
        return LSTVOR;
    }

    public void setLSTVOR(String LSTVOR) {
        this.LSTVOR = LSTVOR;
    }


}
