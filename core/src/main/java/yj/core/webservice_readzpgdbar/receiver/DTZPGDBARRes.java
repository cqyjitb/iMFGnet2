package yj.core.webservice_readzpgdbar.receiver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DT_ZPGDBAR_Res complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_ZPGDBAR_Res">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RETURN">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_ZPGDBAR_Res", propOrder = { "_return" })
public class DTZPGDBARRes {

    @XmlElement(name = "RETURN", required = true)
    protected DTZPGDBARRes.RETURN _return;

    /**
     * Gets the value of the return property.
     *
     * @return possible object is {@link DTZPGDBARRes.RETURN }
     *
     */
    public DTZPGDBARRes.RETURN getRETURN() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value
     *            allowed object is {@link DTZPGDBARRes.RETURN }
     *
     */
    public void setRETURN(DTZPGDBARRes.RETURN value) {
        this._return = value;
    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained
     * within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "type", "message" })
    public static class RETURN {

        @XmlElement(name = "TYPE", required = true)
        protected String type;
        @XmlElement(name = "MESSAGE", required = true)
        protected String message;

        /**
         * Gets the value of the type property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getTYPE() {
            return type;
        }

        /**
         * Sets the value of the type property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setTYPE(String value) {
            this.type = value;
        }

        /**
         * Gets the value of the message property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMESSAGE() {
            return message;
        }

        /**
         * Sets the value of the message property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMESSAGE(String value) {
            this.message = value;
        }

    }

}
