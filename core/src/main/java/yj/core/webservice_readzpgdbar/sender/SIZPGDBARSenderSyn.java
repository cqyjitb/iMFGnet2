package yj.core.webservice_readzpgdbar.sender;

import yj.core.webservice_readzpgdbar.receiver.DTZPGDBARRes;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 *
 */
@WebService(name = "SI_ZPGDBAR_Sender_Syn", targetNamespace = "http://www.cq-yj.com/HAP/READZPGDBAR/Sender")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SIZPGDBARSenderSyn {

    /**
     *
     * @param mtZPGDBARReq
     * @return returns com.cq_yj.hap.readzpgdbar.sender.DTZPGDBARRes
     */
    @WebMethod(operationName = "SI_ZPGDBAR_Sender_Syn", action = "http://sap.com/xi/WebService/soap1.1")
    @WebResult(name = "MT_ZPGDBAR_Res", targetNamespace = "http://www.cq-yj.com/HAP/READZPGDBAR/Sender", partName = "MT_ZPGDBAR_Res")
    public DTZPGDBARRes siZPGDBARSenderSyn(
            @WebParam(name = "MT_ZPGDBAR_Req", targetNamespace = "http://www.cq-yj.com/HAP/READZPGDBAR/Sender", partName = "MT_ZPGDBAR_Req") DTZPGDBARReq mtZPGDBARReq);

}