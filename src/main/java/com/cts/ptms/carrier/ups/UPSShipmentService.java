package main.java.com.cts.ptms.carrier.ups;

import main.java.com.cts.ptms.core.ClientGateway;
import main.java.com.cts.ptms.core.ClientShipmentService;
import main.java.com.cts.ptms.model.ShipmentRequest;
import main.java.com.cts.ptms.model.ShipmentResponse;

public class UPSShipmentService implements ClientShipmentService{
	
	private ShipmentRequest shipmentRequest;
	private ClientGateway clientService;
	
	public UPSShipmentService(ShipmentRequest shipmentRequest) {
		this.shipmentRequest = shipmentRequest;
		getClientService();
	}

	public ShipmentResponse createShipment(ShipmentRequest request) {
		ShipmentResponse shipmentResponse = clientService.createShipmentRequest(request);
		return shipmentResponse;
	}

	
	public ClientGateway getClientService() {
		clientService = new UPSHTTPClient();
		return clientService;
	}

	

	public void processOutputFiles() {
		// TODO Auto-generated method stubs
		
	}

}
