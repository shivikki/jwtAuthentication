package com.modal;

public class ResultResponse {
	private boolean validationFlag; //true if validation error
	private String validationStatus;
	private int responseCode;
	public boolean isValidationFlag() {
		return validationFlag;
	}
	public void setValidationFlag(boolean validationFlag) {
		this.validationFlag = validationFlag;
	}
	public String getValidationStatus() {
		return validationStatus;
	}
	public void setValidationStatus(String validationStatus) {
		this.validationStatus = validationStatus;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	ResultResponse(boolean validationFlag, String validationStatus, int responseCode) {
		super();
		this.validationFlag = validationFlag;
		this.validationStatus = validationStatus;
		this.responseCode = responseCode;
	}
	public ResultResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ResultResponse [validationFlag=" + validationFlag + ", validationStatus=" + validationStatus
				+ ", responseCode=" + responseCode + "]";
	}
	
	
	
	

}
