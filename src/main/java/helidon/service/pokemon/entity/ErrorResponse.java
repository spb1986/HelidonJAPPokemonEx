package helidon.service.pokemon.entity;

import java.io.Serializable;

/**
 * Entity implementation class for Entity: ErrorResponse
 *
 */
public class ErrorResponse implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private int errorCode = 404;
	private String errorMessage = "Not Found";

	public ErrorResponse(int errorCode, String message) {
		this.setErrorCode(errorCode);
		this.setErrorMessage(message);
	}


	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public int getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}


   
}
