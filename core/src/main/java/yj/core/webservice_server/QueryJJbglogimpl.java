package yj.core.webservice_server;

import org.springframework.web.context.ContextLoaderListener;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.mapper.InputLogMapper;
import yj.core.webservice_server.dto.ReturnQueryjjbg;

import javax.jws.WebService;

@WebService
public class QueryJJbglogimpl implements IQueryJJbglog {
    @Override
    public ReturnQueryjjbg querybglog(String uuid) {
        ReturnQueryjjbg rs = new ReturnQueryjjbg();
        InputLogMapper inputLogMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(InputLogMapper.class);
        InputLog inputLog = new InputLog();
        inputLog = inputLogMapper.queryInputlogByJjuuid(uuid);
        if (inputLog == null){
            rs.setMSGTY("E");
            rs.setMESSAGE("无报工日志记录");
        }else{
            if (inputLog.getMsgty().equals("S")){
                rs.setMSGTY(inputLog.getMsgty());
                rs.setMESSAGE(inputLog.getMsgtx());
                rs.setAUFNR(inputLog.getOrderno());
                rs.setMATNR(inputLog.getMaterial());
                rs.setMAKTX(inputLog.getMatDesc());
                rs.setRSNUM(inputLog.getConfirmationNo());
                rs.setRSPOS(inputLog.getConfirmationPos());
                rs.setFEVOR(inputLog.getFevor());
                rs.setTXT(inputLog.getFevorTxt());
                rs.setYeild(inputLog.getYeild());
                rs.setWorkscrap(inputLog.getWorkScrap());
                rs.setRowscrap(inputLog.getRowScrap());


//                returnResult.setAUFNR(aufnr);
//                returnResult.setMATNR(matnr);
//                returnResult.setMSGTY(msgTy);
//                returnResult.setMSGNO(msgNo);
//                returnResult.setMSGID(msgId);
//                returnResult.setMSGV1(msgV1);
//                returnResult.setMSGV2(msgV2);
//                returnResult.setMSGV3(msgV3);
//                returnResult.setMSGV4(msgV4);
//                returnResult.setMAKTX(maktx);
//                returnResult.setMESSAGE(message);
//                returnResult.setRSNUM(rsnum);
//                returnResult.setRSPOS(rspos);
//                returnResult.setFEVOR(fevor);
//                returnResult.setTXT(txt);
//                returnResult.setLTXA1(ltxa1);
//                returnResult.setCHARG(charg);
//                returnResult.setMJAHR(mjahr);
//                returnResult.setMBLNR(mblnr);
            }else{
                rs.setMSGTY("E");
                rs.setMESSAGE(inputLog.getMsgtx());
                rs.setYeild(inputLog.getYeild());
                rs.setWorkscrap(inputLog.getWorkScrap());
                rs.setRowscrap(inputLog.getRowScrap());
            }
        }
        return rs;
    }
}
