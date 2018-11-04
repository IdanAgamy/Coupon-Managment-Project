package com.idan.coupons.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.idan.coupons.enums.InputErrorType;


@XmlRootElement
public class ApplicationError {

	private Integer errorCode;
	private String errorType;
	private String errorMessage;
	private List<InputErrorType> inputErrorTypes;

	public ApplicationError() {
		super();
	}

	public ApplicationError(Integer errorCode, String errorType, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorType = errorType;
		this.errorMessage = errorMessage;
	}

	public ApplicationError(Integer errorCode, String errorMessage, List<InputErrorType> inputErrorTypes) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.inputErrorTypes = inputErrorTypes;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public List<InputErrorType> getInputErrorTypes() {
		return inputErrorTypes;
	}

	public void setInputErrorTypes(List<InputErrorType> inputErrorTypes) {
		this.inputErrorTypes = inputErrorTypes;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}



	@Override
	public String toString() {
		return "ApplicationError [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", inputErrorTypes="
				+ inputErrorTypes + "]";
	}



}
