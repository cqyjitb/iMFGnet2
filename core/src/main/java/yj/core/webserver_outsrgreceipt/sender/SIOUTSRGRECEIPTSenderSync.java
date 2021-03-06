package yj.core.webserver_outsrgreceipt.sender;

import yj.core.webserver_outsrgreceipt.receiver.DTOUTSRGRECEIPTRes;

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
@WebService(name = "SI_OUTSRGRECEIPT_Sender_Sync", targetNamespace = "http://www.cq-yj.com/HAP/OUTSRGRECEIPT/Sender")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SIOUTSRGRECEIPTSenderSync {

    /**
     *
     * @param mtOUTSRGRECEIPTReq
     * @return returns com.cq_yj.hap.outsrgreceipt.sender.DTOUTSRGRECEIPTRes
     */
    @WebMethod(operationName = "SI_OUTSRGRECEIPT_Sender_Sync", action = "http://sap.com/xi/WebService/soap1.1")
    @WebResult(name = "MT_OUTSRGRECEIPT_Res", targetNamespace = "http://www.cq-yj.com/HAP/OUTSRGRECEIPT/Sender", partName = "MT_OUTSRGRECEIPT_Res")
    public DTOUTSRGRECEIPTRes siOUTSRGRECEIPTSenderSync(
            @WebParam(name = "MT_OUTSRGRECEIPT_Req", targetNamespace = "http://www.cq-yj.com/HAP/OUTSRGRECEIPT/Sender", partName = "MT_OUTSRGRECEIPT_Req") DTOUTSRGRECEIPTReq mtOUTSRGRECEIPTReq);

}
