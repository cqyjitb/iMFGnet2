package yj.core.dispatch.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.dto.Log;
import yj.core.dispatch.dto.Result;
import yj.core.dispatch.mapper.InputLogMapper;
import yj.core.dispatch.mapper.LogMapper;
import yj.core.dispatch.mapper.ResultMapper;
import yj.core.dispatch.service.IInputLogService;
import yj.core.webservice.components.ConfirmationWebserviceUtil;
import yj.core.webservice.dto.DTPP001Parameters;
import yj.core.webservice.dto.DTPP001ReturnResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class InputLogServiceImpl extends BaseServiceImpl<InputLog> implements IInputLogService {

    @Autowired
    private ConfirmationWebserviceUtil webserviceUtil;

    @Autowired
    private InputLogMapper inputLogMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private ResultMapper resultMapper;

    DateFormat df=new SimpleDateFormat("yyyyMMdd");

    //报功结果页面数据查询
    @Override
    public List<InputLog> queryAllBg(IRequest iRequest, InputLog inputLog, int page, int pageSize) {
        return inputLogMapper.queryAllBg(inputLog);
    }



    //报功日志界面数据查询
    public List<InputLog> queryAllLog(IRequest iRequest, InputLog inputLog, int page, int pageSize) {
        return inputLogMapper.queryAllLog(inputLog);
    }
    //插入一条信息到confirmation_input_log
    public int insertInputLog(InputLog inputLog) {
        return inputLogMapper.insertInputLog(inputLog);
    }

    ;

    //根据ID查询表格confirmation_input_log
    public int queryInputLogById(Long id) {
        return inputLogMapper.queryInputLogById(id);
    }


    @Override
    public void nextId(InputLog input) {
        inputLogMapper.insert(input);
        Long id = inputLogMapper.selectNextId();
        System.out.println(id);
    }

    @Override
    public DTPP001ReturnResult inputDispatch(InputLog input) {
        DTPP001ReturnResult returnResult = new DTPP001ReturnResult();
        returnResult.setMESSAGE("报工失败！当前工序报工数量大于前工序合格数量。");
        returnResult.setMSGTY("E");
        Double currentInputSum = 0d;
        Double historyMaxOperationYeildSum = 0d;
        List<InputLog> inputLogs = inputLogMapper.confirmationInfoByOrdernoAndOperation(input);
        List<InputLog> maxInputLogs = inputLogMapper.confirmationMaxOperationInfo(input);
        if (inputLogs.size() > 0) {
            for (InputLog inputLog : inputLogs) {
                currentInputSum += inputLog.getYeild() + inputLog.getRowScrap() + inputLog.getWorkScrap();
            }
        }
        currentInputSum = currentInputSum + input.getYeild() + input.getRowScrap() + input.getWorkScrap();
        if (maxInputLogs.size() > 0) {
            for (InputLog inputLog : maxInputLogs) {
                historyMaxOperationYeildSum += inputLog.getYeild();
            }
            if (historyMaxOperationYeildSum >= currentInputSum) {
                 return returnResultAndUpdateConfirmation(input);
            } else {
                //不允许报工，错误提示信息（报工失败！当前工序报工数量大于前工序合格数量。）
                return returnResult;
            }
        } else {
            return returnResultAndUpdateConfirmation(input);
        }
    }


    public DTPP001ReturnResult returnResultAndUpdateConfirmation(InputLog inputLog){

        DTPP001Parameters param = new DTPP001Parameters();
        param.setPWERK(inputLog.getPlant());
        param.setAUFNR(inputLog.getOrderno());
        param.setVORNR(inputLog.getOperation());
        param.setBUDAT(inputLog.getPostingDate().replaceAll("-",""));
        param.setGMNGA(inputLog.getYeild().toString());
        param.setXMNGA(inputLog.getWorkScrap().toString());
        param.setRMNGA(inputLog.getRowScrap().toString());
        param.setZSCBC(inputLog.getClassgrp() == null ? "" : inputLog.getClassgrp());
        param.setZSCX(inputLog.getLine() == null ? "" : inputLog.getLine());
        param.setZMNUM(inputLog.getModelNo() == null ? "" : inputLog.getModelNo());
        param.setDATUM(df.format(new Date()));
        param.setZPGDBAR(inputLog.getDispatch());
        param.setZPGDBH(inputLog.getDispatchLogicID());

        DTPP001ReturnResult returnResult = webserviceUtil.receiveConfirmation(param);
        Log log = null;
        Result result = null;
        if("S".equals(returnResult.getMSGTY())){
            //添加数据
            inputLogMapper.insertInputLog(inputLog);
            Long id = inputLogMapper.selectNextId();
            //System.out.println(id);
            result.setPlant(inputLog.getPlant());
            result.setInputId(id);
            result.setIsReversed("0");
            result.setMaterial(inputLog.getMaterial());
            result.setMatDesc(inputLog.getMatDesc());
            log.setMsgty(returnResult.getMSGTY());
            log.setMsgtx(returnResult.getMESSAGE());
            log.setTranType("0");
            log.setRefId(id);
            resultMapper.insertResult(result);
            logMapper.insertLog(log);

        }else{
            //只存log
            log.setMsgty(returnResult.getMSGTY());
            log.setMsgtx(returnResult.getMESSAGE());
            log.setTranType("0");
            logMapper.insertLog(log);
        }
        return returnResult;

    }


    @Override
    public DTPP001ReturnResult writeOffDispatch(InputLog input){
        DTPP001ReturnResult returnResult = new DTPP001ReturnResult();
        returnResult.setMESSAGE("冲销失败！当前工序存在后续工序已报工。");
        returnResult.setMSGTY("E");
        List<InputLog> inputLogs = inputLogMapper.queryAllGTOperation(input);
        if (inputLogs.size() > 0){
            return returnResult;
        }
        return returnWriteOffResultAndUpdateConfirmation(input);
    }

    public DTPP001ReturnResult returnWriteOffResultAndUpdateConfirmation(InputLog input){
        DTPP001ReturnResult returnResult = new DTPP001ReturnResult();
        Log log = null;
        if("S".equals(returnResult.getMSGTY())){
            log.setMsgtx(returnResult.getMESSAGE());
            log.setMsgty(returnResult.getMSGTY());
            log.setTranType("1");
            log.setRefId(input.getId());
            logMapper.insertLog(log);

            resultMapper.updateReveseByInputId(input.getId());

        }
        return returnResult;
    }




}