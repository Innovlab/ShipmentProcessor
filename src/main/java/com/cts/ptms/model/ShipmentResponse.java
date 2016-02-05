package main.java.com.cts.ptms.model;

import java.util.ArrayList;
import java.util.List;

public class ShipmentResponse {
	private String status;
	private String ErrorSeverity;
	private String ErrorCode;
	private String ErrorDescription;
	private String trackingNumber;
	private List<ShipmentDocument> shipmentDocuments = new ArrayList<>();
	
	public String getErrorSeverity() {
		return ErrorSeverity;
	}
	public void setErrorSeverity(String errorSeverity) {
		ErrorSeverity = errorSeverity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorCode() {
		return ErrorCode;
	}
	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}
	public String getErrorDescription() {
		return ErrorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		ErrorDescription = errorDescription;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public List<ShipmentDocument> getShipmentDocuments() {
		return shipmentDocuments;
	}
	public void setShipmentDocuments(List<ShipmentDocument> shipmentDocuments) {
		this.shipmentDocuments = shipmentDocuments;
	}
}
