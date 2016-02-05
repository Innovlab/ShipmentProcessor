package main.java.com.cts.ptms.utils.constants;

/**
 * 
 * @author 117847
 *
 */
public class ShippingConstants {
	
	public static final String SHIPPING_CONFIRM_URL	= "shipConfirmURL";
	public static final String SHIPPING_ACCEPT_URL 	= "shipAcceptURL";
	public static final String ACCESS_KEY = "accesskey";
    public static final String USER_ID = "username";
    public static final String PASSWORD = "password";
    public static final String SHIP_REQUEST_ACTION = "shipRequestAction";
    public static final String SHIP_REQUEST_OPTION = "shipRequestOption";
    public static final String XML_STANDALONE_STRING ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
    public static final String PDF_FILE_NAME="pdfFileName";
    public static final String INPUTFILE="D:\\inputData.xml";
    public static final String UPS = "UPS";
    public static final String buildPropertiesPath=".\\main\\resources\\Config\\ups\\Integraiton\\UPS.properties";
    
    // Hard coded strings used in business logic
    public static final String XML_NAMESPACE = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
    public static final String SHIP_Response_TAG = "<ShipmentConfirmResponse>";
    public static final String ship_Response_Replace = "<ShipmentConfirmResponse xmlns=\"http://ScanData.com/WTM/XMLSchemas/WTM_XMLSchema_11.00.0000.xsd\">";
    public static final String error_Open_tag = "<ErrorDescription>";
    public static final String error_Close_tag = "</ErrorDescription>";
    public static final String SHIP_Accept_TAG =  "<ShipmentAcceptResponse>";
    public static final String ship_Accept_Replace = "<ShipmentAcceptResponse xmlns=\"http://ScanData.com/WTM/XMLSchemas/WTM_XMLSchema_11.00.0000.xsd\">";
    public static final String PNG_FILE = ".png";
    public static final String PDF_fILE = ".PDF";
    public static final String FILE_PATH = "/WEB-INF/classes";
    public static final String File_Path_Replace = "/ShipLabels";
}

