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
 * <p>Java class for FreightCollectType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FreightCollectType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BillReceiver" type="{}BillReceiverType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FreightCollectType", propOrder = {
    "billReceiver"
})
public class FreightCollectType {

    @XmlElement(name = "BillReceiver", required = true)
    protected BillReceiverType billReceiver;

    /**
     * Gets the value of the billReceiver property.
     * 
     * @return
     *     possible object is
     *     {@link BillReceiverType }
     *     
     */
    public BillReceiverType getBillReceiver() {
        return billReceiver;
    }

    /**
     * Sets the value of the billReceiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillReceiverType }
     *     
     */
    public void setBillReceiver(BillReceiverType value) {
        this.billReceiver = value;
    }

}
