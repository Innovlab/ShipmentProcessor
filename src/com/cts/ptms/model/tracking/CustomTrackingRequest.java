/**
 * 
 */
package com.cts.ptms.model.tracking;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import com.cts.ptms.model.ups.generated.tracking.accessrequest.AccessRequest;



/**
 * Custom tracking request 
 * 
 * @author 417765
 *
 */
public class CustomTrackingRequest implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 52277734828706040L;
	
	@XmlElement(name = "AccessRequest", required = true)
	private AccessRequest accessRequest;
	
	@XmlElement(name = "TrackRequestDetails", required = true)
	private TrackRequestDetails trackRequestDetails;
	
	@XmlElement(name = "CarrierName", required = true)
	private String carrierName;
	
	
	/**
	 * Default constructor
	 */
	public CustomTrackingRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the accessRequest
	 */
	public AccessRequest getAccessRequest() {
		return accessRequest;
	}

	/**
	 * @param accessRequest the accessRequest to set
	 */
	public void setAccessRequest(AccessRequest accessRequest) {
		this.accessRequest = accessRequest;
	}

	/**
	 * @return the trackRequestDetails
	 */
	public TrackRequestDetails getTrackRequestDetails() {
		return trackRequestDetails;
	}

	/**
	 * @param trackRequestDetails the trackRequestDetails to set
	 */
	public void setTrackRequestDetails(TrackRequestDetails trackRequestDetails) {
		this.trackRequestDetails = trackRequestDetails;
	}

	/**
	 * @return the carrierName
	 */
	public String getCarrierName() {
		return carrierName;
	}

	/**
	 * @param carrierName the carrierName to set
	 */
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	
}
