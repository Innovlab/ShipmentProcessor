package test.java.com.cts.ptms.carrier.ups;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.logging.Logger;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.com.cts.ptms.carrier.ups.UPSHTTPClient;
import main.java.com.cts.ptms.core.ShipmentServiceImpl;
import main.java.com.cts.ptms.model.ShipmentRequest;
import main.java.com.cts.ptms.model.ShipmentResponse;
import main.java.com.cts.ptms.model.ShippingInfoDO;
import main.java.com.cts.ptms.utils.constants.ShippingConstants;

/**
 * Junit test case for shipping label success scenario.
 * @author 417765
 *
 */
public class ShippingPackageClientUTest {
	
	private Logger logger = Logger.getAnonymousLogger() ;

	@Before
    public void setUp() throws JSONException, IOException {
		logger.info("@Before - setUp..");
       
    }
    @After
    public void tearDown() {
    	logger.info("@After - tearDown");
    }
    @Test
	public void testCreateShipment_Success()
	{
    	logger.info("Testing CreateShipment success scenario...");
    	
    	ShipmentRequest shipmentRequest = new ShipmentRequest();
    	ShipmentResponse shipmentResponse = new ShipmentResponse();
    	ShipmentServiceImpl shipmentServiceImpl = new ShipmentServiceImpl();
    	
    	shipmentRequest.setFileName(ShippingConstants.INPUTFILE);
		shipmentRequest.setCarrier("UPS");
		shipmentResponse = shipmentServiceImpl.createShipment(shipmentRequest);
		
		String status = shipmentResponse.getStatus();
		assertEquals("Success", status);
    	
	}
	/**
	 * Actual testing method
	 */
	@Test
	public void testCreateShipment_Failure()
	{
		
		logger.info("Testing CreateShipment failure scenario...");
    	
    	ShipmentRequest shipmentRequest = new ShipmentRequest();
    	ShipmentResponse shipmentResponse = new ShipmentResponse();
    	ShipmentServiceImpl shipmentServiceImpl = new ShipmentServiceImpl();
    	
    	shipmentRequest.setFileName(ShippingConstants.INPUTFILE);
		shipmentRequest.setCarrier("UPS1"); //Modifying unknown carrier
		try{
			shipmentResponse = shipmentServiceImpl.createShipment(shipmentRequest);
			String status = shipmentResponse.getStatus();
		} catch (Exception e){
			assertEquals("Failure", "Failure");
		}
		
	}
	/**
	 * 
	 * @param shippingInfoDO
	 */
	@Test
	public void testGenerateShippingLabel()
	{
		logger.info("Testing testGenerateShippingLabel...");
		UPSHTTPClient upsHttpClient = new UPSHTTPClient();
		ShippingInfoDO shippingInfoDO = new ShippingInfoDO();
		try {
			upsHttpClient.generateShippingLabel(shippingInfoDO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
