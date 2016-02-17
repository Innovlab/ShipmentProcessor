/**
 * 
 */
package com.cts.ptms.service.tracking;

import com.cts.ptms.exception.tracking.TrackingException;
import com.cts.ptms.model.tracking.CustomTrackingRequest;
import com.cts.ptms.model.tracking.CustomTrackingResponse;

/**
 * @author 417765
 *
 */
public interface ITrackingService {
	
	/**
	 * A Method signature for getting tracking details.
	 * 
	 * @param customTrackingRequest
	 * @return CustomTrackingResponse
	 * @throws TrackingException
	 */
	CustomTrackingResponse getShipmentTrackingDetails(CustomTrackingRequest customTrackingRequest) 
			throws TrackingException;
	

}