/**
 * 
 */
package com.cts.ptms.tracking;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.cts.ptms.commonutils.ShipmentCommonUtilities;
import com.cts.ptms.exception.tracking.TrackingException;
import com.cts.ptms.model.tracking.Activity;
import com.cts.ptms.model.tracking.ActivityLocation;
import com.cts.ptms.model.tracking.Address;
import com.cts.ptms.model.tracking.CodeDescription;
import com.cts.ptms.model.tracking.CodeNoDescription;
import com.cts.ptms.model.tracking.CodeType;
import com.cts.ptms.model.tracking.CustomTrackingRequest;
import com.cts.ptms.model.tracking.CustomTrackingResponse;
import com.cts.ptms.model.tracking.Package;
import com.cts.ptms.model.tracking.PackageServiceOptions;
import com.cts.ptms.model.tracking.ShipTo;
import com.cts.ptms.model.tracking.Shipment;
import com.cts.ptms.model.tracking.ShipmentReferenceNumber;
import com.cts.ptms.model.tracking.Shipper;
import com.cts.ptms.model.tracking.Status;
import com.cts.ptms.model.tracking.TrackRequestDetails;
import com.cts.ptms.model.tracking.TrackingError;
import com.cts.ptms.model.tracking.UnitOfMeasurement;
import com.cts.ptms.model.tracking.Weight;
import com.cts.ptms.model.ups.generated.tracking.accessrequest.AccessRequest;
import com.cts.ptms.model.ups.generated.trackresponse.TrackResponse;



/**
 * @author 417765
 *
 */
public class UPSTracking implements ITrackingDetails{

	Properties properties = null;
	/**
	 * Constructor for instantiating required objects.
	 */
	public UPSTracking() {
		
		try{
			properties = ShipmentCommonUtilities.getProperties("./main/resources/Config/ups/Integraiton/UPS.properties");
		}catch (Exception e){
			System.out.println("Exception occured while loading the UPS properties file.");
		}
	}

