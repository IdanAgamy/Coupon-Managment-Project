package com.idan.coupons.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.idan.coupons.beans.ApplicationError;
import com.idan.coupons.enums.ErrorType;

@Provider
public class ExceptionHandler implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable exception) {
		ApplicationError error;
		if (exception instanceof ApplicationException) {
			ApplicationException  applicationException = (ApplicationException) exception;
			error = new ApplicationError(applicationException.getType().getNumber(),applicationException.getType().name(), exception.getMessage());
			if (applicationException.getType() == ErrorType.INVALID_PARAMETER) {
				error.setInputErrorTypes(applicationException.getTypes()); 
			}
			// In case of SYSTEM ERROR we will log the error
			if (applicationException.getType() == ErrorType.SYSTEM_ERROR) {
				//TODO implement logger.
			}
			return Response.status(error.getErrorCode()).entity(error).build();
		}
		// In case of unexpected error we will log the error
		exception.printStackTrace();
		//TODO implement logger.
		error = new ApplicationError(601, "General error",exception.getMessage());
		return Response.status(error.getErrorCode()).entity(error).build();
	}

}
