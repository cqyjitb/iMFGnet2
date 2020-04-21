package yj.core.webservice_readztpbar.sender;

import yj.core.webservice_readztpbar.receiver.DTREADZTPBAR2Res;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cq_yj.hap.readztpbar2.sender package.
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

    private final static QName _MTREADZTPBAR2Req_QNAME = new QName(
            "http://www.cq-yj.com/HAP/READZTPBAR2/Sender", "MT_READZTPBAR2_Req");
    private final static QName _MTREADZTPBAR2Res_QNAME = new QName(
            "http://www.cq-yj.com/HAP/READZTPBAR2/Sender", "MT_READZTPBAR2_Res");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.cq_yj.hap.readztpbar2.sender
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTREADZTPBAR2Res.TRETURN }
     *
     */
    public DTREADZTPBAR2Res.TRETURN createDTREADZTPBAR2ResTRETURN() {
        return new DTREADZTPBAR2Res.TRETURN();
    }

    /**
     * Create an instance of {@link DTREADZTPBAR2Req }
     *
     */
    public DTREADZTPBAR2Req createDTREADZTPBAR2Req() {
        return new DTREADZTPBAR2Req();
    }

    /**
     * Create an instance of {@link DTREADZTPBAR2Req.IDATA }
     *
     */
    public DTREADZTPBAR2Req.IDATA createDTREADZTPBAR2ReqIDATA() {
        return new DTREADZTPBAR2Req.IDATA();
    }

    /**
     * Create an instance of {@link DTREADZTPBAR2Res }
     *
     */
    public DTREADZTPBAR2Res createDTREADZTPBAR2Res() {
        return new DTREADZTPBAR2Res();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link DTREADZTPBAR2Req }{@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/READZTPBAR2/Sender", name = "MT_READZTPBAR2_Req")
    public JAXBElement<DTREADZTPBAR2Req> createMTREADZTPBAR2Req(
            DTREADZTPBAR2Req value) {
        return new JAXBElement<DTREADZTPBAR2Req>(_MTREADZTPBAR2Req_QNAME,
                DTREADZTPBAR2Req.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link DTREADZTPBAR2Res }{@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/READZTPBAR2/Sender", name = "MT_READZTPBAR2_Res")
    public JAXBElement<DTREADZTPBAR2Res> createMTREADZTPBAR2Res(
            DTREADZTPBAR2Res value) {
        return new JAXBElement<DTREADZTPBAR2Res>(_MTREADZTPBAR2Res_QNAME,
                DTREADZTPBAR2Res.class, null, value);
    }

}

