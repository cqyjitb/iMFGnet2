package yj.core.webservice_server.dto;

public class Rec_Outsrgreceipthead {

    private String receiptnm; //外协收货单号

    private String werks; //工厂

    private String lifnr; //供应商编号

    private String matnr; //物料编号

    private String status; //单据状态

    private String prtflag; //打印标识

    private String zipdat;

    private String ziptim;

    private String zipuser;

    public String getReceiptnm() {
        return receiptnm;
    }

    public void setReceiptnm(String receiptnm) {
        this.receiptnm = receiptnm;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getLifnr() {
        return lifnr;
    }

    public void setLifnr(String lifnr) {
        this.lifnr = lifnr;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrtflag() {
        return prtflag;
    }

    public void setPrtflag(String prtflag) {
        this.prtflag = prtflag;
    }

    public String getZipdat() {
        return zipdat;
    }

    public void setZipdat(String zipdat) {
        this.zipdat = zipdat;
    }

    public String getZiptim() {
        return ziptim;
    }

    public void setZiptim(String ziptim) {
        this.ziptim = ziptim;
    }

    public String getZipuser() {
        return zipuser;
    }

    public void setZipuser(String zipuser) {
        this.zipuser = zipuser;
    }
}
