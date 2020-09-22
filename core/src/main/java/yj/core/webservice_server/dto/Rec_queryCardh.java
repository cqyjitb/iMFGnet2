package yj.core.webservice_server.dto;

import java.util.List;

public class Rec_queryCardh {
    private String zpgdbar;

    private List<Aufnr> aufnrs;

    public String getZpgdbar() {
        return zpgdbar;
    }

    public void setZpgdbar(String zpgdbar) {
        this.zpgdbar = zpgdbar;
    }

    public List<Aufnr> getAufnrs() {
        return aufnrs;
    }

    public void setAufnrs(List<Aufnr> aufnrs) {
        this.aufnrs = aufnrs;
    }
}


