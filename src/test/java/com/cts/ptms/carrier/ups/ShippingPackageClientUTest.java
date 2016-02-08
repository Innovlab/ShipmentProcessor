package test.java.com.cts.ptms.carrier.ups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;
import java.util.Properties;
import java.util.logging.Logger;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import main.java.com.cts.ptms.carrier.ups.UPSHTTPClient;
import main.java.com.cts.ptms.core.ShipmentServiceImpl;
import main.java.com.cts.ptms.model.ShipmentDocument;
import main.java.com.cts.ptms.model.ShipmentRequest;
import main.java.com.cts.ptms.model.ShipmentResponse;
import main.java.com.cts.ptms.utils.constants.ShippingConstants;

/**
 * Junit test case for shipping label success scenario.
 * @author 417765
 *
 */
public class ShippingPackageClientUTest {
	
	/**
	 * Class variables
	 */
	private Logger logger = Logger.getAnonymousLogger() ;
	private ShipmentRequest shipmentRequest;
	private ShipmentResponse shipmentResponse;
	private ShipmentServiceImpl shipmentServiceImpl;
	private UPSHTTPClient upsHTTPClient ;
	private String filePath;
	private Properties properties; 
	
	@Before
    public void setUp() throws JSONException, IOException {
		
		logger.info("setting up all required data for each test case method.");
		shipmentRequest = new ShipmentRequest();
    	shipmentResponse = new ShipmentResponse();
    	shipmentServiceImpl = new ShipmentServiceImpl();
    	upsHTTPClient = new UPSHTTPClient();
    	
    	try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("./test/java/com/cts/ptms/carrier/ups/resources/documents_location.properties");
			properties = new Properties();
			properties.load(inputStream);
			System.out.println("Saving the file .."+ properties.getProperty("SAVE_FILE_PATH"));

		} catch (IOException e) {
			e.printStackTrace();
		}
       
    }
    @After
    public void tearDown() 
    {
    	logger.info("nullifying up all required data for each test case method.");
    	shipmentRequest = null;
    	shipmentResponse = null;
    	shipmentServiceImpl = null;
    	upsHTTPClient = null;
    	filePath = null;
    }
          
    /**
	 * Test case for missing or invalid service code like Ground or Next day Air or 2nd Day Air
	 * @param shippingInfoDO
	 */
	@Test
	public void testCreateShipmentRequest_InvalidServiceCode()
	{
		logger.info("Testing CreateShipmentRequest for Invalid ServiceCode...");
		try 
		{
			String errorMsg = "Missing or invalid service code";
			filePath = "./test/java/com/cts/ptms/carrier/ups/resources/InputData_InvalidServiceCode.xml";
			shipmentRequest.setCarrier("UPS"); 
			URL url = getClass().getClassLoader().getResource(filePath);
			shipmentRequest.setFileName(url.getFile());
			shipmentResponse = upsHTTPClient.createShipmentRequest(shipmentRequest);
			System.out.println(shipmentResponse.getErrorDescription());
			assertEquals(errorMsg, shipmentResponse.getErrorDescription());
			
		} 
		catch(Exception e)
		{
			assertEquals("Failure", "Failure");
			e.printStackTrace();
			System.out.println("Exception occured"+e.getMessage());
		}
		
	}
	/**
	 * Test method for valid service code.
	 */
	@Test
	public void testCreateShipmentRequest_validServiceCode()
	{
		logger.info("Testing CreateShipmentRequest for Invalid ServiceCode...");
		try 
		{
			filePath = "./test/java/com/cts/ptms/carrier/ups/resources/InputData_ValidServiceCode.xml";
			shipmentRequest.setCarrier("UPS"); 
			URL url = getClass().getClassLoader().getResource(filePath);
			shipmentRequest.setFileName(url.getFile());
			shipmentResponse = upsHTTPClient.createShipmentRequest(shipmentRequest);
			//System.out.println(shipmentResponse.getErrorDescription());
			assertEquals("Success", shipmentResponse.getStatus());
			System.out.println("Status:"+shipmentResponse.getStatus() );
			
			assertFalse(shipmentResponse == null);
			assertFalse(shipmentResponse.getShipmentDocuments().size() == 0);
			ShipmentDocument shipmentDoc = shipmentResponse.getShipmentDocuments().get(0);
			assertFalse(shipmentDoc.getName() != "SHIPPINGLABEL");
			assertFalse(null == shipmentDoc.getContent());
			byte[] decoded = Base64.getDecoder().decode(shipmentDoc.getContent());
			saveBase64DataToLocalFile(decoded, shipmentResponse.getTrackingNumber()+"_ValidSrvcCd", false);
			
			assertEquals("Success", shipmentResponse.getStatus());
		} 
		catch(Exception e)
		{
			assertEquals("Failure", "Failure");
			e.printStackTrace();
			System.out.println("Exception occured"+e.getMessage());
		}
		
	}
	/**
	 * Test case for invalid weight
	 */
	@Test
	public void testCreateShipmentRequest_InvalidWeight()
	{
		logger.info("Testing CreateShipmentRequest for Invalid Weight...");
		try 
		{
			String errorMsg = "Package weight value cannot exceed a length of 6";
			filePath = "./test/java/com/cts/ptms/carrier/ups/resources/InputData_InvalidWeight.xml";
			shipmentRequest.setCarrier("UPS"); 
			URL url = getClass().getClassLoader().getResource(filePath);
			shipmentRequest.setFileName(url.getFile());
			shipmentResponse = upsHTTPClient.createShipmentRequest(shipmentRequest);
			System.out.println(shipmentResponse.getErrorDescription());
			assertEquals(errorMsg, shipmentResponse.getErrorDescription());
			
		} 
		catch(Exception e)
		{
			assertEquals("Failure", "Failure");
			e.printStackTrace();
			System.out.println("Exception occured"+e.getMessage());
		}
		
	}
	/**
	 * Test case for valid weight
	 */
	@Test
	public void testCreateShipmentRequest_ValidWeight()
	{
		logger.info("Testing CreateShipmentRequest for Valid Weight...");
		try 
		{
			filePath = "./test/java/com/cts/ptms/carrier/ups/resources/InputData_ValidWeight.xml";
			shipmentRequest.setCarrier("UPS"); 
			URL url = getClass().getClassLoader().getResource(filePath);
			shipmentRequest.setFileName(url.getFile());
			shipmentResponse = upsHTTPClient.createShipmentRequest(shipmentRequest);
			assertEquals("Success", shipmentResponse.getStatus());
			System.out.println("Status:"+shipmentResponse.getStatus() );
			
			assertFalse(shipmentResponse == null);
			assertFalse(shipmentResponse.getShipmentDocuments().size() == 0);
			ShipmentDocument shipmentDoc = shipmentResponse.getShipmentDocuments().get(0);
			assertFalse(shipmentDoc.getName() != "SHIPPINGLABEL");
			assertFalse(null == shipmentDoc.getContent());
			byte[] decoded = Base64.getDecoder().decode(shipmentDoc.getContent());
			saveBase64DataToLocalFile(decoded, shipmentResponse.getTrackingNumber()+"_ValidWieght", false);
			assertEquals("Success", shipmentResponse.getStatus());
			
		} 
		catch(Exception e)
		{
			assertEquals("Failure", "Failure");
			e.printStackTrace();
			System.out.println("Exception occured"+e.getMessage());
		}
		
	}
	/**
	 * Address_Validate_Success_Ground
	 * @param shippingInfoDO
	 */
	@Test
	public void testCreateShipmentRequest_Address_Validate_Success_Ground()
	{
		logger.info("Testing testCreateShipmentRequest_Address_Validate_Success_Ground...");
		try 
		{
			filePath = "./test/java/com/cts/ptms/carrier/ups/resources/InputData_Address_Validation_Success_Ground.xml";
			shipmentRequest.setCarrier("UPS"); 
			URL url = getClass().getClassLoader().getResource(filePath);
			shipmentRequest.setFileName(url.getFile());
			shipmentResponse = null;
			shipmentResponse = upsHTTPClient.createShipmentRequest(shipmentRequest);
			
			assertFalse(shipmentResponse == null);
			assertFalse(shipmentResponse.getShipmentDocuments().size() == 0);
			ShipmentDocument shipmentDoc = shipmentResponse.getShipmentDocuments().get(0);
			assertFalse(shipmentDoc.getName() != "SHIPPINGLABEL");
			assertFalse(null == shipmentDoc.getContent());
			byte[] decoded = Base64.getDecoder().decode(shipmentDoc.getContent());
			
			saveBase64DataToLocalFile(decoded, shipmentResponse.getTrackingNumber()+"AddressValid_Ground", false);
			System.out.println("Saved successfully on "+getClass().getClassLoader().getResource("").getPath());
			assertEquals("Success", shipmentResponse.getStatus());
		} 
		catch(Exception e)
		{
			assertEquals("Failure", "Failure");
			e.printStackTrace();
		}
		
	}
	/**
	 * Address_Validate_Failure_Ground
	 * @param shippingInfoDO
	 */
	@Test
	public void testCreateShipmentRequest_Address_Validate_Failure_Ground()
	{
		logger.info("Testing testCreateShipmentRequest_Address_Validate_Failure_Ground...");
		try 
		{
			filePath = "./test/java/com/cts/ptms/carrier/ups/resources/InputData_Address_Validation_Failure_Ground.xml";
			//shipmentRequest.setFileName("./test/java/com/cts/ptms/carrier/ups/resources/InputData_Address_Validation_Failure_Ground.xml");
			shipmentRequest.setCarrier("UPS"); 
			URL url = getClass().getClassLoader().getResource(filePath);
			shipmentRequest.setFileName(url.getFile());
			shipmentResponse = upsHTTPClient.createShipmentRequest(shipmentRequest);
			String errorMsg = "Address Validation Error on ShipFrom address";
			assertEquals(errorMsg, shipmentResponse.getErrorDescription());
			System.out.println("Validation failed for ORDERED_BY address");
		} 
		catch(Exception e)
		{
			assertEquals("Failure", "Failure");
			e.printStackTrace();
			System.out.println("Exception occured"+e.getMessage());
		}
		
	}
	
	
	/**
	 * ORDER BY Address Validation Success for 2ndDayAir
	 * @param shippingInfoDO
	 */
	@Test
	public void testCreateShipmentRequest_Address_Validate_Success_2ndDayAir()
	{
		logger.info("Testing testCreateShipmentRequest_Address_Validate_Success_2ndDayAir...");
		try 
		{

			filePath = "./test/java/com/cts/ptms/carrier/ups/resources/InputData_Address_Validation_Success_2DayAir.xml";
			//shipmentRequest.setFileName("./test/java/com/cts/ptms/carrier/ups/resources/InputData_Address_Validation_Success_2DayAir.xml");
			shipmentRequest.setCarrier("UPS"); 
			URL url = getClass().getClassLoader().getResource(filePath);
			shipmentRequest.setFileName(url.getFile());
			shipmentResponse = null;
			shipmentResponse = upsHTTPClient.createShipmentRequest(shipmentRequest);
			
			assertFalse(shipmentResponse == null);
			assertFalse(shipmentResponse.getShipmentDocuments().size() == 0);
			ShipmentDocument shipmentDoc = shipmentResponse.getShipmentDocuments().get(0);
			assertFalse(shipmentDoc.getName() != "SHIPPINGLABEL");
			assertFalse(null == shipmentDoc.getContent());
			byte[] decoded = Base64.getDecoder().decode(shipmentDoc.getContent());
			
			saveBase64DataToLocalFile(decoded, shipmentResponse.getTrackingNumber()+"_AddVal_2ndDayAir", false);
			System.out.println("Saved successfully on "+getClass().getClassLoader().getResource("").getPath());
			assertEquals("Success", shipmentResponse.getStatus());
		} 
		catch(Exception e)
		{
			assertEquals("Failure", "Failure");
			System.out.println("Exception occured at "+e.getMessage());
		}
		
	}
	/**
	 * 
	 * @param shippingInfoDO
	 */
	@Test
	public void testCreateShipmentRequest_Address_Validate_Failure_2ndDayAir()
	{
		logger.info("Testing testCreateShipmentRequest_Address_Validate_Failure_2ndDayAir...");
		try 
		{
			filePath = "./test/java/com/cts/ptms/carrier/ups/resources/InputData_Address_Validation_Failure_2DayAir.xml";
			//shipmentRequest.setFileName("./test/java/com/cts/ptms/carrier/ups/resources/InputData_Address_Validation_Failure_2DayAir.xml");
			shipmentRequest.setCarrier("UPS"); 
			URL url = getClass().getClassLoader().getResource(filePath);
			shipmentRequest.setFileName(url.getFile());
			shipmentResponse = upsHTTPClient.createShipmentRequest(shipmentRequest);
			assertEquals("Address Validation Error on ShipFrom address", shipmentResponse.getErrorDescription());
			System.out.println("Validation failed on ORDERED_BY Address");
		} 
		catch(Exception e)
		{
			assertEquals("Failure", "Failure");
			e.printStackTrace();
			System.out.println("Exception occured"+e.getMessage());
		}
		
	}
	
	/**
	 * Check for additional documents if shipment is international shipment
	 */
	@Test
	public void testCreateShipmentRequest_Check_For_InvoiceDocuments()
	{
		logger.info("Testing CreateShipmentRequest for Invoice Documents...");
		try 
		{
			filePath = "./test/java/com/cts/ptms/carrier/ups/resources/InputData_Check_For_Invoice_Success.xml";
			shipmentRequest.setCarrier("UPS"); 
			URL url = getClass().getClassLoader().getResource(filePath);
			shipmentRequest.setFileName(url.getFile());
			shipmentResponse = upsHTTPClient.createShipmentRequest(shipmentRequest);
			
			assertFalse(shipmentResponse == null);
			assertFalse(shipmentResponse.getShipmentDocuments().size() == 0);
			
			
			ShipmentDocument shipmentDoc = shipmentResponse.getShipmentDocuments().get(1);
			assertFalse(shipmentDoc.getName() != "INVOICE");
			
			assertFalse(null == shipmentDoc.getContent());
			byte[] decoded = Base64.getDecoder().decode(shipmentDoc.getContent());
			
			saveBase64DataToLocalFile(decoded, shipmentResponse.getTrackingNumber()+"_Invoice", true);
			//System.out.println("Saved successfully on "+getClass().getClassLoader().getResource("").getPath());
			assertEquals("Success", shipmentResponse.getStatus());
			
		} 
		catch(Exception e)
		{
			assertEquals("Failure", "Failure");
			e.printStackTrace();
			System.out.println("Exception occured"+e.getMessage());
		}
		
	}
	
	/**
	 * Check for additional documents if shipment is international shipment
	 */
	@Test
	public void testCreateShipmentRequest_Check_For_InvoiceDocuments_Failure()
	{
		logger.info("Testing CreateShipmentRequest for Invoice Documents failure case...");
		try 
		{
			filePath = "./test/java/com/cts/ptms/carrier/ups/resources/InputData_Check_For_Invoice_Failure.xml";
			shipmentRequest.setCarrier("UPS"); 
			URL url = getClass().getClassLoader().getResource(filePath);
			shipmentRequest.setFileName(url.getFile());
			shipmentResponse = upsHTTPClient.createShipmentRequest(shipmentRequest);
			
			assertFalse(shipmentResponse == null);
			assertEquals(0, shipmentResponse.getShipmentDocuments().size());
			System.out.println("Invoice documents can not be created");
			
		} 
		catch(Exception e)
		{
			assertEquals("Failure", "Failure");
			e.printStackTrace();
			System.out.println("Exception occured"+e.getMessage());
		}
		
	}
	
	
	//Utility Class
	
	/**
	 *  Utility for saving the image file into a folder.
	 * @throws IOException 
	 */
	private void saveBase64DataToLocalFile(byte[] decoded, String trackingNumber, boolean isDocument ) throws IOException 
	{
		Document document = new Document();
		PdfWriter writer;
		try {
			String filename = properties.getProperty("SAVE_FILE_PATH") +"/"+ trackingNumber+ShippingConstants.PDF_fILE;
			String path  = null;
			OutputStream out1 = null;
			if(!isDocument) {
				 path = properties.getProperty("SAVE_FILE_PATH") +"/"+ trackingNumber+ShippingConstants.PNG_FILE;
				
				filename = filename.replace(ShippingConstants.FILE_PATH, "");
				
				//If directory is not found , create a directory.
				File pngDir = new File(properties.getProperty("SAVE_FILE_PATH"));
				if(!pngDir.exists()) {
					pngDir.mkdir();
				} 
				try {
					out1 = new BufferedOutputStream(new FileOutputStream(path));
					out1.write(decoded);
				} finally {
					if (out1 != null) {
						out1.close();
					}
				}
			
				File pdfFile = new File(filename);
				writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
				document.open();
				Image barcode = Image.getInstance(decoded);
				barcode.setBorder(50);
				barcode.scalePercent(40, 60);
				document.add(barcode);
	
				document.close();
				writer.close();
			} else {
				try {
					out1 = new BufferedOutputStream(new FileOutputStream(filename));
					out1.write(decoded);
				} finally {
					if (out1 != null) {
						out1.close();
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
