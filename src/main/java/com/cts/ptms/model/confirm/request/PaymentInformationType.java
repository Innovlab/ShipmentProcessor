//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.27 at 04:45:15 PM CST 
//


package main.java.com.cts.ptms.model.confirm.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaymentInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="Prepaid" type="{}PrepaidType" minOccurs="0"/>
 *         &lt;element name="BillThirdParty" type="{}BillThirdPartyType" minOccurs="0"/>
 *         &lt;element name="FreightCollect" type="{}FreightCollectType" minOccurs="0"/>
 *         &lt;element name="ConsigneeBilled" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentInformationType", propOrder = {
    "prepaid",
    "billThirdParty",
    "freightCollect",
    "consigneeBilled"
})
public class PaymentInformationType {

    @XmlElement(name = "Prepaid")
    protected PrepaidType prepaid;
    @XmlElement(name = "BillThirdParty")
    protected BillThirdPartyType billThirdParty;
    @XmlElement(name = "FreightCollect")
    protected FreightCollectType freightCollect;
    @XmlElement(name = "ConsigneeBilled")
    protected String consigneeBilled;

    /**
     * Gets the value of the prepaid property.
     * 
     * @return
     *     possible object is
     *     {@link PrepaidType }
     *     
     */
    public PrepaidType getPrepaid() {
        return prepaid;
    }

    /**
     * Sets the value of the prepaid property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrepaidType }
     *     
     */
    public void setPrepaid(PrepaidType value) {
        this.prepaid = value;
    }

    /**
     * Gets the value of the billThirdParty property.
     * 
     * @return
     *     possible object is
     *     {@link BillThirdPartyType }
     *     
     */
    public BillThirdPartyType getBillThirdParty() {
        return billThirdParty;
    }

    /**
     * Sets the value of the billThirdParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillThirdPartyType }
     *     
     */
    public void setBillThirdParty(BillThirdPartyType value) {
        this.billThirdParty = value;
    }

    /**
     * Gets the value of the freightCollect property.
     * 
     * @return
     *     possible object is
     *     {@link FreightCollectType }
     *     
     */
    public FreightCollectType getFreightCollect() {
        return freightCollect;
    }

    /**
     * Sets the value of the freightCollect property.
     * 
     * @param value
     *     allowed object is
     *     {@link FreightCollectType }
     *     
     */
    public void setFreightCollect(FreightCollectType value) {
        this.freightCollect = value;
    }

    /**
     * Gets the value of the consigneeBilled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsigneeBilled() {
        return consigneeBilled;
    }

    /**
     * Sets the value of the consigneeBilled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsigneeBilled(String value) {
        this.consigneeBilled = value;
    }

}
