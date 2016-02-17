/**
 * 
 */
package com.cts.ptms.service.tracking;

import com.cts.ptms.exception.tracking.TrackingException;
import com.cts.ptms.model.tracking.CustomTrackingRequest;
import com.cts.ptms.model.tracking.CustomTrackingResponse;
import com.cts.ptms.tracking.TrackingCarrierProducer;

/**
 * Tracking service implementation class
 * @author 417765
 *
 */
public class TrackingServiceImpl implements ITrackingService {

	/**
	 * Default Constructor
	 */
	public TrackingServiceImpl() 
	{
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see main.java.com.cts.ptms.service.tracking.ITrackingService#getShipmentTrackingDetails(java.lang.String)
	 */
	@Override
	public CustomTrackingResponse getShipmentTrackingDetails(CustomTrackingRequest customTrackingRequest) throws TrackingException
	{

		return new TrackingCarrierProducer().getTrackingCarrier(customTrackingRequest.getCarrierName()).
				getTrackingDetails(customTrackingRequest);
	}

	
	
	
	

}
