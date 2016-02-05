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
 * <p>Java class for RequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;sequence>
 *           &lt;element name="RequestAction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="RequestOption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="SubVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="TransactionReference" type="{}TransactionReferenceType" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestType", propOrder = {
    "requestAction",
    "requestOption",
    "subVersion",
    "transactionReference"
})
public class RequestType {

    @XmlElement(name = "RequestAction", required = true)
    protected String requestAction;
    @XmlElement(name = "RequestOption")
    protected String requestOption;
    @XmlElement(name = "SubVersion")
    protected String subVersion;
    @XmlElement(name = "TransactionReference")
    protected TransactionReferenceType transactionReference;

    /**
     * Gets the value of the requestAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestAction() {
        return requestAction;
    }

    /**
     * Sets the value of the requestAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestAction(String value) {
        this.requestAction = value;
    }

    /**
     * Gets the value of the requestOption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestOption() {
        return requestOption;
    }

    /**
     * Sets the value of the requestOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestOption(String value) {
        this.requestOption = value;
    }

    /**
     * Gets the value of the subVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubVersion() {
        return subVersion;
    }

    /**
     * Sets the value of the subVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubVersion(String value) {
        this.subVersion = value;
    }

    /**
     * Gets the value of the transactionReference property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionReferenceType }
     *     
     */
    public TransactionReferenceType getTransactionReference() {
        return transactionReference;
    }

    /**
     * Sets the value of the transactionReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionReferenceType }
     *     
     */
    public void setTransactionReference(TransactionReferenceType value) {
        this.transactionReference = value;
    }

}
