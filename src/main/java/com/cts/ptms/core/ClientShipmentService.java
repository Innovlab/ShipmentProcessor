package main.java.com.cts.ptms.core;

import main.java.com.cts.ptms.model.ShipmentRequest;
import main.java.com.cts.ptms.model.ShipmentResponse;

public interface ClientShipmentService {	
	
	ClientGateway getClientService();	
	void processOutputFiles () ;
	ShipmentResponse createShipment(ShipmentRequest shipmentRequest);

}
