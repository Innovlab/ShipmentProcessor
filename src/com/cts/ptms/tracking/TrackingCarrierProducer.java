/**
 * 
 */
package com.cts.ptms.tracking;

import main.java.com.cts.ptms.core.ECarriers;

/**
 *  Tracking Carrier Producer
 * @author 417765
 *
 */
public class TrackingCarrierProducer {
	
	
	/**
	 * Default constructor.
	 */
	public TrackingCarrierProducer() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This method is used to get the respective carrier instance tracking details  
	 * for the given input.
	 * @param carrierName
	 * @return ITrackingDetails
	 */
	public ITrackingDetails getTrackingCarrier(String carrierName) 
	{
		
		ITrackingDetails obj = null;
		if(carrierName != null && !carrierName.isEmpty()) 
		{
			switch (ECarriers.valueOf(carrierName)) 
			{
				case UPS : 
					obj = new UPSTracking();
					break;
				case YRC : 
					obj = new YRCTracking();
					break;
				default : 
					System.out.println("Invalid Carrier");
					break;
			}
		}
		return obj;
	}

}
