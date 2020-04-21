package yj.core.webservice_readztpbar.receiver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for DT_READZTPBAR2_Res complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_READZTPBAR2_Res">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="T_RETURN" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZTPNUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="CHARG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZXHNUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZXHBAR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZTPZT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZTPZT2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="LGORT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="MENGE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="MEINS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZTPWZ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZTPBAR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZMNUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZSCBC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZTXT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZTXT2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZBZ1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZBZ2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZBZ3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZBZ4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZBZ5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZBZ6" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZBZ7" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZBZ8" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZBZ9" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZBZ10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "DT_READZTPBAR2_Res", propOrder = { "type", "message",
        "treturn" })
public class DTREADZTPBAR2Res {

    @XmlElement(name = "TYPE", required = true)
    protected String type;
    @XmlElement(name = "MESSAGE", required = true)
    protected String message;
    @XmlElement(name = "T_RETURN")
    protected List<DTREADZTPBAR2Res.TRETURN> treturn;

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

    /**
     * Gets the value of the treturn property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the treturn property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getTRETURN().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTREADZTPBAR2Res.TRETURN }
     *
     *
     */
    public List<DTREADZTPBAR2Res.TRETURN> getTRETURN() {
        if (treturn == null) {
            treturn = new ArrayList<DTREADZTPBAR2Res.TRETURN>();
        }
        return this.treturn;
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
     *         &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZTPNUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="CHARG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZXHNUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZXHBAR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZTPZT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZTPZT2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="LGORT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="MENGE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="MEINS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZTPWZ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZTPBAR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZMNUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZSCBC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZTXT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZTXT2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZBZ1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZBZ2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZBZ3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZBZ4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZBZ5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZBZ6" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZBZ7" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZBZ8" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZBZ9" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZBZ10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "werks", "zdate", "ztpnum", "matnr",
            "charg", "zxhnum", "zxhbar", "ztpzt", "ztpzt2", "lgort", "menge",
            "meins", "ztpwz", "ztpbar", "zmnum", "zscbc", "ztxt", "ztxt2",
            "zbz1", "zbz2", "zbz3", "zbz4", "zbz5", "zbz6", "zbz7", "zbz8",
            "zbz9", "zbz10" })
    public static class TRETURN {

        @XmlElement(name = "WERKS")
        protected String werks;
        @XmlElement(name = "ZDATE")
        protected String zdate;
        @XmlElement(name = "ZTPNUM")
        protected String ztpnum;
        @XmlElement(name = "MATNR")
        protected String matnr;
        @XmlElement(name = "CHARG")
        protected String charg;
        @XmlElement(name = "ZXHNUM")
        protected String zxhnum;
        @XmlElement(name = "ZXHBAR")
        protected String zxhbar;
        @XmlElement(name = "ZTPZT")
        protected String ztpzt;
        @XmlElement(name = "ZTPZT2")
        protected String ztpzt2;
        @XmlElement(name = "LGORT")
        protected String lgort;
        @XmlElement(name = "MENGE")
        protected String menge;
        @XmlElement(name = "MEINS")
        protected String meins;
        @XmlElement(name = "ZTPWZ")
        protected String ztpwz;
        @XmlElement(name = "ZTPBAR")
        protected String ztpbar;
        @XmlElement(name = "ZMNUM")
        protected String zmnum;
        @XmlElement(name = "ZSCBC")
        protected String zscbc;
        @XmlElement(name = "ZTXT")
        protected String ztxt;
        @XmlElement(name = "ZTXT2")
        protected String ztxt2;
        @XmlElement(name = "ZBZ1")
        protected String zbz1;
        @XmlElement(name = "ZBZ2")
        protected String zbz2;
        @XmlElement(name = "ZBZ3")
        protected String zbz3;
        @XmlElement(name = "ZBZ4")
        protected String zbz4;
        @XmlElement(name = "ZBZ5")
        protected String zbz5;
        @XmlElement(name = "ZBZ6")
        protected String zbz6;
        @XmlElement(name = "ZBZ7")
        protected String zbz7;
        @XmlElement(name = "ZBZ8")
        protected String zbz8;
        @XmlElement(name = "ZBZ9")
        protected String zbz9;
        @XmlElement(name = "ZBZ10")
        protected String zbz10;

        /**
         * Gets the value of the werks property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getWERKS() {
            return werks;
        }

        /**
         * Sets the value of the werks property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setWERKS(String value) {
            this.werks = value;
        }

        /**
         * Gets the value of the zdate property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZDATE() {
            return zdate;
        }

        /**
         * Sets the value of the zdate property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZDATE(String value) {
            this.zdate = value;
        }

        /**
         * Gets the value of the ztpnum property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZTPNUM() {
            return ztpnum;
        }

        /**
         * Sets the value of the ztpnum property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZTPNUM(String value) {
            this.ztpnum = value;
        }

        /**
         * Gets the value of the matnr property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMATNR() {
            return matnr;
        }

        /**
         * Sets the value of the matnr property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMATNR(String value) {
            this.matnr = value;
        }

        /**
         * Gets the value of the charg property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getCHARG() {
            return charg;
        }

        /**
         * Sets the value of the charg property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setCHARG(String value) {
            this.charg = value;
        }

        /**
         * Gets the value of the zxhnum property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZXHNUM() {
            return zxhnum;
        }

        /**
         * Sets the value of the zxhnum property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZXHNUM(String value) {
            this.zxhnum = value;
        }

        /**
         * Gets the value of the zxhbar property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZXHBAR() {
            return zxhbar;
        }

        /**
         * Sets the value of the zxhbar property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZXHBAR(String value) {
            this.zxhbar = value;
        }

        /**
         * Gets the value of the ztpzt property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZTPZT() {
            return ztpzt;
        }

        /**
         * Sets the value of the ztpzt property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZTPZT(String value) {
            this.ztpzt = value;
        }

        /**
         * Gets the value of the ztpzt2 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZTPZT2() {
            return ztpzt2;
        }

        /**
         * Sets the value of the ztpzt2 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZTPZT2(String value) {
            this.ztpzt2 = value;
        }

        /**
         * Gets the value of the lgort property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getLGORT() {
            return lgort;
        }

        /**
         * Sets the value of the lgort property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setLGORT(String value) {
            this.lgort = value;
        }

        /**
         * Gets the value of the menge property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMENGE() {
            return menge;
        }

        /**
         * Sets the value of the menge property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMENGE(String value) {
            this.menge = value;
        }

        /**
         * Gets the value of the meins property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMEINS() {
            return meins;
        }

        /**
         * Sets the value of the meins property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMEINS(String value) {
            this.meins = value;
        }

        /**
         * Gets the value of the ztpwz property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZTPWZ() {
            return ztpwz;
        }

        /**
         * Sets the value of the ztpwz property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZTPWZ(String value) {
            this.ztpwz = value;
        }

        /**
         * Gets the value of the ztpbar property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZTPBAR() {
            return ztpbar;
        }

        /**
         * Sets the value of the ztpbar property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZTPBAR(String value) {
            this.ztpbar = value;
        }

        /**
         * Gets the value of the zmnum property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZMNUM() {
            return zmnum;
        }

        /**
         * Sets the value of the zmnum property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZMNUM(String value) {
            this.zmnum = value;
        }

        /**
         * Gets the value of the zscbc property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZSCBC() {
            return zscbc;
        }

        /**
         * Sets the value of the zscbc property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZSCBC(String value) {
            this.zscbc = value;
        }

        /**
         * Gets the value of the ztxt property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZTXT() {
            return ztxt;
        }

        /**
         * Sets the value of the ztxt property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZTXT(String value) {
            this.ztxt = value;
        }

        /**
         * Gets the value of the ztxt2 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZTXT2() {
            return ztxt2;
        }

        /**
         * Sets the value of the ztxt2 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZTXT2(String value) {
            this.ztxt2 = value;
        }

        /**
         * Gets the value of the zbz1 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZBZ1() {
            return zbz1;
        }

        /**
         * Sets the value of the zbz1 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZBZ1(String value) {
            this.zbz1 = value;
        }

        /**
         * Gets the value of the zbz2 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZBZ2() {
            return zbz2;
        }

        /**
         * Sets the value of the zbz2 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZBZ2(String value) {
            this.zbz2 = value;
        }

        /**
         * Gets the value of the zbz3 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZBZ3() {
            return zbz3;
        }

        /**
         * Sets the value of the zbz3 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZBZ3(String value) {
            this.zbz3 = value;
        }

        /**
         * Gets the value of the zbz4 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZBZ4() {
            return zbz4;
        }

        /**
         * Sets the value of the zbz4 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZBZ4(String value) {
            this.zbz4 = value;
        }

        /**
         * Gets the value of the zbz5 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZBZ5() {
            return zbz5;
        }

        /**
         * Sets the value of the zbz5 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZBZ5(String value) {
            this.zbz5 = value;
        }

        /**
         * Gets the value of the zbz6 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZBZ6() {
            return zbz6;
        }

        /**
         * Sets the value of the zbz6 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZBZ6(String value) {
            this.zbz6 = value;
        }

        /**
         * Gets the value of the zbz7 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZBZ7() {
            return zbz7;
        }

        /**
         * Sets the value of the zbz7 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZBZ7(String value) {
            this.zbz7 = value;
        }

        /**
         * Gets the value of the zbz8 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZBZ8() {
            return zbz8;
        }

        /**
         * Sets the value of the zbz8 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZBZ8(String value) {
            this.zbz8 = value;
        }

        /**
         * Gets the value of the zbz9 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZBZ9() {
            return zbz9;
        }

        /**
         * Sets the value of the zbz9 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZBZ9(String value) {
            this.zbz9 = value;
        }

        /**
         * Gets the value of the zbz10 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZBZ10() {
            return zbz10;
        }

        /**
         * Sets the value of the zbz10 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZBZ10(String value) {
            this.zbz10 = value;
        }

    }

}
