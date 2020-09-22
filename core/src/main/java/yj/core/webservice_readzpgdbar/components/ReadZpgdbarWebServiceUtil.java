package yj.core.webservice_readzpgdbar.components;

import org.springframework.stereotype.Component;
import yj.core.util.WebServerHelp;
import yj.core.webservice_readzpgdbar.dto.ReadZpgdbarParam;
import yj.core.webservice_readzpgdbar.dto.ReadZpgdbarResult;
import yj.core.webservice_readzpgdbar.receiver.DTZPGDBARRes;
import yj.core.webservice_readzpgdbar.sender.DTZPGDBARReq;
import yj.core.webservice_readzpgdbar.sender.SIZPGDBARSenderSyn;
import yj.core.webservice_readzpgdbar.sender.SIZPGDBARSenderSynService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Map;

@Component
public class ReadZpgdbarWebServiceUtil {
    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/READZPGDBAR/Sender","SI_ZPGDBAR_Sender_SynService");
    public  ReadZpgdbarWebServiceUtil(){

    }

    public ReadZpgdbarResult receiveConfirmation(ReadZpgdbarParam param){
        ReadZpgdbarResult rs = new ReadZpgdbarResult();
        URL wsdlURL = SIZPGDBARSenderSynService.WSDL_LOCATION;
        SIZPGDBARSenderSynService ss = new SIZPGDBARSenderSynService(wsdlURL, SERVICE_NAME);
        SIZPGDBARSenderSyn port = ss.getHTTPPort();
        Map<String, Object> reqCtxt = ((javax.xml.ws.BindingProvider) port).getRequestContext();
        WebServerHelp webServerHelp = new WebServerHelp();
        String username = webServerHelp.getUsername();
        String password = webServerHelp.getPassword();

        reqCtxt.put(javax.xml.ws.BindingProvider.USERNAME_PROPERTY, username);
        reqCtxt.put(javax.xml.ws.BindingProvider.PASSWORD_PROPERTY, password);

        DTZPGDBARReq req = new DTZPGDBARReq();
        DTZPGDBARReq.ITEM item = new DTZPGDBARReq.ITEM();
        item.setZPGDBAR(param.getZpgdbar());
        item.setVORNR(param.getVornr());
        req.setITEM(item);
        DTZPGDBARRes res = port.siZPGDBARSenderSyn(req);
        rs.setMESSAGE(res.getRETURN().getMESSAGE());
        rs.setTYPE(res.getRETURN().getTYPE());
        return rs;
    }
}
