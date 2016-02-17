package test.com.cts.ptms.carrier.yrc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.logging.Logger;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cts.ptms.model.tracking.CustomTrackingRequest;
import com.cts.ptms.model.tracking.CustomTrackingResponse;
import com.cts.ptms.model.tracking.TrackRequestDetails;
import com.cts.ptms.model.ups.generated.tracking.accessrequest.AccessRequest;

import main.java.com.cts.ptms.core.ManageTrackingResource;

/**
 * 
 * @author 417765
 *
 */
public class ShipmentTrackingJUnitTest {
	
	private Logger logger = Logger.getAnonymousLogger() ;
	private AccessRequest accessRequest;
	private CustomTrackingResponse customTrackingResponse = null;
	private CustomTrackingRequest customTrackingRequest = null;
	private ManageTrackingResource manageTrackingResource = null;
	private TrackRequestDetails trackRequestDetails = null;
	private String SUCCESS = "Success";
	private String FAILURE = "Failure";
	
	//Services 
	private String GROUND = "GROUND";
	private String SECONDDAYAIR = "2ND DAY AIR";
	private String NEXT_DAY_AIR_SAVER  = "NEXT DAY AIR SAVER";
	
	//Response Delivery Status
	private String DELIVERED = "DELIVERED";
	private String ORIGIN_SCAN = "ORIGIN SCAN";
	private String INVALID_TRACKING_NUMBER = "Invalid Tracking Number";
	private String NO_INFORMATION_AVIALABLE = "No Tracking Information Available";
	
	@Before
    public void setUp() throws JSONException, IOException {
		
		logger.info("Setting up all required data for each test case method.");
		accessRequest = new AccessRequest();
		accessRequest.setAccessLicenseNumber("BD02B06EAB9F9B56");
		accessRequest.setUserId("varaprasadk.05");
		accessRequest.setPassword("Vara@2016");
		
		customTrackingRequest = new CustomTrackingRequest();
		customTrackingRequest.setAccessRequest(accessRequest);
		
		trackRequestDetails = new TrackRequestDetails();
		trackRequestDetails.setRequestAction("Track");
		
		customTrackingRequest.setCarrierName("UPS");
		manageTrackingResource = new ManageTrackingResource();
		
		
		
    }
    @After
    public void tearDown() 
    {
    	logger.info("nullifying up all required data for each test case method.");
    	accessRequest = null;
    	customTrackingRequest = null;
    	manageTrackingResource = null;
    	trackRequestDetails = null;
    }
    
	/**
	 * Junit method for testing the shipment tracking for ground success scenario.
	 */
	@Test
	public void testShipmentTracking_Ground_Success()
	{
		logger.info("Testing Shipment Tracking for Ground Delivered...");
		String trackingNum = "1Z12345E0390515214";
		
		try
		{
			//0- Last Activity 1-All Activity
			trackRequestDetails.getRequestOptions().add("0");
			trackRequestDetails.setTrackingNumber(trackingNum);
			customTrackingRequest.setTrackRequestDetails(trackRequestDetails);
			
			System.out.println("Input tracking request is ::" + customTrackingRequest);
			customTrackingResponse = manageTrackingResource.getTrackingDetails(customTrackingRequest);
			
			assertEquals(SUCCESS, customTrackingResponse.getResponseStatusDescription());
			assertEquals(GROUND, customTrackingResponse.getShipment().get(0).getService().getDescription());
			assertEquals(DELIVERED, customTrackingResponse.getShipment().get(0).get_package().
					get(0).getActivity().get(0).getStatus().getStatusType().getDescription());
			
		} 
		catch (Exception e)
		{
			System.out.println("Exception occured while tracking.."+e.getMessage());
			logger.severe("Exception occured while tracking..");
			assertEquals(FAILURE, FAILURE);
		}
	}
	/**
	 * Junit method for testing the shipment tracking for ground failure scenario.
	 */
	@Test
	public void testShipmentTracking_Ground_Failure()
	{
		logger.info("Testing Shipment Tracking for ground failure...");
		String trackingNum = "1Z12345E0390515210";
		
		try
		{
			//0- Last Activity 1-All Activity
			trackRequestDetails.getRequestOptions().add("1");
			trackRequestDetails.setTrackingNumber(trackingNum);
			customTrackingRequest.setTrackRequestDetails(trackRequestDetails);
			
			System.out.println("Input tracking request is ::" + customTrackingRequest);
			customTrackingResponse = manageTrackingResource.getTrackingDetails(customTrackingRequest);
			assertFalse(SUCCESS.equals(customTrackingResponse.getResponseStatusDescription()));
			
		} 
		catch (Exception e)
		{
			System.out.println("Exception occured while tracking.."+e.getMessage());
			logger.severe("Exception occured while tracking..");
			assertEquals(FAILURE, FAILURE);
		}
	}

