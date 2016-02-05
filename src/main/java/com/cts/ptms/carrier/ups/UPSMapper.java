package main.java.com.cts.ptms.carrier.ups;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import main.java.com.cts.ptms.model.confirm.request.DiscountType;
import main.java.com.cts.ptms.model.confirm.request.FreightChargesType;
import main.java.com.cts.ptms.model.confirm.request.InsuranceChargesType;
import main.java.com.cts.ptms.model.confirm.request.OtherChargesType;

import main.java.com.cts.ptms.model.ADDRESS;
import main.java.com.cts.ptms.model.CreateShipUnits;
import main.java.com.cts.ptms.model.INVOICE;
import main.java.com.cts.ptms.model.SHIPUNIT;
import main.java.com.cts.ptms.model.ShipmentAcceptRequest;
import main.java.com.cts.ptms.model.ShipmentAcceptRequestObjectFactory;
//import main.java.com.cts.ptms.model.ShipmentConfirmRequest;
//import main.java.com.cts.ptms.model.ShipmentConfirmRequest.Request.TransactionReference;
import main.java.com.cts.ptms.model.ShipmentConfirmResponse;
import main.java.com.cts.ptms.model.confirm.request.CodeType;
import main.java.com.cts.ptms.model.confirm.request.InternationalFormsType;
import main.java.com.cts.ptms.model.confirm.request.PackageType;
import main.java.com.cts.ptms.model.confirm.request.PackageWeightType;
import main.java.com.cts.ptms.model.confirm.request.PackagingTypeType;
import main.java.com.cts.ptms.model.confirm.request.ProductType;
import main.java.com.cts.ptms.model.confirm.request.ProductWeightType;
import main.java.com.cts.ptms.model.confirm.request.ReferenceNumberType;
import main.java.com.cts.ptms.model.confirm.request.ShipmentConfirmRequest;
import main.java.com.cts.ptms.model.confirm.request.SoldToAddressType;
import main.java.com.cts.ptms.model.confirm.request.SoldToType;
import main.java.com.cts.ptms.model.confirm.request.TransactionReferenceType;
import main.java.com.cts.ptms.model.confirm.request.UnitType;
import main.java.com.cts.ptms.utils.constants.UPSConstants;

public class UPSMapper {
	
