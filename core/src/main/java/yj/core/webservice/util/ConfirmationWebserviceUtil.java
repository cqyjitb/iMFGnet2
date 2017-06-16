package yj.core.webservice.util;

import yj.core.webservice.dto.DTPP001Parameters;
import yj.core.webservice.receiver.DTPP001RecRes;
import yj.core.webservice.sender.DTPP001SendReq;
import yj.core.webservice.sender.SIPP001SenderSync;
import yj.core.webservice.sender.SIPP001SenderSyncService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Map;

/**
 * Created by TFR on 2017/6/14.
 */
public class ConfirmationWebserviceUtil {
    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/PP001/Sender", "SI_PP001_Sender_SyncService");

    public ConfirmationWebserviceUtil() {
    }

    //测试方法
    public String receiveConfirmation(DTPP001Parameters params){
        URL wsdlURL = SIPP001SenderSyncService.WSDL_LOCATION;
        SIPP001SenderSyncService ss = new SIPP001SenderSyncService(wsdlURL, SERVICE_NAME);
        SIPP001SenderSync port = ss.getHTTPPort();
        // Set credentials
        Map<String, Object> reqCtxt = ((javax.xml.ws.BindingProvider) port).getRequestContext();
        reqCtxt.put(javax.xml.ws.BindingProvider.USERNAME_PROPERTY, "HAPUSER");
        reqCtxt.put(javax.xml.ws.BindingProvider.PASSWORD_PROPERTY, "Yjsap123@CQ");


        System.out.println("Invoking siPP001SenderSync...");
        //新建参数
        DTPP001SendReq.ITEM item = new DTPP001SendReq.ITEM();
        item.setPWERK(params.getPWERK());
        item.setAUFNR(params.getAUFNR());
        item.setVORNR(params.getVORNR());
        item.setBUDAT(params.getBUDAT());
        item.setGMNGA(params.getGMNGA());
        item.setXMNGA(params.getXMNGA());
        item.setRMNGA(params.getRMNGA());
        item.setZSCBC(params.getZSCBC());
        item.setZSCX(params.getZSCX());
        item.setZMNUM(params.getZMNUM());
        item.setDATUM(params.getDATUM());
        item.setZPGDBAR(params.getZPGDBAR());
        item.setZPGDBH(params.getZPGDBH());

        DTPP001SendReq _siPP001SenderSync_mtPP001SendReq = new DTPP001SendReq();
        //传入参数
        _siPP001SenderSync_mtPP001SendReq.getITEM().add(item);

        DTPP001RecRes _siPP001SenderSync__return = port.siPP001SenderSync(_siPP001SenderSync_mtPP001SendReq);
        String message = _siPP001SenderSync__return.getRETURN().getMESSAGE();
        String msdId = _siPP001SenderSync__return.getRETURN().getMSDID();
        String msgNo = _siPP001SenderSync__return.getRETURN().getMSGNO();
        String msgTy = _siPP001SenderSync__return.getRETURN().getMSGTY();
        String msgV1 = _siPP001SenderSync__return.getRETURN().getMSGV1();
        String msgV2 = _siPP001SenderSync__return.getRETURN().getMSGV2();
        String msgV3 = _siPP001SenderSync__return.getRETURN().getMSGV3();
        String msgV4 = _siPP001SenderSync__return.getRETURN().getMSGV4();
        String returnResult = "siPP001SenderSync.result= message:" + message +"\tmsdId:"+msdId+"\tmsgNo:"+msgNo+"\tmsgTy:"+msgTy+"\tmsgV1:"+msgV1+"\tmsgV2:"+msgV2+"\tmsgV3:"+msgV3+"\tmsgV4:"+msgV4;

        return returnResult;

    }
}
