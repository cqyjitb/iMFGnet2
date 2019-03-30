package yj.core.webservice_server;

import org.springframework.web.context.ContextLoaderListener;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.mapper.CardhMapper;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.dto.Log;
import yj.core.dispatch.dto.Result;
import yj.core.dispatch.mapper.InputLogMapper;
import yj.core.dispatch.mapper.LogMapper;
import yj.core.dispatch.mapper.ResultMapper;
import yj.core.webservice_newbg.components.ConfirmationWebserviceUtilNew;
import yj.core.webservice_newbg.dto.DTBAOGONGParameters;
import yj.core.webservice_newbg.dto.DTBAOGONGParametersitem;
import yj.core.webservice_newbg.dto.DTBAOGONGReturnResult;

import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 917110140 on 2018/9/17.
 */
@WebService
public class JjbgImpl implements IJjbg {

    @Override
    public DTBAOGONGReturnResult callbg(DTBAOGONGParameters params, List<DTBAOGONGParametersitem> list) {
        DTBAOGONGReturnResult rs = new DTBAOGONGReturnResult();
        ConfirmationWebserviceUtilNew confirmationWebserviceUtilNew = new ConfirmationWebserviceUtilNew();
        CardhMapper cardhMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(CardhMapper.class);
        InputLogMapper inputLogMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(InputLogMapper.class);
        ResultMapper resultMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ResultMapper.class);
        LogMapper logMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(LogMapper.class);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curdate = df.format(new Date()).substring(0,10).replaceAll("-","");
        String curtim  = df.format(new Date()).substring(11,19).replaceAll(":","");
        rs = confirmationWebserviceUtilNew.receiveConfirmation(params,list);
        if (rs.getMSGTY().equals("S")){
            Cardh cardh = new Cardh();

            cardh = cardhMapper.selectByBarcode(params.getZPGDBAR());
            cardh.setLastUpdatedDate(new Date());
            cardh.setLastUpdatedBy(Long.valueOf(params.getUSERNAME()));
            cardh.setEprddat(params.getBUDAT());
            cardh.setActgltrp(curdate);
            cardh.setActdt(curtim);
            cardh.setQtysm(Double.valueOf(params.getRMNGA()));
            cardh.setQtysp(Double.valueOf(params.getXMNGA()));
            cardh.setShift(params.getZSCBC());
            cardh.setAlqty(Double.valueOf(params.getGMNGA()));
            cardh.setStatus2(cardh.getStatus());
            cardh.setStatus("ECNF");
            cardh.setAttr4(params.getATTR4());//班次
            cardhMapper.updateCardhStatus(cardh);
        }
        Log log = new Log();
        Result result = new Result();
        InputLog inputLog = new InputLog();
        inputLog.setBarcode(params.getZPGDBAR());
        inputLog.setOrderno(params.getAUFNR());
        inputLog.setDispatch(params.getZPGDBAR());
        inputLog.setOperation(params.getVORNR());
        inputLog.setYeild(Double.parseDouble(params.getGMNGA()));
        inputLog.setWorkScrap(Double.parseDouble(params.getRMNGA()));
        inputLog.setRowScrap(Double.parseDouble(params.getXMNGA()));
        inputLog.setClassgrp(params.getZSCBC());
        inputLog.setLine(params.getZSCX());
        inputLog.setModelNo("");
        inputLog.setPlant(params.getPWERK());
        inputLog.setPostingDate(params.getBUDAT());
        inputLog.setDispatchLogicID(params.getZPGDBH());
        inputLog.setCreated_by(params.getUSERNAME());
        inputLog.setAttr1(params.getATTR1());
        inputLog.setAttr2(params.getATTR2());
        inputLog.setAttr3(params.getATTR3());
        inputLog.setAttr4(params.getATTR4());
        inputLog.setAttr5(params.getATTR5());
        inputLog.setAttr6(params.getATTR6());
        inputLog.setAttr7(params.getATTR7());
        inputLog.setUserName(params.getUSERNAME());
        inputLog.setMaterial(rs.getMATNR());
        inputLog.setMatDesc(rs.getMAKTX());
        inputLogMapper.insertInputLog(inputLog);

        Long id = inputLogMapper.selectNextId();
        result.setPlant(inputLog.getPlant());
        result.setInputId(id);
        if (params.getREVERSE().equals("X")){
            result.setIsReversed("1");//冲销
        }else{
            result.setIsReversed("0");//报工
        }
        result.setMaterial(inputLog.getMaterial());
        result.setMatDesc(inputLog.getMatDesc());
        result.setCreated_by(inputLog.getCreated_by());
        result.setConfirmationNo(rs.getRSNUM());
        result.setConfirmationPos(rs.getRSPOS());
        result.setFevor(rs.getFEVOR());
        result.setFevorTxt(rs.getTXT());
        result.setOperationDesc(rs.getLTXA1());
        log.setMsgty(rs.getMSGTY());
        log.setMsgtx(rs.getMESSAGE());

        if (params.getREVERSE().equals("X")){

            log.setTranType("1");
        }else{

            log.setTranType("0");
        }
        log.setRefId(id);
        log.setCreated_by(inputLog.getCreated_by());
        resultMapper.insertResult(result);
        logMapper.insertLog(log);
        return  rs;
    }
}
