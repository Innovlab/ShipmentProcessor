//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.27 at 04:44:35 PM CST 
//


package main.java.com.cts.ptms.model.accept.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}ItemizedCharges" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}TaxCharges" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}NetSummaryCharges"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "itemizedCharges",
    "taxCharges",
    "netSummaryCharges"
})
@XmlRootElement(name = "NegotiatedRates")
public class NegotiatedRates {

    @XmlElement(name = "ItemizedCharges")
    protected List<MonetaryType> itemizedCharges;
    @XmlElement(name = "TaxCharges")
    protected List<TaxChargeType> taxCharges;
    @XmlElement(name = "NetSummaryCharges", required = true)
    protected NetSummaryCharges netSummaryCharges;

    /**
     * Gets the value of the itemizedCharges property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemizedCharges property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemizedCharges().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MonetaryType }
     * 
     * 
     */
    public List<MonetaryType> getItemizedCharges() {
        if (itemizedCharges == null) {
            itemizedCharges = new ArrayList<MonetaryType>();
        }
        return this.itemizedCharges;
    }

    /**
     * Gets the value of the taxCharges property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taxCharges property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaxCharges().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TaxChargeType }
     * 
     * 
     */
    public List<TaxChargeType> getTaxCharges() {
        if (taxCharges == null) {
            taxCharges = new ArrayList<TaxChargeType>();
        }
        return this.taxCharges;
    }

    /**
     * Gets the value of the netSummaryCharges property.
     * 
     * @return
     *     possible object is
     *     {@link NetSummaryCharges }
     *     
     */
    public NetSummaryCharges getNetSummaryCharges() {
        return netSummaryCharges;
    }

    /**
     * Sets the value of the netSummaryCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link NetSummaryCharges }
     *     
     */
    public void setNetSummaryCharges(NetSummaryCharges value) {
        this.netSummaryCharges = value;
    }

}
