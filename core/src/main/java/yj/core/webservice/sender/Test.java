package yj.core.webservice.sender;

import yj.core.webservice.receiver.DTPP001SendRes;

import java.util.Map;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SIPP001SenderSyncService ss = new SIPP001SenderSyncService();
        SIPP001SenderSync port = ss.getHTTPPort();
        Map<String, Object> reqCtxt = ((javax.xml.ws.BindingProvider) port).getRequestContext();
        reqCtxt.put(javax.xml.ws.BindingProvider.USERNAME_PROPERTY, "hand");
        reqCtxt.put(javax.xml.ws.BindingProvider.PASSWORD_PROPERTY, "h@nd1234");
        
        DTPP001SendReq.ITEM item = new DTPP001SendReq.ITEM();
        item.setPWERK("");
        item.setAUFNR("");
        item.setVORNR("");
        item.setBUDAT("");
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
        
        _siPP001SenderSync_mtPP001SendReq.getITEM().add(item);
        
        DTPP001SendRes returnResult = port.siPP001SenderSync(_siPP001SenderSync_mtPP001SendReq);
        System.out.println(returnResult.getDETAIL().get(0).getMESSAGE());
	}

}
