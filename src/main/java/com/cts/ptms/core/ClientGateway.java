package main.java.com.cts.ptms.core;
//import main.java.com.cts.ptms.model.ShipmentAcceptResponse;
//import main.java.com.cts.ptms.model.ShipmentConfirmResponse;
import main.java.com.cts.ptms.model.ShipmentRequest;
import main.java.com.cts.ptms.model.ShipmentResponse;
import main.java.com.cts.ptms.model.accept.response.ShipmentAcceptResponse;
import main.java.com.cts.ptms.model.confirm.response.ShipmentConfirmResponse;


public interface ClientGateway {
	
	public void initialize();
	
	public ShipmentResponse createShipmentRequest(ShipmentRequest request);	
	
	void handleError();
	
	public ShipmentResponse createShipmentResposeObj (ShipmentConfirmResponse confirmResponse,ShipmentAcceptResponse acceptResponse);
	
	
}
