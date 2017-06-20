package yj.core.webservice.components;


import yj.core.webservice.dto.DTPP001Parameters;

/**
 * Created by TFR on 2017/6/14.
 */
public class test {

    public static void main(String[] args) {
        ConfirmationWebserviceUtil util = new ConfirmationWebserviceUtil();
        //InputLog inputLog = new InputLog();
        DTPP001Parameters param = new DTPP001Parameters();
        param.setPWERK("1001");
        param.setAUFNR("12131423545346");
        param.setVORNR("1010");
        param.setBUDAT("20170301");
        param.setGMNGA("17");
        param.setXMNGA("2");
        param.setRMNGA("1");
        param.setZSCBC("");
        param.setZSCX("");
        param.setZMNUM("");
        param.setDATUM("");
        param.setZPGDBAR("");
        param.setZPGDBH("");

        /*inputLog.setPlant("1001");
        inputLog.setOrderno("12131423545346");
        inputLog.setOperation("1010");
        inputLog.setPostingDate("20170301");
        inputLog.setYeild(17d);
        inputLog.setWorkScrap(2d);
        inputLog.setRowScrap(2d);
        inputLog.setClassgrp("");
        inputLog.setLine("");
        inputLog.setModelNo("");
        inputLog.setDispatch("");
        inputLog.setDispatchLogicID("");*/
        util.receiveConfirmation(param);

    }
}
