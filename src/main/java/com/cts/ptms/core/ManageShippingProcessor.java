package main.java.com.cts.ptms.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import main.java.com.cts.ptms.model.ShipmentRequest;
import main.java.com.cts.ptms.model.ShipmentResponse;
import main.java.com.cts.ptms.utils.constants.ShippingConstants;


@Path("/ManageShipping")
public class ManageShippingProcessor {

	private Properties properties = new Properties();

	@POST
	@Path( "/importXMLFile" )
	@Produces( MediaType.APPLICATION_JSON )
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public ShipmentResponse importXMLFile(@Context HttpServletRequest request) throws Exception {
		boolean genLbl = false;
		String carrierName="";
		loadProperties();
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload uploadHandler = new ServletFileUpload(
				fileItemFactory);
		fileItemFactory
		.setSizeThreshold(1024*1024);
		List items = uploadHandler.parseRequest(request);
		Iterator itr = items.iterator();
		FileItem data;
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			if (item.getFieldName().equalsIgnoreCase("FILEUPLOAD")) {
				String ipData = item.getString();
				File file = new File(ShippingConstants.INPUTFILE);	
				if(file.exists()){
					file.delete();
				}
				FileUtils.writeStringToFile(file, ipData);
			}
			if(item.getFieldName().equalsIgnoreCase("CARRIER")){
				carrierName = item.getString();
			}
			if(item.getFieldName().equalsIgnoreCase("GENLBL")){
				if(item.getString().equalsIgnoreCase("true")){
					genLbl = true;
				}
			}
		}
		ShipmentServiceImpl impl = new ShipmentServiceImpl();
		ShipmentRequest shipmentRequest = new ShipmentRequest();
		shipmentRequest.setFileName(ShippingConstants.INPUTFILE);
		shipmentRequest.setCarrier(carrierName);
		shipmentRequest.setGenLabel(genLbl);
		return impl.createShipment(shipmentRequest);
	}
	
	private void loadProperties(){
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(ShippingConstants.buildPropertiesPath);
			properties.load(inputStream);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
