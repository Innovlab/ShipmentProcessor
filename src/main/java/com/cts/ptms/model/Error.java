package main.java.com.cts.ptms.model;

public class Error {
	private String ErrorSeverity;
	private String ErrorCode;
	private String ErrorDescription;
	
	public String getErrorSeverity() {
		return ErrorSeverity;
	}
	public void setErrorSeverity(String errorSeverity) {
		ErrorSeverity = errorSeverity;
	}
	public String getErrorCode() {
		return ErrorCode;
	}
	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}
	public String getErrorDescription() {
		return ErrorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		ErrorDescription = errorDescription;
	}
}
