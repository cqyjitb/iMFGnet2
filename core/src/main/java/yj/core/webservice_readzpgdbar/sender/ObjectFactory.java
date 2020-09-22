package yj.core.webservice_readzpgdbar.sender;

import yj.core.webservice_readzpgdbar.receiver.DTZPGDBARRes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cq_yj.hap.readzpgdbar.sender package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MTZPGDBARReq_QNAME = new QName(
            "http://www.cq-yj.com/HAP/READZPGDBAR/Sender", "MT_ZPGDBAR_Req");
    private final static QName _MTZPGDBARRes_QNAME = new QName(
            "http://www.cq-yj.com/HAP/READZPGDBAR/Sender", "MT_ZPGDBAR_Res");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.cq_yj.hap.readzpgdbar.sender
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTZPGDBARReq.ITEM }
     *
     */
    public DTZPGDBARReq.ITEM createDTZPGDBARReqITEM() {
        return new DTZPGDBARReq.ITEM();
    }

    /**
     * Create an instance of {@link DTZPGDBARReq }
     *
     */
    public DTZPGDBARReq createDTZPGDBARReq() {
        return new DTZPGDBARReq();
    }

    /**
     * Create an instance of {@link DTZPGDBARRes.RETURN }
     *
     */
    public DTZPGDBARRes.RETURN createDTZPGDBARResRETURN() {
        return new DTZPGDBARRes.RETURN();
    }

    /**
     * Create an instance of {@link DTZPGDBARRes }
     *
     */
    public DTZPGDBARRes createDTZPGDBARRes() {
        return new DTZPGDBARRes();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTZPGDBARReq }
     * {@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/READZPGDBAR/Sender", name = "MT_ZPGDBAR_Req")
    public JAXBElement<DTZPGDBARReq> createMTZPGDBARReq(DTZPGDBARReq value) {
        return new JAXBElement<DTZPGDBARReq>(_MTZPGDBARReq_QNAME,
                DTZPGDBARReq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTZPGDBARRes }
     * {@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/READZPGDBAR/Sender", name = "MT_ZPGDBAR_Res")
    public JAXBElement<DTZPGDBARRes> createMTZPGDBARRes(DTZPGDBARRes value) {
        return new JAXBElement<DTZPGDBARRes>(_MTZPGDBARRes_QNAME,
                DTZPGDBARRes.class, null, value);
    }

}
