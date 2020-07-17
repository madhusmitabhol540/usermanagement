package com.honeywell.usermanagement.payload.response;

public class MessageResponse {
	private boolean responseCode;
	private String message;
	private Object responseObject;

	public MessageResponse(boolean _responseCode, String _message, Object _object) {
		this.responseCode = _responseCode;
		this.message = _message;
		this.responseObject = _object;
	}
	public Object getResponseObject() {
		return responseObject;
	}
	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}
	public boolean getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(boolean responseCode) {
		this.responseCode = responseCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
