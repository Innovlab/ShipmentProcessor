//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.11 at 12:13:44 PM CST 
//


package com.cts.ptms.model.ups.generated.trackresponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActivityLocationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActivityLocationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Address" minOccurs="0"/>
 *         &lt;element ref="{}AddressArtifactFormat" minOccurs="0"/>
 *         &lt;element name="TransportFacility" type="{}TransportFacilityType" minOccurs="0"/>
 *         &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SignedForByName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{}SignatureImage" minOccurs="0"/>
 *         &lt;element name="PODLetter" type="{}PODLetterType" minOccurs="0"/>
 *         &lt;element name="ElectronicDeliveryNotification" type="{}ElectronicDeliveryNotificationType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActivityLocationType", propOrder = {
    "address",
    "addressArtifactFormat",
    "transportFacility",
    "code",
    "description",
    "signedForByName",
    "signatureImage",
    "podLetter",
    "electronicDeliveryNotification"
})
public class ActivityLocationType {

    @XmlElement(name = "Address")
    protected Address address;
    @XmlElement(name = "AddressArtifactFormat")
    protected AddressArtifactType addressArtifactFormat;
    @XmlElement(name = "TransportFacility")
    protected TransportFacilityType transportFacility;
    @XmlElement(name = "Code")
    protected String code;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "SignedForByName")
    protected String signedForByName;
    @XmlElement(name = "SignatureImage")
    protected SignatureImage signatureImage;
    @XmlElement(name = "PODLetter")
    protected PODLetterType podLetter;
    @XmlElement(name = "ElectronicDeliveryNotification")
    protected ElectronicDeliveryNotificationType electronicDeliveryNotification;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
    }

    /**
     * Gets the value of the addressArtifactFormat property.
     * 
     * @return
     *     possible object is
     *     {@link AddressArtifactType }
     *     
     */
    public AddressArtifactType getAddressArtifactFormat() {
        return addressArtifactFormat;
    }

    /**
     * Sets the value of the addressArtifactFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressArtifactType }
     *     
     */
    public void setAddressArtifactFormat(AddressArtifactType value) {
        this.addressArtifactFormat = value;
    }

    /**
     * Gets the value of the transportFacility property.
     * 
     * @return
     *     possible object is
     *     {@link TransportFacilityType }
     *     
     */
    public TransportFacilityType getTransportFacility() {
        return transportFacility;
    }

    /**
     * Sets the value of the transportFacility property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransportFacilityType }
     *     
     */
    public void setTransportFacility(TransportFacilityType value) {
        this.transportFacility = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the signedForByName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignedForByName() {
        return signedForByName;
    }

    /**
     * Sets the value of the signedForByName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignedForByName(String value) {
        this.signedForByName = value;
    }

    /**
     * Gets the value of the signatureImage property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureImage }
     *     
     */
    public SignatureImage getSignatureImage() {
        return signatureImage;
    }

    /**
     * Sets the value of the signatureImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureImage }
     *     
     */
    public void setSignatureImage(SignatureImage value) {
        this.signatureImage = value;
    }

    /**
     * Gets the value of the podLetter property.
     * 
     * @return
     *     possible object is
     *     {@link PODLetterType }
     *     
     */
    public PODLetterType getPODLetter() {
        return podLetter;
    }

    /**
     * Sets the value of the podLetter property.
     * 
     * @param value
     *     allowed object is
     *     {@link PODLetterType }
     *     
     */
    public void setPODLetter(PODLetterType value) {
        this.podLetter = value;
    }

    /**
     * Gets the value of the electronicDeliveryNotification property.
     * 
     * @return
     *     possible object is
     *     {@link ElectronicDeliveryNotificationType }
     *     
     */
    public ElectronicDeliveryNotificationType getElectronicDeliveryNotification() {
        return electronicDeliveryNotification;
    }

    /**
     * Sets the value of the electronicDeliveryNotification property.
     * 
     * @param value
     *     allowed object is
     *     {@link ElectronicDeliveryNotificationType }
     *     
     */
    public void setElectronicDeliveryNotification(ElectronicDeliveryNotificationType value) {
        this.electronicDeliveryNotification = value;
    }

}
