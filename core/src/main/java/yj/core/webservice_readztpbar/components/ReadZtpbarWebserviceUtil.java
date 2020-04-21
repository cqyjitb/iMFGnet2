package yj.core.webservice_readztpbar.components;

import org.springframework.stereotype.Component;
import yj.core.util.WebServerHelp;
import yj.core.webservice_readztpbar.dto.DTReadZtpbarReturnResult;
import yj.core.webservice_readztpbar.dto.ReadZtpbarParam;
import yj.core.webservice_readztpbar.receiver.DTREADZTPBAR2Res;
import yj.core.webservice_readztpbar.sender.DTREADZTPBAR2Req;
import yj.core.webservice_readztpbar.sender.SIREADZTPBAR2SenderSyn;
import yj.core.webservice_readztpbar.sender.SIREADZTPBAR2SenderSynService;
import yj.core.ztbc0002.dto.Ztbc0002;

import javax.xml.namespace.QName;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Component
public class ReadZtpbarWebserviceUtil {
    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/READZTPBAR2/Sender","SI_READZTPBAR2_Sender_SynService");
    public ReadZtpbarWebserviceUtil(){

    }

    public DTReadZtpbarReturnResult receiveConfirmation(ReadZtpbarParam param){
        DTReadZtpbarReturnResult rs = new DTReadZtpbarReturnResult();
        URL wsdlURL = SIREADZTPBAR2SenderSynService.WSDL_LOCATION;
        SIREADZTPBAR2SenderSynService ss = new SIREADZTPBAR2SenderSynService(wsdlURL, SERVICE_NAME);
        SIREADZTPBAR2SenderSyn port = ss.getHTTPPort();
        Map<String, Object> reqCtxt = ((javax.xml.ws.BindingProvider) port).getRequestContext();
        WebServerHelp webServerHelp = new WebServerHelp();
        String username = webServerHelp.getUsername();
        String password = webServerHelp.getPassword();
        reqCtxt.put(javax.xml.ws.BindingProvider.USERNAME_PROPERTY, username);
        reqCtxt.put(javax.xml.ws.BindingProvider.PASSWORD_PROPERTY, password);
        List<Ztbc0002> list = new ArrayList<>();
        DTREADZTPBAR2Req dtreadztpbar2Req = new DTREADZTPBAR2Req();
        DTREADZTPBAR2Req.IDATA idata = new DTREADZTPBAR2Req.IDATA();
        idata.setZBZ1("");
        idata.setZBZ2("");
        idata.setOPTION("");
        idata.setHIGH("");
        idata.setFILED("ZTPBAR");
        idata.setSIGN("");
        idata.setLOW(param.getZTPBAR());
        dtreadztpbar2Req.getIDATA().add(idata);
        DateFormat format1 = new SimpleDateFormat("yyyyMMdd");

        DTREADZTPBAR2Res res = port.siREADZTPBAR2SenderSyn(dtreadztpbar2Req);
        if (res.getTYPE().equals("E")){
            rs.setTYPE(res.getTYPE());
            rs.setMESSAGE(res.getMESSAGE());
        }else{
            rs.setTYPE(res.getTYPE());
            rs.setMESSAGE(res.getMESSAGE());
            for (int i=0;i<res.getTRETURN().size();i++){
                Ztbc0002 ztbc0002 = new Ztbc0002();
                ztbc0002.setWerks(res.getTRETURN().get(i).getWERKS());
                try{ztbc0002.setZdate(format1.parse(res.getTRETURN().get(i).getZDATE()));}catch (Exception e){
                    System.out.print(e.getStackTrace());
                }
                ztbc0002.setZtpnum(res.getTRETURN().get(i).getZTPNUM());
                ztbc0002.setMatnr(res.getTRETURN().get(i).getMATNR());
                ztbc0002.setCharg(res.getTRETURN().get(i).getCHARG());
                ztbc0002.setZxhnum(res.getTRETURN().get(i).getZXHNUM());
                ztbc0002.setZxhbar(res.getTRETURN().get(i).getZXHBAR());
                ztbc0002.setZtpzt(res.getTRETURN().get(i).getZTPZT());
                ztbc0002.setMenge(Double.parseDouble(res.getTRETURN().get(i).getMENGE()));
                ztbc0002.setMeins(res.getTRETURN().get(i).getMEINS());
                ztbc0002.setZtpbar(res.getTRETURN().get(i).getZTPBAR());
                ztbc0002.setZmnum(res.getTRETURN().get(i).getZMNUM());
                ztbc0002.setZscbc(res.getTRETURN().get(i).getZSCBC());
                list.add(ztbc0002);
            }
        }
        if (list.size() > 0){
            rs.setZtbc0002List(list);
        }
        return rs;
    }
}
