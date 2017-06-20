package yj.core.webservice.dto;

/**
 * Created by TFR on 2017/6/15.
 */
public class DTPP001Parameters {

    private String PWERK;
    private String AUFNR;
    private String VORNR;
    private String BUDAT;
    private String GMNGA;
    private String XMNGA;
    private String RMNGA;
    private String ZSCBC;
    private String ZSCX;
    private String ZMNUM;
    private String DATUM;
    private String ZPGDBAR;
    private String ZPGDBH;
    public DTPP001Parameters(){

    }

    public DTPP001Parameters(String PWERK, String AUFNR, String VORNR, String BUDAT, String GMNGA, String XMNGA, String RMNGA, String ZSCBC, String ZSCX, String ZMNUM, String DATUM, String ZPGDBAR, String ZPGDBH) {
        this.PWERK = PWERK;
        this.AUFNR = AUFNR;
        this.VORNR = VORNR;
        this.BUDAT = BUDAT;
        this.GMNGA = GMNGA;
        this.XMNGA = XMNGA;
        this.RMNGA = RMNGA;
        this.ZSCBC = ZSCBC;
        this.ZSCX = ZSCX;
        this.ZMNUM = ZMNUM;
        this.DATUM = DATUM;
        this.ZPGDBAR = ZPGDBAR;
        this.ZPGDBH = ZPGDBH;
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
}
