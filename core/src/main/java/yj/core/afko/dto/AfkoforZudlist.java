package yj.core.afko.dto;

public class AfkoforZudlist {

    private String aufnr;
    private String auart;
    private Long fgnum;//报废的数量合计
    private Long gfnum;
    private Long lfnum;
    private Long qnum;//处理结论为Q的数量合计
    private String matnr;
    private String auartxt;
    private String gltrp;
    private String gstrp;
    private Double gamng;
    private String itemsf;
    private String itemsq;
    private String zudnum;
    private String lineId;

    public AfkoforZudlist() {
        this.fgnum = 0l;
        this.gfnum = 0l;
        this.lfnum = 0l;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public Long getGfnum() {
        return gfnum;
    }

    public void setGfnum(Long gfnum) {
        this.gfnum = gfnum;
    }

    public Long getLfnum() {
        return lfnum;
    }

    public void setLfnum(Long lfnum) {
        this.lfnum = lfnum;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public Long getQnum() {
        return qnum;
    }

    public void setQnum(Long qnum) {
        this.qnum = qnum;
    }

    public String getItemsf() {
        return itemsf;
    }

    public void setItemsf(String itemsf) {
        this.itemsf = itemsf;
    }

    public String getItemsq() {
        return itemsq;
    }

    public void setItemsq(String itemsq) {
        this.itemsq = itemsq;
    }

    public String getZudnum() {
        return zudnum;
    }

    public void setZudnum(String zudnum) {
        this.zudnum = zudnum;
    }

    public String getAuartxt() {
        return auartxt;
    }

    public void setAuartxt(String auartxt) {
        this.auartxt = auartxt;
    }

    public String getGltrp() {
        return gltrp;
    }

    public void setGltrp(String gltrp) {
        this.gltrp = gltrp;
    }

    public String getGstrp() {
        return gstrp;
    }

    public void setGstrp(String gstrp) {
        this.gstrp = gstrp;
    }

    public Double getGamng() {
        return gamng;
    }

    public void setGamng(Double gamng) {
        this.gamng = gamng;
    }

    public String getAufnr() {
        return aufnr;
    }

    public void setAufnr(String aufnr) {
        this.aufnr = aufnr;
    }

    public String getAuart() {
        return auart;
    }

    public void setAuart(String auart) {
        this.auart = auart;
    }

    public Long getFgnum() {
        return fgnum;
    }

    public void setFgnum(Long fgnum) {
        this.fgnum = fgnum;
    }
}
