package yj.core.webservice.sender;

import yj.core.webservice.receiver.DTPP001RecRes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cq_yj.hap.pp001.receiver package.
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

	private final static QName _MTPP001SendReq_QNAME = new QName(
			"http://www.cq-yj.com/HAP/PP001/Receiver", "MT_PP001_Send_Req");
	private final static QName _MTPP001SendRes_QNAME = new QName(
			"http://www.cq-yj.com/HAP/PP001/Receiver", "MT_PP001_Send_Res");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.cq_yj.hap.pp001.receiver
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link DTPP001RecRes.DETAIL }
	 * 
	 */
	public DTPP001RecRes.DETAIL createDTPP001RecResDETAIL() {
		return new DTPP001RecRes.DETAIL();
	}

	/**
	 * Create an instance of {@link DTPP001RecRes.RETURN }
	 * 
	 */
	public DTPP001RecRes.RETURN createDTPP001RecResRETURN() {
		return new DTPP001RecRes.RETURN();
	}

	/**
	 * Create an instance of {@link DTPP001RecRes }
	 * 
	 */
	public DTPP001RecRes createDTPP001RecRes() {
		return new DTPP001RecRes();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link DTPP001SendReq }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/PP001/Receiver", name = "MT_PP001_Send_Req")
	public JAXBElement<DTPP001SendReq> createMTPP001SendReq(DTPP001SendReq value) {
		return new JAXBElement<DTPP001SendReq>(_MTPP001SendReq_QNAME,
				DTPP001SendReq.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link DTPP001RecRes }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/PP001/Receiver", name = "MT_PP001_Send_Res")
	public JAXBElement<DTPP001RecRes> createMTPP001SendRes(DTPP001RecRes value) {
		return new JAXBElement<DTPP001RecRes>(_MTPP001SendRes_QNAME,
				DTPP001RecRes.class, null, value);
	}

}
