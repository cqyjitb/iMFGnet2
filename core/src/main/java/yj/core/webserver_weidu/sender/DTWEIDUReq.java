package yj.core.webserver_weidu.sender;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DT_WEIDU_Req complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_WEIDU_Req">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ITEM">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBANB" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZMODEL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZXHBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZXQH" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ6" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ7" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ8" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ9" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ10" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_WEIDU_Req", propOrder = { "item" })
public class DTWEIDUReq {

    @XmlElement(name = "ITEM", required = true)
    protected DTWEIDUReq.ITEM item;

    /**
     * Gets the value of the item property.
     *
     * @return possible object is {@link DTWEIDUReq.ITEM }
     *
     */
    public DTWEIDUReq.ITEM getITEM() {
        return item;
    }

    /**
     * Sets the value of the item property.
     *
     * @param value
     *            allowed object is {@link DTWEIDUReq.ITEM }
     *
     */
    public void setITEM(DTWEIDUReq.ITEM value) {
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
     *         &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZBANB" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZMODEL" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZXHBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZXQH" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZBZ1" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZBZ2" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZBZ3" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZBZ4" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZBZ5" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZBZ6" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZBZ7" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZBZ8" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZBZ9" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZBZ10" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "werks", "matnr", "zbanb", "zmodel",
            "zxhbar", "zxqh", "zbz1", "zbz2", "zbz3", "zbz4", "zbz5", "zbz6",
            "zbz7", "zbz8", "zbz9", "zbz10" })
    public static class ITEM {

        @XmlElement(name = "WERKS", required = true)
        protected String werks;
        @XmlElement(name = "MATNR", required = true)
        protected String matnr;
        @XmlElement(name = "ZBANB", required = true)
        protected String zbanb;
        @XmlElement(name = "ZMODEL", required = true)
        protected String zmodel;
        @XmlElement(name = "ZXHBAR", required = true)
        protected String zxhbar;
        @XmlElement(name = "ZXQH", required = true)
        protected String zxqh;
        @XmlElement(name = "ZBZ1", required = true)
        protected String zbz1;
        @XmlElement(name = "ZBZ2", required = true)
        protected String zbz2;
        @XmlElement(name = "ZBZ3", required = true)
        protected String zbz3;
        @XmlElement(name = "ZBZ4", required = true)
        protected String zbz4;
        @XmlElement(name = "ZBZ5", required = true)
        protected String zbz5;
        @XmlElement(name = "ZBZ6", required = true)
        protected String zbz6;
        @XmlElement(name = "ZBZ7", required = true)
        protected String zbz7;
        @XmlElement(name = "ZBZ8", required = true)
        protected String zbz8;
        @XmlElement(name = "ZBZ9", required = true)
        protected String zbz9;
        @XmlElement(name = "ZBZ10", required = true)
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
         * Gets the value of the zbanb property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZBANB() {
            return zbanb;
        }

        /**
         * Sets the value of the zbanb property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZBANB(String value) {
            this.zbanb = value;
        }

        /**
         * Gets the value of the zmodel property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZMODEL() {
            return zmodel;
        }

        /**
         * Sets the value of the zmodel property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZMODEL(String value) {
            this.zmodel = value;
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
         * Gets the value of the zxqh property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZXQH() {
            return zxqh;
        }

        /**
         * Sets the value of the zxqh property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZXQH(String value) {
            this.zxqh = value;
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
