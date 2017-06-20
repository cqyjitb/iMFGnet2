package yj.core.webservice.components;

import org.springframework.stereotype.Component;
import yj.core.dispatch.dto.InputLog;
import yj.core.webservice.dto.DTPP001ReturnResult;
import yj.core.webservice.receiver.DTPP001SendRes;
import yj.core.webservice.sender.DTPP001SendReq;
import yj.core.webservice.sender.SIPP001SenderSync;
import yj.core.webservice.sender.SIPP001SenderSyncService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by TFR on 2017/6/14.
 */
@Component
public class ConfirmationWebserviceUtil {
    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/PP001/Sender", "SI_PP001_Sender_SyncService");

    public ConfirmationWebserviceUtil() {
    }


    public DTPP001ReturnResult receiveConfirmation(InputLog input){
        URL wsdlURL = SIPP001SenderSyncService.WSDL_LOCATION;
        SIPP001SenderSyncService ss = new SIPP001SenderSyncService();
        SIPP001SenderSync port = ss.getHTTPPort();
        // Set credentials
        Map<String, Object> reqCtxt = ((javax.xml.ws.BindingProvider) port).getRequestContext();
        reqCtxt.put(javax.xml.ws.BindingProvider.USERNAME_PROPERTY, "hand");
        reqCtxt.put(javax.xml.ws.BindingProvider.PASSWORD_PROPERTY, "h@nd1234");


        System.out.println("Invoking siPP001SenderSync...");
        DateFormat df=new SimpleDateFormat("yyyyMMdd");
        //新建参数
        DTPP001SendReq.ITEM item = new DTPP001SendReq.ITEM();
        item.setPWERK(input.getPwerk());
        item.setAUFNR(input.getOrderno());
        item.setVORNR(input.getOperation());
        item.setBUDAT(df.format(input.getPostingDate()).toString());
        item.setGMNGA(input.getYeild().toString());
        item.setXMNGA(input.getWorkScrap().toString());
        item.setRMNGA(input.getRowScrap().toString());
        item.setZSCBC(input.getClassgrp());
        item.setZSCX(input.getLine());
        item.setZMNUM(input.getModelNo());
        item.setDATUM(df.format(input.getCreateDate()).toString());
        item.setZPGDBAR(input.getBarcode());
        item.setZPGDBH(input.getDispatch());

        DTPP001SendReq _siPP001SenderSync_mtPP001SendReq = new DTPP001SendReq();
        //传入参数
        _siPP001SenderSync_mtPP001SendReq.getITEM().add(item);

        DTPP001SendRes _siPP001SenderSync__return = port.siPP001SenderSync(_siPP001SenderSync_mtPP001SendReq);
        String aufnr = _siPP001SenderSync__return.getDETAIL().get(0).getAUFNR();//订单号
        String matnr = _siPP001SenderSync__return.getDETAIL().get(0).getMATNR();//物料号
        String msgTy = _siPP001SenderSync__return.getDETAIL().get(0).getMSGTY();//信息状态  S(成功)/E(失败)
        String msgNo = _siPP001SenderSync__return.getDETAIL().get(0).getMSGNO();//消息号
        String msgId = _siPP001SenderSync__return.getDETAIL().get(0).getMSGID();//消息ID
        String msgV1 = _siPP001SenderSync__return.getDETAIL().get(0).getMSGV1();
        String msgV2 = _siPP001SenderSync__return.getDETAIL().get(0).getMSGV2();
        String msgV3 = _siPP001SenderSync__return.getDETAIL().get(0).getMSGV3();
        String msgV4 = _siPP001SenderSync__return.getDETAIL().get(0).getMSGV4();
        String message = _siPP001SenderSync__return.getDETAIL().get(0).getMESSAGE();//信息

        DTPP001ReturnResult returnResult = new DTPP001ReturnResult();
        returnResult.setAUFNR(aufnr);
        returnResult.setMATNR(matnr);
        returnResult.setMSGTY(msgTy);
        returnResult.setMSGNO(msgNo);
        returnResult.setMSGID(msgId);
        returnResult.setMSGV1(msgV1);
        returnResult.setMSGV2(msgV2);
        returnResult.setMSGV3(msgV3);
        returnResult.setMSGV4(msgV4);
        returnResult.setMESSAGE(message);

       // String returnResult = "siPP001SenderSync.result= message:" + message +"\taufnr:"+aufnr+"\tmatnr:"+matnr+"\tmsgId:"+msgId+"\tmsgNo:"+msgNo+"\tmsgTy:"+msgTy+"\tmsgV1:"+msgV1+"\tmsgV2:"+msgV2+"\tmsgV3:"+msgV3+"\tmsgV4:"+msgV4;

        return returnResult;

    }
}
