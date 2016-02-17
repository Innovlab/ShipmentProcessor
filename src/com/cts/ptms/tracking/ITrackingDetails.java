/**
 * 
 */
package com.cts.ptms.tracking;

import com.cts.ptms.exception.tracking.TrackingException;
import com.cts.ptms.model.tracking.CustomTrackingRequest;
import com.cts.ptms.model.tracking.CustomTrackingResponse;

/**
 * @author 417765
 *
 */
public interface ITrackingDetails {
	
	/**
	 * Tracking details
	 * @param customTrackingRequest
	 * @return
	 * @throws TrackingException
	 */
	CustomTrackingResponse getTrackingDetails(CustomTrackingRequest customTrackingRequest) throws TrackingException;

}
