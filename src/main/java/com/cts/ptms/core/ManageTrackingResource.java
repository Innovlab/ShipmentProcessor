/**
 * 
 */
package main.java.com.cts.ptms.core;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.cts.ptms.exception.tracking.TrackingException;
import com.cts.ptms.model.tracking.CustomTrackingRequest;
import com.cts.ptms.model.tracking.CustomTrackingResponse;
import com.cts.ptms.model.tracking.TrackRequestDetails;
import com.cts.ptms.model.ups.generated.tracking.accessrequest.AccessRequest;
import com.cts.ptms.service.tracking.ITrackingService;
import com.cts.ptms.service.tracking.TrackingServiceImpl;


/**
 * This resource is used for tracking the shipment
 * @author 417765
 *
 */
@Path("/Tracking")
public class ManageTrackingResource {
	
	private Logger logger = Logger.getAnonymousLogger() ;
	
	
	@POST	
	@Path("/trackingdetails")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON) 
	public CustomTrackingResponse getTrackingDetails(CustomTrackingRequest customTrackingRequest)
	{
		CustomTrackingResponse customTrackingResponse = null;
		logger.info("Trying to get the tracking details..");
		try
		{
			if ( customTrackingRequest == null) 
			{
				throw new TrackingException("Invalid Request");
			}
			ITrackingService trackingService = new TrackingServiceImpl();
			customTrackingResponse = 
					trackingService.getShipmentTrackingDetails(customTrackingRequest);
			System.out.println(customTrackingResponse);
		} 
		catch (TrackingException e)
		{
			System.out.println("Exception occured while tracking.."+e.getMessage());
			logger.severe("Exception occured while tracking..");
		}
		return customTrackingResponse;
	}
	
	
	@GET	
	@Path("/trackingdetails")
	@Consumes(MediaType.TEXT_PLAIN) 
	@Produces(MediaType.APPLICATION_JSON) 
	public CustomTrackingResponse getTrackingDetails(@QueryParam("trackingNumber") String trackingNum, 
			@QueryParam("carrierName") String carrierName)
	{
		CustomTrackingResponse customTrackingResponse = null;
		CustomTrackingRequest customTrackingRequest = null;
		logger.info("Trying to get the tracking details..");
		try
		{
			if ( trackingNum == null) 
			{
				throw new TrackingException("Invalid Request");
			}
			customTrackingRequest = new CustomTrackingRequest();
			AccessRequest accessRequest = new AccessRequest();
			accessRequest.setAccessLicenseNumber("BD02B06EAB9F9B56");
			accessRequest.setUserId("varaprasadk.05");
			accessRequest.setPassword("Vara@2016");
			
			customTrackingRequest.setAccessRequest(accessRequest);
			customTrackingRequest.setCarrierName(carrierName);
			
			TrackRequestDetails trackRequestDetails = new TrackRequestDetails();
			trackRequestDetails.setRequestAction("Track");
			trackRequestDetails.getRequestOptions().add("7");
			trackRequestDetails.setTrackingNumber(trackingNum);
			customTrackingRequest.setTrackRequestDetails(trackRequestDetails);
			
			System.out.println("Input tracking request is ::" + customTrackingRequest);
			customTrackingResponse = getTrackingDetails(customTrackingRequest);
			System.out.println(customTrackingResponse);
		} 
		catch (TrackingException e)
		{
			System.out.println("Exception occured while tracking.."+e.getMessage());
			logger.severe("Exception occured while tracking..");
		}
		return customTrackingResponse;
	}
}
