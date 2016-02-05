package main.java.com.cts.ptms.carrier.seiko;

import main.java.com.cts.ptms.core.ClientGateway;
import main.java.com.cts.ptms.core.ClientShipmentService;
import main.java.com.cts.ptms.model.ShipmentRequest;
import main.java.com.cts.ptms.model.ShipmentResponse;

public class SekoShipmentService implements ClientShipmentService{
	
	private ShipmentRequest shipmentRequest;
	private Object clientService;
	
	public SekoShipmentService(ShipmentRequest shipmentRequest) {
		this.shipmentRequest = shipmentRequest;
	}

	public ShipmentResponse createShipment(ShipmentRequest shipmentRequest) {		
		return null;
	}

	
	public ClientGateway getClientService() {
		// TODO Auto-generated method stub
		// Bassed on the carrier gateway configuration create the handle for the respective client
		//clientService = new SeikoRestfulClient();
		//clientService = new SeikoSoapClient();
		return null;
	}

	

	public void processOutputFiles() {
		// TODO Auto-generated method stubs
		
	}

}
