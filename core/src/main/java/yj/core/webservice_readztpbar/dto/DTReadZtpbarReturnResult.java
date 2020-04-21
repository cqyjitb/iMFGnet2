package yj.core.webservice_readztpbar.dto;

import yj.core.ztbc0002.dto.Ztbc0002;

import java.util.List;

public class DTReadZtpbarReturnResult {
    private String TYPE;
    private String MESSAGE;
    private List<Ztbc0002> ztbc0002List;

    public List<Ztbc0002> getZtbc0002List() {
        return ztbc0002List;
    }

    public void setZtbc0002List(List<Ztbc0002> ztbc0002List) {
        this.ztbc0002List = ztbc0002List;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }
}
