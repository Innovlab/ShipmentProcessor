//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.11 at 12:13:44 PM CST 
//


package com.cts.ptms.model.tracking;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "statusType",
    "statusCode"
})
@XmlRootElement(name = "Status")
public class Status 
{

    @XmlElement(name = "StatusType", required = true)
    private CodeType statusType;
    @XmlElement(name = "StatusCode")
    private CodeNoDescription statusCode;
	/**
	 * @return the statusType
	 */
	public CodeType getStatusType() {
		return statusType;
	}
	/**
	 * @param statusType the statusType to set
	 */
	public void setStatusType(CodeType statusType) {
		this.statusType = statusType;
	}
	/**
	 * @return the statusCode
	 */
	public CodeNoDescription getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(CodeNoDescription statusCode) {
		this.statusCode = statusCode;
	}

}
