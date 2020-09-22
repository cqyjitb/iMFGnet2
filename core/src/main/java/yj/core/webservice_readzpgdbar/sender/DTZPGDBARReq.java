package yj.core.webservice_readzpgdbar.sender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DT_ZPGDBAR_Req complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_ZPGDBAR_Req">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ITEM">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ZPGDBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="VORNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_ZPGDBAR_Req", propOrder = { "item" })
public class DTZPGDBARReq {

    @XmlElement(name = "ITEM", required = true)
    protected DTZPGDBARReq.ITEM item;

    /**
     * Gets the value of the item property.
     *
     * @return possible object is {@link DTZPGDBARReq.ITEM }
     *
     */
    public DTZPGDBARReq.ITEM getITEM() {
        return item;
    }

    /**
     * Sets the value of the item property.
     *
     * @param value
     *            allowed object is {@link DTZPGDBARReq.ITEM }
     *
     */
    public void setITEM(DTZPGDBARReq.ITEM value) {
        this.item = value;
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
     *         &lt;element name="ZPGDBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="VORNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "zpgdbar", "vornr" })
    public static class ITEM {

        @XmlElement(name = "ZPGDBAR", required = true)
        protected String zpgdbar;
        @XmlElement(name = "VORNR", required = true)
        protected String vornr;

        /**
         * Gets the value of the zpgdbar property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZPGDBAR() {
            return zpgdbar;
        }

        /**
         * Sets the value of the zpgdbar property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZPGDBAR(String value) {
            this.zpgdbar = value;
        }

        /**
         * Gets the value of the vornr property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getVORNR() {
            return vornr;
        }

        /**
         * Sets the value of the vornr property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setVORNR(String value) {
            this.vornr = value;
        }

    }

}
