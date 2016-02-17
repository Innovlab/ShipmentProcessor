/**
 * 
 */
package com.cts.ptms.model.tracking;

import java.util.List;

/**
 * @author 417765
 *
 */
public class CustomTrackingResponse {

	/**
	 * Default constructor
	 */
	public CustomTrackingResponse() {
		// TODO Auto-generated constructor stub
	}
	
	private String responseStatusCode;
	private String responseStatusDescription;
	private List<TrackingError> error;
	private List<Shipment> shipment;
	/**
	 * @return the responseStatusCode
	 */
	public String getResponseStatusCode() {
		return responseStatusCode;
	}
	/**
	 * @param responseStatusCode the responseStatusCode to set
	 */
	public void setResponseStatusCode(String responseStatusCode) {
		this.responseStatusCode = responseStatusCode;
	}
	/**
	 * @return the responseStatusDescription
	 */
	public String getResponseStatusDescription() {
		return responseStatusDescription;
	}
	/**
	 * @param responseStatusDescription the responseStatusDescription to set
	 */
	public void setResponseStatusDescription(String responseStatusDescription) {
		this.responseStatusDescription = responseStatusDescription;
	}
	/**
	 * @return the error
	 */
	public List<TrackingError> getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(List<TrackingError> error) {
		this.error = error;
	}
	/**
	 * @return the shipment
	 */
	public List<Shipment> getShipment() {
		return shipment;
	}
	/**
	 * @param shipment the shipment to set
	 */
	public void setShipment(List<Shipment> shipment) {
		this.shipment = shipment;
	}
	
	

}