	/**
	 * Junit method for testing the shipment tracking for 2nd Day Air success scenario.
	 */
	@Test
	public void testShipmentTracking_2ndDayAir_Success()
	{
		logger.info("Testing Shipment Tracking for 2nd Day Air with Status Delivered...");
		String trackingNum = "1Z12345E0291980793";
		
		try
		{
			//0- Last Activity 1-All Activity
			trackRequestDetails.getRequestOptions().add("1");
			trackRequestDetails.setTrackingNumber(trackingNum);
			customTrackingRequest.setTrackRequestDetails(trackRequestDetails);
			
			System.out.println("Input tracking request is ::" + customTrackingRequest);
			customTrackingResponse = manageTrackingResource.getTrackingDetails(customTrackingRequest);
			
			assertEquals(SUCCESS, customTrackingResponse.getResponseStatusDescription());
			assertEquals(SECONDDAYAIR, customTrackingResponse.getShipment().get(0).getService().getDescription());
			assertEquals(DELIVERED, customTrackingResponse.getShipment().get(0).get_package().
					get(0).getActivity().get(0).getStatus().getStatusType().getDescription());
			
		} 
		catch (Exception e)
		{
			System.out.println("Exception occured while tracking.."+e.getMessage());
			logger.severe("Exception occured while tracking..");
			assertEquals(FAILURE, FAILURE);
		}
	}
	/**
	 * Junit method for testing the shipment tracking for 2nd Day Air Failure scenario.
	 */
	@Test
	public void testShipmentTracking_2ndDayAir_Failure()
	{
		logger.info("Testing Shipment Tracking for 2nd Day Air with Status Failure...");
		
		String trackingNum = "1Z12345E0291980796";
		try
		{
			//0- Last Activity 1-All Activity
			trackRequestDetails.getRequestOptions().add("1");
			trackRequestDetails.setTrackingNumber(trackingNum);
			customTrackingRequest.setTrackRequestDetails(trackRequestDetails);
			
			System.out.println("Input tracking request is ::" + customTrackingRequest);
			customTrackingResponse = manageTrackingResource.getTrackingDetails(customTrackingRequest);
			
			assertEquals(FAILURE, customTrackingResponse.getResponseStatusDescription());
			
		} 
		catch (Exception e)
		{
			System.out.println("Exception occured while tracking.."+e.getMessage());
			logger.severe("Exception occured while tracking..");
			assertEquals(FAILURE, FAILURE);
		}
	}
    /**
     *  Junit Test case with Next Day Air Saver
     */
    @Test
	public void testShipmentTracking_NextDayAirSaver()
	{
		logger.info("Testing Shipment Tracking for next Day Air saver with Status ORIGIN SCAN...");
		String trackingNum = "1Z12345E1392654435";
		
		try
		{
			//0- Last Activity 1-All Activity
			trackRequestDetails.getRequestOptions().add("1");
			trackRequestDetails.setTrackingNumber(trackingNum);
			customTrackingRequest.setTrackRequestDetails(trackRequestDetails);
			
			System.out.println("Input tracking request is ::" + customTrackingRequest);
			customTrackingResponse = manageTrackingResource.getTrackingDetails(customTrackingRequest);
			
			assertEquals(SUCCESS, customTrackingResponse.getResponseStatusDescription());
			assertEquals(NEXT_DAY_AIR_SAVER, customTrackingResponse.getShipment().get(0).getService().getDescription());
			assertEquals(ORIGIN_SCAN, customTrackingResponse.getShipment().get(0).get_package().
					get(0).getActivity().get(0).getStatus().getStatusType().getDescription());
			
		} 
		catch (Exception e)
		{
			System.out.println("Exception occured while tracking.."+e.getMessage());
			logger.severe("Exception occured while tracking..");
			assertEquals(FAILURE, FAILURE);
		}
	}
    /**
     *  Junit Test case with Invalid Tracking number
     */
    @Test
	public void testShipmentTracking_InvalidTrackingNumber()
	{
		logger.info("Testing Shipment Tracking for Invalid Tracking number...");
		String trackingNum = "1Z12345E029198079";
		
		try
		{
			//0- Last Activity 1-All Activity
			trackRequestDetails.getRequestOptions().add("1");
			trackRequestDetails.setTrackingNumber(trackingNum);
			customTrackingRequest.setTrackRequestDetails(trackRequestDetails);
			
			System.out.println("Input tracking request is ::" + customTrackingRequest);
			customTrackingResponse = manageTrackingResource.getTrackingDetails(customTrackingRequest);
			
			assertEquals(FAILURE, customTrackingResponse.getResponseStatusDescription());
			assertEquals("Invalid tracking number", customTrackingResponse.getError().get(0).getErrorDescription());
			
		} 
		catch (Exception e)
		{
			System.out.println("Exception occured while tracking.."+e.getMessage());
			logger.severe("Exception occured while tracking..");
			assertEquals(FAILURE, FAILURE);
		}
	}
    /**
     *  Junit Test case for Tracking number with No information available.
     */
    @Test
	public void testShipmentTracking_NoInformationAvailable()
	{
		logger.info("Testing Shipment Tracking for scenario no information available for the given tracking number...");
		String trackingNum = "1Z12345E1591910450";
		
		try
		{
			//0- Last Activity 1-All Activity
			trackRequestDetails.getRequestOptions().add("1");
			trackRequestDetails.setTrackingNumber(trackingNum);
			customTrackingRequest.setTrackRequestDetails(trackRequestDetails);
			
			System.out.println("Input tracking request is ::" + customTrackingRequest);
			customTrackingResponse = manageTrackingResource.getTrackingDetails(customTrackingRequest);
			
			assertEquals(FAILURE, customTrackingResponse.getResponseStatusDescription());
			assertEquals("No tracking information available", customTrackingResponse.getError().get(0).getErrorDescription());
			
		} 
		catch (Exception e)
		{
			System.out.println("Exception occured while tracking.."+e.getMessage());
			logger.severe("Exception occured while tracking..");
			assertEquals(FAILURE, FAILURE);
		}
	}
	
}
