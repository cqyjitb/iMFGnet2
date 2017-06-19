package yj.core.dispatch.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.service.IInputLogService;
import yj.core.webservice.components.ConfirmationWebserviceUtil;
import yj.core.webservice.dto.DTPP001ReturnResult;
import yj.core.webservice.receiver.DTPP001SendRes;
import yj.core.webservice.sender.DTPP001SendReq;
import yj.core.webservice.sender.SIPP001SenderSync;
import yj.core.webservice.sender.SIPP001SenderSyncService;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
public class InputLogController extends BaseController{

@Autowired
private IInputLogService service;

@Autowired
private ConfirmationWebserviceUtil util;


/*
*报功日志界面查询
 */
    @RequestMapping(value = "/confirmation/input/log/queryLog")
    @ResponseBody
    public ResponseData queryWriteOff(InputLog dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);

            if ( dto.getCreatDateBefore() !=null){
                String cdBefore;
                cdBefore = dto.getCreatDateBefore();
                cdBefore = dto.getCreatDateBefore().replace("00:00:00","23:59:59");
                dto.setCreatDateBefore(cdBefore);

            }
            if(dto.getPostingDateBefore() != null){
                String pdBefore;
                pdBefore = dto.getPostingDateBefore();
                pdBefore = dto.getPostingDateBefore().replace("00:00:00","23:59:59");
                dto.setPostingDateBefore(pdBefore);
            }


        return new ResponseData(service.queryAllLog(requestContext,dto,page,pageSize));
    }


    /*
    *报功结果/报功冲销界面查询
     */
    @RequestMapping(value = "/confirmation/input/log/queryResult")
    @ResponseBody
    public ResponseData queryResult(InputLog dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        if ( dto.getCreatDateBefore() !=null){
            String cdBefore;
            cdBefore = dto.getCreatDateBefore();
            cdBefore = dto.getCreatDateBefore().replace("00:00:00","23:59:59");
            dto.setCreatDateBefore(cdBefore);

        }
        if(dto.getPostingDateBefore() != null){
            String pdBefore;
            pdBefore = dto.getPostingDateBefore();
            pdBefore = dto.getPostingDateBefore().replace("00:00:00","23:59:59");
            dto.setPostingDateBefore(pdBefore);
        }

        List<InputLog> list = service.queryAllBg(requestContext,dto,page,pageSize);
/*

        for (InputLog i:list
             ) {
            System.out.println(i.getPostingDate());
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            String d = dateFormat.format(i.getPostingDate());
            System.out.println(d);
            System.out.println(i);
        }
*/

        return new ResponseData(list);
    }


@RequestMapping(value = "/confirmation/input/log/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, BindingResult result, @RequestBody List<InputLog> dto){
    getValidator().validate(dto, result);
    if (result.hasErrors()) {
        ResponseData rd = new ResponseData(false);
        rd.setMessage(getErrorMessage(result, request));
        return rd;
    }
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

    @RequestMapping(value = "/confirmation/input/test")
    @ResponseBody
    public void  test(){
        InputLog inputLog = new InputLog();
        inputLog.setPlant("1001");
        inputLog.setOrderno("1000010");
        inputLog.setOperation("0010");
        inputLog.setPostingDate("20170321");
        inputLog.setYeild(17d);
        inputLog.setWorkScrap(2d);
        inputLog.setRowScrap(1d);
        inputLog.setClassgrp("");
        inputLog.setLine("");
        inputLog.setModelNo("");
        inputLog.setCreatDate("20170321");
        inputLog.setBarcode("1001100001000010010");
        inputLog.setDispatch("");
        util.receiveConfirmation();

        //service.returnResultAndUpdateConfirmation(inputLog);

    }
    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/PP001/Sender", "SI_PP001_Sender_SyncService");

    public DTPP001ReturnResult receiveConfirmation(){
        URL wsdlURL = SIPP001SenderSyncService.WSDL_LOCATION;
        SIPP001SenderSyncService ss = new SIPP001SenderSyncService(wsdlURL,SERVICE_NAME);
        SIPP001SenderSync port = ss.getHTTPPort();
        // Set credentials
        Map<String, Object> reqCtxt = ((javax.xml.ws.BindingProvider) port).getRequestContext();
        reqCtxt.put(javax.xml.ws.BindingProvider.USERNAME_PROPERTY, "hand");
        reqCtxt.put(javax.xml.ws.BindingProvider.PASSWORD_PROPERTY, "h@nd1234");


        System.out.println("Invoking siPP001SenderSync...");
        DateFormat df=new SimpleDateFormat("yyyyMMdd");
        //新建参数
        DTPP001SendReq.ITEM item = new DTPP001SendReq.ITEM();
        item.setPWERK("1001");
        item.setAUFNR("1000010");
        item.setVORNR("0010");
        item.setBUDAT("20170321");
        item.setGMNGA("");
        item.setXMNGA("");
        item.setRMNGA("");
        item.setRMNGA("");
        item.setZSCBC("");
        item.setZSCX("");
        item.setZMNUM("");
        item.setDATUM("");
        item.setZPGDBAR("");
        item.setZPGDBH("");

        DTPP001SendReq _siPP001SenderSync_mtPP001SendReq = new DTPP001SendReq();
        //传入参数
        _siPP001SenderSync_mtPP001SendReq.getITEM().add(item);

        DTPP001SendRes _siPP001SenderSync__return = port.siPP001SenderSync(_siPP001SenderSync_mtPP001SendReq);
        System.out.println(_siPP001SenderSync__return.getDETAIL().get(0).getMESSAGE());
        /*String aufnr = _siPP001SenderSync__return.getDETAIL().get(0).getAUFNR();//订单号
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
        returnResult.setMESSAGE(message);*/

        // String returnResult = "siPP001SenderSync.result= message:" + message +"\taufnr:"+aufnr+"\tmatnr:"+matnr+"\tmsgId:"+msgId+"\tmsgNo:"+msgNo+"\tmsgTy:"+msgTy+"\tmsgV1:"+msgV1+"\tmsgV2:"+msgV2+"\tmsgV3:"+msgV3+"\tmsgV4:"+msgV4;

        return null;

    }


}