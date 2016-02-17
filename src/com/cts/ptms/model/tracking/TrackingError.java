/**
 * 
 */
package com.cts.ptms.model.tracking;

/**
 * @author 417765
 *
 */
public class TrackingError {

	private String errorSeverity;
	private String errorCode;
	private String errorDescription;
	
	/**
	 * 
	 */
	public TrackingError() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the errorSeverity
	 */
	public String getErrorSeverity() {
		return errorSeverity;
	}

	/**
	 * @param errorSeverity the errorSeverity to set
	 */
	public void setErrorSeverity(String errorSeverity) {
		this.errorSeverity = errorSeverity;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * @param errorDescription the errorDescription to set
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TrackingError [errorSeverity=" + errorSeverity + ", errorCode=" + errorCode + ", errorDescription="
				+ errorDescription + "]";
	}
	
	
	
}