	public  ShipmentConfirmRequest populateShipConfirmRequest(CreateShipUnits createShipUnits) {
		
		main.java.com.cts.ptms.model.confirm.request.ObjectFactory requestObjectFactory = new main.java.com.cts.ptms.model.confirm.request.ObjectFactory();
		ShipmentConfirmRequest shipRequest = requestObjectFactory.createShipmentConfirmRequest();
		TransactionReferenceType transactionReference = new TransactionReferenceType();

		transactionReference.setCustomerContext(UPSConstants.CONTEXT_STRING);
		if(null == shipRequest.getRequest()){
			shipRequest.setRequest(requestObjectFactory.createRequestType());
			shipRequest.setLabelSpecification(requestObjectFactory.createLabelSpecificationType());
			shipRequest.setShipment(requestObjectFactory.createShipmentType());
			shipRequest.getRequest().setTransactionReference(requestObjectFactory.createTransactionReferenceType());
			shipRequest.getLabelSpecification().setLabelImageFormat(requestObjectFactory.createLabelImageFormatCodeDescriptionType());
			shipRequest.getLabelSpecification().setLabelPrintMethod(requestObjectFactory.createLabelPrintMethodCodeDescriptionType());
			
			//International Forms
			shipRequest.getShipment().setShipmentServiceOptions(requestObjectFactory.createShipmentServiceOptionsType());
			shipRequest.getShipment().getShipmentServiceOptions().setInternationalForms(requestObjectFactory.createInternationalFormsType());
			shipRequest.getShipment().setSoldTo(requestObjectFactory.createSoldToType());
			shipRequest.getShipment().getSoldTo().setAddress(requestObjectFactory.createSoldToAddressType());
			
			shipRequest.getShipment().getShipmentServiceOptions().getInternationalForms().setDiscount(requestObjectFactory.createDiscountType());
			shipRequest.getShipment().getShipmentServiceOptions().getInternationalForms().setFreightCharges(requestObjectFactory.createFreightChargesType());
			shipRequest.getShipment().getShipmentServiceOptions().getInternationalForms().setInsuranceCharges(requestObjectFactory.createInsuranceChargesType());
			shipRequest.getShipment().getShipmentServiceOptions().getInternationalForms().setOtherCharges(requestObjectFactory.createOtherChargesType());
		}
		shipRequest.getRequest().setRequestAction(UPSConstants.REQUEST_TYPE);
		shipRequest.getRequest().setRequestOption(UPSConstants.VALIDATION_MODE);
		shipRequest.getRequest().setTransactionReference(transactionReference);
		//Label
		shipRequest.getLabelSpecification().getLabelPrintMethod().setCode(UPSConstants.IMAGE_FORMAT);
		shipRequest.getLabelSpecification().setHTTPUserAgent(UPSConstants.BROWSER_TYPE);
		shipRequest.getLabelSpecification().getLabelImageFormat().setCode(UPSConstants.IMAGE_FORMAT);
		//Shipment
		if(null == shipRequest.getShipment().getShipper()){
			shipRequest.getShipment().setShipper(requestObjectFactory.createShipperType());
		}
		SHIPUNIT  shipUnit = createShipUnits.getCreateShipUnitsParams().getCREATESHIPUNITSPARAMS1().getSHIPUNIT();
		List<ADDRESS> addressList = shipUnit.getADDRESS();
		for (ADDRESS address : addressList ) {
			
			if(address.getClazz().equals(UPSConstants.DELIVER_TO)) {
				if(null == shipRequest.getShipment().getShipTo()){
					shipRequest.getShipment().setShipTo(requestObjectFactory.createShipToType());
				}
				shipRequest.getShipment().getShipTo().setCompanyName(address.getIndividualName());
				shipRequest.getShipment().getShipTo().setAttentionName(address.getIndividualName());
				shipRequest.getShipment().getShipTo().setPhoneNumber("1234567890");
				if(null == shipRequest.getShipment().getShipTo().getAddress()){
					shipRequest.getShipment().getShipTo().setAddress(requestObjectFactory.createShipToAddressType());
				}
				shipRequest.getShipment().getShipTo().getAddress().setAddressLine1(address.getAddress1());
				shipRequest.getShipment().getShipTo().getAddress().setCity(address.getCity());
				shipRequest.getShipment().getShipTo().getAddress().setCountryCode("US");
				shipRequest.getShipment().getShipTo().getAddress().setStateProvinceCode(address.getState());
				shipRequest.getShipment().getShipTo().getAddress().setPostalCode(address.getZIPCode().toString());
				
				//International Forms
				
				
				SoldToType soldTo = shipRequest.getShipment().getSoldTo();
				soldTo.setOption("01");
				soldTo.setAttentionName(address.getIndividualName());
				soldTo.setCompanyName(address.getIndividualName());
				soldTo.setPhoneNumber("1234567890");
				SoldToAddressType soldToAddress =  shipRequest.getShipment().getSoldTo().getAddress();
				soldToAddress.setAddressLine1(address.getAddress1());
				soldToAddress.setCity(address.getCity());
				soldToAddress.setPostalCode(address.getZIPCode().toString());
				soldToAddress.setCountryCode("DE");
				soldTo.setAddress(soldToAddress);
				shipRequest.getShipment().setSoldTo(soldTo);
				
				
			} else if (address.getClazz().equals(UPSConstants.ORDERED_BY)) {

				if(null == shipRequest.getShipment().getShipFrom()){
					shipRequest.getShipment().setShipFrom(requestObjectFactory.createShipFromType());
				}
				shipRequest.getShipment().getShipFrom().setCompanyName(address.getIndividualName());
				shipRequest.getShipment().getShipFrom().setAttentionName(address.getIndividualName());
				shipRequest.getShipment().getShipFrom().setPhoneNumber("1234567890");
				shipRequest.getShipment().getShipFrom().setTaxIdentificationNumber("1234567877");
				if(null == shipRequest.getShipment().getShipFrom().getAddress()){
					shipRequest.getShipment().getShipFrom().setAddress(requestObjectFactory.createShipFromAddressType());
				}
				shipRequest.getShipment().getShipFrom().getAddress().setAddressLine1(address.getAddress1());
				shipRequest.getShipment().getShipFrom().getAddress().setCity(address.getCity());
				shipRequest.getShipment().getShipFrom().getAddress().setCountryCode("US");
				shipRequest.getShipment().getShipFrom().getAddress().setStateProvinceCode(address.getState());
				shipRequest.getShipment().getShipFrom().getAddress().setPostalCode(address.getZIPCode().toString());
				
				
			} else if (address.getClazz().equals(UPSConstants.RETURN)) {
				shipRequest.getShipment().getShipper().setName(address.getIndividualName());
				shipRequest.getShipment().getShipper().setPhoneNumber("1234567890");
				shipRequest.getShipment().getShipper().setShipperNumber("1801E0");
				shipRequest.getShipment().getShipper().setTaxIdentificationNumber("1234567877");
				if(null == shipRequest.getShipment().getShipper().getAddress()){
					shipRequest.getShipment().getShipper().setAddress(requestObjectFactory.createShipperAddressType());
				}
				shipRequest.getShipment().getShipper().getAddress().setAddressLine1(address.getAddress1());
				shipRequest.getShipment().getShipper().getAddress().setCity(address.getCity());
				shipRequest.getShipment().getShipper().getAddress().setCountryCode("US");
				shipRequest.getShipment().getShipper().getAddress().setStateProvinceCode(address.getState());
				shipRequest.getShipment().getShipper().getAddress().setPostalCode(address.getZIPCode().toString());
				
			}
		}
		
		
		
		if(null == shipRequest.getShipment().getPaymentInformation()){
			shipRequest.getShipment().setPaymentInformation(requestObjectFactory.createPaymentInformationType());
			shipRequest.getShipment().getPaymentInformation().setPrepaid(requestObjectFactory.createPrepaidType());
			shipRequest.getShipment().getPaymentInformation().getPrepaid().setBillShipper(requestObjectFactory.createBillShipperType());
		}
		shipRequest.getShipment().getPaymentInformation().getPrepaid().getBillShipper().setAccountNumber("1801E0");
		if(null == shipRequest.getShipment().getService()){
			shipRequest.getShipment().setService(requestObjectFactory.createServiceType());
		}
		shipRequest.getShipment().getService().setCode("02");
		if(shipRequest.getShipment().getPackage().isEmpty()){
			PackageType firstPackage = new PackageType();
			PackagingTypeType pkgingType = new PackagingTypeType();
			firstPackage.setPackagingType(pkgingType);
			shipRequest.getShipment().getPackage().add(firstPackage);
			
			ReferenceNumberType refNo = new ReferenceNumberType();
			shipRequest.getShipment().getPackage().get(0).getReferenceNumber().add(refNo);
			
			PackageWeightType weight = new PackageWeightType();
			shipRequest.getShipment().getPackage().get(0).setPackageWeight(weight);
			
	;
		}
		String serviceCode = shipUnit.getShipVia().toString();
		if(serviceCode.length() == 1){
			serviceCode = "0"+serviceCode;
		}
		
		for (int pkgCount=0; pkgCount < shipRequest.getShipment().getPackage().size();pkgCount++ ){
			shipRequest.getShipment().getService().setCode(serviceCode);
			shipRequest.getShipment().getPackage().get(pkgCount).getPackagingType().setCode("02");
			shipRequest.getShipment().getPackage().get(pkgCount).getReferenceNumber().get(0).setCode("00");
			shipRequest.getShipment().getPackage().get(pkgCount).getReferenceNumber().get(0).setValue("Package");
			shipRequest.getShipment().getPackage().get(pkgCount).getPackageWeight().setWeight(shipUnit.getWeight().toString());
		}
	
		
		//International Forms
		InternationalFormsType internationalForms = shipRequest.getShipment().getShipmentServiceOptions().getInternationalForms();
		List<String> formTypeList = internationalForms.getFormType();

		//Commercial Invoice
		formTypeList.add("01");

		// Add Product 
		List<ProductType> productList = internationalForms.getProduct();
		ProductType product = new ProductType();
		List<String> descriptionList = product.getDescription();
		descriptionList.add("Product 1");
		product.setCommodityCode("123COM");
		product.setOriginCountryCode("US");
		UnitType unit = new UnitType();
		unit.setNumber("147");
		unit.setValue("478");
		CodeType uom = new CodeType();
		uom.setCode("BOX");
		uom.setDescription("BOX");
		unit.setUnitOfMeasurement(uom);
		product.setUnit(unit);
		ProductWeightType productWeight = new ProductWeightType();
		productWeight.setWeight("10");
		CodeType uomForWeight = new CodeType();
		uomForWeight.setCode("LBS");
		uomForWeight.setDescription("LBS");
		productWeight.setUnitOfMeasurement(uomForWeight);
		product.setProductWeight(productWeight);
		productList.add(product);
		
		INVOICE invoice = shipUnit.getDataXML().getINVOICE();
		//LocalDateTime.parse(shipUnit.getDatePlannedShipment(), DateTimeFormatter.ofPattern("yyyyMMdd").withLocale(Locale.ENGLISH));
		internationalForms.setInvoiceNumber(invoice.getCustomerOrderNumber().toString());
		//internationalForms.setInvoiceDate(LocalDateTime.parse(shipUnit.getDatePlannedShipment(), DateTimeFormatter.ofPattern("yyyyMMdd").withLocale(Locale.ENGLISH)).toString());
		internationalForms.setInvoiceDate("20150401");
		internationalForms.setPurchaseOrderNumber(shipUnit.getOrderNumber().toString());
		internationalForms.setTermsOfShipment("CFR");
		internationalForms.setReasonForExport("Sale");
		internationalForms.setComments("Clothing Items");
		internationalForms.setDeclarationStatement("Declaration Statement");
		
		/** **** Discount, FreightCharges, InsuranceCharges, OtherCharges and CurrencyCode  ***** */
		DiscountType discount = shipRequest.getShipment().getShipmentServiceOptions().getInternationalForms().getDiscount();
		discount.setMonetaryValue("10");
		internationalForms.setDiscount(discount);
		FreightChargesType freight = shipRequest.getShipment().getShipmentServiceOptions().getInternationalForms().getFreightCharges();
		freight.setMonetaryValue("50");
		internationalForms.setFreightCharges(freight);
		InsuranceChargesType insurance = shipRequest.getShipment().getShipmentServiceOptions().getInternationalForms().getInsuranceCharges();
		insurance.setMonetaryValue("200");
		internationalForms.setInsuranceCharges(insurance);
		OtherChargesType otherCharges = shipRequest.getShipment().getShipmentServiceOptions().getInternationalForms().getOtherCharges();
		otherCharges.setMonetaryValue("50");
		otherCharges.setDescription("Misc");
		internationalForms.setOtherCharges(otherCharges);
		internationalForms.setCurrencyCode("USD");
		
		shipRequest.getShipment().getShipmentServiceOptions().setInternationalForms(internationalForms);

		
		return shipRequest;
	}

	public ShipmentAcceptRequest populateShipAcceptRequest(ShipmentConfirmResponse shipconfirmResponse) {
		ShipmentAcceptRequestObjectFactory acceptRequestObjectFactory = new ShipmentAcceptRequestObjectFactory();
		ShipmentAcceptRequest shipAccept = new ShipmentAcceptRequest();
		if(null == shipAccept.getRequest()){
			shipAccept.setRequest(acceptRequestObjectFactory.createShipmentAcceptRequestRequest());
			shipAccept.getRequest().setTransactionReference(acceptRequestObjectFactory.createShipmentAcceptRequestRequestTransactionReference());
			
		}
		shipAccept.getRequest().setRequestAction("ShipAccept");
		shipAccept.getRequest().setRequestOption("1");
		shipAccept.getRequest().getTransactionReference().setCustomerContext("Test Content");
		shipAccept.setShipmentDigest(shipconfirmResponse.getShipmentDigest());
		return shipAccept;
	}
	
	

}
