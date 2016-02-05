/**
 * 
 */
package main.java.com.cts.ptms.core;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


public class BaseRestService {

	public BaseRestService() {
		super();
	}

		public Response buildResponse(Object entity, String contentType) {
			ResponseBuilder responseBuilder = Response.ok(entity, contentType);
			responseBuilder = responseBuilder.header("Content-Type", contentType);
			return responseBuilder.build();
	}

	public Response buildResponse(Object entity, String contentType,
			String headerKey, String headerValue) {
		ResponseBuilder responseBuilder = Response.ok(entity, contentType);
		responseBuilder.header(headerKey, headerValue);
		return responseBuilder.build();
	}
}
