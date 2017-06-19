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
import yj.core.webservice.dto.DTPP001ReturnResult;

import java.util.List;

@Service
@Transactional
public class InputLogServiceImpl extends BaseServiceImpl<InputLog> implements IInputLogService{

    @Autowired
    private ConfirmationWebserviceUtil webserviceUtil;

    @Autowired
    private InputLogMapper inputLogMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private ResultMapper resultMapper;

     @Override

    //报功结果页面数据查询
     public  List<InputLog> queryAllBg(IRequest iRequest, InputLog inputLog, int page,int pageSize){
         return inputLogMapper.queryAllBg(inputLog);
     };

    //报功日志界面数据查询
    public List<InputLog> queryAllLog(IRequest iRequest,InputLog inputLog,int page,int pageSize){
        return  inputLogMapper.queryAllLog(inputLog);
    };


    //插入一条信息到confirmation_input_log
    public int insertInputLog(InputLog inputLog){
        return inputLogMapper.insertInputLog(inputLog);
    };

    //根据ID查询表格confirmation_input_log
    public int queryInputLogById(Long id){
        return inputLogMapper.queryInputLogById(id);
    };


    @Override
    public DTPP001ReturnResult inputDispatch(InputLog input) {
        DTPP001ReturnResult returnResult = new DTPP001ReturnResult();
        returnResult.setMESSAGE("报工失败！当前工序报工数量大于前工序合格数量。");
        Double currentInputSum = 0d;
        Double historyMaxOperationYeildSum = 0d;
        List<InputLog> inputLogs = inputLogMapper.confirmationInfoByOrdernoAndOperation(input);
        List<InputLog> maxInputLogs = inputLogMapper.confirmationMaxOperationInfo(input);
        if(inputLogs.size() > 0){
            for (InputLog inputLog : inputLogs) {
                currentInputSum += inputLog.getYeild()+inputLog.getRowScrap()+inputLog.getWorkScrap();
            }
        }
        currentInputSum = currentInputSum + input.getYeild()+input.getRowScrap() + input.getWorkScrap();
        if(maxInputLogs.size() > 0 ){
            for(InputLog inputLog : maxInputLogs){
                historyMaxOperationYeildSum += inputLog.getYeild();
            }
            if(historyMaxOperationYeildSum >= currentInputSum){
                return returnResultAndUpdateConfirmation(input);
            }else{
                //不允许报工，错误提示信息（报工失败！当前工序报工数量大于前工序合格数量。）
                return returnResult;
            }
        }else{
            return returnResultAndUpdateConfirmation(input);
        }
    }

    @Override
    public void nextId(InputLog input){
        inputLogMapper.insert(input);
        Long id = inputLogMapper.selectNextId();
        System.out.println(id);
    }
    @Override
    public DTPP001ReturnResult returnResultAndUpdateConfirmation(InputLog input){
        DTPP001ReturnResult returnResult = webserviceUtil.receiveConfirmation();
        Log log = null;
        Result result = null;
        if("S".equals(returnResult.getMSGTY())){
            //添加数据
            inputLogMapper.insertInputLog(input);
            Long id = inputLogMapper.selectNextId();
            //System.out.println(id);
            result.setPlant(input.getPlant());
            result.setInputId(id);
            result.setIsReversed("0");
            result.setMaterial(input.getMaterial());
            result.setMatDesc(input.getMatDesc());
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

}