package yj.core.webservice.sender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * 报工接收数据结构
 *
 * <p>
 * Java class for DT_PP001_Send_Req complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_PP001_Send_Req">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ITEM" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PWERK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="VORNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="BUDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="GMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="XMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZSCBC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZSCX" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZMNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DATUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZPGDBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZPGDBH" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RSNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RSPOS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="REVERSE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="USERNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR6" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR7" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR8" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR9" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR10" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR11" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR12" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR13" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR14" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR15" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZTPBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="UUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_PP001_Send_Req", propOrder = { "item" })
public class DTPP001SendReq {

	@XmlElement(name = "ITEM", required = true)
	protected List<DTPP001SendReq.ITEM> item;

	/**
	 * Gets the value of the item property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the item property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getITEM().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link DTPP001SendReq.ITEM }
	 *
	 *
	 */
	public List<DTPP001SendReq.ITEM> getITEM() {
		if (item == null) {
			item = new ArrayList<DTPP001SendReq.ITEM>();
		}
		return this.item;
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
	 *         &lt;element name="PWERK" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="VORNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="BUDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="GMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="XMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="RMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZSCBC" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZSCX" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZMNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="DATUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZPGDBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZPGDBH" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="RSNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="RSPOS" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="REVERSE" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="USERNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ATTR1" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ATTR2" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ATTR3" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ATTR4" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ATTR5" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ATTR6" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ATTR7" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ATTR8" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ATTR9" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ATTR10" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ATTR11" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ATTR12" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ATTR13" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ATTR14" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ATTR15" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZTPBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="UUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 *
	 *
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "pwerk", "aufnr", "vornr", "budat",
			"gmnga", "xmnga", "rmnga", "zscbc", "zscx", "zmnum", "datum",
			"zpgdbar", "zpgdbh", "rsnum", "rspos", "reverse", "username",
			"attr1", "attr2", "attr3", "attr4", "attr5", "attr6", "attr7",
			"attr8", "attr9", "attr10", "attr11", "attr12", "attr13", "attr14",
			"attr15", "ztpbar", "uuid" })
	public static class ITEM {

		@XmlElement(name = "PWERK", required = true)
		protected String pwerk;
		@XmlElement(name = "AUFNR", required = true)
		protected String aufnr;
		@XmlElement(name = "VORNR", required = true)
		protected String vornr;
		@XmlElement(name = "BUDAT", required = true)
		protected String budat;
		@XmlElement(name = "GMNGA", required = true)
		protected String gmnga;
		@XmlElement(name = "XMNGA", required = true)
		protected String xmnga;
		@XmlElement(name = "RMNGA", required = true)
		protected String rmnga;
		@XmlElement(name = "ZSCBC", required = true)
		protected String zscbc;
		@XmlElement(name = "ZSCX", required = true)
		protected String zscx;
		@XmlElement(name = "ZMNUM", required = true)
		protected String zmnum;
		@XmlElement(name = "DATUM", required = true)
		protected String datum;
		@XmlElement(name = "ZPGDBAR", required = true)
		protected String zpgdbar;
		@XmlElement(name = "ZPGDBH", required = true)
		protected String zpgdbh;
		@XmlElement(name = "RSNUM", required = true)
		protected String rsnum;
		@XmlElement(name = "RSPOS", required = true)
		protected String rspos;
		@XmlElement(name = "REVERSE", required = true)
		protected String reverse;
		@XmlElement(name = "USERNAME", required = true)
		protected String username;
		@XmlElement(name = "ATTR1", required = true)
		protected String attr1;
		@XmlElement(name = "ATTR2", required = true)
		protected String attr2;
		@XmlElement(name = "ATTR3", required = true)
		protected String attr3;
		@XmlElement(name = "ATTR4", required = true)
		protected String attr4;
		@XmlElement(name = "ATTR5", required = true)
		protected String attr5;
		@XmlElement(name = "ATTR6", required = true)
		protected String attr6;
		@XmlElement(name = "ATTR7", required = true)
		protected String attr7;
		@XmlElement(name = "ATTR8", required = true)
		protected String attr8;
		@XmlElement(name = "ATTR9", required = true)
		protected String attr9;
		@XmlElement(name = "ATTR10", required = true)
		protected String attr10;
		@XmlElement(name = "ATTR11", required = true)
		protected String attr11;
		@XmlElement(name = "ATTR12", required = true)
		protected String attr12;
		@XmlElement(name = "ATTR13", required = true)
		protected String attr13;
		@XmlElement(name = "ATTR14", required = true)
		protected String attr14;
		@XmlElement(name = "ATTR15", required = true)
		protected String attr15;
		@XmlElement(name = "ZTPBAR", required = true)
		protected String ztpbar;
		@XmlElement(name = "UUID", required = true)
		protected String uuid;

		/**
		 * Gets the value of the pwerk property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getPWERK() {
			return pwerk;
		}

		/**
		 * Sets the value of the pwerk property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setPWERK(String value) {
			this.pwerk = value;
		}

		/**
		 * Gets the value of the aufnr property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getAUFNR() {
			return aufnr;
		}

		/**
		 * Sets the value of the aufnr property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setAUFNR(String value) {
			this.aufnr = value;
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

		/**
		 * Gets the value of the budat property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getBUDAT() {
			return budat;
		}

		/**
		 * Sets the value of the budat property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setBUDAT(String value) {
			this.budat = value;
		}

		/**
		 * Gets the value of the gmnga property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getGMNGA() {
			return gmnga;
		}

		/**
		 * Sets the value of the gmnga property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setGMNGA(String value) {
			this.gmnga = value;
		}

		/**
		 * Gets the value of the xmnga property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getXMNGA() {
			return xmnga;
		}

		/**
		 * Sets the value of the xmnga property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setXMNGA(String value) {
			this.xmnga = value;
		}

		/**
		 * Gets the value of the rmnga property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getRMNGA() {
			return rmnga;
		}

		/**
		 * Sets the value of the rmnga property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setRMNGA(String value) {
			this.rmnga = value;
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
		 * Gets the value of the zscx property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getZSCX() {
			return zscx;
		}

		/**
		 * Sets the value of the zscx property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setZSCX(String value) {
			this.zscx = value;
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
		 * Gets the value of the datum property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getDATUM() {
			return datum;
		}

		/**
		 * Sets the value of the datum property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setDATUM(String value) {
			this.datum = value;
		}

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
		 * Gets the value of the zpgdbh property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getZPGDBH() {
			return zpgdbh;
		}

		/**
		 * Sets the value of the zpgdbh property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setZPGDBH(String value) {
			this.zpgdbh = value;
		}

		/**
		 * Gets the value of the rsnum property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getRSNUM() {
			return rsnum;
		}

		/**
		 * Sets the value of the rsnum property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setRSNUM(String value) {
			this.rsnum = value;
		}

		/**
		 * Gets the value of the rspos property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getRSPOS() {
			return rspos;
		}

		/**
		 * Sets the value of the rspos property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setRSPOS(String value) {
			this.rspos = value;
		}

		/**
		 * Gets the value of the reverse property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getREVERSE() {
			return reverse;
		}

		/**
		 * Sets the value of the reverse property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setREVERSE(String value) {
			this.reverse = value;
		}

		/**
		 * Gets the value of the username property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getUSERNAME() {
			return username;
		}

		/**
		 * Sets the value of the username property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setUSERNAME(String value) {
			this.username = value;
		}

		/**
		 * Gets the value of the attr1 property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getATTR1() {
			return attr1;
		}

		/**
		 * Sets the value of the attr1 property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setATTR1(String value) {
			this.attr1 = value;
		}

		/**
		 * Gets the value of the attr2 property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getATTR2() {
			return attr2;
		}

		/**
		 * Sets the value of the attr2 property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setATTR2(String value) {
			this.attr2 = value;
		}

		/**
		 * Gets the value of the attr3 property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getATTR3() {
			return attr3;
		}

		/**
		 * Sets the value of the attr3 property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setATTR3(String value) {
			this.attr3 = value;
		}

		/**
		 * Gets the value of the attr4 property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getATTR4() {
			return attr4;
		}

		/**
		 * Sets the value of the attr4 property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setATTR4(String value) {
			this.attr4 = value;
		}

		/**
		 * Gets the value of the attr5 property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getATTR5() {
			return attr5;
		}

		/**
		 * Sets the value of the attr5 property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setATTR5(String value) {
			this.attr5 = value;
		}

		/**
		 * Gets the value of the attr6 property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getATTR6() {
			return attr6;
		}

		/**
		 * Sets the value of the attr6 property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setATTR6(String value) {
			this.attr6 = value;
		}

		/**
		 * Gets the value of the attr7 property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getATTR7() {
			return attr7;
		}

		/**
		 * Sets the value of the attr7 property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setATTR7(String value) {
			this.attr7 = value;
		}

		/**
		 * Gets the value of the attr8 property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getATTR8() {
			return attr8;
		}

		/**
		 * Sets the value of the attr8 property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setATTR8(String value) {
			this.attr8 = value;
		}

		/**
		 * Gets the value of the attr9 property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getATTR9() {
			return attr9;
		}

		/**
		 * Sets the value of the attr9 property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setATTR9(String value) {
			this.attr9 = value;
		}

		/**
		 * Gets the value of the attr10 property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getATTR10() {
			return attr10;
		}

		/**
		 * Sets the value of the attr10 property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setATTR10(String value) {
			this.attr10 = value;
		}

		/**
		 * Gets the value of the attr11 property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getATTR11() {
			return attr11;
		}

		/**
		 * Sets the value of the attr11 property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setATTR11(String value) {
			this.attr11 = value;
		}

		/**
		 * Gets the value of the attr12 property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getATTR12() {
			return attr12;
		}

		/**
		 * Sets the value of the attr12 property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setATTR12(String value) {
			this.attr12 = value;
		}

		/**
		 * Gets the value of the attr13 property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getATTR13() {
			return attr13;
		}

		/**
		 * Sets the value of the attr13 property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setATTR13(String value) {
			this.attr13 = value;
		}

		/**
		 * Gets the value of the attr14 property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getATTR14() {
			return attr14;
		}

		/**
		 * Sets the value of the attr14 property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setATTR14(String value) {
			this.attr14 = value;
		}

		/**
		 * Gets the value of the attr15 property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getATTR15() {
			return attr15;
		}

		/**
		 * Sets the value of the attr15 property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setATTR15(String value) {
			this.attr15 = value;
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
		 * Gets the value of the uuid property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getUUID() {
			return uuid;
		}

		/**
		 * Sets the value of the uuid property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setUUID(String value) {
			this.uuid = value;
		}

	}

}
