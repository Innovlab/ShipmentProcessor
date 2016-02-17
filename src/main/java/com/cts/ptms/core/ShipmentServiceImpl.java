package main.java.com.cts.ptms.core;

import main.java.com.cts.ptms.carrier.ups.UPSShipmentService;
import main.java.com.cts.ptms.model.ShipmentRequest;
import main.java.com.cts.ptms.model.ShipmentResponse;
import main.java.com.cts.ptms.utils.constants.ShippingConstants;

public class ShipmentServiceImpl implements ShipmentService {

	public ShipmentResponse createShipment(ShipmentRequest shipmentRequest) {
		
		String carrier = identifyCarrier(shipmentRequest);
		// Based on carrier and the corresponding configuration load the
		// properties and inject the respective service objects
		ClientShipmentService clientShipmentService = null;
		if(carrier== ShippingConstants.UPS){
			clientShipmentService =  new UPSShipmentService(shipmentRequest);
		}
		if(carrier== ShippingConstants.YRC){
			clientShipmentService =  new UPSShipmentService(shipmentRequest);
		}
		ShipmentResponse shipmentResponse =  clientShipmentService.createShipment(shipmentRequest);
		return shipmentResponse;
		
		
	}
	
	
	private String identifyCarrier(ShipmentRequest shipmentRequest) {
		//HashMap carrierIdentifierMap = new HashMap<String,String>();
		String carrierName = shipmentRequest.getCarrier();
		
		if(shipmentRequest.getCarrier().toString().equalsIgnoreCase(ShippingConstants.UPS)){
			return ShippingConstants.UPS;
		}
		if(shipmentRequest.getCarrier().toString().equalsIgnoreCase(ShippingConstants.YRC)){
			return ShippingConstants.YRC;
		}
		return null;
		
	}
	
	public static void main(String [] args) {
		
		
	}

}
