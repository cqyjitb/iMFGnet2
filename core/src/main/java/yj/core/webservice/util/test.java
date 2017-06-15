package yj.core.webservice.util;

import yj.core.webservice.dto.DTPP001Parameters;

/**
 * Created by TFR on 2017/6/14.
 */
public class test {

    public static void main(String[] args) {
        ConfirmationWebserviceUtil util = new ConfirmationWebserviceUtil();
        DTPP001Parameters params = new DTPP001Parameters("","","","","","","","","","","","","");
        String result = util.receiveConfirmation(params);
        System.out.println(result);
    }
}
