package yj.core.webservice_readzpgdbar.sender;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import java.net.URL;
import java.util.logging.Logger;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * <p>
 * An example of how this class may be used:
 *
 * <pre>
 * SI_ZPGDBAR_Sender_SynService service = new SI_ZPGDBAR_Sender_SynService();
 * SIZPGDBARSenderSyn portType = service.getHTTPPort();
 * portType.siZPGDBARSenderSyn(...);
 * </pre>
 *
 * </p>
 *
 */
@WebServiceClient(name = "SI_ZPGDBAR_Sender_SynService", targetNamespace = "http://www.cq-yj.com/HAP/READZPGDBAR/Sender", wsdlLocation = "resources/readzpgdbar.xml")
public class SIZPGDBARSenderSynService extends Service {

    public final static URL WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(SIZPGDBARSenderSynService.class.getName());

    static {
        URL url = null;
        url = SIZPGDBARSenderSynService.class.getClassLoader().getResource("readzpgdbar.xml");
        System.out.println(url);
        WSDL_LOCATION = url;
    }

    public SIZPGDBARSenderSynService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SIZPGDBARSenderSynService() {
        super(WSDL_LOCATION, new QName(
                "http://www.cq-yj.com/HAP/READZPGDBAR/Sender",
                "SI_ZPGDBAR_Sender_SynService"));
    }

    /**
     *
     * @return returns SIZPGDBARSenderSyn
     */
    @WebEndpoint(name = "HTTP_Port")
    public SIZPGDBARSenderSyn getHTTPPort() {
        return super.getPort(new QName(
                        "http://www.cq-yj.com/HAP/READZPGDBAR/Sender", "HTTP_Port"),
                SIZPGDBARSenderSyn.class);
    }

    /**
     *
     * @return returns SIZPGDBARSenderSyn
     */
    @WebEndpoint(name = "HTTPS_Port")
    public SIZPGDBARSenderSyn getHTTPSPort() {
        return super.getPort(new QName(
                        "http://www.cq-yj.com/HAP/READZPGDBAR/Sender", "HTTPS_Port"),
                SIZPGDBARSenderSyn.class);
    }

}