	/* (non-Javadoc)
	 * @see main.java.com.cts.ptms.tracking.ITrackingDetails#getTrackingDetails()
	 */
	@Override
	public CustomTrackingResponse getTrackingDetails(CustomTrackingRequest customTrackingRequest) throws TrackingException {
		
		CustomTrackingResponse customTrackingResponse = null;
		try 
		{
			com.cts.ptms.model.ups.generated.tracking.accessrequest.AccessRequest accessRequest = null;
			com.cts.ptms.model.ups.generated.trackrequest.TrackRequest trackRequest = null; 
			com.cts.ptms.model.ups.generated.trackresponse.TrackResponse trackResponse = null;
			
			//Create JAXBContext and marshaller for AccessRequest object        			
	    	JAXBContext accessRequestJAXBC = JAXBContext.newInstance(AccessRequest.class.getPackage().getName() );	            
			Marshaller accessRequestMarshaller = accessRequestJAXBC.createMarshaller();
			com.cts.ptms.model.ups.generated.tracking.accessrequest.ObjectFactory accessRequestObjectFactory = 
					new com.cts.ptms.model.ups.generated.tracking.accessrequest.ObjectFactory();
			accessRequest = accessRequestObjectFactory.createAccessRequest();
			
			AccessRequest receivedAccessReq = customTrackingRequest.getAccessRequest();
			
			//Populate the access request
			accessRequest.setAccessLicenseNumber(receivedAccessReq.getAccessLicenseNumber());
	    	accessRequest.setUserId(receivedAccessReq.getUserId());
	    	accessRequest.setPassword(receivedAccessReq.getPassword());
	    	
	    	TrackRequestDetails inputRequestDtls = customTrackingRequest.getTrackRequestDetails();
	    	if (inputRequestDtls == null || inputRequestDtls.getTrackingNumber() == null) {
	    		throw new TrackingException("No Tracking Tracking number found in the request.");
	    	}
	    	
			//Create JAXBContext and marshaller for TrackRequest object
			JAXBContext trackRequestJAXBC = JAXBContext.newInstance(
					com.cts.ptms.model.ups.generated.trackrequest.TrackRequest.class.getPackage().getName() );	            
			Marshaller trackRequestMarshaller = trackRequestJAXBC.createMarshaller();
			com.cts.ptms.model.ups.generated.trackrequest.ObjectFactory requestObjectFactory = 
					new com.cts.ptms.model.ups.generated.trackrequest.ObjectFactory();
			
			
			trackRequest = requestObjectFactory.createTrackRequest();
			//Populate the Track request
			com.cts.ptms.model.ups.generated.trackrequest.Request request = 
					new com.cts.ptms.model.ups.generated.trackrequest.Request();
			com.cts.ptms.model.ups.generated.trackrequest.TransactionReference transReference = 
					new com.cts.ptms.model.ups.generated.trackrequest.TransactionReference();
			transReference.setCustomerContext("Tracking customer shipment Details");
			request.setTransactionReference(transReference);
			request.setRequestAction("Track");
			request.getRequestOption().addAll(inputRequestDtls.getRequestOptions());
			trackRequest.setRequest(request);
	    	trackRequest.setTrackingNumber(inputRequestDtls.getTrackingNumber());
			
			//Get String out of access request and track request objects.
			StringWriter strWriter = new StringWriter();       		       
			accessRequestMarshaller.marshal(accessRequest, strWriter);
			trackRequestMarshaller.marshal(trackRequest, strWriter);
			strWriter.flush();
			strWriter.close();
			
			System.out.println("Request: " + strWriter.getBuffer().toString());
			
			if (properties == null) {
				throw new TrackingException("Error while loading the ups properties.");
			} 
			URLConnection httpUrlConnection = ShipmentCommonUtilities.contactService(strWriter.getBuffer().toString(), 
					new URL(properties.getProperty("INTEGRATION_URL")));
			String strResults = null;
			if(httpUrlConnection != null) {
				strResults = ShipmentCommonUtilities.readURLConnection(httpUrlConnection);
			} else 
				throw new TrackingException("Exception occured while contacting the service..");
			
			
			//Parse response object
			JAXBContext trackResponseJAXBC = JAXBContext.newInstance(TrackResponse.class.getPackage().getName());
			Unmarshaller trackUnmarshaller = trackResponseJAXBC.createUnmarshaller();
			ByteArrayInputStream input = new ByteArrayInputStream(strResults.getBytes());
			Object objResponse = trackUnmarshaller.unmarshal(input);
			trackResponse = (com.cts.ptms.model.ups.generated.trackresponse.TrackResponse)objResponse;
			
			List<TrackingError> trackingErrors = null;
			if (trackResponse.getResponse() != null)
			{
				
				String responseCode = trackResponse.getResponse().getResponseStatusCode();
				customTrackingResponse = populateResponse(trackResponse);
				if (responseCode != null && responseCode.equals("1")){
			    	
			    	System.out.println("Response Status: " + trackResponse.getResponse().getResponseStatusCode());
					System.out.println("Response Status Description: " + trackResponse.getResponse().
							getResponseStatusDescription());
			    } 
				else if (responseCode != null && responseCode.equals("0")) 
			    {	
					trackingErrors = new ArrayList<TrackingError>(0);
					List<com.cts.ptms.model.ups.generated.trackresponse.Error> resErrors = 
							trackResponse.getResponse().getError();
					if(resErrors != null && !resErrors.isEmpty()) {
						for (com.cts.ptms.model.ups.generated.trackresponse.Error resError : resErrors){
							TrackingError trackingError = new TrackingError();
							trackingError.setErrorSeverity(resError.getErrorSeverity());
							trackingError.setErrorCode(resError.getErrorCode());
							trackingError.setErrorDescription(resError.getErrorDescription());
							trackingErrors.add(trackingError);
						}
					}
					customTrackingResponse.setError(trackingErrors);
			    }
		    }
			
		}
		catch (TrackingException e) {
			System.out.println("Exception occurred at : "+e.getMessage());
		}
		catch (Exception e) {
			System.out.println("Exception occurred at : "+e.getMessage());
			throw new TrackingException(e.getMessage());
		}
		return customTrackingResponse;
	}
	/**
	 * Populate the custom tracking response
	 * @param customTrackingResponse
	 * @return CustomTrackingResponse
	 */
	private CustomTrackingResponse populateResponse(TrackResponse rcvdTrackingResponse) 
	{
		
		CustomTrackingResponse customTrackRes = new CustomTrackingResponse();
		try 
		{
			customTrackRes = new CustomTrackingResponse();
			customTrackRes.setResponseStatusCode(rcvdTrackingResponse.getResponse().getResponseStatusCode());
			customTrackRes.setResponseStatusDescription(rcvdTrackingResponse.getResponse().getResponseStatusDescription());
			
			
			List<com.cts.ptms.model.ups.generated.trackresponse.Shipment> recvdShipments = rcvdTrackingResponse.getShipment();
			if(recvdShipments == null) {
				throw new TrackingException("Shipment details not found");
			}
			List<Shipment> shipments = new ArrayList<Shipment>(0);
			for (com.cts.ptms.model.ups.generated.trackresponse.Shipment recvdShipment : recvdShipments) {
				
				Shipment shipment = new Shipment();
				//Shipper information 
				Shipper shipper = new Shipper();
				com.cts.ptms.model.ups.generated.trackresponse.ShipperType receivedShipperType =
					recvdShipment.getShipper();
				
				if(receivedShipperType != null) {
					shipper.setShipperNumber(receivedShipperType.getShipperNumber());
					com.cts.ptms.model.ups.generated.trackresponse.Address rcvdShipperAddress = receivedShipperType.getAddress();
					if (rcvdShipperAddress != null) {
						
						Address address = new Address();
						address.setAddressLine1(rcvdShipperAddress.getAddressLine1());
						address.setAddressLine2(rcvdShipperAddress.getAddressLine2());
						address.setAddressLine3(rcvdShipperAddress.getAddressLine3());
						address.setCity(rcvdShipperAddress.getCity());
						address.setCountryCode(rcvdShipperAddress.getCountryCode());
						address.setPostalCode(rcvdShipperAddress.getPostalCode());
						address.setStateProvinceCode(rcvdShipperAddress.getStateProvinceCode());
						shipper.setAddress(address);
					}
					else{
						System.out.println("Shipper Address details not found");
					}
				} else {
					System.out.println("Shipper details not found");
				}
				shipment.setShipper(shipper);
				//End of Shipper information
				
				//ShipTo address information 
				ShipTo shipTo = new ShipTo();
				com.cts.ptms.model.ups.generated.trackresponse.ShipToType receivedShipTo = recvdShipment.getShipTo();
				if (receivedShipTo != null ) {
					com.cts.ptms.model.ups.generated.trackresponse.Address receivedShipToAddress = receivedShipTo.getAddress();
					if (receivedShipToAddress != null ) {
						Address shipToAddress = new Address();
						shipToAddress.setAddressLine1(receivedShipToAddress.getAddressLine1());
						shipToAddress.setCity(receivedShipToAddress.getCity());
						shipToAddress.setCountryCode(receivedShipToAddress.getCountryCode());
						shipToAddress.setPostalCode(receivedShipToAddress.getPostalCode());
						shipToAddress.setStateProvinceCode(receivedShipToAddress.getStateProvinceCode());
						shipTo.setAddress(shipToAddress);
					} else {
						System.out.println("Ship To Address details not found.");
					}
				} else {
					System.out.println("Ship To details not found.");
				}
				
				shipment.setShipTo(shipTo);
				
				//Weight Detais
				com.cts.ptms.model.ups.generated.trackresponse.WeightType recievedWeightType =  recvdShipment.getShipmentWeight();
				Weight weightDetails  = null;
				if (recievedWeightType != null) {
					com.cts.ptms.model.ups.generated.trackresponse.UnitOfMeasurement recievedUOM = recievedWeightType.getUnitOfMeasurement();
					if(recievedUOM != null ) {
						
						UnitOfMeasurement uom = new UnitOfMeasurement();
						uom.setCode(recievedUOM.getCode());
						uom.setDescription(recievedUOM.getDescription());
						
						weightDetails = new Weight();
						weightDetails.setUnitOfMeasurement(uom);
						weightDetails.setWeight(recievedWeightType.getWeight());
					} else {
						System.out.println("Unit of Measurement Details not found.");
					}
				} else {
					System.out.println("No weight details found");
				}
				shipment.setShipmentWeight(weightDetails);
				
				//Service type
				com.cts.ptms.model.ups.generated.trackresponse.CodeType receivedSrvcType = recvdShipment.getService();
				if (receivedSrvcType != null) {
					CodeType codeType = new CodeType();
					codeType.setCode(receivedSrvcType.getCode());
					codeType.setDescription(receivedSrvcType.getDescription());
					shipment.setService(codeType);
				} else {
					System.out.println("Service type details not found.");
				}
				
				//Shipment ReferenceNumber
				List<com.cts.ptms.model.ups.generated.trackresponse.ShipmentReferenceNumberType> receivedRefTypes = recvdShipment.getReferenceNumber();
				
				if (receivedRefTypes == null ) {
					throw new TrackingException(" Shipment ReferenceNumber details not found.");
				}
				List<ShipmentReferenceNumber> shipmentReferenceNumbers = new ArrayList<ShipmentReferenceNumber>(0);
				
				for (com.cts.ptms.model.ups.generated.trackresponse.ShipmentReferenceNumberType shipmentReferenceNumberType 
						: receivedRefTypes) {
					
					ShipmentReferenceNumber shipmentReferenceNumber = new ShipmentReferenceNumber();
					shipmentReferenceNumber.setCode(shipmentReferenceNumberType.getCode());
					shipmentReferenceNumber.setValue(shipmentReferenceNumberType.getValue());
					shipmentReferenceNumbers.add(shipmentReferenceNumber);
				}
				shipment.setReferenceNumber(shipmentReferenceNumbers);
				//Shipment Identification Number
				shipment.setShipmentIdentificationNumber(recvdShipment.getShipmentIdentificationNumber());
				//Pickup date
				shipment.setPickupDate(recvdShipment.getPickupDate());
				
				//Package
				List<com.cts.ptms.model.ups.generated.trackresponse.PackageType> receivedPackages = recvdShipment.getPackage();
				if ( receivedPackages == null) {
					throw new TrackingException("No Package Details available.");
				}
				List<Package> packages = new ArrayList<Package>(0);
				CodeDescription codeDesc = new CodeDescription();
				Package tempPackage = null; 
				
				for (com.cts.ptms.model.ups.generated.trackresponse.PackageType rcvdpkgType : receivedPackages) {
					tempPackage = new Package();
					tempPackage.setTrackingNumber(rcvdpkgType.getTrackingNumber());
					com.cts.ptms.model.ups.generated.trackresponse.PackageServiceOptionsType receivedPackSerOptions =  rcvdpkgType.getPackageServiceOptions();
					PackageServiceOptions packageSerOptions = new PackageServiceOptions();
					if (receivedPackSerOptions != null) {
						com.cts.ptms.model.ups.generated.trackresponse.CodeDescriptionType rcvdCdDesc =  receivedPackSerOptions.getSignatureRequired();
						if ( rcvdCdDesc != null ) {
							codeDesc.setCode(rcvdCdDesc.getCode());
							codeDesc.setDescription(rcvdCdDesc.getDescription());
							packageSerOptions.setSignatureRequired(codeDesc);
						}
					}
					tempPackage.setPackageServiceOptions(packageSerOptions);
					
					
					//Activity
					List<Activity> activities = new ArrayList<Activity>(0);
					Activity activity = null;
					List<com.cts.ptms.model.ups.generated.trackresponse.Activity> rcvdActivitys = rcvdpkgType.getActivity();
					if(rcvdActivitys == null) {
						new TrackingException("No Activiti Details available.");
					}
					for (com.cts.ptms.model.ups.generated.trackresponse.Activity rcvdActivity : rcvdActivitys) {
						
						com.cts.ptms.model.ups.generated.trackresponse.ActivityLocationType rcvdActvityLoc  = rcvdActivity.getActivityLocation();
						if(rcvdActvityLoc == null) {
							new TrackingException("No Activity Location Details available.");
						}
						activity = new Activity();
						ActivityLocation activityLoc = null;
						if(rcvdActvityLoc != null) {
							com.cts.ptms.model.ups.generated.trackresponse.Address rcvdLocAddress = rcvdActvityLoc.getAddress();
							Address  tempAddress = new Address();
							tempAddress.setAddressLine1(rcvdLocAddress.getAddressLine1());
							tempAddress.setCity(rcvdLocAddress.getCity());
							tempAddress.setCountryCode(rcvdLocAddress.getCountryCode());
							tempAddress.setPostalCode(rcvdLocAddress.getPostalCode());
							tempAddress.setStateProvinceCode(rcvdLocAddress.getStateProvinceCode());
							activityLoc = new ActivityLocation();
							activityLoc.setAddress(tempAddress);
							activityLoc.setCode(rcvdActvityLoc.getCode());
							activityLoc.setDescription(rcvdActvityLoc.getDescription());
							activityLoc.setSignedForByName(rcvdActvityLoc.getSignedForByName());
						}
						activity.setActivityLocation(activityLoc);
						
						com.cts.ptms.model.ups.generated.trackresponse.Status rcvdStatus = rcvdActivity.getStatus();
						if (rcvdStatus == null) {
							new TrackingException("No Status Details available.");
						}
						Status status = new Status();
						
						com.cts.ptms.model.ups.generated.trackresponse.CodeType recCdType =  rcvdStatus.getStatusType();
						if(recCdType != null) {
							CodeType tempCodeType = new CodeType();
							tempCodeType.setCode(recCdType.getCode());
							tempCodeType.setDescription(recCdType.getDescription());
							status.setStatusType(tempCodeType);
						} 
						else 
						{
							System.out.println("No Status type Details available.");
						}
						
						
						com.cts.ptms.model.ups.generated.trackresponse.CodeNoDescriptionType recCdNoDescType =  rcvdStatus.getStatusCode();
						if(recCdNoDescType != null) {
							CodeNoDescription tempCodeNoDescType = new CodeNoDescription();
							tempCodeNoDescType.setCode(recCdNoDescType.getCode());
							status.setStatusCode(tempCodeNoDescType);
						} else {
							System.out.println("No Status code Details available.");
						}
						activity.setStatus(status);
						activity.setDate(rcvdActivity.getDate());
						activity.setTime(rcvdActivity.getTime());
						
						activities.add(activity);
						tempPackage.setActivity(activities);
					}
					
					packages.add(tempPackage);
				}
				
				shipment.set_package(packages);
				shipments.add(shipment);
			}
			customTrackRes.setShipment(shipments);
		}
		catch (TrackingException e)
		{
			System.out.println("Exception occured while populating the response:"+e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("Exception occured while populating the response:"+e.getMessage());
		}
		return customTrackRes;
	}

}