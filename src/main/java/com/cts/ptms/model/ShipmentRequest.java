package main.java.com.cts.ptms.model;

public class ShipmentRequest {

	private String fileName;
	
	private String carrier;
	
	private boolean genLabel;
	
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public boolean isGenLabel() {
		return genLabel;
	}
	public void setGenLabel(boolean genLabel) {
		this.genLabel = genLabel;
	}
	
}
